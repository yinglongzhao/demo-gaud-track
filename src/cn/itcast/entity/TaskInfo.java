package cn.itcast.entity;

public class TaskInfo {

	private String jingdu;
	private String weidu;

	private String time;

	public String getJingdu() {
		return jingdu;
	}

	public void setJingdu(String jingdu) {
		this.jingdu = jingdu;
	}

	public String getWeidu() {
		return weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TaskInfo [jingdu=" + jingdu + ", weidu=" + weidu + ", time=" + time + "]";
	}

	public TaskInfo(String jingdu, String weidu, String time) {
		super();
		this.jingdu = jingdu;
		this.weidu = weidu;
		this.time = time;
	}

}
