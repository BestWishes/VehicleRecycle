package com.vehiclerecycle.bigbest.entities;

/**
 * 部门类
 * 
 * @author LJJ
 *
 */
public class Department {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 部门名称
	 */
	private String deptName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName
				+  "]";
	}
		
}
