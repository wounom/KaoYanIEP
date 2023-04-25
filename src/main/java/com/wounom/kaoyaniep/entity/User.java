package com.wounom.kaoyaniep.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description  
 * @Author  zhf
 * @Date 2023-04-25 
 */

@ApiModel(value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  implements Serializable {


	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long id;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 用户密码
	 */
	@ApiModelProperty(value = "用户密码")
	private String password;

	/**
	 * 用户电子邮箱
	 */
	@ApiModelProperty(value = "用户电子邮箱")
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
	 * 激活状态(默认为0，未激活；1已激活)
	 */
	@ApiModelProperty(value = "激活状态(默认为0，未激活；1已激活)")
	private Integer isValid;

	/**
	 * 激活时间
	 */
	@ApiModelProperty(value = "激活时间")
	private LocalDateTime activeTime;

	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private String sex;

	/**
	 * 生日
	 */
	@ApiModelProperty(value = "生日")
	private Date birthday;

	/**
	 * 个性签名
	 */
	@ApiModelProperty(value = "个性签名")
	private String signature;

	/**
	 * 目标院校
	 */
	@ApiModelProperty(value = "目标院校")
	private String targetUnversity;

	/**
	 * 头像（头像名字）
	 */
	@ApiModelProperty(value = "头像（头像名字）")
	private String image;

	/**
	 * 头像文件路径
	 */
	@ApiModelProperty(value = "头像文件路径")
	private String imagepath;

}
