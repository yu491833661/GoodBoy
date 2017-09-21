package yxinfo.yjh.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dy on 2017/9/14.
 */
public abstract class AbstractInterceptor {

    private static final Logger log = LoggerFactory.getLogger( ApiInterceptor.class );

    /**
     * 获取访问者IP
     *
     * @param request
     * @return
     */
    protected static String getIp( HttpServletRequest request ) {
        try {
            String ip = request.getHeader( "X-Real-IP" );
            if ( !StringUtils.isEmpty( ip ) && !"unknown".equalsIgnoreCase( ip ) ) {
                return ip;
            }
            ip = request.getHeader( "X-Forwarded-For" );
            if ( !StringUtils.isEmpty( ip ) && !"unknown".equalsIgnoreCase( ip ) ) {
                int index = ip.indexOf( ',' );
                if ( index != -1 ) {
                    return ip.substring( 0, index );
                } else {
                    return ip;
                }
            } else {
                return request.getRemoteAddr();
            }
        } catch ( Exception e ) {
            log.error( "获取访问者IP异常, 由于{}", e.getMessage(), e );
            return null;
        }
    }

    /**
     * 跨域检测
     *
     * @param request
     * @param response
     * @return
     */
    protected boolean accessControlAllow( HttpServletRequest request, HttpServletResponse response ) {
        String origin = request.getHeader( "origin" );
        response.addHeader( "Access-Control-Allow-Origin", origin );
        response.addHeader( "Access-Control-Allow-Credentials", "true" );
        response.setHeader( "Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS" );
        response.setHeader( "Access-Control-Allow-Headers", "x-requested-with,content-type,code,token" );
        if ( request.getMethod().equals( "OPTIONS" ) ) {
            return false;
        }
        return true;
    }
}
