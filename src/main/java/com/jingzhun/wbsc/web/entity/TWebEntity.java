package com.jingzhun.wbsc.web.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: t_web
 * @author onlineGenerator
 * @date 2019-06-11 11:42:11
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

	/**登陆参数*/
	@Excel(name="登陆参数",width=15)
	private String paramLogin;/**网站url*/
	@Excel(name="图片上传参数",width=15)
	private String paramImgupload;/**网站url*/
	@Excel(name="信息推送参数",width=15)
	private String paramXxts;

	@Excel(name="网站标识",width=15)
	private String identification;

	@Excel(name="网站图片上传对应的name",width=15)
	private String imgUploadName;

	@Column(name ="IMG_UPLOAD_NAME",nullable=true,length=30)
	public String getImgUploadName() {
		return imgUploadName;
	}
	public void setImgUploadName(String imgUploadName) {
		this.imgUploadName = imgUploadName;
	}

	/**网站url*/
	@Excel(name="使用工具类方法",width=15)
	private String method;

	@Column(name ="PARAM_LOGIN",nullable=true,length=30)
	public String getParamLogin() {
		return paramLogin;
	}
	public void setParamLogin(String paramLogin) {
		this.paramLogin = paramLogin;
	}

	@Column(name ="PARAM_IMGUPLOAD",nullable=true,length=30)
	public String getParamImgupload() {
		return paramImgupload;
	}
	public void setParamImgupload(String paramImgupload) {
		this.paramImgupload = paramImgupload;
	}

	@Column(name ="PARAM_XXTS",nullable=true,length=30)
	public String getParamXxts() {
		return paramXxts;
	}
	public void setParamXxts(String paramXxts) {
		this.paramXxts = paramXxts;
	}

	@Column(name ="IDENTIFICATION",nullable=true,length=30)
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}


	@Column(name ="METHOD",nullable=true,length=30)
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}


	/**网站图片上传的url*/
	@Excel(name="网站图片上传的url",width=15)
	private java.lang.String uploadUrl;

	@Column(name ="UPLOAD_URL",nullable=true,length=30)
	public String getUploadUrl() {
		return uploadUrl;
	}
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	/**网站信息推送的url*/
	@Excel(name="网站信息推送的url",width=15)
	private java.lang.String xxtsUrl;

	@Column(name ="XXTS_URL",nullable=true,length=30)
	public String getXxtsUrl() {
		return uploadUrl;
	}
	public void setXxtsUrl(String xxtsUrl) {
		this.xxtsUrl = xxtsUrl;
	}


	/**网站推送结果查询的url*/
	@Excel(name="网站推送结果查询的url",width=15)
	private java.lang.String resultlistUrl;

	@Column(name ="RESULTLIST_URL",nullable=true,length=30)
	public String getResultlistUrl() {
		return resultlistUrl;
	}
	public void setResultlistUrl(String resultlistUrl) {
		this.resultlistUrl = resultlistUrl;
	}

	/**网站推送结果参数*/
	@Excel(name="网站推送结果参数",width=15)
	private java.lang.String paramResultlist;

	@Column(name ="PARAM_RESULTLIST",nullable=true,length=30)
	public String getParamResultlist() {
		return paramResultlist;
	}
	public void setParamResultlist(String paramResultlist) {
		this.paramResultlist = paramResultlist;
	}
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

	@Override
	public String toString() {
		return "TWebEntity{" +
				"id=" + id +
				", webName='" + webName + '\'' +
				", url='" + url + '\'' +
				", paramLogin='" + paramLogin + '\'' +
				", paramImgupload='" + paramImgupload + '\'' +
				", paramXxts='" + paramXxts + '\'' +
				", identification='" + identification + '\'' +
				", imgUploadName='" + imgUploadName + '\'' +
				", method='" + method + '\'' +
				'}';
	}
}