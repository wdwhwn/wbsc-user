package com.jingzhun.wbsc.user.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: t_article
 * @author onlineGenerator
 * @date 2019-06-11 11:46:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_article", schema = "")
@SuppressWarnings("serial")
public class TArticleEntity implements java.io.Serializable {
	/**id*/
	private Integer id;
	/**标题*/
	@Excel(name="标题",width=15)
	private String title;
	/**关键词*/
	@Excel(name="关键词",width=15)
	private String keywords;
	/**参数*/
	@Excel(name="参数",width=15)
	private String parameter;
	/**文章*/
	@Excel(name="文章",width=15)
	private String article;
	/**生成时间*/
	@Excel(name="生成时间",width=15,format = "yyyy-MM-dd")
	private Date generationTime;
	/**用户名称*/
	@Excel(name="用户名称",width=15,dictTable ="t_user",dicCode ="id",dicText ="username")
	private Integer userId;
	/**任务开始时间*/
	@Excel(name="任务开始时间",width=15,format = "yyyy-MM-dd")
	private Date taskBegin;
	/**任务持续天数*/
	@Excel(name="任务持续天数",width=15)
	private Integer taskDay;
	/**任务终止时间*/
	@Excel(name="任务终止时间",width=15)
	private String taskEnd;
	/**网站名称*/
	@Excel(name="网站名称",width=15,dictTable ="t_web",dicCode ="id",dicText ="web_name")
	private Integer webId;

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
	 *@return: java.lang.String  标题
	 */

	@Column(name ="TITLE",nullable=true,length=50)
	public String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关键词
	 */

	@Column(name ="KEYWORDS",nullable=true,length=80)
	public String getKeywords(){
		return this.keywords;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关键词
	 */
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数
	 */

	@Column(name ="PARAMETER",nullable=true,length=255)
	public String getParameter(){
		return this.parameter;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数
	 */
	public void setParameter(String parameter){
		this.parameter = parameter;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文章
	 */

	@Column(name ="ARTICLE",nullable=true,length=9999)
	public String getArticle(){
		return this.article;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文章
	 */
	public void setArticle(String article){
		this.article = article;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生成时间
	 */

	@Column(name ="GENERATION_TIME",nullable=true)
	public Date getGenerationTime(){
		return this.generationTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生成时间
	 */
	public void setGenerationTime(Date generationTime){
		this.generationTime = generationTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户名称
	 */

	@Column(name ="USER_ID",nullable=true,length=10)
	public Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户名称
	 */
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  任务开始时间
	 */

	@Column(name ="TASK_BEGIN",nullable=true)
	public Date getTaskBegin(){
		return this.taskBegin;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  任务开始时间
	 */
	public void setTaskBegin(Date taskBegin){
		this.taskBegin = taskBegin;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  任务持续天数
	 */

	@Column(name ="TASK_DAY",nullable=true,length=10)
	public Integer getTaskDay(){
		return this.taskDay;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  任务持续天数
	 */
	public void setTaskDay(Integer taskDay){
		this.taskDay = taskDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务终止时间
	 */

	@Column(name ="TASK_END",nullable=true,length=255)
	public String getTaskEnd(){
		return this.taskEnd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务终止时间
	 */
	public void setTaskEnd(String taskEnd){
		this.taskEnd = taskEnd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  网站名称
	 */

	@Column(name ="WEB_ID",nullable=true,length=10)
	public Integer getWebId(){
		return this.webId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  网站名称
	 */
	public void setWebId(Integer webId){
		this.webId = webId;
	}
}