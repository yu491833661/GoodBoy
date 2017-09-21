package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

/**
 * Created by dy on 2017/8/15.
 */
public class MemberOrgDTO extends BaseDTO {

    private static final long serialVersionUID = -1522566095871325349L;

    private Integer id;

    // 成员id
    private Integer memberId;

    // 组织id
    private Integer orgId;

    // 人员编号
    private String fcode;

    // 职位
    private String position;

    // 认证状态（1未认证，2已认证）
    private Short authStatus;

    // 座机
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId( Integer memberId ) {
        this.memberId = memberId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId( Integer orgId ) {
        this.orgId = orgId;
    }

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
