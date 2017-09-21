package yxinfo.yjh.web.dto;

import java.io.Serializable;

/**
 * Created by dy on 2016/11/17.
 */
public class ResponseMsg implements Serializable {

    private static final long serialVersionUID = 2662176669573634344L;

    // 返回码
    private String code;

    // 返回数据
    private Object data;

    // 异常信息
    private String msg;

    public ResponseMsg() {
    }

    public ResponseMsg( String code, Object data ) {
        this.code = code;
        this.data = data;
    }

    public ResponseMsg( String code, Object data, String msg ) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData( Object data ) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }
}
