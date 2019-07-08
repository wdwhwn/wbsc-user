package com.jingzhun.wbsc.category.entity;

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
 * @Description: t_category
 * @author onlineGenerator
 * @date 2019-06-12 11:29:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_category", schema = "")
@SuppressWarnings("serial")
public class TCategoryEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**父类*/
	@Excel(name="父类",width=15)
	private java.lang.String pid;
	/**类别名称*/
	@Excel(name="类别名称",width=15)
	private java.lang.String categoryName;
	/**网站名称*/
	@Excel(name="网站名称",width=15,dictTable ="t_web",dicCode ="id",dicText ="web_name")
	private java.lang.Integer webId;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父类
	 */

	@Column(name ="PID",nullable=true,length=7)
	public java.lang.String getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父类
	 */
	public void setPid(java.lang.String pid){
		this.pid = pid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类别名称
	 */

	@Column(name ="CATEGORY_NAME",nullable=true,length=20)
	public java.lang.String getCategoryName(){
		return this.categoryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类别名称
	 */
	public void setCategoryName(java.lang.String categoryName){
		this.categoryName = categoryName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  网站名称
	 */

	@Column(name ="WEB_ID",nullable=true,length=10)
	public java.lang.Integer getWebId(){
		return this.webId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  网站名称
	 */
	public void setWebId(java.lang.Integer webId){
		this.webId = webId;
	}
}