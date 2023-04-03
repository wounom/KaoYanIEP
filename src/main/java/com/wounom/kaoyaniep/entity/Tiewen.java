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
 * @Date 2023-04-03 
 */

@ApiModel(value = "tiewen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tiewen  implements Serializable {


	/**
	 * 贴文id
	 */
	@ApiModelProperty(value = "贴文id")
	private int tiewenId;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private int userId;

	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	private String title;

	/**
	 * 贴文状态：0.待审核（默认）1.已过审2.被删除
	 */
	@ApiModelProperty(value = "贴文状态：0.待审核（默认）1.已过审2.被删除")
	private Integer status;

	/**
	 * 贴文创建时间
	 */
	@ApiModelProperty(value = "贴文创建时间")
	private Date create_Time;

	/**
	 * 评论计数
	 */
	@ApiModelProperty(value = "评论计数")
	private Long comment_Count;

	/**
	 * 贴文内容
	 */
	@ApiModelProperty(value = "贴文内容")
	private String content;

	/**
	 * 版块id
	 */
	@ApiModelProperty(value = "版块id")
	private Long blockId;

}
