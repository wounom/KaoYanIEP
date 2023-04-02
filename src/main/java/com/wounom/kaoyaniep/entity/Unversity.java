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
 * @Date 2023-04-02 
 */

@ApiModel(value = "unversity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unversity  implements Serializable {


	private Long unversityId;

	private String unversityName;

	private String unversityIntroduce;

	/**
	 * 院校状态默认0正常1为已删除
	 */
	@ApiModelProperty(value = "院校状态默认0正常1为已删除")
	private Long unversityStatus;

}
