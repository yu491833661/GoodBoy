package yxinfo.yjh.service.approve.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.service.ou.dto.MemberDTO;
import yxinfo.yjh.service.approve.validation.AddApprove;
import yxinfo.yjh.service.approve.validation.Approve;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author jn
 * @date 2017/9/15 14:06
 **/
public class ApproveCoreDTO extends BaseDTO {

    private Integer id;

    //标题
    @NotEmpty( groups = AddApprove.class, message = ErrorCodeCore.NOT_EMPTY )
    private String title;

    //提交审核人姓名
    private String memberName;

    //提交审核人id，<FK>sys_member.id
    @NotNull( groups = AddApprove.class, message = ErrorCodeCore.NOT_NULL )
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

    // 是否通过
    @NotNull( groups = {Approve.class}, message = ErrorCodeCore.NOT_NULL )
    private Boolean approve;

    //审核意见
    @Length( groups = {Approve.class}, max = 255, message = ErrorCodeCore.MAX )
    private String opinion;

    //关联记录id，根据ftype分别为文章id、提问id、人员id
    @NotNull( groups = {AddApprove.class}, message = ErrorCodeCore.NOT_NULL )
    private Integer relId;

    // 人员信息
    private MemberDTO memberInfo;

    public MemberDTO getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberDTO memberInfo) {
        this.memberInfo = memberInfo;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public Integer getRealApproveMemberId() {
        return realApproveMemberId;
    }

    public void setRealApproveMemberId(Integer realApproveMemberId) {
        this.realApproveMemberId = realApproveMemberId;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getApproveMemberId() {
        return approveMemberId;
    }

    public void setApproveMemberId(Integer approveMemberId) {
        this.approveMemberId = approveMemberId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getApproveAt() {
        return approveAt;
    }

    public void setApproveAt(Date approveAt) {
        this.approveAt = approveAt;
    }

    public Short getFstatus() {
        return fstatus;
    }

    public void setFstatus(Short fstatus) {
        this.fstatus = fstatus;
    }

    public Short getFtype() {
        return ftype;
    }

    public void setFtype(Short ftype) {
        this.ftype = ftype;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getRelId() {
        return relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }
}
