package yxinfo.yjh.service.article.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Delete;
import yxinfo.core.common.validation.Update;
import yxinfo.yjh.service.approve.dto.ApproveCoreDTO;
import yxinfo.yjh.service.article.validation.AddNews;
import yxinfo.yjh.service.article.validation.UpdateNews;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author jn
 * @date 2017/9/14 18:25
 **/
public class ArticleCoreDTO extends BaseDTO {

    @NotNull( groups = {Update.class, Delete.class, UpdateNews.class}, message = ErrorCodeCore.NOT_NULL )
    private Integer id;

    //标题
    @NotEmpty( groups = {Add.class, Update.class, AddNews.class, UpdateNews.class}, message = ErrorCodeCore.NOT_EMPTY )
    @Length( groups = {Add.class, Update.class, AddNews.class, UpdateNews.class}, max = 60, message = ErrorCodeCore.MAX )
    private String title;

    // 内容
    private String context;

    //归属板块id，<FK>block_core.id
    @NotNull( groups = {Add.class, Update.class}, message = ErrorCodeCore.NOT_NULL )
    private Integer blockId;

    //发布人id，<FK>sys_member.id
    private Integer memberId;

    //发布人姓名
    private String memberName;

    //作者姓名
    @Length( groups = {Add.class, Update.class, AddNews.class, UpdateNews.class}, max = 40, message = ErrorCodeCore.MAX )
    private String authorName;

    //创建时间
    private Date createAt;

    //上一次更新时间
    private Date updateAt;

    //点击量
    private Integer clickNum;

    //类型（1文章，2提问）
    @NotNull( groups = {Add.class}, message = ErrorCodeCore.NOT_NULL )
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
    @NotEmpty( groups = {AddNews.class, UpdateNews.class}, message = ErrorCodeCore.NOT_EMPTY )
    @Length( groups = {AddNews.class, UpdateNews.class}, max = 200, message = ErrorCodeCore.MAX )
    private String imgSrc;

    //是否启用 1启用 0停用
    private Boolean isEnable;

    // 附件列表
    private List<ArticleAttachDTO> attachList;

    // 审核信息
    private ApproveCoreDTO approveInfo;

    // 回答列表
    private List<ArticleAnswerDTO> answerList;

    public List<ArticleAnswerDTO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<ArticleAnswerDTO> answerList) {
        this.answerList = answerList;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public ApproveCoreDTO getApproveInfo() {
        return approveInfo;
    }

    public void setApproveInfo(ApproveCoreDTO approveInfo) {
        this.approveInfo = approveInfo;
    }

    public List<ArticleAttachDTO> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<ArticleAttachDTO> attachList) {
        this.attachList = attachList;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Short getFtype() {
        return ftype;
    }

    public void setFtype(Short ftype) {
        this.ftype = ftype;
    }

    public Short getFstatus() {
        return fstatus;
    }

    public void setFstatus(Short fstatus) {
        this.fstatus = fstatus;
    }

    public Short getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Short answerNum) {
        this.answerNum = answerNum;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getFront() {
        return isFront;
    }

    public void setFront(Boolean front) {
        isFront = front;
    }

    public String getAnswerSummary() {
        return answerSummary;
    }

    public void setAnswerSummary(String answerSummary) {
        this.answerSummary = answerSummary;
    }

    public Integer getApproveId() {
        return approveId;
    }

    public void setApproveId(Integer approveId) {
        this.approveId = approveId;
    }
}
