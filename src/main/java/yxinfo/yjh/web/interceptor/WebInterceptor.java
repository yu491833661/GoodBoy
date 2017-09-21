package yxinfo.yjh.web.interceptor;

import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.AllowRoles;
import yxinfo.core.common.route.ClearAuth;
import yxinfo.core.framework.exception.DctException;
import yxinfo.core.service.ou.AccessTokenService;
import yxinfo.core.service.ou.MemberRoleService;
import yxinfo.core.service.ou.context.OuConstants;
import yxinfo.core.service.ou.dto.AccessTokenDTO;
import yxinfo.yjh.web.api.JsonApiCtrl;
import yxinfo.yjh.web.context.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by dy on 2016/7/1.
 */
public class WebInterceptor extends AbstractInterceptor implements HandlerInterceptor {

    @Resource
    private MemberRoleService memberRoleService;
    @Resource
    private AccessTokenService akService;

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object o ) throws Exception {

        // 跨域
        if ( !accessControlAllow( request, response ) ) {
            return false;
        }

        // 获取目标方法
        if ( o != null && o instanceof HandlerMethod ) {
            Method targetMethod = ( (HandlerMethod) o ).getMethod();
            if ( targetMethod != null ) {

                ClearAuth clearAuth = targetMethod.getAnnotation( ClearAuth.class );
                if ( clearAuth == null ) {

                    // 从session中获取用户信息
                    RequestMsg requestMsg = (RequestMsg) request.getSession().getAttribute( SessionAttribute.MEMBER_INFO );
                    if ( requestMsg == null ) {
                        response.sendRedirect( "login" );
                        return false;
                    }

                    // 检查访问令牌
                    AccessTokenDTO ak = null;
                    try {
                        ak = JsonApiCtrl.checkToken( akService, requestMsg.getToken(), response, request );
                    } catch ( DctException e ) {
                        response.sendRedirect( "login" );
                        return false;
                    }

                    // 鉴权
                    AllowRoles allowRoles = targetMethod.getAnnotation( AllowRoles.class );
                    if ( allowRoles != null && allowRoles.roles() != null && allowRoles.roles().length > 0 ) {
                        List<Integer> roleIds = memberRoleService.getMemberRoleId( ak.getMemberId(), OuConstants.CORE_ORGID );
                        if ( CollectionUtils.isEmpty( roleIds ) ) {
                            response.sendRedirect( "not_allow" );
                            return false;
                        }
                        boolean notAllow = true;
                        for ( Integer roleId : roleIds ) {
                            for ( int id : allowRoles.roles() ) {
                                if ( id == roleId ) {
                                    notAllow = false;
                                    break;
                                }
                            }
                        }
                        if ( notAllow ) {
                            response.sendRedirect( "not_allow" );
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView ) throws Exception {

    }

    @Override
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object o, Exception e ) throws Exception {

    }
}
