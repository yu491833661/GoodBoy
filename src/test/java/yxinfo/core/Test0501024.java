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
public class Test0501024 extends BaseHttpRequest {

    public static void main( String[] args ) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put( RequestHeader.CODE, RouteCode.Ou.REGISTER );
        headers.put( RequestHeader.VERSION, "1.0.0" );

        MemberDTO member = new MemberDTO();
        member.setLoginName( "18767122532" );
        member.setPwd( "123456" );
        member.setVcode( "11501" );

        System.out.println( JSON.toJSONString( member ) );
        String res = sendPost( URL, headers, JSON.toJSONString( member ) );
        System.out.println( res );
    }
}
