package com.ocean.angel.tool.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ocean.angel.tool.annotation.MaskCardType;
import com.ocean.angel.tool.annotation.MaskField;
import com.ocean.angel.tool.common.ResultBean;
import com.ocean.angel.tool.common.ResultPage;
import com.ocean.angel.tool.constatnt.SensitiveType;
import com.ocean.angel.tool.util.SensitiveUtils;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SensitiveDataHandler {
    public static void handle(Object obj) throws Exception {
        if (null != obj) {
            if (obj instanceof List) {
                List<?> list = (List<?>) obj;
                for (Object o : list) {
                    mask(o);
                }
                return;
            }
            if (obj instanceof ResultPage) {
                ResultPage<?> tableDataInfo = (ResultPage<?>) obj;
                if (CollUtil.isNotEmpty(tableDataInfo.getRows())) {
                    for (Object o : tableDataInfo.getRows()) {
                        mask(o);
                    }
                }
                return;
            }
            if (obj instanceof ResultBean) {
                ResultBean<?> baseResult = (ResultBean<?>) obj;
                if (null != baseResult.getData()) {
                    if (baseResult.getData() instanceof List) {
                        List<?> list = (List<?>) baseResult.getData();
                        for (Object o : list) {
                            mask(o);
                        }
                        return;
                    }
                    mask(baseResult.getData());
                    return;
                }
            }
            mask(obj);
        }
    }

    private static void mask(Object obj) throws Exception {
        if (null == obj) {
            return;
        }
        Class<?> objClazz = obj.getClass();
        if (null != objClazz) {
            List<Field> allFieldsList = Arrays.asList(objClazz.getDeclaredFields());
            if (CollUtil.isNotEmpty(allFieldsList)) {
                for (Field declaredField : allFieldsList) {
                    declaredField.setAccessible(true);
                    MaskField sensitiveField = declaredField.getAnnotation(MaskField.class);
                    if (null != sensitiveField) {
                        Object fieldVal = declaredField.get(obj);
                        if (null != fieldVal) {
                            if (SensitiveType.OBJECT.equals(sensitiveField.value())) {
                                handle(fieldVal);
                            } else if (SensitiveType.CARD_REF.equals(sensitiveField.value())) {
                                // 处理
                                maskCard(obj, allFieldsList, declaredField, sensitiveField, (String) fieldVal);
                            } else {
                                // 处理
                                try {
                                    String valStr = (String) fieldVal;
                                    String result = handleSensitiveString(sensitiveField.value(), valStr);
                                    declaredField.set(obj, result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void maskCard(Object obj, List<Field> allFieldsList, Field declaredField, MaskField sensitiveField, String fieldVal) {
        try {
            MaskCardType sensitiveCardType = sensitiveField.cardRef();
            if (Objects.nonNull(sensitiveCardType)) {
                String idCardType = sensitiveCardType.idCardType();
                String refField = sensitiveCardType.refField();
                if (StrUtil.isNotEmpty(idCardType) && StrUtil.isNotEmpty(refField)) {
                    for (Field declaredFieldCur : allFieldsList) {
                        declaredFieldCur.setAccessible(Boolean.TRUE);
                        if (declaredFieldCur.getName().equals(refField)) {
                            Object idCardTypeVal = declaredFieldCur.get(obj);
                            String valStr = fieldVal;
                            if (String.valueOf(idCardTypeVal).equals(idCardType)) {
                                String result = handleSensitiveString(SensitiveType.IDCARD, valStr);
                                declaredField.set(obj, result);
                            } else {
                                String result = handleSensitiveString(SensitiveType.OTHERCARD, valStr);
                                declaredField.set(obj, result);
                            }
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String handleSensitiveString(SensitiveType type, String val) {
        try {
            String result = "";
            switch (type) {
                case NAME:
                    result = SensitiveUtils.maskName(val);
                    break;
                case MOBILE:
                    result = SensitiveUtils.maskMobile(val);
                    break;
                case EMAIL:
                    result = SensitiveUtils.maskEmail(val);
                    break;
                case IDCARD:
                    result = SensitiveUtils.maskIdCard(val);
                    break;
                case BIRTHDAY:
                    result = SensitiveUtils.maskBirthday(val);
                    break;
                case PASSWORD:
                    result = SensitiveUtils.maskPassword(val);
                    break;
                case BANKCARD:
                    result = SensitiveUtils.maskBankCard(val);
                    break;
                case SALARY:
                    result = SensitiveUtils.maskSalary(val);
                    break;
                case OTHERCARD:
                    result = SensitiveUtils.maskOtherCard(val);
                    break;
                default:
                    result = val;
                    break;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return val;
        }
    }

}
