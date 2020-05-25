package cn.itcast.entity;

import java.util.List;

public class ResultInfo {


	private String distance;
	private String speed;	//平均速度
	private String speed2;
	private List<TaskInfo> list;
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getSpeed2() {
		return speed2;
	}
	public void setSpeed2(String speed2) {
		this.speed2 = speed2;
	}
	public List<TaskInfo> getList() {
		return list;
	}
	public void setList(List<TaskInfo> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ResultInfo [distance=" + distance + ", speed=" + speed + ", speed2=" + speed2 + ", list=" + list + "]";
	}
	
}
