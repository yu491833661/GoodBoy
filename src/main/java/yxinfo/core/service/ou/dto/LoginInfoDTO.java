package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

import java.util.Date;

public class LoginInfoDTO extends BaseDTO {

    private static final long serialVersionUID = 6227301619643671915L;

    // 用户id
    private Integer memberId;

    // 终端编号
    private String termCode;

    // 设备品牌
    private String deviceBrand;

    // 设备型号
    private String deviceModel;

    // 设备唯一标识
    private String imei;

    // 系统版本号
    private String systemVer;

    // 是否采用默认App推送渠道
    private Boolean defAppPush;

    private Date createAt;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId( Integer memberId ) {
        this.memberId = memberId;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode( String termCode ) {
        this.termCode = termCode;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand( String deviceBrand ) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel( String deviceModel ) {
        this.deviceModel = deviceModel;
    }

    public String getImei() {
        return imei;
    }

    public void setImei( String imei ) {
        this.imei = imei;
    }

    public String getSystemVer() {
        return systemVer;
    }

    public void setSystemVer( String systemVer ) {
        this.systemVer = systemVer;
    }

    public Boolean getDefAppPush() {
        return defAppPush;
    }

    public void setDefAppPush( Boolean defAppPush ) {
        this.defAppPush = defAppPush;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt( Date createAt ) {
        this.createAt = createAt;
    }
}