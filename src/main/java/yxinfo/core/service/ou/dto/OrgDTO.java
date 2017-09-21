package yxinfo.core.service.ou.dto;

import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public class OrgDTO extends BaseDTO {

    private static final long serialVersionUID = -8666863336124771359L;

    @NotNull( groups = { Update.class }, message = ErrorCodeCore.NOT_NULL )
    private Integer id;

    // 名称
    @NotEmpty( groups = { Add.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String fname;

    // 地址
    private String address;

    // 上级组织id
    private Integer pid;

    // 创建时间
    private Date createAt;

    // 是否被删除（1是，0否）
    private Boolean isDel;

    // 用户用户组
    private List<Integer> groupIds;

    // 用户角色
    private List<Integer> roleIds;

    // 用户在组织下的信息
    private MemberOrgDTO memberOrg;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname( String fname ) {
        this.fname = fname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
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

    public Boolean getDel() {
        return isDel;
    }

    public void setDel( Boolean del ) {
        isDel = del;
    }

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds( List<Integer> groupIds ) {
        this.groupIds = groupIds;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds( List<Integer> roleIds ) {
        this.roleIds = roleIds;
    }

    public MemberOrgDTO getMemberOrg() {
        return memberOrg;
    }

    public void setMemberOrg( MemberOrgDTO memberOrg ) {
        this.memberOrg = memberOrg;
    }
}
