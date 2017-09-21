package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

public class MemberInputDTO extends BaseDTO {

    private static final long serialVersionUID = 5884255952693382279L;

    private Integer id;

    // 登录名
    private String loginName;

    // 人员编号
    private String fcode;

    // 真实姓名
    private String realName;

    // 组织id
    private Integer orgId;

    // 录入用户组
    private String groups;

    // 录入角色
    private String roles;

    // 姓名拼音
    private String pyinName;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName( String loginName ) {
        this.loginName = loginName;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode( String fcode ) {
        this.fcode = fcode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName( String realName ) {
        this.realName = realName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId( Integer orgId ) {
        this.orgId = orgId;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups( String groups ) {
        this.groups = groups;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles( String roles ) {
        this.roles = roles;
    }

    public String getPyinName() {
        return pyinName;
    }

    public void setPyinName( String pyinName ) {
        this.pyinName = pyinName;
    }
}