package yxinfo.core.service.ou;

/**
 * Created by dy on 2016/8/9.
 */
public interface OrgFeeService {

    /**
     * 检查组织余额是否足够
     *
     * @param orgId  组织id
     * @param itemNo 收支项目
     * @param amt    计费次数
     * @return
     */
    boolean checkBal( Integer orgId, String itemNo, Integer count );

    /**
     * 按收支项目支出金额
     *
     * @param orgId  组织id
     * @param itemNo 收支项目
     * @param count  计费次数
     * @param relId  关联记录id
     */
    void expend( Integer orgId, String itemNo, Integer count, Integer relId );
}
