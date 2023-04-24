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
 * @Date 2023-04-24 
 */

@ApiModel(value = "collectlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collectlist  implements Serializable {


	/**
	 * 收藏本身的标识
	 */
	@ApiModelProperty(value = "收藏本身的标识")
	private Long id;

	/**
	 * 用户自己的标识
	 */
	@ApiModelProperty(value = "用户自己的标识")
	private Long userId;

	/**
	 * 收藏时间
	 */
	@ApiModelProperty(value = "收藏时间")
	private Date collecttime;

	/**
	 * 收藏的类型（-1为文章，0为版块，1为贴文）
	 */
	@ApiModelProperty(value = "收藏的类型（-1为文章，0为版块，1为贴文）")
	private Integer target;

	/**
	 * 收藏的名字
	 */
	@ApiModelProperty(value = "收藏的名字")
	private String targetName;

	/**
	 * 目标标识符（id）可以重复
	 */
	@ApiModelProperty(value = "目标标识符（id）可以重复")
	private Long tid;

}
