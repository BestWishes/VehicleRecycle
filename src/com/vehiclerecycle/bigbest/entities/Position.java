package com.vehiclerecycle.bigbest.entities;

/**
 * 职位
 * 
 * @author LJJ
 *
 */
public class Position {

	/**
	 * 职位id
	 */
	private Integer id;
	/**
	 * 职位名称
	 */
	private String positionName;
	/**
	 * 职位所属部门
	 */
	private Department department;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		//String d=department==(null)?"null":department.getDeptName();
		return "Position [id=" + id + ", positionName=" + positionName
				+ ", department=" + department + "]";
	}
	
	
}
