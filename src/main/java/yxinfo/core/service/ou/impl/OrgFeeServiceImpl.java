package yxinfo.core.service.ou.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yxinfo.core.service.ou.OrgFeeService;
import yxinfo.core.service.ou.dao.mapper.SysOrgBopMapper;
import yxinfo.core.service.ou.dao.mapper.SysOrgFeeMapper;
import yxinfo.core.service.ou.dao.mapper.SysOrgMapper;
import yxinfo.core.service.ou.dao.mapper.SysOrgMapperExtend;
import yxinfo.core.service.ou.dao.model.SysOrg;
import yxinfo.core.service.ou.dao.model.SysOrgBop;
import yxinfo.core.service.ou.dao.model.SysOrgFee;
import yxinfo.core.service.ou.dao.model.SysOrgFeeKey;

import javax.annotation.Resource;

/**
 * Created by dy on 2016/8/9.
 */
@Component
@Service()
public class OrgFeeServiceImpl implements OrgFeeService {

    private static final Logger log = LoggerFactory.getLogger( OrgFeeServiceImpl.class );
    @Resource
    private SysOrgMapper orgDal;
    @Resource
    private SysOrgBopMapper orgBopMapper;
    @Resource
    private SysOrgFeeMapper orgFeeMapper;
    @Resource
    private SysOrgMapperExtend sysOrgMapperExtend;

    @Transactional( readOnly = true )
    public boolean checkBal( Integer orgId, String itemNo, Integer count ) {
        if ( orgId <= 0 || count <= 0 ) {
            return false;
        }
        SysOrg org = orgDal.selectByPrimaryKey( orgId );
        if ( org == null ) {
            return false;
        }
        SysOrgFee fee = getOrgFee( orgId, itemNo );
        if ( fee == null ) {
            return false;
        }
        return org.getBal() >= fee.getFee() * count;
    }

    @Transactional
    public void expend( Integer orgId, String itemNo, Integer count, Integer relId ) {
        if ( orgId <= 0 || count <= 0 ) {
            return;
        }
        SysOrgFee fee = getOrgFee( orgId, itemNo );
        if ( fee == null ) {
            return;
        }
        // 支出金额
        int amt = fee.getFee() * count;
        plusOrgBal( orgId, -amt );
        log.info( "组织ID[{}], 计费项目[{}], 支出金额[{}]分, ", orgId, itemNo, amt );
        // 添加收支记录
        addBop( orgId, false, itemNo, amt, relId );
        return;
    }

    /**
     * 增加商户余额
     *
     * @param orgId
     * @param amt
     * @return
     */
    private int plusOrgBal( int orgId, int amt ) {
        return sysOrgMapperExtend.plusOrgBal( orgId, amt );
    }

    /**
     * 获取商户计费项目
     *
     * @param orgId
     * @param itemNo
     * @return
     */
    private SysOrgFee getOrgFee( int orgId, String itemNo ) {
        SysOrgFeeKey feeKey = new SysOrgFee();
        feeKey.setItemNo( itemNo );
        feeKey.setOrgId( orgId );
        return orgFeeMapper.selectByPrimaryKey( feeKey );
    }

    /**
     * 添加组织收支明细
     *
     * @param orgId
     * @param isIncome
     * @param itemNo
     * @param amt
     * @param relId
     * @return
     */
    private int addBop( int orgId, boolean isIncome, String itemNo, int amt, int relId ) {
        SysOrgBop bop = new SysOrgBop();
        bop.setOrgId( orgId );
        bop.setItemNo( itemNo );
        bop.setFtype( isIncome ? "1" : "2" );
        bop.setAmt( amt );
        bop.setRelId( relId );
        return orgBopMapper.insertSelective( bop );
    }
}
