package yxinfo.core.service.ou.dao.model;

public class SysRoleMenu {
    //角色id，<FK>sys_role.id
    private Integer roleId;

    //菜单id，<FK>sys_menu.id
    private Integer menuId;

    //排序权重
    private Integer sort;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_menu.role_id
     *
     * @return the value of sys_role_menu.role_id
     *
     * @mbggenerated
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_menu.role_id
     *
     * @param roleId the value for sys_role_menu.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_menu.menu_id
     *
     * @return the value of sys_role_menu.menu_id
     *
     * @mbggenerated
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_menu.menu_id
     *
     * @param menuId the value for sys_role_menu.menu_id
     *
     * @mbggenerated
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_menu.sort
     *
     * @return the value of sys_role_menu.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_menu.sort
     *
     * @param sort the value for sys_role_menu.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}