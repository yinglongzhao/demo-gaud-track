package cn.itcast.entity;

import java.sql.Timestamp;

public class TaskInfo {

	private Double jingdu;
	private Double weidu;

	private Timestamp time;

	public Double getJingdu() {
		return jingdu;
	}

	public void setJingdu(String jingdu) {
		this.jingdu = Double.valueOf(jingdu);
	}

	public Double getWeidu() {
		return weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = Double.valueOf(weidu);
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TaskInfo [jingdu=" + jingdu + ", weidu=" + weidu + ", time=" + time + "]";
	}

	public TaskInfo(String jingdu, String weidu, Timestamp time) {
		super();
		this.jingdu = Double.valueOf(jingdu);
		this.weidu = Double.valueOf(weidu);
		this.time = time;
	}

}
