package cn.itcast.entity;

import java.util.List;

public class ResultInfo {


	private Double distance;
	private Double speed;	//平均速度
	private List<TaskInfo> list;
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public List<TaskInfo> getList() {
		return list;
	}
	public void setList(List<TaskInfo> list) {
		this.list = list;
	}
	
	
}
