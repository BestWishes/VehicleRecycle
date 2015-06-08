package com.vehiclerecycle.bigbest.entities;

/**
 * 客户
 * @author LJJ
 *
 */
public class Client {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 客户性别
	 */
	private String sex;
	/**
	 * 身份证
	 */
	private String idCard;
	/**
	 * 客户现在居住地址
	 */
	private String address;
	/**
	 * 客户电话
	 */
	private String tel;
	/**
	 * 客户邮箱
	 */
	private String email;
	/**
	 * 客户职业
	 */
	private String job;
	/**
	 * 客户公司
	 */
	private String company;
	/**
	 * 客户性质String c=clientType==null?"无":clientType.getTypeName();
	 */
	private ClientType clientType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	@Override
	public String toString() {
		String c=clientType==null?"无":clientType.getTypeName();
		return "Client [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", idCard=" + idCard + ", address=" + address + ", tel="
				+ tel + ", email=" + email + ", job=" + job + ", company="
				+ company + ", clientType=" + c + "]";
	}
	
	
}
