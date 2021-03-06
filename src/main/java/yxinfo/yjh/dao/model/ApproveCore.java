package yxinfo.yjh.dao.model;

import java.util.Date;

public class ApproveCore {
    private Integer id;

    //标题
    private String title;

    //提交审核人姓名
    private String memberName;

    //提交审核人id，<FK>sys_member.id
    private Integer memberId;

    //审核人id，<FK>sys_member.id
    private Integer approveMemberId;

    //实际审核人id
    private Integer realApproveMemberId;

    //创建时间
    private Date createAt;

    //审核时间
    private Date approveAt;

    //状态（1待审核，2审核通过，3审核不通过，4作废）
    private Short fstatus;

    //类型（1文章审核，2提问审核，3人员审核）
    private Short ftype;

    //摘要
    private String summary;

    //审核意见
    private String opinion;

    //关联记录id，根据ftype分别为文章id、提问id、人员id
    private Integer relId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.id
     *
     * @return the value of approve_core.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.id
     *
     * @param id the value for approve_core.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.title
     *
     * @return the value of approve_core.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.title
     *
     * @param title the value for approve_core.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.member_name
     *
     * @return the value of approve_core.member_name
     *
     * @mbg.generated
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.member_name
     *
     * @param memberName the value for approve_core.member_name
     *
     * @mbg.generated
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.member_id
     *
     * @return the value of approve_core.member_id
     *
     * @mbg.generated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.member_id
     *
     * @param memberId the value for approve_core.member_id
     *
     * @mbg.generated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.approve_member_id
     *
     * @return the value of approve_core.approve_member_id
     *
     * @mbg.generated
     */
    public Integer getApproveMemberId() {
        return approveMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.approve_member_id
     *
     * @param approveMemberId the value for approve_core.approve_member_id
     *
     * @mbg.generated
     */
    public void setApproveMemberId(Integer approveMemberId) {
        this.approveMemberId = approveMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.real_approve_member_id
     *
     * @return the value of approve_core.real_approve_member_id
     *
     * @mbg.generated
     */
    public Integer getRealApproveMemberId() {
        return realApproveMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.real_approve_member_id
     *
     * @param realApproveMemberId the value for approve_core.real_approve_member_id
     *
     * @mbg.generated
     */
    public void setRealApproveMemberId(Integer realApproveMemberId) {
        this.realApproveMemberId = realApproveMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.create_at
     *
     * @return the value of approve_core.create_at
     *
     * @mbg.generated
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.create_at
     *
     * @param createAt the value for approve_core.create_at
     *
     * @mbg.generated
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.approve_at
     *
     * @return the value of approve_core.approve_at
     *
     * @mbg.generated
     */
    public Date getApproveAt() {
        return approveAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.approve_at
     *
     * @param approveAt the value for approve_core.approve_at
     *
     * @mbg.generated
     */
    public void setApproveAt(Date approveAt) {
        this.approveAt = approveAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.fstatus
     *
     * @return the value of approve_core.fstatus
     *
     * @mbg.generated
     */
    public Short getFstatus() {
        return fstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.fstatus
     *
     * @param fstatus the value for approve_core.fstatus
     *
     * @mbg.generated
     */
    public void setFstatus(Short fstatus) {
        this.fstatus = fstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.ftype
     *
     * @return the value of approve_core.ftype
     *
     * @mbg.generated
     */
    public Short getFtype() {
        return ftype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.ftype
     *
     * @param ftype the value for approve_core.ftype
     *
     * @mbg.generated
     */
    public void setFtype(Short ftype) {
        this.ftype = ftype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.summary
     *
     * @return the value of approve_core.summary
     *
     * @mbg.generated
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.summary
     *
     * @param summary the value for approve_core.summary
     *
     * @mbg.generated
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.opinion
     *
     * @return the value of approve_core.opinion
     *
     * @mbg.generated
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.opinion
     *
     * @param opinion the value for approve_core.opinion
     *
     * @mbg.generated
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approve_core.rel_id
     *
     * @return the value of approve_core.rel_id
     *
     * @mbg.generated
     */
    public Integer getRelId() {
        return relId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approve_core.rel_id
     *
     * @param relId the value for approve_core.rel_id
     *
     * @mbg.generated
     */
    public void setRelId(Integer relId) {
        this.relId = relId;
    }
}