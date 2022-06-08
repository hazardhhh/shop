package com.hhh.shop.admin.modules.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 商品类型
 * 
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-29 01:32:46
 */
@Data
@TableName("t_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父id
	 */
	private Integer pid;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 顺序
	 */
	private Integer orderNum;

	// 用来保存一级类型的子类型
	@TableField(exist = false) // 表中不存在这个字段
	private List<CategoryEntity> children;
}
