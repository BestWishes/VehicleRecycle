package com.vehiclerecycle.bigbest.entities;

import java.util.Date;

/**
 * 用户类
 * 
 * @author LJJ
 *
 */
public class Employee {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String employeeName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 密码
	 */
	private String password;
	/**
	 * 职位
	 */
	private Position position;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 身份证
	 */
	private String idCard;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		String p = position == null ? "null" : position.getPositionName();

		return "Employee [id=" + id + ", employeeName=" + employeeName
				+ ", sex=" + sex + ", password=" + password + ", position=" + p
				+ ", birthday=" + birthday + ", email=" + email + ", tel="
				+ tel + ", idCard=" + idCard + "]";
	}

}
