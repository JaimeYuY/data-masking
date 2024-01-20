package com.ocean.angel.tool.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.ocean.angel.tool.annotation.MaskField;
import com.ocean.angel.tool.constatnt.SensitiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Jaime.yu
 * @since 2024-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @MaskField(SensitiveType.PASSWORD)
    private String pwd;

    /**
     * 手机号
     */
    @MaskField(SensitiveType.MOBILE)
    private String mobile;

    /**
     * 身份证号
     */
    @MaskField(SensitiveType.IDCARD)
    private String idCard;

    /**
     * 昵称
     */
    @MaskField(SensitiveType.NAME)
    private String nick;

    /**
     * 创建时间
     */
    private Date createTime;


}
