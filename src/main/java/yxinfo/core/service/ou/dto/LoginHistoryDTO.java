package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

import java.util.Date;

public class LoginHistoryDTO extends BaseDTO {

    private static final long serialVersionUID = 1254933269410177987L;

    private Integer id;

    // 用户id
    private Integer memberId;

    // 终端编号
    private String termCode;

    // ip地址
    private String ip;

    // 创建时间
    private Date createAt;

    // 国家
    private String country;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String district;

    // 网络
    private String isp;

    // 最常登录地区
    private String mostLgRegion;

    // 时间范围左值
    private Date createAtFrom;

    // 时间范围右值
    private Date createAtTo;

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

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode( String termCode ) {
        this.termCode = termCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp( String ip ) {
        this.ip = ip;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt( Date createAt ) {
        this.createAt = createAt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince( String province ) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict( String district ) {
        this.district = district;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp( String isp ) {
        this.isp = isp;
    }

    public String getMostLgRegion() {
        return mostLgRegion;
    }

    public void setMostLgRegion( String mostLgRegion ) {
        this.mostLgRegion = mostLgRegion;
    }

    public Date getCreateAtFrom() {
        return createAtFrom;
    }

    public void setCreateAtFrom( Date createAtFrom ) {
        this.createAtFrom = createAtFrom;
    }

    public Date getCreateAtTo() {
        return createAtTo;
    }

    public void setCreateAtTo( Date createAtTo ) {
        this.createAtTo = createAtTo;
    }
}