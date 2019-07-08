package com.jingzhun.wbsc.web.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: t_web
 * @author onlineGenerator
 * @date 2019-06-11 11:42:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_web", schema = "")
@SuppressWarnings("serial")
public class TWebEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**网站名称*/
	@Excel(name="网站名称",width=15)
	private String webName;
	/**网站url*/
	@Excel(name="网站url",width=15)
	private String url;

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=10)
	public Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网站名称
	 */

	@Column(name ="WEB_NAME",nullable=true,length=30)
	public String getWebName(){
		return this.webName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网站名称
	 */
	public void setWebName(String webName){
		this.webName = webName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网站url
	 */

	@Column(name ="URL",nullable=true,length=100)
	public String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网站url
	 */
	public void setUrl(String url){
		this.url = url;
	}
}