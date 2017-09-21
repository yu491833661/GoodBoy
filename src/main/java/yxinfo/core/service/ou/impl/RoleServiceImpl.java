package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.service.ou.AppTerminalService;
import yxinfo.core.service.ou.RoleService;
import yxinfo.core.service.ou.dao.mapper.SysRoleMapper;
import yxinfo.core.service.ou.dao.model.SysRole;
import yxinfo.core.service.ou.dao.model.SysRoleExample;
import yxinfo.core.service.ou.dto.RoleDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanley on 2016/6/24.
 */
@Component
@Service()
public class RoleServiceImpl implements RoleService {

    @Resource
    private AppTerminalService appTerminalService;
    @Resource
    private SysRoleMapper roleMapper;

    @Validator
    public Integer addRole( @Valid( groups = Add.class ) RoleDTO role, RequestMsg req ) {
        SysRole add = role.toModel( SysRole.class );
        add.setApp( role.getApp() );
        add.setOrgId( req.getOrgId() );
        roleMapper.insertSelective( add );
        return add.getId();
    }

    public RoleDTO getRoleById( Integer id ) {
        return new RoleDTO().toDTO( roleMapper.selectByPrimaryKey( id ) );
    }

    public void updateRoleById( RoleDTO role ) {
        SysRole update = role.toModel( SysRole.class );
        if ( update.getId() == null ) {
            return;
        }
        roleMapper.updateByPrimaryKeySelective( update );
    }

    public void deleteRoleById( Integer id ) {
        if ( id == null ) {
            return;
        }
        roleMapper.deleteByPrimaryKey( id );
    }

    public List<RoleDTO> getRoleByTerminal( String terminal ) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        List<String> apps = appTerminalService.getAppByTerminal( terminal );
        if ( !CollectionUtils.isEmpty( apps ) ) {
            criteria.andAppIn( apps );
        } else {
            return null;
        }
        List<SysRole> roles = roleMapper.selectByExample( example );
        return new ListTransformer<SysRole, RoleDTO>().listTransform( roles, new Transformer<SysRole, RoleDTO>() {
            public RoleDTO transform( SysRole sysRole ) {
                return new RoleDTO().toDTO( sysRole );
            }
        } );
    }

    public List<RoleDTO> getRoles( RoleDTO data, RequestMsg req ) {
        data.setIsDel( false );
        data.setOrgId( req.getOrgId() );
        List<SysRole> roleList = getRoleModels( data, data.getTerminal() );
        return new ListTransformer<SysRole, RoleDTO>().listTransform( roleList, new Transformer<SysRole, RoleDTO>() {
            public RoleDTO transform( SysRole sysRole ) {
                return new RoleDTO().toDTO( sysRole );
            }
        } );
    }

    public List<RoleDTO> getRoleByOrgId( Integer orgId, String app, String terminal ) {
        RoleDTO data = new RoleDTO();
        data.setOrgId( orgId );
        data.setApp( app );
        data.setIsDel( false );
        List<SysRole> roleList = getRoleModels( data, terminal );
        return new ListTransformer<SysRole, RoleDTO>().listTransform( roleList, new Transformer<SysRole, RoleDTO>() {
            public RoleDTO transform( SysRole sysRole ) {
                return new RoleDTO().toDTO( sysRole );
            }
        } );
    }

    public List<Integer> getRoleIdByOrgId( Integer orgId, String app, String terminal ) {
        RoleDTO data = new RoleDTO();
        data.setOrgId( orgId );
        data.setApp( app );
        data.setIsDel( false );
        List<SysRole> roleList = getRoleModels( data, terminal );
        return new ListTransformer<SysRole, Integer>().listTransform( roleList, new Transformer<SysRole, Integer>() {
            public Integer transform( SysRole sysRole ) {
                return sysRole.getId();
            }
        } );
    }

    private List<SysRole> getRoleModels( RoleDTO data, String terminal ) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();

        // 获取终端应用
        List<String> apps = null;
        if ( !StringUtils.isEmpty( terminal ) ) {
            apps = appTerminalService.getAppByTerminal( terminal );
        }

        // 查询组织角色
        criteria.andOrgIdEqualTo( data.getOrgId() );
        buildCoreGetRoleModelsCriteria( criteria, data, apps );

        // 查询公角色
        if ( !StringUtils.isEmpty( data.getApp() ) || !CollectionUtils.isEmpty( apps ) ) {
            SysRoleExample.Criteria or = example.or();
            or.andOrgIdIsNull();
            buildCoreGetRoleModelsCriteria( or, data, apps );
        }
        return roleMapper.selectByExample( example );
    }

    private void buildCoreGetRoleModelsCriteria( SysRoleExample.Criteria criteria, RoleDTO data, List<String> apps ) {
        if ( data.getIsDel() != null ) {
            criteria.andIsDelEqualTo( data.getIsDel() );
        }
        if ( !StringUtils.isEmpty( data.getApp() ) ) {
            criteria.andAppEqualTo( data.getApp() );
        }
        if ( !CollectionUtils.isEmpty( apps ) ) {
            criteria.andAppIn( apps );
        }
    }
}
