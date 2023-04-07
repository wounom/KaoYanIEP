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
 * @Date 2023-04-07 
 */

@ApiModel(value = "block")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block  implements Serializable {


	/**
	 * 版块id
	 */
	@ApiModelProperty(value = "版块id")
	private Long blockId;

	/**
	 * 版块名字
	 */
	@ApiModelProperty(value = "版块名字")
	private String blockName;

	/**
	 * 版块状态（0正常（默认）1已删除）
	 */
	@ApiModelProperty(value = "版块状态（0正常（默认）1已删除）")
	private Integer status;

	/**
	 * 版块创建时间
	 */
	@ApiModelProperty(value = "版块创建时间")
	private Date blockTime;

}
