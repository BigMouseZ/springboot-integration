package com.mybatis.dao.master.aa;

public class TestDatetime {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_datetime.guid
     *
     * @mbg.generated Tue Apr 16 11:57:09 CST 2019
     */
    private String guid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_datetime.test_name
     *
     * @mbg.generated Tue Apr 16 11:57:09 CST 2019
     */
    private String testName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test_datetime.guid
     *
     * @return the value of test_datetime.guid
     *
     * @mbg.generated Tue Apr 16 11:57:09 CST 2019
     */
    public String getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test_datetime.guid
     *
     * @param guid the value for test_datetime.guid
     *
     * @mbg.generated Tue Apr 16 11:57:09 CST 2019
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test_datetime.test_name
     *
     * @return the value of test_datetime.test_name
     *
     * @mbg.generated Tue Apr 16 11:57:09 CST 2019
     */
    public String getTestName() {
        return testName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test_datetime.test_name
     *
     * @param testName the value for test_datetime.test_name
     *
     * @mbg.generated Tue Apr 16 11:57:09 CST 2019
     */
    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }
}