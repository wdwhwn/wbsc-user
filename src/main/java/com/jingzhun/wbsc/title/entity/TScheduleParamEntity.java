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
 * @Description: t_schedule_param
 * @author onlineGenerator
 * @date 2019-07-13 12:06:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_schedule_param", schema = "")
@SuppressWarnings("serial")
public class TScheduleParamEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**用户*/
	@Excel(name="用户",width=15,dictTable ="t_user",dicCode ="id",dicText ="username")
	private java.lang.Integer userid;
	/**参数*/
	@Excel(name="参数",width=15)
	private java.lang.String params;
	/**图片id*/
	@Excel(name="图片id",width=15)
	private java.lang.String picsid;
	/**秒数*/
	@Excel(name="秒数",width=15)
	private java.lang.Integer second;
	/**分数*/
	@Excel(name="分数",width=15)
	private java.lang.Integer minute;
	/**小时*/
	@Excel(name="小时",width=15)
	private java.lang.Integer hour;
	/**开始天数*/
	@Excel(name="开始天数",width=15)
	private java.lang.Integer daybegin;
	/**结束天数*/
	@Excel(name="结束天数",width=15)
	private java.lang.Integer dayend;
	/**月份*/
	@Excel(name="月份",width=15)
	private java.lang.Integer month;
	/**年份*/
	@Excel(name="年份",width=15)
	private java.lang.Integer year;
	/**type*/
	@Excel(name="type",width=15)
	private java.lang.Integer type;

	/**type*/
	@Excel(name="timetaskid",width=15)
	private java.lang.String timetaskid;

	@Column(name ="TIMETASKID",nullable=true,length=255)
	public String getTimetaskid() {
		return timetaskid;
	}

	public void setTimetaskid(String timetaskid) {
		this.timetaskid = timetaskid;
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
	 *@return: java.lang.Integer  用户
	 */

	@Column(name ="USERID",nullable=true,length=10)
	public java.lang.Integer getUserid(){
		return this.userid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户
	 */
	public void setUserid(java.lang.Integer userid){
		this.userid = userid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数
	 */

	@Column(name ="PARAMS",nullable=true,length=255)
	public java.lang.String getParams(){
		return this.params;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数
	 */
	public void setParams(java.lang.String params){
		this.params = params;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片id
	 */

	@Column(name ="PICSID",nullable=true,length=255)
	public java.lang.String getPicsid(){
		return this.picsid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片id
	 */
	public void setPicsid(java.lang.String picsid){
		this.picsid = picsid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  秒数
	 */

	@Column(name ="SECOND",nullable=true,length=10)
	public java.lang.Integer getSecond(){
		return this.second;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  秒数
	 */
	public void setSecond(java.lang.Integer second){
		this.second = second;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分数
	 */

	@Column(name ="MINUTE",nullable=true,length=10)
	public java.lang.Integer getMinute(){
		return this.minute;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分数
	 */
	public void setMinute(java.lang.Integer minute){
		this.minute = minute;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  小时
	 */

	@Column(name ="HOUR",nullable=true,length=10)
	public java.lang.Integer getHour(){
		return this.hour;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  小时
	 */
	public void setHour(java.lang.Integer hour){
		this.hour = hour;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  开始天数
	 */

	@Column(name ="DAYBEGIN",nullable=true,length=10)
	public java.lang.Integer getDaybegin(){
		return this.daybegin;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  开始天数
	 */
	public void setDaybegin(java.lang.Integer daybegin){
		this.daybegin = daybegin;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  结束天数
	 */

	@Column(name ="DAYEND",nullable=true,length=10)
	public java.lang.Integer getDayend(){
		return this.dayend;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  结束天数
	 */
	public void setDayend(java.lang.Integer dayend){
		this.dayend = dayend;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  月份
	 */

	@Column(name ="MONTH",nullable=true,length=10)
	public java.lang.Integer getMonth(){
		return this.month;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  月份
	 */
	public void setMonth(java.lang.Integer month){
		this.month = month;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  年份
	 */

	@Column(name ="YEAR",nullable=true,length=10)
	public java.lang.Integer getYear(){
		return this.year;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  年份
	 */
	public void setYear(java.lang.Integer year){
		this.year = year;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  type
	 */

	@Column(name ="TYPE",nullable=true,length=10)
	public java.lang.Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  type
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
}