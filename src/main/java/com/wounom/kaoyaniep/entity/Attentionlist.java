package com.wounom.kaoyaniep.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description  
 * @Author  zhf
 * @Date 2023-04-13 
 */

@ApiModel(value = "attentionlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attentionlist  implements Serializable {


	/**
	 * 关注列表id
	 */
	@ApiModelProperty(value = "关注列表id")
	private Long attentionid;

	/**
	 * 关注对象标识（用户）
	 */
	@ApiModelProperty(value = "关注对象标识（用户）")
	private Long targetid;

	/**
	 * 关注对象类型（0为用户（默认）1为院校）
	 */
	@ApiModelProperty(value = "关注对象类型（0为用户（默认）1为院校）")
	private Integer target;

	/**
	 * 名字
	 */
	@ApiModelProperty(value = "名字")
	private String name;

	/**
	 * 自己的标识
	 */
	@ApiModelProperty(value = "自己的标识")
	private Long id;

	/**
	 * 关注对象标识（院校）
	 */
	@ApiModelProperty(value = "关注对象标识（院校）")
	private Long schoolid;

}
