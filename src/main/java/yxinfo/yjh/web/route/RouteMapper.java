package yxinfo.yjh.web.route;

import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import yxinfo.core.common.route.ClearAuth;
import yxinfo.core.common.route.Route;
import yxinfo.core.framework.util.ScanPackageUtil;
import yxinfo.yjh.web.dto.RouteMsg;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by dy on 2017/5/24.
 */
public class RouteMapper {

    public static final String DATA_TYPE_PREFIX = "DT";
    public static final String SCAN_PACKAGE = "yxinfo";
    private static final Logger log = LoggerFactory.getLogger( RouteMapper.class );
    private static RouteMapper instance = null;
    private Map<String, RouteMsg> route = new HashMap<String, RouteMsg>();

    private RouteMapper() {
        try {
            List<Class> classes = ScanPackageUtil.scanService( SCAN_PACKAGE );
            for ( Class clz : classes ) {
                Method[] methods = clz.getMethods();
                for ( Method method : methods ) {
                    Route annotation = method.getAnnotation( Route.class );
                    if ( annotation != null ) {
                        if ( StringUtils.isEmpty( annotation.code() ) ) {
                            continue;
                        }
                        TypeReference typeReference = null;
                        if ( !Class.class.equals( annotation.dataType() ) ) {
                            typeReference = getDataType( annotation.code(), annotation.dataType() );
                            if ( typeReference == null ) {
                                continue;
                            }
                        }
                        ClearAuth clearAuth = method.getAnnotation( ClearAuth.class );

                        List<Integer> allowRoles = null;
                        if ( annotation.allowRoles() != null && annotation.allowRoles().length > 0 ) {
                            allowRoles = new ArrayList<>();
                            for ( int roleId : annotation.allowRoles() ) {
                                allowRoles.add( roleId );
                            }
                        }

                        RouteMsg routeMsg = new RouteMsg( clz, method.getName(), method.getParameterTypes(),
                                typeReference, clearAuth != null, allowRoles );
                        log.info( "Register route, code: [{}], {}", annotation.code(), routeMsg );
                        route.put( annotation.code(), routeMsg );
                    }
                }
            }
        } catch ( Exception e ) {
            log.warn( "Initialization route mapper error, {}", e.getMessage(), e );
            throw new RuntimeException( e );
        }
    }

    public static RouteMapper getInstance() {
        if ( instance == null ) {
            synchronized ( RouteMapper.class ) {
                if ( instance == null ) {
                    instance = new RouteMapper();
                }
            }
        }
        return instance;
    }

    public void destroy() {
        if ( instance != null ) {
            synchronized ( RouteMapper.class ) {
                if ( instance != null ) {
                    instance = null;
                }
            }
        }
    }

    public RouteMsg getRouteMsg( String code ) {
        if ( route != null ) {
            return route.get( code );
        }
        return null;
    }

    private TypeReference getDataType( String code, Class dataType ) throws IllegalAccessException {
        try {
            Field field = dataType.getField( DATA_TYPE_PREFIX + code );
            return (TypeReference) field.get( dataType );
        } catch ( Exception e ) {
            log.info( "data type: [DT{}] not exist, can not route this service", code );
            return null;
        }
    }
}
