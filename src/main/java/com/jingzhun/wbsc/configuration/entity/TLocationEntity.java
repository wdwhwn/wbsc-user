package com.jingzhun.wbsc.configuration.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: t_location
 * @author onlineGenerator
 * @date 2019-06-11 11:45:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_location", schema = "")
@SuppressWarnings("serial")
public class TLocationEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**地区名称*/
	@Excel(name="地区名称",width=15)
	private String locationName;
	/**父类地区*/
	@Excel(name="父类地区",width=15,dictTable ="t_location",dicCode ="id",dicText ="location_name")
	private Integer pid;

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
	 *@return: java.lang.String  地区名称
	 */

	@Column(name ="LOCATION_NAME",nullable=true,length=50)
	public String getLocationName(){
		return this.locationName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区名称
	 */
	public void setLocationName(String locationName){
		this.locationName = locationName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  父类地区
	 */

	@Column(name ="PID",nullable=true,length=10)
	public Integer getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  父类地区
	 */
	public void setPid(Integer pid){
		this.pid = pid;
	}
}