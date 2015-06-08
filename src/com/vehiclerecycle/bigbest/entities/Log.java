package com.vehiclerecycle.bigbest.entities;

import java.util.Date;

public class Log {

	private Integer id;
	private Date operateTime;
	private String operateMsg;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateMsg() {
		return operateMsg;
	}
	public void setOperateMsg(String operateMsg) {
		this.operateMsg = operateMsg;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", operateTime="
				+ operateTime +", operateMsg="
				+ operateMsg + "]";
	}
	
}
