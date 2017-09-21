package yxinfo.core.service.ou;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import yxinfo.core.common.cache.CacheName;
import yxinfo.core.common.dto.PageDTO;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.ClearAuth;
import yxinfo.core.common.route.DataType;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.core.service.ou.dto.OrgDTO;
import yxinfo.core.service.ou.dto.RoleDTO;
import yxinfo.core.service.ou.validation.Auth;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface MemberService {

    /**
     * 新增用户
     *
     * @param member
     * @param terminal
     * @return
     */
    @Validator
    Integer addMember( @Valid( groups = Add.class ) MemberDTO member );

    /**
     * 更新用户
     *
     * @param member
     * @param terminal
     * @return
     */
    @Validator
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER + '#' + #member.id" )
    Integer updateMember( @Valid( groups = Update.class ) MemberDTO member );

    /**
     * 删除用户
     *
     * @param memberIds
     * @param req
     * @return
     */
    @Route( code = RouteCode.Ou.DELETE_MEMBER )
    void deleteMember( List<Integer> memberIds, RequestMsg req );

    /**
     * 删除用户
     *
     * @param memberIds
     * @param orgId
     * @return
     */
    void deleteMember( List<Integer> memberIds, Integer orgId );

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @Cacheable( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).USER + '#' + #id" )
    MemberDTO getMemberById( Integer id );

    /**
     * 根据id获取用户以及用户的角色
     *
     * @param id
     * @param terminal
     * @return
     */
    MemberDTO getMemberWithRoleById( Integer id, String terminal );

    /**
     * 判断用户名是否存在
     *
     * @param loginName        用户名
     * @param memberIdNotEqual 传值时, 查询排除此id用户
     * @return
     */
    boolean isMemberExist( String loginName, Integer memberIdNotEqual );

    /**
     * 获取用户所有角色
     *
     * @param req
     * @return
     */
    @Route( code = RouteCode.Ou.GET_MEMBER_ROLE )
    List<RoleDTO> getMemberRoles( RequestMsg req );

    /**
     * 获取用户所有角色
     *
     * @param memberId
     * @param orgId
     * @param terminal
     * @return
     */
    List<RoleDTO> getMemberRoles( Integer memberId, Integer orgId, String terminal );

    /**
     * 获取用户所有组织
     *
     * @param req
     * @return
     */
    @Route( code = RouteCode.Ou.GET_MEMBER_ORG )
    List<OrgDTO> getMemberOrgs( RequestMsg req );

    /**
     * 查询用户
     *
     * @param data
     * @return
     */
    MemberDTO getMember( MemberDTO data );

    /**
     * 查询用户
     *
     * @param data
     * @return
     */
    List<MemberDTO> getMembers( MemberDTO data );

    /**
     * 查询用户数量
     *
     * @param data
     * @return
     */
    int countMembers( MemberDTO data );

    /**
     * 搜索用户
     *
     * @param pageData
     * @return
     */
    @Route( code = RouteCode.Ou.SEARCH_EMEBER, dataType = DataType.class )
    PageDTO<List<MemberDTO>> searchMember( PageDTO<MemberDTO> pageData, RequestMsg req );

    /**
     * 混淆密码
     *
     * @param pwd
     * @return
     */
    String confusePwd( String pwd );

    /**
     * 重置成员密码
     *
     * @param member
     * @return
     */
    Integer updatePassword( MemberDTO member );

    /**
     * 根据登录名获取用户
     *
     * @param loginName
     * @return
     */
    MemberDTO getMemberByLoginName( String loginName );

    /**
     * 重置密码时发送验证码
     *
     * @param mobile
     */
    @ClearAuth
    @Route( code = RouteCode.Ou.SEND_VCODE_4_UPDATE_PWD )
    void sendVcode4UpdatePwd( String mobile );


    @Validator
    void auth( @Valid( groups = Auth.class ) MemberDTO member, RequestMsg req );
}
