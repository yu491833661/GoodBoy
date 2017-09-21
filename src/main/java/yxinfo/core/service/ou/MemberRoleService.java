package yxinfo.core.service.ou;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import yxinfo.core.common.cache.CacheName;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface MemberRoleService {

    /**
     * 获取用户某个应用下的角色id
     *
     * @param memberId
     * @param orgId
     * @param app
     * @return
     */
    List<Integer> getMemberRoleId( Integer memberId, Integer orgId, String app );

    /**
     * 获取授权了某角色的用户id
     *
     * @param roleId
     * @param orgId
     * @return
     */
    List<Integer> getRoleMemberId( Integer roleId, Integer orgId );

    /**
     * 获取用户角色id
     *
     * @param memberId
     * @param orgId
     * @return
     */
    @Cacheable( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ROLE + '#' + #memberId + '#'  + #orgId" )
    List<Integer> getMemberRoleId( Integer memberId, Integer orgId );

    /**
     * 根据终端编号获取用户角色id
     *
     * @param memberId
     * @param orgId
     * @param terminal
     * @return
     */
    List<Integer> getMemberRoleIdByTerminal( Integer memberId, Integer orgId, String terminal );

    /**
     * 删除用户角色
     *
     * @param memberId
     * @param orgId
     * @param app      传递则只删除某个应用下的角色
     * @return
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ROLE + '#' + #memberId + '#'  + #orgId" )
    void deleteMemberRoles( Integer memberId, Integer orgId, String app );

    /**
     * 添加用户角色
     *
     * @param roleIds
     * @param memberId
     * @param orgId
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ROLE + '#' + #memberId + '#'  + #orgId" )
    void addMemberRoles( List<Integer> roleIds, Integer memberId, Integer orgId );

    /**
     * 删除用户某个角色
     *
     * @param roleId
     * @param memberId
     * @param orgId
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ROLE + '#' + #memberId + '#'  + #orgId" )
    void deleteMemberRoleByRoleId( Integer roleId, Integer memberId, Integer orgId );
}
