package yxinfo.yjh.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.util.CollectionUtils;
import yxinfo.core.framework.service.dal.Page;
import yxinfo.core.framework.service.dal.dao.AbstractModel;

public class ArticleMoveHistoryExample extends AbstractModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public ArticleMoveHistoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
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
     * This method corresponds to the database table article_move_history
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
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
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
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_move_history
     *
     * @mbg.generated
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table article_move_history
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

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNull() {
            addCriterion("ftype is null");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNotNull() {
            addCriterion("ftype is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeEqualTo(Short value) {
            addCriterion("ftype =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(Short value) {
            addCriterion("ftype <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(Short value) {
            addCriterion("ftype >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("ftype >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(Short value) {
            addCriterion("ftype <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(Short value) {
            addCriterion("ftype <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<Short> values) {
            addCriterion("ftype in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<Short> values) {
            addCriterion("ftype not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(Short value1, Short value2) {
            addCriterion("ftype between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(Short value1, Short value2) {
            addCriterion("ftype not between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNull() {
            addCriterion("author_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNotNull() {
            addCriterion("author_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameEqualTo(String value) {
            addCriterion("author_name =", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotEqualTo(String value) {
            addCriterion("author_name <>", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThan(String value) {
            addCriterion("author_name >", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThanOrEqualTo(String value) {
            addCriterion("author_name >=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThan(String value) {
            addCriterion("author_name <", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThanOrEqualTo(String value) {
            addCriterion("author_name <=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLike(String value) {
            addCriterion("author_name like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotLike(String value) {
            addCriterion("author_name not like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIn(List<String> values) {
            addCriterion("author_name in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotIn(List<String> values) {
            addCriterion("author_name not in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameBetween(String value1, String value2) {
            addCriterion("author_name between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotBetween(String value1, String value2) {
            addCriterion("author_name not between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdIsNull() {
            addCriterion("from_block_id is null");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdIsNotNull() {
            addCriterion("from_block_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdEqualTo(Integer value) {
            addCriterion("from_block_id =", value, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdNotEqualTo(Integer value) {
            addCriterion("from_block_id <>", value, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdGreaterThan(Integer value) {
            addCriterion("from_block_id >", value, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_block_id >=", value, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdLessThan(Integer value) {
            addCriterion("from_block_id <", value, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdLessThanOrEqualTo(Integer value) {
            addCriterion("from_block_id <=", value, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdIn(List<Integer> values) {
            addCriterion("from_block_id in", values, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdNotIn(List<Integer> values) {
            addCriterion("from_block_id not in", values, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdBetween(Integer value1, Integer value2) {
            addCriterion("from_block_id between", value1, value2, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andFromBlockIdNotBetween(Integer value1, Integer value2) {
            addCriterion("from_block_id not between", value1, value2, "fromBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdIsNull() {
            addCriterion("to_block_id is null");
            return (Criteria) this;
        }

        public Criteria andToBlockIdIsNotNull() {
            addCriterion("to_block_id is not null");
            return (Criteria) this;
        }

        public Criteria andToBlockIdEqualTo(Integer value) {
            addCriterion("to_block_id =", value, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdNotEqualTo(Integer value) {
            addCriterion("to_block_id <>", value, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdGreaterThan(Integer value) {
            addCriterion("to_block_id >", value, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_block_id >=", value, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdLessThan(Integer value) {
            addCriterion("to_block_id <", value, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_block_id <=", value, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdIn(List<Integer> values) {
            addCriterion("to_block_id in", values, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdNotIn(List<Integer> values) {
            addCriterion("to_block_id not in", values, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdBetween(Integer value1, Integer value2) {
            addCriterion("to_block_id between", value1, value2, "toBlockId");
            return (Criteria) this;
        }

        public Criteria andToBlockIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_block_id not between", value1, value2, "toBlockId");
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
     * This class corresponds to the database table article_move_history
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
     * This class corresponds to the database table article_move_history
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