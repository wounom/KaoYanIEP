package com.wounom.kaoyaniep.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description  
 * @Author  zhf
 * @Date 2023-04-14 
 */

@ApiModel(value = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment  implements Serializable {


	/**
	 * 评论本身id
	 */
	@ApiModelProperty(value = "评论本身id")
	private Long id;

	/**
	 * 发出评论的用户id
	 */
	@ApiModelProperty(value = "发出评论的用户id")
	private Long userId;

	/**
	 * 评论人的用户名
	 */
	@ApiModelProperty(value = "评论人的用户名")
	private String userName;

	/**
	 * 评论本身内容
	 */
	@ApiModelProperty(value = "评论本身内容")
	private String content;

	/**
	 * 评论的贴文id
	 */
	@ApiModelProperty(value = "评论的贴文id")
	private Long tiewenId;

	/**
	 * 评论时间
	 */
	@ApiModelProperty(value = "评论时间")
	private Date createTime;

	/**
	 * 父级评论id
	 */
	@ApiModelProperty(value = "父级评论id")
	private Long parentId;
	/**
	 * 根级id
	 */
	@ApiModelProperty(value = "根级id")
	private Long rootId;

	/**
	 * 用户头像
	 */
	@ApiModelProperty(value = "用户头像")
	private String userImg;
	/**
	 * 留言用户id
	 */
	@ApiModelProperty(value = "留言用户id")
	private Long targetUserId;
	/**
	 * 子评论
	 **/
	@ApiModelProperty(value = "子评论")
	private List<Comment> children;


}
