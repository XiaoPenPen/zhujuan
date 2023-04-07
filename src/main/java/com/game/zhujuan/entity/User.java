package com.game.zhujuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**   
 * @version: V1.0
 * @author: xuchunpeng
 * 
 */
@Entity
@Data
@ApiModel
@Table(name = "user",
		indexes = {
				@Index(name = "index_username", columnList = "username")
		}
)
public class User extends Model<User> {

	private static final long serialVersionUID = 1637680478986L;

	@Id
	@TableId(value = "id", type = IdType.INPUT)
	@Column(columnDefinition = "varchar(32) COMMENT 'id'")
	@ApiModelProperty(name = "id" , value = "")
	private String id;

	@Column(columnDefinition = "varchar(20) COMMENT '账号'")
	@ApiModelProperty(name = "username" , value = "")
	private String username;

	@Column(columnDefinition = "varchar(20) COMMENT '昵称'")
	@ApiModelProperty(name = "nickname" , value = "")
	private String nickname;

	@Column(columnDefinition = "varchar(50) COMMENT '头像'")
	@ApiModelProperty(name = "avatar" , value = "")
	private String avatar;

	@Column(columnDefinition = "varchar(50) COMMENT '权限'")
	@ApiModelProperty(name = "role" , value = "")
	private String role;

	@Column(columnDefinition = "varchar(255) COMMENT '密码'")
	@ApiModelProperty(name = "password" , value = "")
	private String password;

	@Column(columnDefinition = "datetime COMMENT '创建时间'")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "")
	private Date createTime;
    

}
