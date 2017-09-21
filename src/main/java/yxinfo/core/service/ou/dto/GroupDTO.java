package yxinfo.core.service.ou.dto;

import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class GroupDTO extends BaseDTO {

    private static final long serialVersionUID = -309665910867836219L;

    @NotNull( groups = { Update.class }, message = ErrorCodeCore.NOT_EMPTY )
    private Integer id;

    // 所属组织id
    private Integer orgId;

    // 名称
    @NotEmpty( groups = { Add.class, Update.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String fname;

    // 编号
    private String fcode;

    // 备注
    private String remark;

    // 上级用户组id
    private Integer pid;

    // 创建时间
    private Date createAt;

    // 是否被删除（1是，0否）
    private Boolean isDel;

    // 排序权重
    private Integer sort;

    // 是否有子部门
    private Boolean haveSon;

    // 获取数量
    private Integer dataLen;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId( Integer orgId ) {
        this.orgId = orgId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname( String fname ) {
        this.fname = fname;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode( String fcode ) {
        this.fcode = fcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark( String remark ) {
        this.remark = remark;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid( Integer pid ) {
        this.pid = pid;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt( Date createAt ) {
        this.createAt = createAt;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel( Boolean isDel ) {
        this.isDel = isDel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort( Integer sort ) {
        this.sort = sort;
    }

    public Boolean getHaveSon() {
        return haveSon;
    }

    public void setHaveSon( Boolean haveSon ) {
        this.haveSon = haveSon;
    }

    public Integer getDataLen() {
        return dataLen;
    }

    public void setDataLen( Integer dataLen ) {
        this.dataLen = dataLen;
    }
}