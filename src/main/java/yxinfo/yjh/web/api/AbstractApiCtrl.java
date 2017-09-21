package yxinfo.yjh.web.api;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.yjh.web.dto.ResponseMsg;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

/**
 * Created by dy on 2017/9/15.
 */
public abstract class AbstractApiCtrl {

    private static final Logger log = LoggerFactory.getLogger( AbstractApiCtrl.class );
    private static final String sepr = "|";
    private static final String seprSplit = "\\|";
    private static final String errorCodeUndefined = "错误码未定义";
    private static final String errorMessage = "业务处理异常";
    @Resource
    private MessageSource messageSource;

    /**
     * 返回JSONException
     *
     * @param errMsg
     * @param request
     * @param response
     */
    protected void retJSONException( String errMsg, HttpServletRequest request, HttpServletResponse response ) {
        errMsg = errMsg.replaceAll( "<", "&lt;" );
        errMsg = errMsg.replaceAll( ">", "&gt;" );
        errMsg = "请求数据解析失败, " + errMsg;
        retJson( new ResponseMsg( ErrorCodeCore.REQUEST_DATA_ERROR, null, errMsg ), request, response );
    }

    /**
     * 返回DctException
     *
     * @param errCode
     * @param args
     * @param request
     * @param response
     */
    protected void retDctException( String errCode, Object[] args, HttpServletRequest request, HttpServletResponse response ) {
        retJson( getMessageI18n( errCode, args, request ), request, response );
    }

    /**
     * 返回Exception
     *
     * @param e
     * @param hit
     * @param request
     * @param response
     */
    protected void retException( Exception e, String hit, HttpServletRequest request, HttpServletResponse response ) {
        retJson( new ResponseMsg( "-3", null, errorMessage ), request, response );
    }

    /**
     * 返回成功响应信息
     *
     * @param data
     * @param request
     * @param response
     * @return
     */
    protected void retSuccess( Object data, HttpServletRequest request, HttpServletResponse response ) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setCode( ErrorCodeCore.SUCCESS );
        responseMsg.setData( data );
        retJson( responseMsg, request, response );
    }

    /**
     * 从国际化资源获取返回信息
     *
     * @param errorCode
     * @param args
     * @param request
     * @return
     */
    protected ResponseMsg getMessageI18n( String errorCode, Object[] args, HttpServletRequest request ) {
        return new ResponseMsg( errorCode, null, messageSource.getMessage( errorCode, args, errorCodeUndefined, getLocale( request ) ) );
    }

    /**
     * 获取语言
     *
     * @param request
     * @return
     */
    protected Locale getLocale( HttpServletRequest request ) {
        String lang = request.getParameter( "lang" );
        if ( "us".equals( lang ) ) {
            return Locale.US;
        }
        return Locale.CHINA;
    }

    /**
     * 返回Json数据
     *
     * @param responseMsg
     * @param request
     * @param response
     */
    protected void retJson( ResponseMsg responseMsg, HttpServletRequest request, HttpServletResponse response ) {
        String revcJson = JSON.toJSONString( responseMsg );
        String callback = request.getParameter( "callback" );
        if ( !StringUtils.isEmpty( callback ) ) {
            // jsonp支持
            StringBuilder callbackJson = new StringBuilder();
            callbackJson.append( callback );
            callbackJson.append( "(" );
            callbackJson.append( revcJson );
            callbackJson.append( ")" );
            revcJson = callbackJson.toString();
        }
        log.debug( "响应数据 <== {}", revcJson );

        response.setCharacterEncoding( "utf-8" );
        Writer out = null;
        try {
            out = response.getWriter();
            out.write( revcJson );
            out.flush();
        } catch ( IOException e ) {
            log.error( "响应异常: {}", e.getMessage(), e );
        } finally {
            if ( out != null ) {
                try {
                    out.close();
                } catch ( IOException e ) {
                }
            }
        }
    }
}
