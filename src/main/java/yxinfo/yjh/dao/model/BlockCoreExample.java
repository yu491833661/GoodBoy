package yxinfo.yjh.dao.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.framework.service.dal.dao.AbstractModel;

public class BlockCoreExample extends AbstractModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table block_core
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table block_core
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table block_core
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table block_core
     *
     * @mbg.generated
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public BlockCoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
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
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_core
     *
     * @mbg.generated
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table block_core
     *
     * @mbg.generated
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFnameIsNull() {
            addCriterion("fname is null");
            return (Criteria) this;
        }

        public Criteria andFnameIsNotNull() {
            addCriterion("fname is not null");
            return (Criteria) this;
        }

        public Criteria andFnameEqualTo(String value) {
            addCriterion("fname =", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotEqualTo(String value) {
            addCriterion("fname <>", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThan(String value) {
            addCriterion("fname >", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThanOrEqualTo(String value) {
            addCriterion("fname >=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThan(String value) {
            addCriterion("fname <", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThanOrEqualTo(String value) {
            addCriterion("fname <=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLike(String value) {
            addCriterion("fname like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotLike(String value) {
            addCriterion("fname not like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameIn(List<String> values) {
            addCriterion("fname in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotIn(List<String> values) {
            addCriterion("fname not in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameBetween(String value1, String value2) {
            addCriterion("fname between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotBetween(String value1, String value2) {
            addCriterion("fname not between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Boolean value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Boolean> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Boolean> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andIsFrontIsNull() {
            addCriterion("is_front is null");
            return (Criteria) this;
        }

        public Criteria andIsFrontIsNotNull() {
            addCriterion("is_front is not null");
            return (Criteria) this;
        }

        public Criteria andIsFrontEqualTo(Boolean value) {
            addCriterion("is_front =", value, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontNotEqualTo(Boolean value) {
            addCriterion("is_front <>", value, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontGreaterThan(Boolean value) {
            addCriterion("is_front >", value, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_front >=", value, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontLessThan(Boolean value) {
            addCriterion("is_front <", value, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontLessThanOrEqualTo(Boolean value) {
            addCriterion("is_front <=", value, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontIn(List<Boolean> values) {
            addCriterion("is_front in", values, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontNotIn(List<Boolean> values) {
            addCriterion("is_front not in", values, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontBetween(Boolean value1, Boolean value2) {
            addCriterion("is_front between", value1, value2, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_front not between", value1, value2, "isFront");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavIsNull() {
            addCriterion("is_front_nav is null");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavIsNotNull() {
            addCriterion("is_front_nav is not null");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavEqualTo(Boolean value) {
            addCriterion("is_front_nav =", value, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavNotEqualTo(Boolean value) {
            addCriterion("is_front_nav <>", value, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavGreaterThan(Boolean value) {
            addCriterion("is_front_nav >", value, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_front_nav >=", value, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavLessThan(Boolean value) {
            addCriterion("is_front_nav <", value, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavLessThanOrEqualTo(Boolean value) {
            addCriterion("is_front_nav <=", value, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavIn(List<Boolean> values) {
            addCriterion("is_front_nav in", values, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavNotIn(List<Boolean> values) {
            addCriterion("is_front_nav not in", values, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavBetween(Boolean value1, Boolean value2) {
            addCriterion("is_front_nav between", value1, value2, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andIsFrontNavNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_front_nav not between", value1, value2, "isFrontNav");
            return (Criteria) this;
        }

        public Criteria andCanSubmitIsNull() {
            addCriterion("can_submit is null");
            return (Criteria) this;
        }

        public Criteria andCanSubmitIsNotNull() {
            addCriterion("can_submit is not null");
            return (Criteria) this;
        }

        public Criteria andCanSubmitEqualTo(Boolean value) {
            addCriterion("can_submit =", value, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitNotEqualTo(Boolean value) {
            addCriterion("can_submit <>", value, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitGreaterThan(Boolean value) {
            addCriterion("can_submit >", value, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitGreaterThanOrEqualTo(Boolean value) {
            addCriterion("can_submit >=", value, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitLessThan(Boolean value) {
            addCriterion("can_submit <", value, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitLessThanOrEqualTo(Boolean value) {
            addCriterion("can_submit <=", value, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitIn(List<Boolean> values) {
            addCriterion("can_submit in", values, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitNotIn(List<Boolean> values) {
            addCriterion("can_submit not in", values, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitBetween(Boolean value1, Boolean value2) {
            addCriterion("can_submit between", value1, value2, "canSubmit");
            return (Criteria) this;
        }

        public Criteria andCanSubmitNotBetween(Boolean value1, Boolean value2) {
            addCriterion("can_submit not between", value1, value2, "canSubmit");
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
     * This class corresponds to the database table block_core
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table block_core
     *
     * @mbg.generated
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