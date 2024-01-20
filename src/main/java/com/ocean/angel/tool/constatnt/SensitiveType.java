package com.ocean.angel.tool.constatnt;

/**
 * 敏感数据类型
 * @author: brave.li
 * @date: 2021.07.31
 */
public enum SensitiveType {
    /**
     * 姓名
     */
    NAME,
    /**
     * 手机
     */
    MOBILE,
    /**
     * 邮箱
     */
    EMAIL,
    /**
     * 身份证
     */
    IDCARD,
    /**
     * 生日
     */
    BIRTHDAY,
    /**
     * 密码
     */
    PASSWORD,
    /**
     * 银行卡
     */
    BANKCARD,
    /**
     * 薪水
     */
    SALARY,
    /**
     * 其他证件号码
     */
    OTHERCARD,
    /**
     * 不确定哪种证件类型,指向某个字段确定类型
     */
    CARD_REF,
    /**
     * 对象（将对标记为OBJECT的对象会处理该对象里面的字段）
     */
    OBJECT
}
