package com.vehiclerecycle.bigbest.entities;

/**
 * 角色
 * @author LJJ
 *
 */
public class Role {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 角色名
	 */
	private String roleName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
	
	
}
