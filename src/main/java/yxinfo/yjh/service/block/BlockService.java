package yxinfo.yjh.service.block;

/**
 * Created by dy on 2017/9/20.
 */
public interface BlockService {

    /**
     * 添加板块管理员
     *
     * @param memberId
     * @param blockId
     */
    void addBlockManager( Integer memberId, Integer blockId );

    /**
     * 删除板块管理员
     *
     * @param memberId
     */
    void deleteBlockManager( Integer memberId );
}
