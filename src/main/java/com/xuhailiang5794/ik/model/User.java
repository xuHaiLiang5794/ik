package com.xuhailiang5794.ik.model;

public class User extends UserKey {
    private Boolean isParent;

    /**
     * 名字
    */
    private String name;

    private String pid;

    private String wdcode;

    private Boolean required;

    public Boolean getIsParent() {
        return isParent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_national_tree.is_parent
     *
     * @param isParent the value for data_national_tree.is_parent
     *
     * @mbggenerated
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_national_tree.name
     *
     * @param name the value for data_national_tree.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_national_tree.pid
     *
     * @param pid the value for data_national_tree.pid
     *
     * @mbggenerated
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getWdcode() {
        return wdcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_national_tree.wdcode
     *
     * @param wdcode the value for data_national_tree.wdcode
     *
     * @mbggenerated
     */
    public void setWdcode(String wdcode) {
        this.wdcode = wdcode == null ? null : wdcode.trim();
    }

    public Boolean getRequired() {
        return required;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_national_tree.required
     *
     * @param required the value for data_national_tree.required
     *
     * @mbggenerated
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }
}