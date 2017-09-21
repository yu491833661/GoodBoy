package yxinfo.core.service.ou.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.util.CollectionUtils;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.framework.service.dal.dao.AbstractModel;

public class SysAccessTokExample extends AbstractModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public SysAccessTokExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAccessTokIsNull() {
            addCriterion("access_tok is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokIsNotNull() {
            addCriterion("access_tok is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokEqualTo(String value) {
            addCriterion("access_tok =", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokNotEqualTo(String value) {
            addCriterion("access_tok <>", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokGreaterThan(String value) {
            addCriterion("access_tok >", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokGreaterThanOrEqualTo(String value) {
            addCriterion("access_tok >=", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokLessThan(String value) {
            addCriterion("access_tok <", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokLessThanOrEqualTo(String value) {
            addCriterion("access_tok <=", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokLike(String value) {
            addCriterion("access_tok like", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokNotLike(String value) {
            addCriterion("access_tok not like", value, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokIn(List<String> values) {
            addCriterion("access_tok in", values, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokNotIn(List<String> values) {
            addCriterion("access_tok not in", values, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokBetween(String value1, String value2) {
            addCriterion("access_tok between", value1, value2, "accessTok");
            return (Criteria) this;
        }

        public Criteria andAccessTokNotBetween(String value1, String value2) {
            addCriterion("access_tok not between", value1, value2, "accessTok");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(String value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(String value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(String value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(String value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(String value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLike(String value) {
            addCriterion("device_type like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotLike(String value) {
            addCriterion("device_type not like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<String> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<String> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(String value1, String value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(String value1, String value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNull() {
            addCriterion("create_at is null");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNotNull() {
            addCriterion("create_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAtEqualTo(Date value) {
            addCriterion("create_at =", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotEqualTo(Date value) {
            addCriterion("create_at <>", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThan(Date value) {
            addCriterion("create_at >", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_at >=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThan(Date value) {
            addCriterion("create_at <", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThanOrEqualTo(Date value) {
            addCriterion("create_at <=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtIn(List<Date> values) {
            addCriterion("create_at in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotIn(List<Date> values) {
            addCriterion("create_at not in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtBetween(Date value1, Date value2) {
            addCriterion("create_at between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotBetween(Date value1, Date value2) {
            addCriterion("create_at not between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandIsNull() {
            addCriterion("device_brand is null");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandIsNotNull() {
            addCriterion("device_brand is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandEqualTo(String value) {
            addCriterion("device_brand =", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandNotEqualTo(String value) {
            addCriterion("device_brand <>", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandGreaterThan(String value) {
            addCriterion("device_brand >", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandGreaterThanOrEqualTo(String value) {
            addCriterion("device_brand >=", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandLessThan(String value) {
            addCriterion("device_brand <", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandLessThanOrEqualTo(String value) {
            addCriterion("device_brand <=", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandLike(String value) {
            addCriterion("device_brand like", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandNotLike(String value) {
            addCriterion("device_brand not like", value, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandIn(List<String> values) {
            addCriterion("device_brand in", values, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandNotIn(List<String> values) {
            addCriterion("device_brand not in", values, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandBetween(String value1, String value2) {
            addCriterion("device_brand between", value1, value2, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceBrandNotBetween(String value1, String value2) {
            addCriterion("device_brand not between", value1, value2, "deviceBrand");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIsNull() {
            addCriterion("device_model is null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIsNotNull() {
            addCriterion("device_model is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelEqualTo(String value) {
            addCriterion("device_model =", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotEqualTo(String value) {
            addCriterion("device_model <>", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelGreaterThan(String value) {
            addCriterion("device_model >", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelGreaterThanOrEqualTo(String value) {
            addCriterion("device_model >=", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLessThan(String value) {
            addCriterion("device_model <", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLessThanOrEqualTo(String value) {
            addCriterion("device_model <=", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLike(String value) {
            addCriterion("device_model like", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotLike(String value) {
            addCriterion("device_model not like", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIn(List<String> values) {
            addCriterion("device_model in", values, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotIn(List<String> values) {
            addCriterion("device_model not in", values, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelBetween(String value1, String value2) {
            addCriterion("device_model between", value1, value2, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotBetween(String value1, String value2) {
            addCriterion("device_model not between", value1, value2, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andImeiIsNull() {
            addCriterion("imei is null");
            return (Criteria) this;
        }

        public Criteria andImeiIsNotNull() {
            addCriterion("imei is not null");
            return (Criteria) this;
        }

        public Criteria andImeiEqualTo(String value) {
            addCriterion("imei =", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotEqualTo(String value) {
            addCriterion("imei <>", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThan(String value) {
            addCriterion("imei >", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThanOrEqualTo(String value) {
            addCriterion("imei >=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThan(String value) {
            addCriterion("imei <", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThanOrEqualTo(String value) {
            addCriterion("imei <=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLike(String value) {
            addCriterion("imei like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotLike(String value) {
            addCriterion("imei not like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiIn(List<String> values) {
            addCriterion("imei in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotIn(List<String> values) {
            addCriterion("imei not in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiBetween(String value1, String value2) {
            addCriterion("imei between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotBetween(String value1, String value2) {
            addCriterion("imei not between", value1, value2, "imei");
            return (Criteria) this;
        }

        private String getKeyValue(Criterion criterion) {
            StringBuffer sb = new StringBuffer();
            if ( criterion.isNoValue() ) {
                sb.append( criterion.getCondition() );
            } else if ( criterion.isSingleValue() ) {
                sb.append( criterion.getCondition() ).append( " " ).append( criterion.getValue() );
            } else if ( criterion.betweenValue ) {
                sb.append( criterion.getCondition() )
                        .append( " " ).append( criterion.getValue() )
                        .append( " and " ).append( criterion.getSecondValue() );
            } else if ( criterion.listValue ) {
                List<Object> inClause = (List<Object>) criterion.getValue();
                if ( !CollectionUtils.isEmpty( inClause ) ) {
                    sb.append( criterion.getCondition() ).append( " ( " );
                    for ( int i = 0; i < inClause.size(); i++ ) {
                        if ( i == inClause.size() - 1 ) {
                            sb.append( inClause.get( i ) ).append( " ) " );
                        } else {
                            sb.append( inClause.get( i ) ).append( " , " );
                        }
                    }
                }
            }
            return sb.toString();
        }

        public Criteria addMutiOrClause(Criteria addMutiOrClause) {
            StringBuffer sb = new StringBuffer();
            List<Criterion> orList = addMutiOrClause.getCriteria();
            if ( !CollectionUtils.isEmpty( orList ) ) {
                sb.append( "( " );
                for ( int i = 0; i< orList.size(); i++ ) {
                    if ( i == addMutiOrClause.getCriteria().size() - 1 ) {
                        sb.append( getKeyValue( orList.get( i ) ) ).append( " )" );
                    } else {
                        sb.append( getKeyValue( orList.get( i ) ) ).append( " or " );
                    }
                }
            }
            addCriterion( sb.toString() );
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_access_tok
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_access_tok
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}