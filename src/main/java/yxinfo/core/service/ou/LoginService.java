package yxinfo.core.service.ou;

import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.ClearAuth;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.common.validation.Valid;
import yxinfo.core.common.validation.Validator;
import yxinfo.core.service.ou.dto.LoginInfoDTO;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.core.service.ou.validation.Register;

/**
 * Created by dy on 2017/7/10.
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param data
     * @param req
     * @return
     */
    @ClearAuth
    @Route( code = RouteCode.Ou.LOGIN )
    MemberDTO login( MemberDTO data, RequestMsg req );

    /**
     * 注册
     *
     * @param member
     * @param terminal
     * @return
     */
    @ClearAuth
    @Validator
    @Route( code = RouteCode.Ou.REGISTER )
    MemberDTO register( @Valid( groups = Register.class ) MemberDTO member, RequestMsg req );

    /**
     * 免密登录
     * <p> 不验证密码直接登录 <p/>
     *
     * @param memberId
     * @param terminal
     * @param ip
     * @param loginInfo
     * @return
     */
    MemberDTO loginNoPwd( Integer memberId, String terminal, String ip, LoginInfoDTO loginInfo );

    /**
     * 注册时发送验证码
     *
     * @param mobile
     */
    @ClearAuth
    @Route( code = RouteCode.Ou.SEND_VCODE_4_REGISTER )
    void sendVcode4Register( String mobile );

    /**
     * 完善用户资料
     *
     * @param member
     * @param req
     */
    void accomplishMember( MemberDTO member, RequestMsg req );
}
