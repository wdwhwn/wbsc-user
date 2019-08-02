package com.jingzhun.wbsc.content.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: t_content
 * @author onlineGenerator
 * @date 2019-06-11 11:43:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_content", schema = "")
@SuppressWarnings("serial")
public class TContentEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**类别名称*/
	@Excel(name="类别名称",width=15,dictTable ="t_category",dicCode ="id",dicText ="category_name")
	private Integer categoryId;
	/**素材内容*/
	@Excel(name="素材内容",width=15)
	private String content;
	/**用户名称*/
	@Excel(name="用户名称",width=15)
	private Integer userKey;

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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  类别名称
	 */

	@Column(name ="CATEGORY_ID",nullable=true,length=10)
	public Integer getCategoryId(){
		return this.categoryId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  类别名称
	 */
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  素材内容
	 */

	@Column(name ="CONTENT",nullable=true,length=9999)
	public String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  素材内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户名称
	 */

	@Column(name ="USER_KEY",nullable=true,length=10)
	public Integer getUserKey(){
		return this.userKey;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户名称
	 */
	public void setUserKey(Integer userKey){
		this.userKey = userKey;
	}
}