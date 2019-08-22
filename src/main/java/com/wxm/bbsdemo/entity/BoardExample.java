package com.wxm.bbsdemo.entity;

import java.util.ArrayList;
import java.util.List;

public class BoardExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table board
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table board
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table board
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    public BoardExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
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
     * This method corresponds to the database table board
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
     * This method corresponds to the database table board
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table board
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
     * This class corresponds to the database table board
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

        public Criteria andBoardidIsNull() {
            addCriterion("boardid is null");
            return (Criteria) this;
        }

        public Criteria andBoardidIsNotNull() {
            addCriterion("boardid is not null");
            return (Criteria) this;
        }

        public Criteria andBoardidEqualTo(Long value) {
            addCriterion("boardid =", value, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidNotEqualTo(Long value) {
            addCriterion("boardid <>", value, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidGreaterThan(Long value) {
            addCriterion("boardid >", value, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidGreaterThanOrEqualTo(Long value) {
            addCriterion("boardid >=", value, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidLessThan(Long value) {
            addCriterion("boardid <", value, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidLessThanOrEqualTo(Long value) {
            addCriterion("boardid <=", value, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidIn(List<Long> values) {
            addCriterion("boardid in", values, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidNotIn(List<Long> values) {
            addCriterion("boardid not in", values, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidBetween(Long value1, Long value2) {
            addCriterion("boardid between", value1, value2, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardidNotBetween(Long value1, Long value2) {
            addCriterion("boardid not between", value1, value2, "boardid");
            return (Criteria) this;
        }

        public Criteria andBoardnameIsNull() {
            addCriterion("boardname is null");
            return (Criteria) this;
        }

        public Criteria andBoardnameIsNotNull() {
            addCriterion("boardname is not null");
            return (Criteria) this;
        }

        public Criteria andBoardnameEqualTo(String value) {
            addCriterion("boardname =", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameNotEqualTo(String value) {
            addCriterion("boardname <>", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameGreaterThan(String value) {
            addCriterion("boardname >", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameGreaterThanOrEqualTo(String value) {
            addCriterion("boardname >=", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameLessThan(String value) {
            addCriterion("boardname <", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameLessThanOrEqualTo(String value) {
            addCriterion("boardname <=", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameLike(String value) {
            addCriterion("boardname like", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameNotLike(String value) {
            addCriterion("boardname not like", value, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameIn(List<String> values) {
            addCriterion("boardname in", values, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameNotIn(List<String> values) {
            addCriterion("boardname not in", values, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameBetween(String value1, String value2) {
            addCriterion("boardname between", value1, value2, "boardname");
            return (Criteria) this;
        }

        public Criteria andBoardnameNotBetween(String value1, String value2) {
            addCriterion("boardname not between", value1, value2, "boardname");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andPostNumIsNull() {
            addCriterion("post_num is null");
            return (Criteria) this;
        }

        public Criteria andPostNumIsNotNull() {
            addCriterion("post_num is not null");
            return (Criteria) this;
        }

        public Criteria andPostNumEqualTo(Long value) {
            addCriterion("post_num =", value, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumNotEqualTo(Long value) {
            addCriterion("post_num <>", value, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumGreaterThan(Long value) {
            addCriterion("post_num >", value, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumGreaterThanOrEqualTo(Long value) {
            addCriterion("post_num >=", value, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumLessThan(Long value) {
            addCriterion("post_num <", value, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumLessThanOrEqualTo(Long value) {
            addCriterion("post_num <=", value, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumIn(List<Long> values) {
            addCriterion("post_num in", values, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumNotIn(List<Long> values) {
            addCriterion("post_num not in", values, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumBetween(Long value1, Long value2) {
            addCriterion("post_num between", value1, value2, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostNumNotBetween(Long value1, Long value2) {
            addCriterion("post_num not between", value1, value2, "postNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumIsNull() {
            addCriterion("postreply_num is null");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumIsNotNull() {
            addCriterion("postreply_num is not null");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumEqualTo(Long value) {
            addCriterion("postreply_num =", value, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumNotEqualTo(Long value) {
            addCriterion("postreply_num <>", value, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumGreaterThan(Long value) {
            addCriterion("postreply_num >", value, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumGreaterThanOrEqualTo(Long value) {
            addCriterion("postreply_num >=", value, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumLessThan(Long value) {
            addCriterion("postreply_num <", value, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumLessThanOrEqualTo(Long value) {
            addCriterion("postreply_num <=", value, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumIn(List<Long> values) {
            addCriterion("postreply_num in", values, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumNotIn(List<Long> values) {
            addCriterion("postreply_num not in", values, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumBetween(Long value1, Long value2) {
            addCriterion("postreply_num between", value1, value2, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andPostreplyNumNotBetween(Long value1, Long value2) {
            addCriterion("postreply_num not between", value1, value2, "postreplyNum");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNull() {
            addCriterion("notice is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNotNull() {
            addCriterion("notice is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeEqualTo(String value) {
            addCriterion("notice =", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotEqualTo(String value) {
            addCriterion("notice <>", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThan(String value) {
            addCriterion("notice >", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("notice >=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThan(String value) {
            addCriterion("notice <", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThanOrEqualTo(String value) {
            addCriterion("notice <=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLike(String value) {
            addCriterion("notice like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotLike(String value) {
            addCriterion("notice not like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeIn(List<String> values) {
            addCriterion("notice in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotIn(List<String> values) {
            addCriterion("notice not in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeBetween(String value1, String value2) {
            addCriterion("notice between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotBetween(String value1, String value2) {
            addCriterion("notice not between", value1, value2, "notice");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table board
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
     * This class corresponds to the database table board
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