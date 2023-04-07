package com.game.zhujuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**   
 * @version: V1.0
 * @author: xuchunpeng
 * 
 */
@Data
@ApiModel
public class User extends Model<User> {

	private static final long serialVersionUID = 1637680478986L;
	
	@TableId(value = "id", type = IdType.INPUT)
	@ApiModelProperty(name = "id" , value = "")
	private String id;
    
	@ApiModelProperty(name = "username" , value = "")
	private String username;
    
	@ApiModelProperty(name = "nickname" , value = "")
	private String nickname;
    
	@ApiModelProperty(name = "avatar" , value = "")
	private String avatar;
    
	@ApiModelProperty(name = "role" , value = "")
	private String role;
    
	@ApiModelProperty(name = "password" , value = "")
	private String password;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "")
	private Date createTime;
    

}
