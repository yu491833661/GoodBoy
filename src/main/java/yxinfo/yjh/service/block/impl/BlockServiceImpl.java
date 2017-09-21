package yxinfo.yjh.service.block.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxinfo.yjh.dao.mapper.BlockManagerMapper;
import yxinfo.yjh.dao.model.BlockManagerExample;
import yxinfo.yjh.dao.model.BlockManagerKey;
import yxinfo.yjh.service.block.BlockService;

import javax.annotation.Resource;

/**
 * Created by dy on 2017/9/20.
 */
@Component
@Service
public class BlockServiceImpl implements BlockService {

    @Resource
    private BlockManagerMapper blockManagerMapper;

    public void addBlockManager( Integer memberId, Integer blockId ) {
        BlockManagerKey blockManagerKey = new BlockManagerKey();
        blockManagerKey.setMemberId( memberId );
        blockManagerKey.setBlockId( blockId );
        blockManagerMapper.insert( blockManagerKey );
    }

    public void deleteBlockManager( Integer memberId ) {
        BlockManagerExample example = new BlockManagerExample();
        example.createCriteria().andMemberIdEqualTo( memberId );
        blockManagerMapper.deleteByExample( example );
    }
}
