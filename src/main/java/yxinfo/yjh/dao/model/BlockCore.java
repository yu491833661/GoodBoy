package yxinfo.yjh.dao.model;

public class BlockCore {
    private Integer id;

    //板块名称
    private String fname;

    //父板块id，<FK>block_core.id
    private Integer pid;

    //是否被删除（1被删除，0未被删除）
    private Boolean isDel;

    //排序权重，越大越前
    private Integer sort;

    //是否展示在首页（1展示，0不展示）
    private Boolean isFront;

    //是否展示在首页上方导航栏（1展示，0不展示）
    private Boolean isFrontNav;

    //是否允许投稿（1允许，2不允许）
    private Boolean canSubmit;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.id
     *
     * @return the value of block_core.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.id
     *
     * @param id the value for block_core.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.fname
     *
     * @return the value of block_core.fname
     *
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.fname
     *
     * @param fname the value for block_core.fname
     *
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.pid
     *
     * @return the value of block_core.pid
     *
     * @mbg.generated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.pid
     *
     * @param pid the value for block_core.pid
     *
     * @mbg.generated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.is_del
     *
     * @return the value of block_core.is_del
     *
     * @mbg.generated
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.is_del
     *
     * @param isDel the value for block_core.is_del
     *
     * @mbg.generated
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.sort
     *
     * @return the value of block_core.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.sort
     *
     * @param sort the value for block_core.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.is_front
     *
     * @return the value of block_core.is_front
     *
     * @mbg.generated
     */
    public Boolean getIsFront() {
        return isFront;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.is_front
     *
     * @param isFront the value for block_core.is_front
     *
     * @mbg.generated
     */
    public void setIsFront(Boolean isFront) {
        this.isFront = isFront;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.is_front_nav
     *
     * @return the value of block_core.is_front_nav
     *
     * @mbg.generated
     */
    public Boolean getIsFrontNav() {
        return isFrontNav;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.is_front_nav
     *
     * @param isFrontNav the value for block_core.is_front_nav
     *
     * @mbg.generated
     */
    public void setIsFrontNav(Boolean isFrontNav) {
        this.isFrontNav = isFrontNav;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block_core.can_submit
     *
     * @return the value of block_core.can_submit
     *
     * @mbg.generated
     */
    public Boolean getCanSubmit() {
        return canSubmit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block_core.can_submit
     *
     * @param canSubmit the value for block_core.can_submit
     *
     * @mbg.generated
     */
    public void setCanSubmit(Boolean canSubmit) {
        this.canSubmit = canSubmit;
    }
}