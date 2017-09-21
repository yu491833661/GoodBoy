package yxinfo.yjh.service.meeting.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by hlf on 2017/9/15.
 */
public class MeetingDTO extends BaseDTO {
    private Integer id;

    //发布人Id
    private Integer memberId;

    @NotEmpty( groups = {Add.class, Update.class}, message = ErrorCodeCore.NOT_EMPTY )
    @Length( groups = {Add.class, Update.class}, max = 80, message = ErrorCodeCore.MAX )
    //会议名称
    private String fname;

    //参会人数
    private Integer attendPersonNum;

    //报名截至时间
    private Date signEndAt;

    //会议开始时间
    private Date startAt;

    //结束时间
    private Date endAt;

    //会议地点
    private String place;

    //咨询电话
    private String consoleMobile;

    //图片url
    private String picUrl;

    @NotNull( groups = {Add.class, Update.class}, message = ErrorCodeCore.NOT_EMPTY )
    //会议状态(1、待发布 2、待进行 3、进行中 4、完成 5、取消)
    private Short fstatus;

    //报名状态（1、待报名 2、报名中 3、报名结束）
    private Short signStatus;

    //创建时间
    private Date createAt;

    //发布时间
    private Date publishAt;

    //取消原因
    private String cancelReason;

    private String meetingIntruduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getAttendPersonNum() {
        return attendPersonNum;
    }

    public void setAttendPersonNum(Integer attendPersonNum) {
        this.attendPersonNum = attendPersonNum;
    }

    public Date getSignEndAt() {
        return signEndAt;
    }

    public void setSignEndAt(Date signEndAt) {
        this.signEndAt = signEndAt;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getConsoleMobile() {
        return consoleMobile;
    }

    public void setConsoleMobile(String consoleMobile) {
        this.consoleMobile = consoleMobile;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Short getFstatus() {
        return fstatus;
    }

    public void setFstatus(Short fstatus) {
        this.fstatus = fstatus;
    }

    public Short getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Short signStatus) {
        this.signStatus = signStatus;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getMeetingIntruduction() {
        return meetingIntruduction;
    }

    public void setMeetingIntruduction(String meetingIntruduction) {
        this.meetingIntruduction = meetingIntruduction;
    }
}
