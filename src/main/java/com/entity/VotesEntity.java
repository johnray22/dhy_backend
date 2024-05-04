package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author zhongruiR
 * @date 2024/4/30 11:04
 */
@TableName("votes")
@Data
public class VotesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public VotesEntity() {
    }

    public VotesEntity(Object obj) {
        try {
            BeanUtils.copyProperties(this, obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "type")
    private String type;

    @TableField(value = "title")
    private String title;

    @TableField(value = "option1")
    private String option1;

    @TableField(value = "option1_num")
    private int option1Num;

    @TableField(value = "option2")
    private String option2;

    @TableField(value = "option2_num")
    private int option2Num;

    @TableField(value = "option3")
    private String option3;

    @TableField(value = "option3_num")
    private int option3Num;

    @TableField(value = "option4")
    private String option4;

    @TableField(value = "option4_num")
    private int option4Num;

    @TableField(value = "username")
    private String username;

    @TableField(value = "timestamp")
    private Date timestamp;

    // Getters and setters for all fields
    // ...省略了getter和setter方法的实现以节省空间...

}