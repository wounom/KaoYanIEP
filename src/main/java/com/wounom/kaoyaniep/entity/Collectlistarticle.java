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

@ApiModel(value = "collectlistarticle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collectlistarticle  implements Serializable {


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
	 * 文章名
	 */
	@ApiModelProperty(value = "文章名")
	private String aName;

}
