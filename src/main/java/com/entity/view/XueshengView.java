package com.entity.view;

import com.entity.XueshengEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 学生
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("xuesheng")
public class XueshengView extends XueshengEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 性别的值
		*/
		private String sexValue;



		//级联表 clazz
			/**
			* 班级名称
			*/
			private String clazzName;
			/**
			* 班级位置
			*/
			private String clazzAddress;

	public XueshengView() {

	}

	public XueshengView(XueshengEntity xueshengEntity) {
		try {
			BeanUtils.copyProperties(this, xueshengEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 性别的值
			*/
			public String getSexValue() {
				return sexValue;
			}
			/**
			* 设置： 性别的值
			*/
			public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
			}






				//级联表的get和set clazz
					/**
					* 获取： 班级名称
					*/
					public String getClazzName() {
						return clazzName;
					}
					/**
					* 设置： 班级名称
					*/
					public void setClazzName(String clazzName) {
						this.clazzName = clazzName;
					}
					/**
					* 获取： 班级位置
					*/
					public String getClazzAddress() {
						return clazzAddress;
					}
					/**
					* 设置： 班级位置
					*/
					public void setClazzAddress(String clazzAddress) {
						this.clazzAddress = clazzAddress;
					}


















}