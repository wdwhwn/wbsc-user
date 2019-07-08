package com.jingzhun.wbsc.user.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: t_user
 * @author onlineGenerator
 * @date 2019-06-11 11:43:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_user", schema = "")
@SuppressWarnings("serial")
public class TUserEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**用户名称*/
	@Excel(name="用户名称",width=15)
	private String username;
	/**密码*/
	private String password;
	/**注册时间*/
	@Excel(name="注册时间",width=15,format = "yyyy-MM-dd")
	private Date register;
	/**购买会员时间*/
	@Excel(name="购买会员时间",width=15,format = "yyyy-MM-dd")
	private Date purchaseDate;
	/**会员终止时间*/
	@Excel(name="会员终止时间",width=15,format = "yyyy-MM-dd")
	private Date endDate;
	/**礼包名称*/
	@Excel(name="礼包名称",width=15,dictTable ="t_vip",dicCode ="id",dicText ="vip_name")
	private Integer vipId;

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
	 *@return: java.lang.String  用户名称
	 */

	@Column(name ="USERNAME",nullable=true,length=30)
	public String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名称
	 */
	public void setUsername(String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */

	@Column(name ="PASSWORD",nullable=true,length=100)
	public String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setPassword(String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  注册时间
	 */

	@Column(name ="REGISTER",nullable=true)
	public Date getRegister(){
		return this.register;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  注册时间
	 */
	public void setRegister(Date register){
		this.register = register;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  购买会员时间
	 */

	@Column(name ="PURCHASE_DATE",nullable=true)
	public Date getPurchaseDate(){
		return this.purchaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  购买会员时间
	 */
	public void setPurchaseDate(Date purchaseDate){
		this.purchaseDate = purchaseDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  会员终止时间
	 */

	@Column(name ="END_DATE",nullable=true)
	public Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  会员终止时间
	 */
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  礼包名称
	 */

	@Column(name ="VIP_ID",nullable=true,length=10)
	public Integer getVipId(){
		return this.vipId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  礼包名称
	 */
	public void setVipId(Integer vipId){
		this.vipId = vipId;
	}
}