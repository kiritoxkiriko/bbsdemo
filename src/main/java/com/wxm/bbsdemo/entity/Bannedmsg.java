package com.wxm.bbsdemo.entity;

public class Bannedmsg {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bannedmsg.bannedmsg_id
     *
     * @mbg.generated
     */
    private Integer bannedmsgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bannedmsg.banneduser_id
     *
     * @mbg.generated
     */
    private Long banneduserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bannedmsg.bannedby_id
     *
     * @mbg.generated
     */
    private Long bannedbyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bannedmsg.bannedmsg_id
     *
     * @return the value of bannedmsg.bannedmsg_id
     *
     * @mbg.generated
     */
    public Integer getBannedmsgId() {
        return bannedmsgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bannedmsg.bannedmsg_id
     *
     * @param bannedmsgId the value for bannedmsg.bannedmsg_id
     *
     * @mbg.generated
     */
    public void setBannedmsgId(Integer bannedmsgId) {
        this.bannedmsgId = bannedmsgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bannedmsg.banneduser_id
     *
     * @return the value of bannedmsg.banneduser_id
     *
     * @mbg.generated
     */
    public Long getBanneduserId() {
        return banneduserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bannedmsg.banneduser_id
     *
     * @param banneduserId the value for bannedmsg.banneduser_id
     *
     * @mbg.generated
     */
    public void setBanneduserId(Long banneduserId) {
        this.banneduserId = banneduserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bannedmsg.bannedby_id
     *
     * @return the value of bannedmsg.bannedby_id
     *
     * @mbg.generated
     */
    public Long getBannedbyId() {
        return bannedbyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bannedmsg.bannedby_id
     *
     * @param bannedbyId the value for bannedmsg.bannedby_id
     *
     * @mbg.generated
     */
    public void setBannedbyId(Long bannedbyId) {
        this.bannedbyId = bannedbyId;
    }
}