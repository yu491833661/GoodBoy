package yxinfo.yjh.web.dto;

import com.alibaba.fastjson.TypeReference;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dy on 2017/6/23.
 */
public class RouteMsg {

    private Class tClass;

    private String method;

    private Class[] parameterTypes;

    private TypeReference dataType;

    private boolean clearAuth;

    private List<Integer> allowRoles;

    public RouteMsg( Class tClass, String method, Class[] parameterTypes, TypeReference dataType,
                     boolean clearAuth, List<Integer> allowRoles ) {
        this.tClass = tClass;
        this.method = method;
        this.parameterTypes = parameterTypes;
        this.dataType = dataType;
        this.clearAuth = clearAuth;
        this.allowRoles = allowRoles;
    }

    public Class gettClass() {
        return tClass;
    }

    public void settClass( Class tClass ) {
        this.tClass = tClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod( String method ) {
        this.method = method;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes( Class[] parameterTypes ) {
        this.parameterTypes = parameterTypes;
    }

    public TypeReference getDataType() {
        return dataType;
    }

    public void setDataType( TypeReference dataType ) {
        this.dataType = dataType;
    }

    public boolean isClearAuth() {
        return clearAuth;
    }

    public void setClearAuth( boolean clearAuth ) {
        this.clearAuth = clearAuth;
    }

    public List<Integer> getAllowRoles() {
        return allowRoles;
    }

    public void setAllowRoles( List<Integer> allowRoles ) {
        this.allowRoles = allowRoles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append( "class: [" ).append( tClass );
        sb.append( "], method: [" ).append( method );
        sb.append( "], parameterTypes: [" ).append( Arrays.toString( parameterTypes ) );
        sb.append( "], dataType: [" ).append( dataType );
        sb.append( "], clearAuth: [" ).append( clearAuth );
        sb.append( "], allowRoles: [" ).append( allowRoles );
        sb.append( "]" );
        return sb.toString();
    }
}
