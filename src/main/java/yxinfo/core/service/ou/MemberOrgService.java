package yxinfo.core.service.ou;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import yxinfo.core.common.cache.CacheName;
import yxinfo.core.service.ou.dto.MemberOrgDTO;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface MemberOrgService {

    /**
     * 添加用户组织
     *
     * @param memberOrg
     * @return
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ORG + '#' + #memberOrg.memberId" )
    void addMemberOrg( MemberOrgDTO memberOrg );

    /**
     * 更新用户组织
     *
     * @param memberOrg
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ORG + '#' + #memberOrg.memberId" )
    void updateMemberOrg( MemberOrgDTO memberOrg );

    /**
     * 删除用户组织
     *
     * @param memberOrg
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ORG + '#' + #memberOrg.memberId" )
    void deleteMemberOrg( MemberOrgDTO memberOrg );

    /**
     * 获取用户全部组织
     *
     * @param memberId
     * @return
     */
    @Cacheable( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER_ORG + '#' + #memberId" )
    List<MemberOrgDTO> getMemberOrgId( Integer memberId );

    /**
     * 获取用户在某个终端下的组织
     *
     * @param memberId
     * @param terminal
     * @return
     */
    List<MemberOrgDTO> getMemberOrgId( Integer memberId, String terminal );

    /**
     * 获取用户与某个组织的关系
     *
     * @param memberId
     * @param orgId
     * @param terminal
     * @return
     */
    MemberOrgDTO getMemberByMemberIdAndOrgId( Integer memberId, Integer orgId, String terminal );
}
