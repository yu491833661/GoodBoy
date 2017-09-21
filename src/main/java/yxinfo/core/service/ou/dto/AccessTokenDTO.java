package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

import java.util.Date;

/**
 * Created by dy on 2017/6/28.
 */
public class AccessTokenDTO extends BaseDTO {

    private static final long serialVersionUID = -9138707598931377532L;

    // 访问令牌
    private String accessTok;

    // 终端编号（terminal）
    private String deviceType;

    // 创建时间
    private Date createAt;

    // 用户id
    private Integer memberId;

    // 有效期
    private Integer expireIn;

    public String getAccessTok() {
        return accessTok;
    }

    public void setAccessTok( String accessTok ) {
        this.accessTok = accessTok;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType( String deviceType ) {
        this.deviceType = deviceType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt( Date createAt ) {
        this.createAt = createAt;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId( Integer memberId ) {
        this.memberId = memberId;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn( Integer expireIn ) {
        this.expireIn = expireIn;
    }
}
