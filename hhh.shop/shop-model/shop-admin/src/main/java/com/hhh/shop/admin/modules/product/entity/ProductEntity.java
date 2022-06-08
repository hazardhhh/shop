package com.hhh.shop.admin.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 商品管理
 * 
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-30 11:01:29
 */
@Data
@TableName("t_product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 父类型
	 */
	private Integer type1;
	/**
	 * 子类型
	 */
	private Integer type2;

	@TableField(exist = false)
//	private List<String> pictureList;
	private List<ProductPicEntity> productPicList;
}
