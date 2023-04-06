package com.wounom.kaoyaniep.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  zhf
 * @Date 2023-04-06 
 */

@ApiModel(value = "tiewen_official")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiewenOfficial  implements Serializable {


	/**
	 * 贴文id
	 */
	@ApiModelProperty(value = "贴文id")
	private Long tiewenId;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long adminId;

	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	private String title;

	/**
	 * 贴文状态：1.已过审(默认)2.被删除
	 */
	@ApiModelProperty(value = "贴文状态：1.已过审(默认)2.被删除")
	private Integer status;

	/**
	 * 贴文创建时间
	 */
	@ApiModelProperty(value = "贴文创建时间")
	private Date createTime;

	/**
	 * 评论计数
	 */
	@ApiModelProperty(value = "评论计数")
	private Long commentCount;

	/**
	 * 贴文内容
	 */
	@ApiModelProperty(value = "贴文内容")
	private String content;

	/**
	 * 版块名字
	 */
	@ApiModelProperty(value = "版块名字")
	private String blockName;

}
