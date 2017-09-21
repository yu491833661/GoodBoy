package yxinfo.core.service.ou;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import yxinfo.core.common.cache.CacheName;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.service.ou.dto.RoleDTO;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface RoleService {

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Route( code = RouteCode.Ou.ADD_ROLE )
    @Validator
    Integer addRole( @Valid( groups = Add.class ) RoleDTO role, RequestMsg req );

    /**
     * 根据id获取角色
     *
     * @param id
     * @return
     */
    @Cacheable( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ROLE + '#' + #id" )
    RoleDTO getRoleById( Integer id );

    /**
     * 根据id更新角色
     *
     * @param role
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ROLE + '#' + #role.id" )
    void updateRoleById( RoleDTO role );

    /**
     * 根据id删除角色
     *
     * @param id
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ROLE + '#' + #id" )
    void deleteRoleById( Integer id );

    /**
     * 根据终端号获取角色
     *
     * @param terminal {@link yxinfo.core.service.ou.context.Terminal}
     * @return
     */
    List<RoleDTO> getRoleByTerminal( String terminal );

    /**
     * 获取组织所有角色
     *
     * @param data
     * @param req
     * @return
     */
    @Route( code = RouteCode.Ou.GET_ORG_ROLE )
    List<RoleDTO> getRoles( RoleDTO data, RequestMsg req );

    /**
     * 根据组织id获取角色
     *
     * @param orgId
     * @param app      {@link yxinfo.core.service.ou.context.Application}
     * @param terminal {@link yxinfo.core.service.ou.context.Terminal}
     * @return
     */
    List<RoleDTO> getRoleByOrgId( Integer orgId, String app, String terminal );

    /**
     * 根据组织id获取角色id
     *
     * @param orgId
     * @param app      {@link yxinfo.core.service.ou.context.Application}
     * @param terminal {@link yxinfo.core.service.ou.context.Terminal}
     * @return
     */
    List<Integer> getRoleIdByOrgId( Integer orgId, String app, String terminal );
}
