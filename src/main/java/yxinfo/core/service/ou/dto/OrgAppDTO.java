package yxinfo.core.service.ou.dto;

import yxinfo.core.common.dto.BaseDTO;

public class OrgAppDTO extends BaseDTO {

    private static final long serialVersionUID = -1030976238789804891L;

    // 组织id
    private Integer orgId;

    // 应用编号
    private String app;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId( Integer orgId ) {
        this.orgId = orgId;
    }

    public String getApp() {
        return app;
    }

    public void setApp( String app ) {
        this.app = app;
    }
}