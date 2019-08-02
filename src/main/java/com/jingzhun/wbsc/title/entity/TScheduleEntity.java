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
 * @Description: t_schedule
 * @author onlineGenerator
 * @date 2019-07-24 11:43:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_schedule", schema = "")
@SuppressWarnings("serial")
public class TScheduleEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**用户*/
	@Excel(name="用户",width=15)
	private java.lang.String userKey;
	/**网站名称*/
	@Excel(name="网站名称",width=15,dictTable ="t_web",dicCode ="id",dicText ="web_name")
	private java.lang.Integer webId;
	/**param*/
	@Excel(name="param",width=15)
	private java.lang.String param;
	/**任务调度标识*/
	@Excel(name="任务调度标识",width=15)
	private java.lang.String scheduleJobname;
	/**任务调度表达式*/
	@Excel(name="任务调度表达式",width=15)
	private java.lang.String scheduleCron;

	/**用户名称*/
	@Excel(name="用户名称",width=15)
	private java.lang.String webUsername;

	/**注册时间*/
	@Excel(name="注册时间",width=15,format = "yyyy-MM-dd")
	private Date createDate;

	@Column(name ="CREATE_DATE",nullable=true)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户
	 */

	@Column(name ="USER_KEY",nullable=true,length=20)
	public java.lang.String getUserKey(){
		return this.userKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户
	 */
	public void setUserKey(java.lang.String userKey){
		this.userKey = userKey;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  param
	 */

	@Column(name ="PARAM",nullable=true,length=255)
	public java.lang.String getParam(){
		return this.param;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  param
	 */
	public void setParam(java.lang.String param){
		this.param = param;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务调度标识
	 */

	@Column(name ="SCHEDULE_JOBNAME",nullable=true,length=40)
	public java.lang.String getScheduleJobname(){
		return this.scheduleJobname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务调度标识
	 */
	public void setScheduleJobname(java.lang.String scheduleJobname){
		this.scheduleJobname = scheduleJobname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务调度表达式
	 */

	@Column(name ="SCHEDULE_CRON",nullable=true,length=30)
	public java.lang.String getScheduleCron(){
		return this.scheduleCron;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务调度表达式
	 */
	public void setScheduleCron(java.lang.String scheduleCron){
		this.scheduleCron = scheduleCron;
	}

	@Column(name ="WEB_USERNAME",nullable=true,length=20)
	public String getWebUsername() {
		return webUsername;
	}


	public void setWebUsername(String webUsername) {
		this.webUsername = webUsername;
	}

}