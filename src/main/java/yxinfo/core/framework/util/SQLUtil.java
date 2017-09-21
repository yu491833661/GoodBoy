package yxinfo.core.framework.util;

/**
 * Sql语句工具
 * Created by hanley on 2016/6/23.
 */
public class SQLUtil {

    public static String toLink( String arg ) {
        return new StringBuilder( "%" ).append( arg ).append( "%" ).toString();
    }
}
