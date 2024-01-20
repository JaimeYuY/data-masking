package com.ocean.angel.tool.util;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.ocean.angel.tool.aspect.SensitiveDataHandler;
import java.util.List;

public class SensitiveUtils {

    /**
     * 手机号脱敏
     */
    public static String maskMobile(String phone) {
        if (StrUtil.isEmpty(phone)) {
            return "";
        }
        phone = DesensitizedUtil.mobilePhone(phone);
        return phone;
    }


    /**
     * 邮箱脱敏
     */
    public static String maskEmail(String email) {
        if (StrUtil.isEmpty(email)) {
            return "";
        }
        email = DesensitizedUtil.email(email);
        return email;
    }


    /**
     * 身份证号脱敏
     */
    public static String maskIdCard(String idCard) {
        if (StrUtil.isEmpty(idCard)) {
            return "";
        }
        idCard = DesensitizedUtil.idCardNum(idCard, 1, 2);
        return idCard;
    }

    /**
     * 生日打码操作
     * 中间月日打码。生日年月日
     *
     * @param birthday
     * @return
     */
    public static String maskBirthday(String birthday) {
        if (StrUtil.isEmpty(birthday)) {
            return "";
        }
        if (StrUtil.length(birthday) <= 4) {
            return birthday;
        }
        String pre = birthday.substring(0, 4);
        String suf = birthday.substring(4);
        String sufResult = StrUtil.replace(suf, "[0-9]", "*");
        return pre + sufResult;
    }

    /**
     * 银行卡号脱敏
     */
    public static String maskBankCard(String bandCard) {
        if (StrUtil.isEmpty(bandCard)) {
            return "";
        }
        bandCard = DesensitizedUtil.bankCard(bandCard);
        return bandCard;
    }

    /**
     * 密码全部打码
     *
     * @param password
     * @return
     */
    public static String maskPassword(String password) {
        if (StrUtil.isEmpty(password)) {
            return "";
        }
        int end = password.length();
        StringBuffer overPlay = new StringBuffer();
        for (int i = 0; i < end; i++) {
            overPlay.append("*");
        }
        return overPlay.toString();
    }

    /**
     * 中文姓名，除了第一位不打码
     *
     * @param name
     * @return
     */
    public static String maskName(String name) {
        if (StrUtil.isEmpty(name)) {
            return "";
        }
        int end = name.length();
        StringBuffer overPlay = new StringBuffer();
        for (int i = 1; i < end; i++) {
            overPlay.append("*");
        }
        return name.substring(0, 1) + overPlay.toString();
    }

    /**
     * 月薪，全部*
     *
     * @param salary
     * @return
     */
    public static String maskSalary(String salary) {
        if (StrUtil.isEmpty(salary)) {
            return "";
        }
        int end = salary.length();
        StringBuffer overPlay = new StringBuffer();
        for (int i = 0; i < end; i++) {
            overPlay.append("*");
        }
        return overPlay.toString();
    }

    /**
     * 其他证件号码前1位和后3位其他全部为*
     *
     * @param otherCard
     * @return
     */
    public static String maskOtherCard(String otherCard) {
        if (StrUtil.isEmpty(otherCard)) {
            return "";
        }
        if (StrUtil.length(otherCard) < 4) {
            return otherCard;
        }
        int start = 1;
        int end = otherCard.length() - 3;
        StringBuffer overPlay = new StringBuffer();
        for (int i = start; i < end; i++) {
            overPlay.append("*");
        }
        int len = otherCard.length();
        otherCard = otherCard.substring(0, 1) + overPlay.toString() + otherCard.substring(len - 3, len);
        return otherCard;
    }


    /**
     * 对list结果集支持
     *
     * @param list
     * @param <T>
     * @throws Exception
     */
    public static <T> void supportList(List<T> list) {
        for (T t : list) {
            try {
                SensitiveDataHandler.handle(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对object结果集支持
     *
     * @param t
     * @param <T>
     * @throws Exception
     */
    public static <T> void supportObject(T t) {
        try {
            SensitiveDataHandler.handle(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
