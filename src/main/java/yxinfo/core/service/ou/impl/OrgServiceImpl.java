package yxinfo.core.service.ou.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.error.ErrorCode;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.framework.util.PyinUtil;
import yxinfo.core.framework.util.SQLUtil;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.service.ou.MemberRoleService;
import yxinfo.core.service.ou.OrgAppService;
import yxinfo.core.service.ou.OrgService;
import yxinfo.core.service.ou.dao.mapper.SysOrgMapper;
import yxinfo.core.service.ou.dao.mapper.SysRoleMapper;
import yxinfo.core.service.ou.dao.model.SysOrg;
import yxinfo.core.service.ou.dao.model.SysOrgExample;
import yxinfo.core.service.ou.dao.model.SysRole;
import yxinfo.core.service.ou.dto.OrgDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service()
public class OrgServiceImpl implements OrgService {

    private static final Logger log = LoggerFactory.getLogger( OrgServiceImpl.class );
    @Resource
    private OrgAppService orgAppService;
    @Resource
    private MemberRoleService memberRoleService;
    @Resource
    private SysOrgMapper orgMapper;
    @Resource
    private SysRoleMapper roleMapper;

    @Validator
    public Integer addOrg( @Valid( groups = Add.class ) OrgDTO org ) {
        if ( checkOrgExistByName( org.getFname() ) ) {
            throw new DctException( ErrorCode.Ou.ORG_EXIST );
        }
        SysOrg sysOrg = org.toModel( SysOrg.class );
        sysOrg.setPyinName( PyinUtil.getPiny( sysOrg.getFname() ) );
        orgMapper.insertSelective( sysOrg );
        return sysOrg.getId();
    }

    @Validator
    @Transactional
    public Integer addOrgAndOpenApp( @Valid( groups = Add.class ) OrgDTO org, List<String> apps, Integer adminRoleId,
                                     Integer adminMemberId ) {
        int orgId = addOrg( org );
        // 为组织开启应用
        if ( CollectionUtils.isEmpty( apps ) ) {
            for ( String app : apps ) {
                orgAppService.addOrgApp( orgId, app );
            }
        }
        // 设置超级管理员
        if ( adminRoleId != null && adminMemberId != null && adminMemberId > 0 ) {
            SysRole role = roleMapper.selectByPrimaryKey( adminRoleId );
            // *只允许添加公权限
            if ( role != null && role.getOrgId() == null ) {
                List<Integer> adminRoleIds = new ArrayList<Integer>();
                adminRoleIds.add( adminRoleId );
                memberRoleService.addMemberRoles( adminRoleIds, adminMemberId, orgId );
            }
        }
        return orgId;
    }

    public OrgDTO getOrgById( Integer orgId ) {
        SysOrg org = orgMapper.selectByPrimaryKey( orgId );
        return new OrgDTO().toDTO( org );
    }

    @Validator
    public void updateOrgById( @Valid( groups = Update.class ) OrgDTO org ) {
        if ( org == null ) {
            return;
        }
        if ( checkOtherOrgExistByName( org.getFname(), org.getId() ) ) {
            throw new DctException( ErrorCode.Ou.ORG_EXIST );
        }
        SysOrg sysOrg = org.toModel( SysOrg.class );
        if ( !StringUtils.isEmpty( sysOrg.getFname() ) ) {
            sysOrg.setPyinName( PyinUtil.getPiny( sysOrg.getFname() ) );
        }
        // *不更新组织余额
        sysOrg.setBal( null );
        orgMapper.updateByPrimaryKeySelective( sysOrg );
    }

    public PageDTO<List<OrgDTO>> getOrgPage( PageDTO<OrgDTO> schPage, RequestMsg req ) {
        Page page = schPage.toModel( Page.class );
        OrgDTO data = schPage.getData();
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();
        if ( data != null && !StringUtils.isEmpty( data.getFname() ) ) {
            criteria.andFnameLike( SQLUtil.toLink( data.getFname() ) );
        }
        example.setPage( page );
        List<SysOrg> orgs = orgMapper.selectByExample( example );

        PageDTO<List<OrgDTO>> retPage = new PageDTO<List<OrgDTO>>().toDTO( page );
        List<OrgDTO> retList = new ArrayList<OrgDTO>();
        if ( !CollectionUtils.isEmpty( orgs ) ) {
            retList = new ListTransformer<SysOrg, OrgDTO>().listTransform( orgs, new Transformer<SysOrg, OrgDTO>() {
                public OrgDTO transform( SysOrg org ) {
                    return new OrgDTO().toDTO( org );
                }
            } );
        }
        retPage.setData( retList );
        return retPage;
    }

    public boolean checkOrgExistByName( String orgName ) {
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();
        criteria.andFnameEqualTo( orgName );
        return orgMapper.countByExample( example ) > 0;
    }

    /**
     * 查询除orgId对应组织之外的组织是否重名
     *
     * @param orgName
     * @param orgId
     * @return
     */
    private boolean checkOtherOrgExistByName( String orgName, Integer orgId ) {
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria();
        criteria.andFnameEqualTo( orgName );
        criteria.andIdNotEqualTo( orgId );
        return orgMapper.countByExample( example ) > 0;
    }
}