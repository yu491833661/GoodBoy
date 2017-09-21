package yxinfo.yjh.service.propaganda.dto;

import yxinfo.core.common.dto.BaseDTO;

import java.util.Date;

/**
 * Created by dy on 2017/9/19.
 */
public class AdCoreDTO extends BaseDTO {

    private static final long serialVersionUID = 6274548920824976776L;

    private Integer id;

    // 标题
    private String title;

    // 图片路径
    private String pic;

    // 位置（1首页左侧，2首页右侧）
    private Short position;

    // 链接
    private String link;

    // 状态（1停用，2启用）
    private Short fstatus;

    // 创建时间
    private Date createAt;

    // 上一次更新时间
    private Date updateAt;

    // 发布人id，<FK>sys_member.id
    private Integer memberId;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic( String pic ) {
        this.pic = pic;
    }

    public Short getPosition() {
        return position;
    }

    public void setPosition( Short position ) {
        this.position = position;
    }

    public String getLink() {
        return link;
    }

    public void setLink( String link ) {
        this.link = link;
    }

    public Short getFstatus() {
        return fstatus;
    }

    public void setFstatus( Short fstatus ) {
        this.fstatus = fstatus;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt( Date createAt ) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt( Date updateAt ) {
        this.updateAt = updateAt;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId( Integer memberId ) {
        this.memberId = memberId;
    }
}
