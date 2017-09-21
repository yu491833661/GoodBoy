package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.error.ErrorCode;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.framework.spring.SpringContext;
import yxinfo.core.framework.util.PyinUtil;
import yxinfo.core.framework.util.RandomUtil;
import yxinfo.core.framework.util.SQLUtil;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.framework.validation.ObjectValidator;
import yxinfo.core.service.ou.*;
import yxinfo.core.service.ou.context.*;
import yxinfo.core.service.ou.dao.mapper.SysMemberMapper;
import yxinfo.core.service.ou.dao.mapper.SysMemberMapperExtend;
import yxinfo.core.service.ou.dao.mapper.SysMemberOrgMapper;
import yxinfo.core.service.ou.dao.model.*;
import yxinfo.core.service.ou.dto.*;
import yxinfo.core.service.ou.validation.Auth;
import yxinfo.core.service.ou.validation.BlockMaster;
import yxinfo.core.service.ou.validation.Personal;
import yxinfo.core.service.ou.validation.SchoolAdmin;
import yxinfo.core.service.sms.VcodeService;
import yxinfo.core.service.sms.context.VcodeUseAt;
import yxinfo.core.service.sms.dto.EvtVcodeDTO;
import yxinfo.yjh.service.block.BlockService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private VcodeService vcodeService;
    @Resource
    private MemberRoleService memberRoleService;
    @Resource
    private RoleService roleService;
    @Resource
    private MemberOrgService memberOrgService;
    @Resource
    private OrgService orgService;
    @Resource
    private RoleManageMenuService roleManageMenuService;
    @Resource
    private ManageMenuService manageMenuService;
    @Resource
    private AppTerminalService appTerminalService;
    @Resource
    private TicketService ticketService;
    @Resource
    private SysMemberOrgMapper memberOrgMapper;
    @Resource
    private SysMemberMapper memberMapper;
    @Resource
    private SysMemberMapperExtend memberMapperExtend;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private BlockService blockService;
    @Resource
    private MemberMajorService memberMajorService;

    @Validator
    @Transactional
    public Integer addMember( @Valid( groups = Add.class ) MemberDTO member ) {

        SysMember add = null;
        if ( isMemberExist( member.getLoginName(), null ) ) {
            // 用户存在
            throw new DctException( ErrorCode.Ou.USER_EXIST );
        }

        add = member.toModel( SysMember.class );
        // 由用户自己设置密码
        add.setPwd( null );
        add.setPwdErrAt( new Date() );
        // 拼音名称
        if ( !StringUtils.isEmpty( add.getRealName() ) ) {
            add.setPyinName( PyinUtil.getPiny( add.getRealName() ) );
        }
        memberMapper.insertSelective( add );

        // 验证角色类型
        if ( Roles.SCHOOL == member.getRoleId() ) {
            ObjectValidator.validate( new Class[]{ SchoolAdmin.class }, member );
            // 添加学校管理员
            for ( Integer schoolId : member.getSchoolIds() ) {
                enterpriseService.addEnterpriseManager( add.getId(), schoolId );
            }
        } else if ( Roles.BLOCK_MASTER == member.getRoleId() ) {
            ObjectValidator.validate( new Class[]{ BlockMaster.class }, member );
            // 添加版主
            for ( Integer blockId : member.getBlockIds() ) {
                blockService.addBlockManager( add.getId(), blockId );
            }
        }

        // 用户与组织
        MemberOrgDTO memberOrg = new MemberOrgDTO();
        memberOrg.setAuthStatus( MemberAuthStatus.WAIT_ACCOMPLISH );
        memberOrg.setFcode( member.getFcode() );
        memberOrg.setMemberId( add.getId() );
        memberOrg.setOrgId( OuConstants.CORE_ORGID );
        memberOrg.setPosition( member.getPosition() );
        memberOrg.setTel( member.getTel() );
        memberOrgService.addMemberOrg( memberOrg );

        // 用户与角色
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add( Roles.MEMBER );
        roleIds.add( member.getRoleId() );
        memberRoleService.addMemberRoles( roleIds, add.getId(), OuConstants.CORE_ORGID );

        return add.getId();
    }

    @Validator
    @Transactional
    public Integer updateMember( @Valid( groups = Update.class ) MemberDTO member ) {

        if ( isMemberExist( member.getLoginName(), member.getId() ) ) {
            throw new DctException( ErrorCode.Ou.USER_EXIST );
        }

        SysMember update = member.toModel( SysMember.class );
        // 由用户自己设置密码
        update.setPwd( null );
        // 拼音名称
        if ( !StringUtils.isEmpty( update.getRealName() ) ) {
            update.setPyinName( PyinUtil.getPiny( update.getRealName() ) );
        }
        memberMapper.updateByPrimaryKeySelective( update );

        // 已认证的用户才能编辑
        MemberOrgDTO mo = memberOrgService.getMemberByMemberIdAndOrgId( update.getId(), OuConstants.CORE_ORGID, null );
        if ( mo == null && !mo.getAuthStatus().equals( MemberAuthStatus.HAS_AUTH ) ) {
            throw new DctException( ErrorCode.Ou.CAN_NOT_UPDATE_BY_NOT_ACCOMPLISH );
        }

        // 用户与组织
        if ( member.getFcode() != null || member.getPosition() != null || member.getTel() != null ) {
            MemberOrgDTO memberOrg = new MemberOrgDTO();
            memberOrg.setMemberId( member.getId() );
            memberOrg.setOrgId( OuConstants.CORE_ORGID );
            memberOrg.setAuthStatus( MemberAuthStatus.HAS_AUTH );
            memberOrg.setFcode( member.getFcode() );
            memberOrg.setPosition( member.getPosition() );
            memberOrg.setTel( member.getTel() );
            memberOrgService.updateMemberOrg( memberOrg );
        }

        // 用户与角色
        memberRoleService.deleteMemberRoles( update.getId(), OuConstants.CORE_ORGID, null );
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add( Roles.MEMBER );
        roleIds.add( member.getRoleId() );
        memberRoleService.addMemberRoles( roleIds, update.getId(), OuConstants.CORE_ORGID );

        // 验证角色类型
        if ( Roles.SCHOOL == member.getRoleId() ) {
            ObjectValidator.validate( new Class[]{ SchoolAdmin.class }, member );
            // 更新学校管理员
            enterpriseService.deleteEnterpriseManager( update.getId() );
            for ( Integer schoolId : member.getSchoolIds() ) {
                enterpriseService.addEnterpriseManager( update.getId(), schoolId );
            }
        } else if ( Roles.BLOCK_MASTER == member.getRoleId() ) {
            ObjectValidator.validate( new Class[]{ BlockMaster.class }, member );
            // 更新版主
            blockService.deleteBlockManager( update.getId() );
            for ( Integer blockId : member.getBlockIds() ) {
                blockService.addBlockManager( update.getId(), blockId );
            }
        }

        // 验证账户类型
        if ( MemberType.PERSONAL == member.getFtype() ) {
            ObjectValidator.validate( new Class[]{ Personal.class }, member );
            // 更新专长
            memberMajorService.deleteMemberMajor( update.getId() );
            for ( String major : member.getMajors() ) {
                memberMajorService.addMemberMajor( update.getId(), major );
            }
        }

        return update.getId();
    }

    @Transactional
    public void deleteMember( List<Integer> memberIds, RequestMsg req ) {
        deleteMember( memberIds, req.getOrgId() );
    }

    @Transactional
    public void deleteMember( List<Integer> memberIds, Integer orgId ) {
        for ( Integer memberId : memberIds ) {
            MemberOrgDTO memberOrg = new MemberOrgDTO();
            memberOrg.setMemberId( memberId );
            memberOrg.setOrgId( orgId );
            memberOrgService.deleteMemberOrg( memberOrg );
            memberRoleService.deleteMemberRoles( memberId, orgId, null );
        }
    }

    public boolean isMemberExist( String loginName, Integer memberIdNotEqual ) {
        SysMemberExample memberExample = new SysMemberExample();
        SysMemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andLoginNameEqualTo( loginName );
        if ( memberIdNotEqual != null ) {
            criteria.andIdNotEqualTo( memberIdNotEqual );
        }
        long userCount = memberMapper.countByExample( memberExample );
        if ( userCount > 0 ) {
            return true;
        }
        return false;
    }

    public MemberDTO getMemberById( Integer id ) {
        MemberDTO member = new MemberDTO().toDTO( memberMapper.selectByPrimaryKey( id ) );
        if ( member == null ) {
            return null;
        }
        member.setPwd( null );
        // 获取人员工号
        MemberOrgDTO memberOrg = memberOrgService.getMemberByMemberIdAndOrgId( id, OuConstants.CORE_ORGID, null );
        member.setAuthStatus( memberOrg.getAuthStatus() );
        member.setFcode( memberOrg.getFcode() );
        member.setPosition( memberOrg.getPosition() );
        member.setTel( memberOrg.getTel() );
        member.setPwd( null );
        return member;
    }

    public MemberDTO getMemberWithRoleById( Integer id, String terminal ) {
        MemberDTO member = SpringContext.getBean( MemberService.class ).getMemberById( id );
        if ( member != null ) {
            List<RoleDTO> roles = getMemberRoles( id, OuConstants.CORE_ORGID, terminal );
            List<RoleDTO> retRoles = new ArrayList<>();
            List<Integer> roleIds = new ArrayList<>();
            // 过滤普通会员
            if ( !CollectionUtils.isEmpty( roles ) ) {
                for ( RoleDTO role : roles ) {
                    if ( Roles.MEMBER == role.getId() ) {
                        continue;
                    }
                    retRoles.add( role );
                    roleIds.add( role.getId() );
                }
            }
            member.setRoles( retRoles );
            member.setRoleIds( roleIds );
        }
        return member;
    }

    public List<RoleDTO> getMemberRoles( RequestMsg req ) {
        return getMemberRoles( req.getMemberId(), req.getOrgId(), req.getTerminal() );
    }

    public List<RoleDTO> getMemberRoles( Integer memberId, Integer orgId, String terminal ) {
        List<Integer> memberOrgRoles = memberRoleService.getMemberRoleIdByTerminal( memberId, orgId, terminal );
        return new ListTransformer<Integer, RoleDTO>().listTransform( memberOrgRoles, new Transformer<Integer, RoleDTO>() {
            public RoleDTO transform( Integer roleId ) {
                return roleService.getRoleById( roleId );
            }
        } );
    }

    public List<OrgDTO> getMemberOrgs( RequestMsg req ) {
        return getMemberOrgs( req.getMemberId(), req.getTerminal() );
    }

    private List<OrgDTO> getMemberOrgs( Integer memberId, String terminal ) {
        List<MemberOrgDTO> memberOrgs = memberOrgService.getMemberOrgId( memberId, terminal );
        return new ListTransformer<MemberOrgDTO, OrgDTO>().listTransform( memberOrgs, new Transformer<MemberOrgDTO, OrgDTO>() {
            public OrgDTO transform( MemberOrgDTO memberOrg ) {
                return orgService.getOrgById( memberOrg.getOrgId() );
            }
        } );
    }

    public MemberDTO getMember( MemberDTO data ) {
        if ( data == null ) {
            return null;
        }
        SysMemberExample example = new SysMemberExample();
        SysMemberExample.Criteria criteria = example.createCriteria();

        SysMemberOrgExample exampleMo = buildCoreGetMemberCriteria( data, criteria );
        List<SysMemberExtend> list = chooseCondition( data, example, exampleMo );
        if ( !CollectionUtils.isEmpty( list ) ) {
            MemberDTO member = new MemberDTO().toDTO( list.get( 0 ) );
            member.setPwd( null );
            return member;
        }
        return null;
    }

    public List<MemberDTO> getMembers( MemberDTO data ) {
        if ( data == null ) {
            return null;
        }
        SysMemberExample example = new SysMemberExample();
        SysMemberExample.Criteria criteria = example.createCriteria();

        SysMemberOrgExample exampleMo = buildCoreGetMemberCriteria( data, criteria );
        List<SysMemberExtend> list = chooseCondition( data, example, exampleMo );

        return new ListTransformer<SysMemberExtend, MemberDTO>().listTransform( list, new Transformer<SysMemberExtend, MemberDTO>() {
            public MemberDTO transform( SysMemberExtend sysMemberExtend ) {
                MemberDTO ret = new MemberDTO().toDTO( sysMemberExtend );
                ret.setPwd( null );
                return ret;
            }
        } );
    }

    public int countMembers( MemberDTO data ) {
        if ( data == null ) {
            return 0;
        }
        SysMemberExample example = new SysMemberExample();
        SysMemberExample.Criteria criteria = example.createCriteria();
        SysMemberOrgExample exampleMo = buildCoreGetMemberCriteria( data, criteria );
        return chooseConditionCount( data, example, exampleMo );
    }

    public PageDTO<List<MemberDTO>> searchMember( PageDTO<MemberDTO> pageData, RequestMsg req ) {
        if ( pageData == null ) {
            return null;
        }
        Page page = pageData.toModel( Page.class );
        MemberDTO data = pageData.getData();
        SysMemberExample example = new SysMemberExample();
        example.setPage( page );
        PageDTO<List<MemberDTO>> retPage = new PageDTO<List<MemberDTO>>();
        List<MemberDTO> retList = searchMember( example, data, req.getTerminal() );
        retPage.toDTO( page );
        retPage.setData( retList );
        return retPage;
    }

    private List<MemberDTO> searchMember( SysMemberExample example, MemberDTO data, String terminal ) {
        SysMemberExample.Criteria criteria = example.createCriteria();
        SysMemberOrgExample exampleMo = buildCoreGetMemberCriteria( data, criteria );
        String schText = null;
        if ( !StringUtils.isEmpty( data.getSchText() ) ) {
            schText = SQLUtil.toLink( data.getSchText() );
        }
        List<SysMemberExtend> list = memberMapperExtend.searchMember( OuConstants.CORE_ORGID, data.getRoleId(), null, schText, example );
        return new ListTransformer<SysMemberExtend, MemberDTO>().listTransform( list, new Transformer<SysMemberExtend, MemberDTO>() {
            public MemberDTO transform( SysMemberExtend sysMemberExtend ) {
                MemberDTO ret = new MemberDTO().toDTO( sysMemberExtend );
                ret.setRoles( getRoles( ret.getId(), OuConstants.CORE_ORGID, terminal ) );
                ret.setPwd( null );
                return ret;
            }
        } );
    }

    private List<RoleDTO> getRoles( Integer memberId, Integer orgId, String terminal ) {
        List<RoleDTO> roles = SpringContext.getBean( MemberService.class ).getMemberRoles( memberId, orgId, terminal );
        if ( !CollectionUtils.isEmpty( roles ) ) {
            List<RoleDTO> ret = new ArrayList<RoleDTO>();
            for ( RoleDTO role : roles ) {
                ret.add( new RoleDTO().toDTO( role ) );
            }
            return ret;
        }
        return null;
    }

    private SysMemberOrgExample buildCoreGetMemberCriteria( MemberDTO data, SysMemberExample.Criteria criteria ) {
        if ( data.getId() != null ) {
            criteria.andIdEqualTo( data.getId() );
        }
        if ( !StringUtils.isEmpty( data.getLoginName() ) ) {
            criteria.andLoginNameEqualTo( data.getLoginName() );
        }
        if ( !StringUtils.isEmpty( data.getRealName() ) ) {
            criteria.andRealNameEqualTo( data.getRealName() );
        }
        if ( !StringUtils.isEmpty( data.getMobile() ) ) {
            criteria.andMobileEqualTo( data.getMobile() );
        }
        if ( data.getSex() != null ) {
            criteria.andSexEqualTo( data.getSex() );
        }

        SysMemberOrgExample exampleMo = null;
        if ( data.getPosition() != null || data.getFcode() != null || data.getAuthStatus() != null || data.getTel() != null ) {
            exampleMo = new SysMemberOrgExample();
            SysMemberOrgExample.Criteria criteriaMo = exampleMo.createCriteria();
            if ( data.getPosition() != null ) {
                criteriaMo.andPositionEqualTo( data.getPosition() );
            }
            if ( data.getFcode() != null ) {
                criteriaMo.andFcodeEqualTo( data.getFcode() );
            }
            if ( data.getAuthStatus() != null ) {
                criteriaMo.andAuthStatusEqualTo( data.getAuthStatus() );
            }
            if ( data.getTel() != null ) {
                criteriaMo.andTelEqualTo( data.getTel() );
            }
        }
        return exampleMo;
    }

    private List<SysMemberExtend> chooseCondition( MemberDTO data, SysMemberExample example, SysMemberOrgExample exampleMo ) {
        if ( data.getRoleId() != null ) {
            // 查角色
            return memberMapperExtend.selectByRole( OuConstants.CORE_ORGID, data.getRoleId(), exampleMo, example );
        } else {
            // 查组织
            return memberMapperExtend.selectByOrg( OuConstants.CORE_ORGID, exampleMo, example );
        }
    }

    private int chooseConditionCount( MemberDTO data, SysMemberExample example, SysMemberOrgExample exampleMo ) {
        if ( data.getRoleId() != null ) {
            // 查角色
            return memberMapperExtend.countByRole( OuConstants.CORE_ORGID, data.getRoleId(), exampleMo, example );
        } else {
            // 查组织
            return memberMapperExtend.countByOrg( OuConstants.CORE_ORGID, exampleMo, example );
        }
    }

    /**
     * 混淆密码
     *
     * @param pwd
     * @return
     */
    public String confusePwd( String pwd ) {
        if ( StringUtils.isEmpty( pwd ) ) {
            return null;
        }
        return DigestUtils.md5DigestAsHex( ( pwd + PwdConfuse.PWD_CONFUSE ).getBytes() );
    }

    public Integer updatePassword( MemberDTO member ) {
        if ( member == null || StringUtils.isEmpty( member.getLoginName() ) || StringUtils.isEmpty( member.getVcode() ) ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }

        if ( !StringUtils.isEmpty( member.getTicket() ) ) {
            // 是否有注册票据
            if ( !ticketService.useTicket( member.getTicket(), TicketUserAt.UPDATE_PASSWORD, member.getLoginName() ) ) {
                throw new DctException( ErrorCode.Ou.REGISTER_MUST_CK_MOBILE );
            }
        } else {
            // 验证短信验证码
            EvtVcodeDTO evtVcode = new EvtVcodeDTO();
            evtVcode.setMobile( member.getLoginName() );
            evtVcode.setUseAt( VcodeUseAt.UPDATE_PASSWORD );
            evtVcode.setFcode( member.getVcode() );
            vcodeService.checkVCode( evtVcode );
        }

        if ( StringUtils.isEmpty( member.getPwd() ) ) {
            throw new DctException( ErrorCodeCore.NOT_EMPTY, "pwd" );
        }

        SysMemberExample memberExample = new SysMemberExample();
        memberExample.createCriteria().andLoginNameEqualTo( member.getLoginName() );
        List<SysMember> members = memberMapper.selectByExample( memberExample );
        if ( CollectionUtils.isEmpty( members ) ) {
            throw new DctException( ErrorCode.Ou.USER_NOT_FOUND );
        }
        SysMember sysMember = members.get( 0 );

        SysMember update = new SysMember();
        update.setId( sysMember.getId() );
        update.setPwd( confusePwd( member.getPwd() ) );
        update.setPwdErrcount( 0 );
        memberMapper.updateByPrimaryKeySelective( update );
        return sysMember.getId();
    }

    public MemberDTO getMemberByLoginName( String loginName ) {
        return new MemberDTO().toDTO( getSysMemberByLoginName( loginName ) );
    }

    public void sendVcode4UpdatePwd( String mobile ) {
        SysMemberExample memberExample = new SysMemberExample();
        SysMemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andLoginNameEqualTo( mobile );
        criteria.andIsDelEqualTo( false );
        criteria.andPwdIsNotNull();
        if ( memberMapper.countByExample( memberExample ) == 0 ) {
            throw new DctException( ErrorCode.Ou.USER_NOT_FOUND );
        }
        // 发送验证码
        EvtVcodeDTO vcodeDTO = new EvtVcodeDTO();
        vcodeDTO.setMobile( mobile );
        vcodeDTO.setFcode( RandomUtil.generateString( 5, RandomUtil.NUMBER_CHAR ) );
        vcodeDTO.setUseAt( VcodeUseAt.UPDATE_PASSWORD );
        vcodeService.sendVCode( vcodeDTO );
    }

    @Validator
    public void auth( @Valid( groups = Auth.class ) MemberDTO member, RequestMsg req ) {
        if ( member == null ) {
            throw new DctException( ErrorCodeCore.DATA_NOT_NULL );
        }
        MemberDTO sysMember = getMemberById( req.getMemberId() );
        if ( sysMember == null ) {
            throw new DctException( ErrorCode.Ou.USER_NOT_FOUND );
        }
        if ( !StringUtils.isEmpty( sysMember.getFcode() ) ) {
            // *fcode不为空, 已录入用户数据
            if ( member.getFcode().equals( sysMember.getFcode() ) && member.getRealName().equals( sysMember.getRealName() ) ) {
                MemberOrgDTO memberOrg = new MemberOrgDTO();
                memberOrg.setOrgId( req.getOrgId() );
                memberOrg.setMemberId( req.getMemberId() );
                memberOrg.setAuthStatus( MemberAuthStatus.HAS_AUTH );
                memberOrgService.updateMemberOrg( memberOrg );
            } else {
                throw new DctException( ErrorCode.Ou.CAN_NOT_REGISTER );
            }
        }
    }

    private SysMember getSysMemberByLoginName( String loginName ) {
        SysMemberExample memberExample = new SysMemberExample();
        SysMemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andLoginNameEqualTo( loginName );
        List<SysMember> members = memberMapper.selectByExample( memberExample );
        if ( !CollectionUtils.isEmpty( members ) ) {
            return members.get( 0 );
        }
        return null;
    }
}
