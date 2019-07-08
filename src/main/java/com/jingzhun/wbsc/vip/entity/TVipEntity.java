package com.jingzhun.wbsc.vip.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.math.BigDecimal;

/**   
 * @Title: Entity
 * @Description: t_vip
 * @author onlineGenerator
 * @date 2019-06-11 11:44:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_vip", schema = "")
@SuppressWarnings("serial")
public class TVipEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**礼包名称*/
	@Excel(name="礼包名称",width=15)
	private String vipName;
	/**礼包价格*/
	@Excel(name="礼包价格",width=15)
	private BigDecimal price;
	/**周期*/
	@Excel(name="周期",width=15)
	private Integer cycle;

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
	 *@return: java.lang.String  礼包名称
	 */

	@Column(name ="VIP_NAME",nullable=true,length=50)
	public String getVipName(){
		return this.vipName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  礼包名称
	 */
	public void setVipName(String vipName){
		this.vipName = vipName;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  礼包价格
	 */

	@Column(name ="PRICE",nullable=true,length=10)
	public BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  礼包价格
	 */
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  周期
	 */

	@Column(name ="CYCLE",nullable=true,length=10)
	public Integer getCycle(){
		return this.cycle;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  周期
	 */
	public void setCycle(Integer cycle){
		this.cycle = cycle;
	}
}