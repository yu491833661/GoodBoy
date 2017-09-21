package yxinfo.core.service.ou.dao.model;

import java.util.Date;

public class SysLoginHistory {
    private Integer id;

    //用户id，<FK>sys_member.id
    private Integer memberId;

    //终端编号，<FK>sys_terminal.term_code
    private String termCode;

    //ip地址
    private String ip;

    //创建时间
    private Date createAt;

    //国家
    private String country;

    //省
    private String province;

    //市
    private String city;

    //区
    private String district;

    //网络
    private String isp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.id
     *
     * @return the value of sys_login_history.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.id
     *
     * @param id the value for sys_login_history.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.member_id
     *
     * @return the value of sys_login_history.member_id
     *
     * @mbggenerated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.member_id
     *
     * @param memberId the value for sys_login_history.member_id
     *
     * @mbggenerated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.term_code
     *
     * @return the value of sys_login_history.term_code
     *
     * @mbggenerated
     */
    public String getTermCode() {
        return termCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.term_code
     *
     * @param termCode the value for sys_login_history.term_code
     *
     * @mbggenerated
     */
    public void setTermCode(String termCode) {
        this.termCode = termCode == null ? null : termCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.ip
     *
     * @return the value of sys_login_history.ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.ip
     *
     * @param ip the value for sys_login_history.ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.create_at
     *
     * @return the value of sys_login_history.create_at
     *
     * @mbggenerated
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.create_at
     *
     * @param createAt the value for sys_login_history.create_at
     *
     * @mbggenerated
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.country
     *
     * @return the value of sys_login_history.country
     *
     * @mbggenerated
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.country
     *
     * @param country the value for sys_login_history.country
     *
     * @mbggenerated
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.province
     *
     * @return the value of sys_login_history.province
     *
     * @mbggenerated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.province
     *
     * @param province the value for sys_login_history.province
     *
     * @mbggenerated
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.city
     *
     * @return the value of sys_login_history.city
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.city
     *
     * @param city the value for sys_login_history.city
     *
     * @mbggenerated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.district
     *
     * @return the value of sys_login_history.district
     *
     * @mbggenerated
     */
    public String getDistrict() {
        return district;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.district
     *
     * @param district the value for sys_login_history.district
     *
     * @mbggenerated
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_history.isp
     *
     * @return the value of sys_login_history.isp
     *
     * @mbggenerated
     */
    public String getIsp() {
        return isp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_history.isp
     *
     * @param isp the value for sys_login_history.isp
     *
     * @mbggenerated
     */
    public void setIsp(String isp) {
        this.isp = isp == null ? null : isp.trim();
    }
}