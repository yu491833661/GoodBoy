package yxinfo.core.service.ou;

/**
 * Created by dy on 2017/9/20.
 */
public interface EnterpriseService {

    /**
     * 添加学校管理员
     *
     * @param memberId
     * @param enterpriseId
     */
    void addEnterpriseManager( Integer memberId, Integer enterpriseId );

    /**
     * 删除学校管理员
     *
     * @param memberId
     */
    void deleteEnterpriseManager( Integer memberId );
}
