package com.jingzhun.wbsc.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: t_user_web
 * @author onlineGenerator
 * @date 2019-06-17 14:09:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_user_web", schema = "")
@SuppressWarnings("serial")
public class TUserWebEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**userId*/
	@Excel(name="userId",width=15)
	private java.lang.Integer userId;
	/**webId*/
	@Excel(name="webId",width=15)
	private java.lang.Integer webId;
	/**username*/
	@Excel(name="username",width=15)
	private java.lang.String username;
	/**password*/
	@Excel(name="password",width=15)
	private java.lang.String password;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  userId
	 */

	@Column(name ="USER_ID",nullable=true,length=10)
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  userId
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  webId
	 */

	@Column(name ="WEB_ID",nullable=true,length=10)
	public java.lang.Integer getWebId(){
		return this.webId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  webId
	 */
	public void setWebId(java.lang.Integer webId){
		this.webId = webId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  username
	 */

	@Column(name ="USERNAME",nullable=true,length=50)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  username
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  password
	 */

	@Column(name ="PASSWORD",nullable=true,length=50)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  password
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
}