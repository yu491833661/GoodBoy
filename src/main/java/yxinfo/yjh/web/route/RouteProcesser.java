package yxinfo.yjh.web.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.framework.spring.SpringContext;
import yxinfo.yjh.web.dto.RouteMsg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/6/23.
 */
public class RouteProcesser {

    /**
     * 业务处理
     *
     * @param route
     * @param requestMsg
     * @param reqData
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    public static Object process( RouteMsg route, RequestMsg requestMsg, String reqData ) throws NoSuchMethodException, IllegalAccessException {
        Object[] arguments = null;
        if ( route.getParameterTypes().length > 0 ) {
            List<Object> args = new ArrayList<Object>();
            for ( Class param : route.getParameterTypes() ) {
                if ( param.equals( RequestMsg.class ) ) {
                    args.add( requestMsg );
                } else if ( param.equals( String.class ) ) {
                    args.add( reqData );
                } else {
                    args.add( parseReqData( reqData, param, route.getDataType() ) );
                }
            }
            arguments = args.toArray();
        }
        // 调用业务方法
        return springServiceInvoke( route.gettClass(), route.getMethod(), route.getParameterTypes(), arguments,
                requestMsg.getVersion(), null, null );
    }

    /**
     * 解析请求数据
     *
     * @param reqData
     * @param tClass
     * @param dataType
     * @param <T>
     * @return
     */
    private static <T> T parseReqData( String reqData, Class<T> tClass, TypeReference dataType ) {
        if ( dataType != null ) {
            return (T) JSON.parseObject( reqData, dataType );
        } else {
            return JSON.parseObject( reqData, tClass );
        }
    }

    /**
     * 执行dubbo服务方法
     *
     * @param tClass
     * @param method
     * @param parameterTypes
     * @param arguments
     * @param version
     * @param group
     * @param timeout
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    public static Object springServiceInvoke( Class tClass, String method, Class[] parameterTypes, Object[] arguments,
                                              String version, String group, Integer timeout ) throws NoSuchMethodException, IllegalAccessException {
        try {
            Object tService = SpringContext.getBean( tClass );
            Method tMethod = tClass.getDeclaredMethod( method, parameterTypes );
            return tMethod.invoke( tService, arguments );
        } catch ( InvocationTargetException e ) {
            throw new RuntimeException( e.getCause() );
        }
    }
}
