package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.framework.spring.SpringContext;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.service.ou.AppTerminalService;
import yxinfo.core.service.ou.MemberRoleService;
import yxinfo.core.service.ou.RoleService;
import yxinfo.core.service.ou.dao.mapper.SysMemberOrgRoleMapper;
import yxinfo.core.service.ou.dao.mapper.SysRoleMapper;
import yxinfo.core.service.ou.dao.model.SysMemberOrgRole;
import yxinfo.core.service.ou.dao.model.SysMemberOrgRoleExample;
import yxinfo.core.service.ou.dto.RoleDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service
public class MemberRoleServiceImpl implements MemberRoleService {

    @Resource
    private RoleService roleService;
    @Resource
    private AppTerminalService appTerminalService;
    @Resource
    private SysMemberOrgRoleMapper memberOrgRoleMapper;
    @Resource
    private SysRoleMapper roleMapper;

    public List<Integer> getMemberRoleId( Integer memberId, Integer orgId, String app ) {
        List<Integer> memberRoleIdAll = SpringContext.getBean( MemberRoleService.class ).getMemberRoleId( memberId, orgId );
        if ( CollectionUtils.isEmpty( memberRoleIdAll ) ) {
            return null;
        }
        if ( StringUtils.isEmpty( app ) ) {
            return memberRoleIdAll;
        }
        List<Integer> memberRoleIds = new ArrayList<Integer>();
        for ( Integer roleId : memberRoleIdAll ) {
            RoleDTO role = roleService.getRoleById( roleId );
            if ( role != null && app.equals( role.getApp() ) ) {
                memberRoleIds.add( roleId );
            }
        }
        return memberRoleIds;
    }

    public List<Integer> getRoleMemberId( Integer roleId, Integer orgId ) {
        SysMemberOrgRoleExample example = new SysMemberOrgRoleExample();
        SysMemberOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo( roleId );
        if ( orgId != null ) {
            criteria.andRoleIdEqualTo( roleId );
        }
        List<SysMemberOrgRole> memberOrgRoles = memberOrgRoleMapper.selectByExample( example );
        if ( !CollectionUtils.isEmpty( memberOrgRoles ) ) {
            return new ListTransformer<SysMemberOrgRole, Integer>().listTransform( memberOrgRoles, new Transformer<SysMemberOrgRole, Integer>() {
                public Integer transform( SysMemberOrgRole sysMemberOrgRole ) {
                    return sysMemberOrgRole.getMemberId();
                }
            } );
        }
        return null;
    }

    public List<Integer> getMemberRoleId( Integer memberId, Integer orgId ) {
        SysMemberOrgRoleExample example = new SysMemberOrgRoleExample();
        SysMemberOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( memberId );
        criteria.andOrgIdEqualTo( orgId );
        List<SysMemberOrgRole> memberOrgRoles = memberOrgRoleMapper.selectByExample( example );
        if ( !CollectionUtils.isEmpty( memberOrgRoles ) ) {
            List<Integer> roleIdIn = new ListTransformer<SysMemberOrgRole, Integer>().listTransform( memberOrgRoles, new Transformer<SysMemberOrgRole, Integer>() {
                public Integer transform( SysMemberOrgRole sysMemberOrgRole ) {
                    return sysMemberOrgRole.getRoleId();
                }
            } );
            return roleIdIn;
        }
        return null;
    }

    public List<Integer> getMemberRoleIdByTerminal( Integer memberId, Integer orgId, String terminal ) {
        if ( !StringUtils.isEmpty( terminal ) ) {
            List<String> apps = appTerminalService.getAppByTerminal( terminal );
            if ( CollectionUtils.isEmpty( apps ) ) {
                return null;
            }
            Set<Integer> roleIds = new HashSet<Integer>();
            for ( String app : apps ) {
                List<Integer> rIds = SpringContext.getBean( MemberRoleService.class ).getMemberRoleId( memberId, orgId, app );
                if ( !CollectionUtils.isEmpty( rIds ) ) {
                    roleIds.addAll( rIds );
                }
            }
            List<Integer> ret = new ArrayList<Integer>();
            if ( !CollectionUtils.isEmpty( roleIds ) ) {
                ret.addAll( roleIds );
            }
            return ret;
        } else {
            return SpringContext.getBean( MemberRoleService.class ).getMemberRoleId( memberId, orgId, null );
        }
    }

    public void deleteMemberRoles( Integer memberId, Integer orgId, String app ) {
        if ( memberId == null || orgId == null ) {
            return;
        }
        SysMemberOrgRoleExample example = new SysMemberOrgRoleExample();
        SysMemberOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( memberId );
        criteria.andOrgIdEqualTo( orgId );
        // 过滤不是此应用下的角色
        if ( !StringUtils.isEmpty( app ) ) {
            List<Integer> roleIdIn = roleService.getRoleIdByOrgId( orgId, app, null );
            criteria.andRoleIdIn( roleIdIn );
        }
        memberOrgRoleMapper.deleteByExample( example );
    }

    @Transactional
    public void addMemberRoles( List<Integer> roleIds, Integer memberId, Integer orgId ) {
        if ( CollectionUtils.isEmpty( roleIds ) || memberId == null || orgId == null ) {
            return;
        }
        for ( Integer roleId : roleIds ) {
            SysMemberOrgRole add = new SysMemberOrgRole();
            add.setRoleId( roleId );
            add.setMemberId( memberId );
            add.setOrgId( orgId );
            memberOrgRoleMapper.insertSelective( add );
        }
    }

    public void deleteMemberRoleByRoleId( Integer roleId, Integer memberId, Integer orgId ) {
        if ( memberId == null || orgId == null ) {
            return;
        }
        SysMemberOrgRoleExample example = new SysMemberOrgRoleExample();
        SysMemberOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo( memberId );
        criteria.andRoleIdEqualTo( roleId );
        memberOrgRoleMapper.deleteByExample( example );
    }
}
