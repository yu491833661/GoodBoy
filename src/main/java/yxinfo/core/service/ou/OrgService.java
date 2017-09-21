package yxinfo.core.service.ou;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import yxinfo.core.common.cache.CacheName;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.DataType;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.service.ou.dto.OrgDTO;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface OrgService {

    /**
     * 添加组织
     *
     * @param org
     * @return 组织id
     */
    @Validator
    Integer addOrg( @Valid( groups = Add.class ) OrgDTO org );

    /**
     * 添加组织并开通应用
     * <p> 添加组织并开通应用, 并设置超级管理员 <p/>
     *
     * @param org           组织信息
     * @param apps          组织开通的应用（非必传）
     * @param adminRoleId   超级管理员权限id, 必须为公权限（非必传）
     * @param adminMemberId 超级管理员用户id（非必传）
     * @return 组织id
     */
    @Validator
    Integer addOrgAndOpenApp( @Valid( groups = Add.class ) OrgDTO org, List<String> apps, Integer adminRoleId,
                              Integer adminMemberId );

    /**
     * 根据id获取组织
     *
     * @param orgId
     * @return
     */
    @Cacheable( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ORG + '#' + #orgId" )
    OrgDTO getOrgById( Integer orgId );

    /**
     * 根据id更新组织
     * <p> 不更新组织余额 <p/>
     *
     * @param orgId
     * @return
     */
    @Validator
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ORG + '#' + #org.id" )
    void updateOrgById( @Valid( groups = Update.class ) OrgDTO org );

    /**
     * 分页搜索所有组织
     *
     * @param schPage
     * @param req
     * @return
     */
    @Route( code = RouteCode.Ou.ORG_PAGE, dataType = DataType.class )
    PageDTO<List<OrgDTO>> getOrgPage( PageDTO<OrgDTO> schPage, RequestMsg req );

    /**
     * 查询组织是否存在
     *
     * @param orgName
     * @return
     */
    boolean checkOrgExistByName( String orgName );
}
