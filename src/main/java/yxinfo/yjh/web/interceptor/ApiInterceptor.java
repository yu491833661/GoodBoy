package yxinfo.yjh.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.yjh.web.context.RequestAttribute;
import yxinfo.yjh.web.context.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dy on 2016/7/1.
 */
public class ApiInterceptor extends AbstractInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger( ApiInterceptor.class );

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object o ) throws Exception {

        // 跨域检测
        if ( !accessControlAllow( request, response ) ) {
            return false;
        }

        // 获取请求信息
        RequestMsg requestMsg = getRequestMsg( request );
        if ( requestMsg == null ) {
            return false;
        }
        request.setAttribute( RequestAttribute.REQUEST_MSG, requestMsg );

        // 获取请求数据
        String reqData = getRequestData( request );
        request.setAttribute( RequestAttribute.REQ_DATA, reqData );

        return true;
    }

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView ) throws Exception {

    }

    @Override
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object o, Exception e ) throws Exception {

    }


    /**
     * 获取请求信息
     *
     * @param request
     * @return
     */
    private RequestMsg getRequestMsg( HttpServletRequest request ) {
        RequestMsg requestMsg = new RequestMsg();
        if ( request.getHeader( RequestHeader.CODE ) == null ) {
            return null;
        }
        requestMsg.setCode( request.getHeader( RequestHeader.CODE ) );
        requestMsg.setVersion( request.getHeader( RequestHeader.VERSION ) );
        requestMsg.setToken( request.getHeader( RequestHeader.TOKEN ) );
        requestMsg.setTerminal( request.getHeader( RequestHeader.TERMINAL ) );
        requestMsg.setOrgId( request.getHeader( RequestHeader.ORG_ID ) == null ?
                null : new Integer( request.getHeader( RequestHeader.ORG_ID ) ) );
        requestMsg.setIp( getIp( request ) );
        requestMsg.setHit( UUID.randomUUID().toString().replaceAll( "-", "" ) );
        return requestMsg;
    }

    /**
     * 获取请求数据
     *
     * @param request
     * @return
     */
    private String getRequestData( HttpServletRequest request ) {
        String reqData = null;
        if ( "GET".equals( request.getMethod() ) ) {
            reqData = request.getParameter( "data" );
            try {
                if ( reqData != null ) {
                    reqData = URLDecoder.decode( reqData, "utf-8" );
                }
            } catch ( Exception e ) {
                log.error( "获取请求数据异常, 由于{}", e.getMessage(), e );
            }
        } else {
            try {
                Map<String, String[]> parameterMap = request.getParameterMap();
                if ( parameterMap != null && parameterMap.size() > 0 ) {
                    reqData = (String) parameterMap.keySet().toArray()[0];
                }
                if ( reqData != null ) {
                    reqData = URLDecoder.decode( reqData, "utf-8" );
                }
            } catch ( Exception e ) {
                log.error( "获取请求数据异常, 由于{}", e.getMessage(), e );
            }
        }
        log.debug( "请求数据 ==> {}", reqData );
        return reqData;
    }
}
