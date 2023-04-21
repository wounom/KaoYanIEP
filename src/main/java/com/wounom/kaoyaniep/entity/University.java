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
 * @Date 2023-04-21 
 */

@ApiModel(value = "university")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class University  implements Serializable {


	/**
	 * 院校id
	 */
	@ApiModelProperty(value = "院校id")
	private Long universityId;

	/**
	 * 院校名字
	 */
	@ApiModelProperty(value = "院校名字")
	private String universityName;

	/**
	 * 院校简介
	 */
	@ApiModelProperty(value = "院校简介")
	private String universityIntroduce;

	/**
	 * 院校地区
	 */
	@ApiModelProperty(value = "院校地区")
	private String universityDistrict;

	/**
	 * 院校隶属于哪个上级机构
	 */
	@ApiModelProperty(value = "院校隶属于哪个上级机构")
	private String universityHigherup;

	/**
	 * 院校是否双一流(默认0不是，1是)
	 */
	@ApiModelProperty(value = "院校是否双一流(默认0不是，1是)")
	private Integer ifDouble;

	/**
	 * 院校是否研究生院(默认0不是，1是)
	 */
	@ApiModelProperty(value = "院校是否研究生院(默认0不是，1是)")
	private Integer ifGraduate;

	/**
	 * 院校是否自划线(默认0不是，1是)
	 */
	@ApiModelProperty(value = "院校是否自划线(默认0不是，1是)")
	private Integer ifIndependent;

	/**
	 * 院校图片路径
	 */
	@ApiModelProperty(value = "院校图片路径")
	private String image;

	/**
	 * 学校官网
	 */
	@ApiModelProperty(value = "学校官网")
	private String officialnet;

	/**
	 * 院校具体地址
	 */
	@ApiModelProperty(value = "院校具体地址")
	private String address;

	/**
	 * 院校邮编
	 */
	@ApiModelProperty(value = "院校邮编")
	private String post;

	/**
	 * 院校电话
	 */
	@ApiModelProperty(value = "院校电话")
	private String phone;

}
