package yxinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by dy on 2016/6/28.
 */
public class BaseHttpRequest {

    public static final String URL = "http://localhost:8080/api/json";
    //public static final String URL = "http://192.168.0.203:8083/yxinfo-core/kernel";
    //public static final String URL = "https://quasi.yuanxininfo.com:8092/yxinfo-core/kernel";
    //public static final String URL = "https://api.yuanxininfo.com/yxinfo-core/kernel";

    public static String sendPost( String url, Map<String, String> headers, String data ) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        long time = System.currentTimeMillis();
        System.out.println( "==> " + url );
        try {
            java.net.URL realUrl = new URL( url );
            // 打开和URL之间的连接
            System.setProperty( "sun.net.http.allowRestrictedHeaders", "true" );
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty( "accept", "*/*" );
            conn.setRequestProperty( "connection", "Keep-Alive" );
            conn.setRequestProperty( "user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );
            conn.setRequestProperty( "X-Real-IP", "183.157.10.87" );
            for ( String key : headers.keySet() ) {
                System.out.println( "header [" + key + "]: [" + headers.get( key ) + "]" );
                conn.setRequestProperty( key, headers.get( key ) );
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput( true );
            conn.setDoInput( true );
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter( conn.getOutputStream() );
            // 发送请求参数
            out.print( data );
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader( conn.getInputStream() ) );
            String line;
            while ( ( line = in.readLine() ) != null ) {
                result += line;
            }
        } catch ( Exception e ) {
            System.out.println( "发送 POST 请求出现异常！" + e );
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if ( out != null ) {
                    out.close();
                }
                if ( in != null ) {
                    in.close();
                }
            } catch ( IOException ex ) {
                ex.printStackTrace();
            }
        }
        System.out.println( "耗时" + ( System.currentTimeMillis() - time ) + "ms" );
        return result;
    }
}
