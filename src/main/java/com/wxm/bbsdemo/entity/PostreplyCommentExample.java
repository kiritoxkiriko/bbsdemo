package com.wxm.bbsdemo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostreplyCommentExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public PostreplyCommentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
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
     * This method corresponds to the database table postreply_comment
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
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table postreply_comment
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table postreply_comment
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

        public Criteria andPostreplyCommentIdIsNull() {
            addCriterion("postreply_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdIsNotNull() {
            addCriterion("postreply_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdEqualTo(Long value) {
            addCriterion("postreply_comment_id =", value, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdNotEqualTo(Long value) {
            addCriterion("postreply_comment_id <>", value, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdGreaterThan(Long value) {
            addCriterion("postreply_comment_id >", value, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("postreply_comment_id >=", value, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdLessThan(Long value) {
            addCriterion("postreply_comment_id <", value, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdLessThanOrEqualTo(Long value) {
            addCriterion("postreply_comment_id <=", value, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdIn(List<Long> values) {
            addCriterion("postreply_comment_id in", values, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdNotIn(List<Long> values) {
            addCriterion("postreply_comment_id not in", values, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdBetween(Long value1, Long value2) {
            addCriterion("postreply_comment_id between", value1, value2, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyCommentIdNotBetween(Long value1, Long value2) {
            addCriterion("postreply_comment_id not between", value1, value2, "postreplyCommentId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdIsNull() {
            addCriterion("postreply_id is null");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdIsNotNull() {
            addCriterion("postreply_id is not null");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdEqualTo(Long value) {
            addCriterion("postreply_id =", value, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdNotEqualTo(Long value) {
            addCriterion("postreply_id <>", value, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdGreaterThan(Long value) {
            addCriterion("postreply_id >", value, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("postreply_id >=", value, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdLessThan(Long value) {
            addCriterion("postreply_id <", value, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdLessThanOrEqualTo(Long value) {
            addCriterion("postreply_id <=", value, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdIn(List<Long> values) {
            addCriterion("postreply_id in", values, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdNotIn(List<Long> values) {
            addCriterion("postreply_id not in", values, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdBetween(Long value1, Long value2) {
            addCriterion("postreply_id between", value1, value2, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andPostreplyIdNotBetween(Long value1, Long value2) {
            addCriterion("postreply_id not between", value1, value2, "postreplyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdIsNull() {
            addCriterion("commentby_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdIsNotNull() {
            addCriterion("commentby_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdEqualTo(Long value) {
            addCriterion("commentby_id =", value, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdNotEqualTo(Long value) {
            addCriterion("commentby_id <>", value, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdGreaterThan(Long value) {
            addCriterion("commentby_id >", value, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("commentby_id >=", value, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdLessThan(Long value) {
            addCriterion("commentby_id <", value, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdLessThanOrEqualTo(Long value) {
            addCriterion("commentby_id <=", value, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdIn(List<Long> values) {
            addCriterion("commentby_id in", values, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdNotIn(List<Long> values) {
            addCriterion("commentby_id not in", values, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdBetween(Long value1, Long value2) {
            addCriterion("commentby_id between", value1, value2, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentbyIdNotBetween(Long value1, Long value2) {
            addCriterion("commentby_id not between", value1, value2, "commentbyId");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentdateIsNull() {
            addCriterion("commentdate is null");
            return (Criteria) this;
        }

        public Criteria andCommentdateIsNotNull() {
            addCriterion("commentdate is not null");
            return (Criteria) this;
        }

        public Criteria andCommentdateEqualTo(Date value) {
            addCriterion("commentdate =", value, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateNotEqualTo(Date value) {
            addCriterion("commentdate <>", value, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateGreaterThan(Date value) {
            addCriterion("commentdate >", value, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateGreaterThanOrEqualTo(Date value) {
            addCriterion("commentdate >=", value, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateLessThan(Date value) {
            addCriterion("commentdate <", value, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateLessThanOrEqualTo(Date value) {
            addCriterion("commentdate <=", value, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateIn(List<Date> values) {
            addCriterion("commentdate in", values, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateNotIn(List<Date> values) {
            addCriterion("commentdate not in", values, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateBetween(Date value1, Date value2) {
            addCriterion("commentdate between", value1, value2, "commentdate");
            return (Criteria) this;
        }

        public Criteria andCommentdateNotBetween(Date value1, Date value2) {
            addCriterion("commentdate not between", value1, value2, "commentdate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table postreply_comment
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
     * This class corresponds to the database table postreply_comment
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