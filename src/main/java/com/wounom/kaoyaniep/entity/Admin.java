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
 * @Date 2023-04-02 
 */

@ApiModel(value = "admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin  implements Serializable {


	private Long id;

	private String username;

	private String password;

	private String email;

	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码")
	private String code;

	/**
	 * 密码加密
	 */
	@ApiModelProperty(value = "密码加密")
	private String salt;

	/**
	 * 激活状态
	 */
	@ApiModelProperty(value = "激活状态")
	private Integer is_Valid;

	/**
	 * 激活时间
	 */
	@ApiModelProperty(value = "激活时间")
	private Date active_Time;

}
