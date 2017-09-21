package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

public class RoleMenuDTO extends BaseDTO {

    private static final long serialVersionUID = 414580213911117953L;

    // 角色id
    private Integer roleId;

    // 菜单id
    private Integer menuId;

    // 排序权重
    private Integer sort;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId( Integer menuId ) {
        this.menuId = menuId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort( Integer sort ) {
        this.sort = sort;
    }
}