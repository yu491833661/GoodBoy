package yxinfo.core.service.sms.dto;

import org.hibernate.validator.constraints.NotEmpty;
import yxinfo.core.common.dto.BaseDTO;
import yxinfo.core.common.exception.ErrorCodeCore;
import yxinfo.core.common.validation.Add;
import yxinfo.core.common.validation.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by dy on 2016/6/27.
 */
public class EvtVcodeDTO extends BaseDTO {

    private static final long serialVersionUID = -2993567093314029540L;

    private Integer id;

    // 用于的功能码
    @NotEmpty( groups = { Add.class, Select.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String useAt;

    // 验证码
    @NotEmpty( groups = { Add.class, Select.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String fcode;

    // 手机号
    @NotEmpty( groups = { Add.class, Select.class }, message = ErrorCodeCore.NOT_EMPTY )
    private String mobile;

    // 创建时间
    private Date createAt;

    // 验证错误次数
    private Integer errCount;

    // 额外信息
    private List<String> ectMsg;

    // 图形验证码
    private String imgVcode;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getUseAt() {
        return useAt;
    }

    public void setUseAt( String useAt ) {
        this.useAt = useAt;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode( String fcode ) {
        this.fcode = fcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt( Date createAt ) {
        this.createAt = createAt;
    }

    public Integer getErrCount() {
        return errCount;
    }

    public void setErrCount( Integer errCount ) {
        this.errCount = errCount;
    }

    public List<String> getEctMsg() {
        return ectMsg;
    }

    public void setEctMsg( List<String> ectMsg ) {
        this.ectMsg = ectMsg;
    }

    public String getImgVcode() {
        return imgVcode;
    }

    public void setImgVcode( String imgVcode ) {
        this.imgVcode = imgVcode;
    }
}
