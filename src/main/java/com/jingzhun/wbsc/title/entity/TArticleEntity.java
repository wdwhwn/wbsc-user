package com.jingzhun.wbsc.title.entity;

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
 * @Description: t_article
 * @author onlineGenerator
 * @date 2019-07-13 12:07:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_article", schema = "")
@SuppressWarnings("serial")
public class TArticleEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**用户名*/
	@Excel(name="用户名",width=15,dictTable ="t_user",dicCode ="id",dicText ="username")
	private java.lang.Integer userid;
	/**网站名*/
	@Excel(name="网站名",width=15,dictTable ="t_web",dicCode ="id",dicText ="web_name")
	private java.lang.Integer webid;
	/**参数*/
	@Excel(name="参数",width=15)
	private java.lang.String parameter;
	/**创建时间*/
	@Excel(name="创建时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date generationTime;
	/**调度id*/
	@Excel(name="调度id",width=15)
	private java.lang.String scheduleid;

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
	 *@return: java.lang.Integer  用户名
	 */

	@Column(name ="USERID",nullable=true,length=10)
	public java.lang.Integer getUserid(){
		return this.userid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户名
	 */
	public void setUserid(java.lang.Integer userid){
		this.userid = userid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  网站名
	 */

	@Column(name ="WEBID",nullable=true,length=10)
	public java.lang.Integer getWebid(){
		return this.webid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  网站名
	 */
	public void setWebid(java.lang.Integer webid){
		this.webid = webid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数
	 */

	@Column(name ="PARAMETER",nullable=true,length=255)
	public java.lang.String getParameter(){
		return this.parameter;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数
	 */
	public void setParameter(java.lang.String parameter){
		this.parameter = parameter;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="GENERATION_TIME",nullable=true)
	public java.util.Date getGenerationTime(){
		return this.generationTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setGenerationTime(java.util.Date generationTime){
		this.generationTime = generationTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  调度id
	 */

	@Column(name ="SCHEDULEID",nullable=true,length=10)
	public java.lang.String getScheduleid(){
		return this.scheduleid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  调度id
	 */
	public void setScheduleid(java.lang.String scheduleid){
		this.scheduleid = scheduleid;
	}
}