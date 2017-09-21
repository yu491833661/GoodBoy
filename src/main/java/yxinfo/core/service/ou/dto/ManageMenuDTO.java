package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public class ManageMenuDTO extends BaseDTO {

    private static final long serialVersionUID = -7769581496153367155L;

    private Integer id;

    // 名称
    private String fname;

    // 上级菜单id
    private Integer pid;

    // 所属功能模块id
    private Integer modId;

    // 图标
    private String icon;

    // 路由
    private String ref;

    // 标签
    private String tab;

    // 副路由
    private String srefActive;

    // 终端编号
    private String termCode;

    // 应用编号
    private String app;

    // 子菜单
    private List<ManageMenuDTO> childs;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid( Integer pid ) {
        this.pid = pid;
    }

    public Integer getModId() {
        return modId;
    }

    public void setModId( Integer modId ) {
        this.modId = modId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon( String icon ) {
        this.icon = icon;
    }

    public String getRef() {
        return ref;
    }

    public void setRef( String ref ) {
        this.ref = ref;
    }

    public String getTab() {
        return tab;
    }

    public void setTab( String tab ) {
        this.tab = tab;
    }

    public String getSrefActive() {
        return srefActive;
    }

    public void setSrefActive( String srefActive ) {
        this.srefActive = srefActive;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode( String termCode ) {
        this.termCode = termCode;
    }

    public String getApp() {
        return app;
    }

    public void setApp( String app ) {
        this.app = app;
    }

    public List<ManageMenuDTO> getChilds() {
        return childs;
    }

    public void setChilds( List<ManageMenuDTO> childs ) {
        this.childs = childs;
    }
}
