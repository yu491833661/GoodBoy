package yxinfo.core;

import com.alibaba.fastjson.JSON;
import yxinfo.BaseHttpRequest;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.yjh.web.context.RequestHeader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dy on 2016/6/28.
 */
public class Test0501023 extends BaseHttpRequest {

    public static void main( String[] args ) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put( RequestHeader.CODE, RouteCode.Ou.LOGIN );
        headers.put( RequestHeader.TERMINAL, "T0001" );
        headers.put( RequestHeader.VERSION, "1.0.0" );

        MemberDTO lg = new MemberDTO();
        lg.setLoginName( "18767122538" );
        lg.setPwd( "123456" );

        System.out.println( JSON.toJSONString( lg ) );
        String res = sendPost( URL, headers, JSON.toJSONString( lg ));
        System.out.println( res );
    }
}
