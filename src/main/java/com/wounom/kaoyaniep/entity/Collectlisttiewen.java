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
 * @Date 2023-04-09 
 */

@ApiModel(value = "collectlisttiewen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collectlisttiewen  implements Serializable {


	/**
	 * 收藏对象标识
	 */
	@ApiModelProperty(value = "收藏对象标识")
	private Long id;

	/**
	 * 用户自己的标识
	 */
	@ApiModelProperty(value = "用户自己的标识")
	private String userEmail;

	/**
	 * 收藏时间
	 */
	@ApiModelProperty(value = "收藏时间")
	private Date collecttime;

	/**
	 * 贴文名
	 */
	@ApiModelProperty(value = "贴文名")
	private String tName;

}
