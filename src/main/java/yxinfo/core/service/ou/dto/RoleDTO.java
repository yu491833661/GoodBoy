package yxinfo.core.service.ou.dto;

import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;

import java.util.Date;

/**
 * Created by dy on 2017/6/28.
 */
public class RoleDTO extends BaseDTO {

    private static final long serialVersionUID = -2015917493884711656L;

    private Integer id;

    // 组织id，公角色时为空
    private Integer orgId;

    // 名称
    @NotEmpty( groups = { Add.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String fname;

    // 备注
    private String remark;

    // 创建时间
    private Date createAt;

    // 是否被删除（1是，0否）
    private Boolean isDel;

    // 所属应用编号
    private String app;

    // 终端编号
    private String terminal;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark( String remark ) {
        this.remark = remark;
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

    public void setIsDel( Boolean del ) {
        isDel = del;
    }

    public String getApp() {
        return app;
    }

    public void setApp( String app ) {
        this.app = app;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal( String terminal ) {
        this.terminal = terminal;
    }
}
