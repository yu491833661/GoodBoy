package yxinfo.core.common.dto;

import javax.validation.Valid;

/**
 * Created by hanley on 2016/6/18.
 */
public class PageDTO<T> extends BaseDTO {

    private static final long serialVersionUID = 190482562177036270L;

    // 页码
    private int pageNum = 1;

    // 每页数据量
    private int numPerPage = 10;

    // 数据总量
    private long total;

    // 总页数
    private long totalPage;

    @Valid
    private T data;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum( int pageNum ) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage( int numPerPage ) {
        this.numPerPage = numPerPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal( long total ) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage( long totalPage ) {
        this.totalPage = totalPage;
    }


    public T getData() {
        return data;
    }

    public void setData( T data ) {
        this.data = data;
    }
}
