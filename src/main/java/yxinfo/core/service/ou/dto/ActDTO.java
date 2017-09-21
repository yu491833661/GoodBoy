package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

public class ActDTO extends BaseDTO {

    private static final long serialVersionUID = -1289413908848052747L;

    private Integer id;

    // 消息所属平台（dct实验无忧，shop电商，lis大仪）
    private String plat;

    // 请求码
    private String fcode;

    // 名称
    private String fname;

    // 备注
    private String remark;

    // 是否跳过鉴权（1跳过鉴权，0需要鉴权）
    private Boolean clearAuth;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat( String plat ) {
        this.plat = plat;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode( String fcode ) {
        this.fcode = fcode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname( String fname ) {
        this.fname = fname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark( String remark ) {
        this.remark = remark;
    }

    public Boolean getClearAuth() {
        return clearAuth;
    }

    public void setClearAuth( Boolean clearAuth ) {
        this.clearAuth = clearAuth;
    }
}