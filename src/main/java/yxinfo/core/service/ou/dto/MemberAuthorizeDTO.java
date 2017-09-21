package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by dy on 2017/8/19.
 */
public class MemberAuthorizeDTO extends BaseDTO {

    private static final long serialVersionUID = -445178436692134143L;

    @NotNull( groups = Update.class, message = ErrorCodeCore.NOT_NULL )
    private Integer roleId;

    @NotNull( groups = Add.class, message = ErrorCodeCore.NOT_NULL )
    private String roleName;

    @NotNull( groups = { Add.class, Update.class }, message = ErrorCodeCore.NOT_NULL )
    private String app;

    @Valid
    private List<MemberAuthorizeMenuDTO> menus;

    private List<Integer> memberIds;

    private List<MemberDTO> members;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName( String roleName ) {
        this.roleName = roleName;
    }

    public String getApp() {
        return app;
    }

    public void setApp( String app ) {
        this.app = app;
    }

    public List<MemberAuthorizeMenuDTO> getMenus() {
        return menus;
    }

    public void setMenus( List<MemberAuthorizeMenuDTO> menus ) {
        this.menus = menus;
    }

    public List<Integer> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds( List<Integer> memberIds ) {
        this.memberIds = memberIds;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    public void setMembers( List<MemberDTO> members ) {
        this.members = members;
    }
}
