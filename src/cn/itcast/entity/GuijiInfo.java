package cn.itcast.entity;

import java.sql.Timestamp;

public class GuijiInfo {

	  private String devID;
	  private String cellID;
      private Timestamp time;
	  private String GPS_longitude;
	  private String GPS_latitude;
	  private Integer carNum;
	  private String state;
	  
	  
	  //devID,time,GPS_lontitude,GPS_latitude,
	public GuijiInfo(String devID, String cellID, Timestamp time, String GPS_longitude,
			String GPS_latitude,Integer carNum, String state) {
		super();
		this.devID = devID;
		this.cellID = cellID;
		this.time = time;
		this.GPS_longitude = GPS_longitude;
		this.GPS_latitude = GPS_latitude;
		this.carNum = carNum;
		this.state = state;
		
	}

	public String getCellID() {
		return cellID;
	}
	
	public void setCellID(String cellID) {
		this.cellID = cellID;
	}
	
	public Integer getCarNum() {
		return carNum;
	}
	
	public void setCarNum(Integer carNum) {
		this.carNum = carNum;
	}
	
	public String getdevID() {
		return devID;
	}

	public void setdevID(String devID) {
		this.devID = devID;
	}

	public Timestamp gettime() {
		return time;
	}

	public void settime(Timestamp time) {
		this.time = time;
	}

	public String getGPS_lontitude() {
		return GPS_longitude;
	}

	public void setGPS_lontitude(String GPS_lontitude) {
		this.GPS_longitude = GPS_lontitude;
	} 
	
	public String getGPS_latitude() {
		return GPS_latitude;
	}

	public void setGPS_latitude(String GPS_latitude) {
		this.GPS_latitude = GPS_latitude;
	}  
	
	public String getstate() {
		return state;
	}

	public void setstate(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "GuijiInfo [devID=" + devID + ", cellID=" + cellID + ", time=" + time + ", GPS_longitude="
				+ GPS_longitude + ", GPS_latitude=" + GPS_latitude + ", carNum=" + carNum + ", state=" + state + "]";
	}   
    
}
