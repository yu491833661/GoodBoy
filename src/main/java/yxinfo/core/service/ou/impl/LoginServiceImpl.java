package yxinfo.core.service.ou.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.error.ErrorCode;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.util.RandomUtil;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.service.ou.*;
import yxinfo.core.service.ou.context.MemberAuthStatus;
import yxinfo.core.service.ou.context.OuConstants;
import yxinfo.core.service.ou.context.Roles;
import yxinfo.core.service.ou.context.TicketUserAt;
import yxinfo.core.service.ou.dao.mapper.SysMemberMapper;
import yxinfo.core.service.ou.dao.model.SysMember;
import yxinfo.core.service.ou.dao.model.SysMemberExample;
import yxinfo.core.service.ou.dto.LoginInfoDTO;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.core.service.ou.dto.MemberOrgDTO;
import yxinfo.core.service.ou.dto.OrgDTO;
import yxinfo.core.service.ou.validation.Accomplish;
import yxinfo.core.service.ou.validation.Register;
import yxinfo.core.service.sms.VcodeService;
import yxinfo.core.service.sms.context.VcodeUseAt;
import yxinfo.core.service.sms.dto.EvtVcodeDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/7/10.
 */
@Component
@Service
public class LoginServiceImpl implements LoginService {

    private static Logger log = LoggerFactory.getLogger( LoginServiceImpl.class );
    @Resource
    private VcodeService vcodeService;
    @Resource
    private MemberService memberService;
    @Resource
    private MemberOrgService memberOrgService;
    @Resource
    private AccessTokenServiceImpl accessTokenService;
    @Resource
    private MemberRoleService memberRoleService;
    @Resource
    private LoginHistoryService loginHistoryService;
    @Resource
    private OrgService orgService;
    @Resource
    private LoginInfoService loginInfoService;
    @Resource
    private TicketService ticketService;
    @Resource
    private SysMemberMapper memberMapper;

    @Transactional
    public MemberDTO login( MemberDTO data, RequestMsg req ) {

        // 获取用户
        SysMemberExample example = new SysMemberExample();
        SysMemberExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo( data.getLoginName() );
        List<SysMember> sysMembers = memberMapper.selectByExample( example );
        if ( CollectionUtils.isEmpty( sysMembers ) ) {
            throw new DctException( ErrorCode.Ou.USER_NOT_FOUND );
        }
        SysMember member = sysMembers.get( 0 );

        // 登录
        // 错误10次, 禁止登录
        if ( member.getPwdErrcount() >= 10 ) {
            throw new DctException( ErrorCode.Ou.USER_PWD_ERROR_TOO_MUCH );
        }
        if ( !memberService.confusePwd( data.getPwd() ).equals( member.getPwd() ) ) {
            // 密码错误, 错误计数增加
            SysMember update = new SysMember();
            update.setId( member.getId() );
            update.setPwdErrcount( member.getPwdErrcount() == null ? 1 : member.getPwdErrcount() + 1 );
            memberMapper.updateByPrimaryKeySelective( update );
            throw new DctException( ErrorCode.Ou.PASSWORD_ERROR );
        }
        // 登录成功, 错误计数清空
        SysMember update = new SysMember();
        update.setId( member.getId() );
        update.setPwdErrcount( 0 );
        memberMapper.updateByPrimaryKeySelective( update );

        // 组装返回数据
        MemberDTO login = new MemberDTO().toDTO( member );
        // 屏蔽密码
        login.setPwd( null );
        // 获取用户组织id
        login.setOrgs( getOrgsOnLogin( login, req.getTerminal() ) );
        // 访问令牌
        login.setToken( accessTokenService.createAccessToken( req.getTerminal(), login.getId() ) );
        // 登录信息
        LoginInfoDTO loginInfo = data.getLoginInfo();
        if ( loginInfo != null ) {
            loginInfo.setMemberId( member.getId() );
            loginInfo.setTermCode( req.getTerminal() );
            loginInfoService.updateLoginInfo( loginInfo );
        }

        // 登录日志
        try {
            loginHistoryService.addLoginHistory( login.getId(), req.getTerminal(), req.getIp() );
        } catch ( Exception e ) {
            log.error( "保存登录日志异常, {}", e.getMessage(), e );
        }

        return login;
    }

    /**
     * 设置组织信息
     *
     * @param member
     * @param terminal
     */
    public List<OrgDTO> getOrgsOnLogin( MemberDTO member, String terminal ) {
        List<MemberOrgDTO> memberOrgs = memberOrgService.getMemberOrgId( member.getId(), terminal );
        if ( !CollectionUtils.isEmpty( memberOrgs ) ) {
            return new ListTransformer<MemberOrgDTO, OrgDTO>().listTransform( memberOrgs, new Transformer<MemberOrgDTO, OrgDTO>() {
                public OrgDTO transform( MemberOrgDTO memberOrg ) {
                    OrgDTO org = orgService.getOrgById( memberOrg.getOrgId() );
                    if ( org != null ) {
                        org.setMemberOrg( memberOrg );
                        org.setRoleIds( memberRoleService.getMemberRoleIdByTerminal( member.getId(), org.getId(), terminal ) );
                    }
                    return org;
                }
            } );
        }
        return null;
    }

    @Validator
    @Transactional
    public MemberDTO register( @Valid( groups = Register.class ) MemberDTO member, RequestMsg req ) {

        if ( member == null ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        if ( !StringUtils.isEmpty( member.getTicket() ) ) {
            // 是否有注册票据
            if ( !ticketService.useTicket( member.getTicket(), TicketUserAt.REGISTER, member.getLoginName() ) ) {
                throw new DctException( ErrorCode.Ou.REGISTER_MUST_CK_MOBILE );
            }
        } else {
            // 验证短信验证码
            EvtVcodeDTO evtVcode = new EvtVcodeDTO();
            evtVcode.setMobile( member.getLoginName() );
            evtVcode.setUseAt( VcodeUseAt.REGISTER );
            evtVcode.setFcode( member.getVcode() );
            vcodeService.checkVCode( evtVcode );
        }

        // 是否已经注册
        SysMemberExample memberExample = new SysMemberExample();
        SysMemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andLoginNameEqualTo( member.getLoginName() );
        long i = memberMapper.countByExample( memberExample );
        if ( i > 0 ) {
            throw new DctException( ErrorCode.Ou.USER_EXIST );
        }

        // 注册
        SysMember addMember = new SysMember();
        addMember.setLoginName( member.getLoginName() );
        addMember.setMobile( member.getLoginName() );
        addMember.setRealName( member.getRealName() );
        addMember.setPwd( memberService.confusePwd( member.getPwd() ) );
        addMember.setFtype( member.getFtype() );
        memberMapper.insertSelective( addMember );

        // 用户与组织
        MemberOrgDTO memberOrg = new MemberOrgDTO();
        memberOrg.setAuthStatus( MemberAuthStatus.WAIT_ACCOMPLISH );
        memberOrg.setFcode( member.getFcode() );
        memberOrg.setMemberId( addMember.getId() );
        memberOrg.setOrgId( OuConstants.CORE_ORGID );
        memberOrg.setPosition( member.getPosition() );
        memberOrg.setTel( member.getTel() );
        memberOrgService.addMemberOrg( memberOrg );

        // 用户与角色
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add( Roles.MEMBER );
        memberRoleService.addMemberRoles( roleIds, addMember.getId(), OuConstants.CORE_ORGID );

        // 登录
        return login( member, req );
    }

    public MemberDTO loginNoPwd( Integer memberId, String terminal, String ip, LoginInfoDTO loginInfo ) {

        if ( memberId == null ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }
        // 获取用户
        SysMember member = memberMapper.selectByPrimaryKey( memberId );
        MemberDTO ret = new MemberDTO().toDTO( member );
        // 屏蔽密码
        ret.setPwd( null );
        // 获取用户组织id
        List<OrgDTO> orgs = getOrgsOnLogin( ret, terminal );
        // 访问令牌
        ret.setToken( accessTokenService.createAccessToken( terminal, ret.getId() ) );
        // 登录信息
        if ( loginInfo != null ) {
            loginInfo.setMemberId( member.getId() );
            loginInfo.setTermCode( terminal );
            loginInfoService.updateLoginInfo( loginInfo );
        }

        // 登录日志
        try {
            loginHistoryService.addLoginHistory( ret.getId(), terminal, ip );
        } catch ( Exception e ) {
            log.error( "保存登录日志异常, {}", e.getMessage(), e );
        }

        return ret;
    }

    public void sendVcode4Register( String mobile ) {
        SysMemberExample example = new SysMemberExample();
        SysMemberExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo( mobile );
        criteria.andIsDelEqualTo( false );
        criteria.andPwdNotEqualTo( "" );
        if ( memberMapper.countByExample( example ) > 0 ) {
            throw new DctException( ErrorCode.Ou.USER_EXIST );
        }
        // 发送验证码
        EvtVcodeDTO vcodeDTO = new EvtVcodeDTO();
        vcodeDTO.setMobile( mobile );
        vcodeDTO.setFcode( RandomUtil.generateString( 5, RandomUtil.NUMBER_CHAR ) );
        vcodeDTO.setUseAt( VcodeUseAt.REGISTER );
        vcodeService.sendVCode( vcodeDTO );
    }

    @Validator
    public void accomplishMember( @Valid( groups = Accomplish.class ) MemberDTO member, RequestMsg req ) {








    }
}
