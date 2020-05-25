package cn.itcast.entity;

import java.util.List;

public class ResultInfo {

	
	private Integer speed;	//平均速度
	private List<TaskInfo> list;
	
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public List<TaskInfo> getList() {
		return list;
	}
	public void setList(List<TaskInfo> list) {
		this.list = list;
	}
	
	
}
