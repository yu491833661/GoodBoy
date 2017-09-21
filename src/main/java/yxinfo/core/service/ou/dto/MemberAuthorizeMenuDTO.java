package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Update;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by dy on 2017/8/19.
 */
public class MemberAuthorizeMenuDTO extends BaseDTO {

    private static final long serialVersionUID = 6385218771923853089L;

    @NotNull( groups = { Add.class, Update.class }, message = ErrorCodeCore.NOT_NULL )
    private String terminal;

    private List<Integer> manageMenuIds;

    private List<Integer> appMenuIds;

    private List<ManageMenuDTO> menus;

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal( String terminal ) {
        this.terminal = terminal;
    }

    public List<Integer> getManageMenuIds() {
        return manageMenuIds;
    }

    public void setManageMenuIds( List<Integer> manageMenuIds ) {
        this.manageMenuIds = manageMenuIds;
    }

    public List<Integer> getAppMenuIds() {
        return appMenuIds;
    }

    public void setAppMenuIds( List<Integer> appMenuIds ) {
        this.appMenuIds = appMenuIds;
    }

    public List<ManageMenuDTO> getMenus() {
        return menus;
    }

    public void setMenus( List<ManageMenuDTO> menus ) {
        this.menus = menus;
    }

}
