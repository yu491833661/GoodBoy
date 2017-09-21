package yxinfo.yjh.web.dto;

import yxinfo.core.common.dto.BaseDTO;

/**
 * Created by dy on 2016/7/1.
 */
public class RetUploadMsg extends BaseDTO {

    private static final long serialVersionUID = -7865632146925349941L;

    // 原文件名
    private String oriFileName;

    // 地址
    private String url;

    // 是否上传成功
    private boolean isSuccess;

    public RetUploadMsg() {
    }

    public RetUploadMsg( String oriFileName, String url ) {
        this.oriFileName = oriFileName;
        this.url = url;
    }

    public String getOriFileName() {
        return oriFileName;
    }

    public void setOriFileName( String oriFileName ) {
        this.oriFileName = oriFileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess( boolean success ) {
        isSuccess = success;
    }
}
