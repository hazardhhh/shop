package com.hhh.shop.admin.modules.recommend.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 首页推荐表
 * 
 * @author hazard
 * @email hazard@gmail.com
 * @date 2022-05-25 15:25:41
 */
@Data
@TableName("t_recommend")
public class RecommendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String des;
	/**
	 * 
	 */
	private String checkUrl;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer inde;
	/**
	 * 
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 
	 */
	private String pngUrl;
	/**
	 *
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")
	private Date showDate;

}
