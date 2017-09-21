package yxinfo.yjh.dao.model;

import java.util.Date;

public class ArticleCore {
    private Integer id;

    //标题
    private String title;

    //归属板块id，<FK>block_core.id
    private Integer blockId;

    //发布人id，<FK>sys_member.id
    private Integer memberId;

    //发布人姓名
    private String memberName;

    //作者姓名
    private String authorName;

    //创建时间
    private Date createAt;

    //上一次更新时间
    private Date updateAt;

    //点击量
    private Integer clickNum;

    //类型（1文章，2提问）
    private Short ftype;

    //状态（0草稿，1未通过，2审核中，3已发表，4已删除）
    private Short fstatus;

    //回答数
    private Short answerNum;

    //是否置顶（1置顶，0不置顶）
    private Boolean isTop;

    //是否推荐展示在首页（1展示，0不展示）
    private Boolean isFront;

    //回答摘要
    private String answerSummary;

    //审核记录id，<FK>approve_core.id
    private Integer approveId;

    //排序
    private Integer sort;

    //新闻动态封面资源
    private String imgSrc;

    //是否启用 1启用 0停用
    private Boolean isEnable;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.id
     *
     * @return the value of article_core.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.id
     *
     * @param id the value for article_core.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.title
     *
     * @return the value of article_core.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.title
     *
     * @param title the value for article_core.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.block_id
     *
     * @return the value of article_core.block_id
     *
     * @mbg.generated
     */
    public Integer getBlockId() {
        return blockId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.block_id
     *
     * @param blockId the value for article_core.block_id
     *
     * @mbg.generated
     */
    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.member_id
     *
     * @return the value of article_core.member_id
     *
     * @mbg.generated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.member_id
     *
     * @param memberId the value for article_core.member_id
     *
     * @mbg.generated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.member_name
     *
     * @return the value of article_core.member_name
     *
     * @mbg.generated
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.member_name
     *
     * @param memberName the value for article_core.member_name
     *
     * @mbg.generated
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.author_name
     *
     * @return the value of article_core.author_name
     *
     * @mbg.generated
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.author_name
     *
     * @param authorName the value for article_core.author_name
     *
     * @mbg.generated
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.create_at
     *
     * @return the value of article_core.create_at
     *
     * @mbg.generated
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.create_at
     *
     * @param createAt the value for article_core.create_at
     *
     * @mbg.generated
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.update_at
     *
     * @return the value of article_core.update_at
     *
     * @mbg.generated
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.update_at
     *
     * @param updateAt the value for article_core.update_at
     *
     * @mbg.generated
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.click_num
     *
     * @return the value of article_core.click_num
     *
     * @mbg.generated
     */
    public Integer getClickNum() {
        return clickNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.click_num
     *
     * @param clickNum the value for article_core.click_num
     *
     * @mbg.generated
     */
    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.ftype
     *
     * @return the value of article_core.ftype
     *
     * @mbg.generated
     */
    public Short getFtype() {
        return ftype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.ftype
     *
     * @param ftype the value for article_core.ftype
     *
     * @mbg.generated
     */
    public void setFtype(Short ftype) {
        this.ftype = ftype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.fstatus
     *
     * @return the value of article_core.fstatus
     *
     * @mbg.generated
     */
    public Short getFstatus() {
        return fstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.fstatus
     *
     * @param fstatus the value for article_core.fstatus
     *
     * @mbg.generated
     */
    public void setFstatus(Short fstatus) {
        this.fstatus = fstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.answer_num
     *
     * @return the value of article_core.answer_num
     *
     * @mbg.generated
     */
    public Short getAnswerNum() {
        return answerNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.answer_num
     *
     * @param answerNum the value for article_core.answer_num
     *
     * @mbg.generated
     */
    public void setAnswerNum(Short answerNum) {
        this.answerNum = answerNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.is_top
     *
     * @return the value of article_core.is_top
     *
     * @mbg.generated
     */
    public Boolean getIsTop() {
        return isTop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.is_top
     *
     * @param isTop the value for article_core.is_top
     *
     * @mbg.generated
     */
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.is_front
     *
     * @return the value of article_core.is_front
     *
     * @mbg.generated
     */
    public Boolean getIsFront() {
        return isFront;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.is_front
     *
     * @param isFront the value for article_core.is_front
     *
     * @mbg.generated
     */
    public void setIsFront(Boolean isFront) {
        this.isFront = isFront;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.answer_summary
     *
     * @return the value of article_core.answer_summary
     *
     * @mbg.generated
     */
    public String getAnswerSummary() {
        return answerSummary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.answer_summary
     *
     * @param answerSummary the value for article_core.answer_summary
     *
     * @mbg.generated
     */
    public void setAnswerSummary(String answerSummary) {
        this.answerSummary = answerSummary == null ? null : answerSummary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.approve_id
     *
     * @return the value of article_core.approve_id
     *
     * @mbg.generated
     */
    public Integer getApproveId() {
        return approveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.approve_id
     *
     * @param approveId the value for article_core.approve_id
     *
     * @mbg.generated
     */
    public void setApproveId(Integer approveId) {
        this.approveId = approveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.sort
     *
     * @return the value of article_core.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.sort
     *
     * @param sort the value for article_core.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.img_src
     *
     * @return the value of article_core.img_src
     *
     * @mbg.generated
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.img_src
     *
     * @param imgSrc the value for article_core.img_src
     *
     * @mbg.generated
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc == null ? null : imgSrc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_core.is_enable
     *
     * @return the value of article_core.is_enable
     *
     * @mbg.generated
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_core.is_enable
     *
     * @param isEnable the value for article_core.is_enable
     *
     * @mbg.generated
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
}