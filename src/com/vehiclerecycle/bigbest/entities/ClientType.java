package com.vehiclerecycle.bigbest.entities;

/**
 * 客户性质
 * @author LJJ
 *
 */
public class ClientType {

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 性质（临时客户，短期合作，长期合作）
	 */
	private String typeName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Override
	public boolean equals(java.lang.Object obj) {
		return this.id==((ClientType)obj).id;
	}
	@Override
	public String toString() {
		return "ClientType [id=" + id + ", typeName=" + typeName + "]";
	}
	
}
