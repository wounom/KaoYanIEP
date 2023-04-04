package com.wounom.kaoyaniep.entity;

import cn.hutool.core.date.DateTime;
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
 * @Date 2023-04-04 
 */

@ApiModel(value = "firstpage_push")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstpagePush  implements Serializable {


	/**
	 * 首页贴文id
	 */
	@ApiModelProperty(value = "首页贴文id")
	private int first_Id;

	/**
	 * 首页贴文标题
	 */
	@ApiModelProperty(value = "首页贴文标题")
	private String title;

	/**
	 * 首页贴文发布时间
	 */
	@ApiModelProperty(value = "首页贴文发布时间")
	private Date create_Time;

	/**
	 * 首页贴文内容
	 */
	@ApiModelProperty(value = "首页贴文内容")
	private String url;

	/**
	 * 图片
	 */
	@ApiModelProperty(value = "图片")
	private String image;
	/**
	 * 图片路径
	 */
	@ApiModelProperty(value = "图片路径")
	private String imagePath;

}
