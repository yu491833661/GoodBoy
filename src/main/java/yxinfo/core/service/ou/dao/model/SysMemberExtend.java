package yxinfo.core.service.ou.dao.model;

/**
 * Created by dy on 2017/7/6.
 */
public class SysMemberExtend extends SysMember {

    // 工号
    private String fcode;

    // 职位
    private String position;

    // 认证状态（1未认证，2已认证）
    private Short authStatus;

    // 座机
    private String tel;

    public String getFcode() {
        return fcode;
    }

    public void setFcode( String fcode ) {
        this.fcode = fcode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition( String position ) {
        this.position = position;
    }

    public Short getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus( Short authStatus ) {
        this.authStatus = authStatus;
    }

    public String getTel() {
        return tel;
    }

    public void setTel( String tel ) {
        this.tel = tel;
    }
}
