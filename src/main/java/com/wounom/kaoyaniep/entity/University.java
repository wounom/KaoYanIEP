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
 * @Date 2023-04-06 
 */

@ApiModel(value = "university")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class University  implements Serializable {


	private Long universityId;

	private String universityName;

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
	 * 是否双一流(默认0不是，1是)
	 */
	@ApiModelProperty(value = "是否双一流(默认0不是，1是)")
	private Integer ifDouble;

	/**
	 * 是否研究生院(默认0不是，1是)
	 */
	@ApiModelProperty(value = "是否研究生院(默认0不是，1是)")
	private Integer ifGraduate;

	/**
	 * 是否自划线(默认0不是，1是)
	 */
	@ApiModelProperty(value = "是否自划线(默认0不是，1是)")
	private Integer ifIndependent;

	private String image;

}
