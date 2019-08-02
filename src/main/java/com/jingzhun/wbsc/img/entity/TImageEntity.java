package com.jingzhun.wbsc.img.entity;

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
 * @Description: t_image
 * @author onlineGenerator
 * @date 2019-06-15 11:49:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_image", schema = "")
@SuppressWarnings("serial")
public class TImageEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**用户名称*/
	@Excel(name="用户key",width=15)
	private java.lang.String userKey;
	/**图片*/
	@Excel(name="图片",width=15)
	private java.lang.String url;
	/**网站*/
	@Excel(name="网站",width=15,dictTable ="t_web",dicCode ="id",dicText ="web_name")
	private java.lang.Integer webId;
	/**网站图片路径*/
	@Excel(name="网站图片路径",width=15)
	private java.lang.String webImgName;

	/**网站用户名*/
	@Excel(name="网站用户名",width=15)
	private java.lang.String webUsername;

	@Column(name ="WEB_USERNAME",nullable=true,length=255)
	public java.lang.String getWebUsername(){
		return this.webUsername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setWebUsername(java.lang.String webUsername){
		this.webUsername = webUsername;
	}


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
	 *@return: java.lang.Integer  用户名称
	 */

	@Column(name ="USER_KEY",nullable=true,length=10)
	public java.lang.String getUserKey(){
		return this.userKey;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户名称
	 */
	public void setUserKey(java.lang.String userKey){
		this.userKey = userKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */

	@Column(name ="URL",nullable=true,length=255)
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  网站
	 */

	@Column(name ="WEB_ID",nullable=true,length=10)
	public java.lang.Integer getWebId(){
		return this.webId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  网站
	 */
	public void setWebId(java.lang.Integer webId){
		this.webId = webId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网站图片路径
	 */

	@Column(name ="WEB_IMG_NAME",nullable=true,length=255)
	public java.lang.String getWebImgName(){
		return this.webImgName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网站图片路径
	 */
	public void setWebImgName(java.lang.String webImgName){
		this.webImgName = webImgName;
	}
}