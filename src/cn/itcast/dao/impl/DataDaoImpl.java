package cn.itcast.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import cn.itcast.dao.DataDao;
import cn.itcast.entity.DataInfo;
import cn.itcast.entity.GuijiInfo;
import cn.itcast.entity.ResultInfo;
import cn.itcast.entity.TaskInfo;
import cn.itcast.utils.JdbcUtils;

public class DataDaoImpl<V> implements DataDao {

/*
 *  查询满足时间的所有车辆信息
 */
	@Override
	public String PrintAllData(String time_start,String time_end) {
		Gson gson=new Gson();
		List<DataInfo> list_data_info=new LinkedList<DataInfo>();
		Connection con=JdbcUtils.getConnection();
		try {
			String sql="SELECT devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn "
					+ " FROM car_info "
					+" where time between '"+ time_start +"' and '"+time_end+"'";
			PreparedStatement pstmt=con.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String devID=rs.getString(1);
				String time=rs.getString(2);
				String state=rs.getString(3);
				String GPS_longitude=rs.getString(4);
				String GPS_latitude=rs.getString(5);
				String cellID=rs.getString(6);
				String carNum=rs.getString(7);
				String warn=rs.getString(8);
				DataInfo di=new DataInfo(devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn);
	            list_data_info.add(di);  	
			}
			return gson.toJson(list_data_info);
		
		    } catch (SQLException e) {
						e.printStackTrace();
		    }finally{
			      JdbcUtils.closeConnection(con);
		    }
		return gson.toJson(list_data_info);
	}
	/*
	 * 用于在地图上显示所有车辆的当前（或最新）位置。
	 */
	@Override
	public String PrintAllDataMobile() {
		Gson gson=new Gson();
		List<DataInfo> list_data_info=new LinkedList<DataInfo>();
		Connection con=JdbcUtils.getConnection();
		try {
			String sql=  " SELECT devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn "
						+" FROM car_info a "
						+" WHERE EXISTS( "
						+" SELECT * FROM ( "
						+" SELECT devID , max(time) as FTime FROM car_info GROUP BY devID)x "
						+" WHERE x.devID=a.devID AND a.time=x.FTime) ";
			PreparedStatement pstmt=con.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String devID=rs.getString(1);
				String time=rs.getString(2);
				String state=rs.getString(3);
				String GPS_longitude=rs.getString(4);
				String GPS_latitude=rs.getString(5);
				String cellID=rs.getString(6);
				String carNum=rs.getString(7);
				String warn=rs.getString(8);
				DataInfo di=new DataInfo(devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn);
	            list_data_info.add(di);  	
			}
			return gson.toJson(list_data_info);
		
		    } catch (SQLException e) {
						e.printStackTrace();
		    }finally{
			      JdbcUtils.closeConnection(con);
		    }
		return gson.toJson(list_data_info);
	}
	/*
	 * 查找某辆车最新时间的信息。
	 */
	@Override
	public String PrintGivenCarDataMobile(String carid) {
		Gson gson=new Gson();
		List<DataInfo> list_data_info=new LinkedList<DataInfo>();
		Connection con=JdbcUtils.getConnection();
		try {
			String sql=" SELECT devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn "
					+" FROM car_info a "
					+" WHERE EXISTS( "
					+" SELECT * FROM ( "
					+" SELECT devID , max(time) as FTime FROM car_info GROUP BY devID)x "
					+" WHERE x.devID='"+carid+"' AND a.time=x.FTime) ";
					   	
			PreparedStatement pstmt=con.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String devID=rs.getString(1);
				String time=rs.getString(2);
				String state=rs.getString(3);
				String GPS_longitude=rs.getString(4);
				String GPS_latitude=rs.getString(5);
				String cellID=rs.getString(6);
				String carNum=rs.getString(7);
				String warn=rs.getString(8);
			
				DataInfo di=new DataInfo(devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn);
	            list_data_info.add(di);  	
			}
			
			return gson.toJson(list_data_info);
		
		    } catch (SQLException e) {
						e.printStackTrace();
		    }finally{
			      JdbcUtils.closeConnection(con);
		    }
		return gson.toJson(list_data_info);
	}
	/*
	 * 在一段时间内，某辆车所有数据信息。
	 */
	@Override
	public String PrintTraceMobile(String start_time, String end_time,
			String carid) {
		Gson gson=new Gson();
		List<DataInfo> list_data_info=new LinkedList<DataInfo>();
		Connection con=JdbcUtils.getConnection();
		try {
			String sql=" SELECT devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn "
					+ " FROM car_info "
					+ " WHERE (devID= "+carid+" AND time BETWEEN '"+start_time+"' AND '"+end_time+"' )";
					   	
			PreparedStatement pstmt=con.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String devID=rs.getString(1);
				String time=rs.getString(2);
				String state=rs.getString(3);
				String GPS_longitude=rs.getString(4);
				String GPS_latitude=rs.getString(5);
				String cellID=rs.getString(6);
				String carNum=rs.getString(7);
				String warn=rs.getString(8);
			
				DataInfo di=new DataInfo(devID,time,state,GPS_longitude,GPS_latitude,cellID,carNum,warn);
	            list_data_info.add(di);  	
			}
			
			return gson.toJson(list_data_info);
		
		    } catch (SQLException e) {
						e.printStackTrace();
		    }finally{
			      JdbcUtils.closeConnection(con);
		    }
		return gson.toJson(list_data_info);
	}
	
	
	@Override
	public Map<String,List<GuijiInfo>> getAllGuiji(String username) {
		
		ArrayList<GuijiInfo> test = new ArrayList<GuijiInfo>();
		Map<String,List<GuijiInfo>> map = new HashMap<String, List<GuijiInfo>>();
		Connection con=JdbcUtils.getConnection();
		try {
			String sql=" SELECT ci.devID,ci.cellID,ci.time,ci.GPS_longitude,ci.GPS_latitude,ci.carNum,ci.state\n" + 
					"FROM `car_info` ci\n" + 
					"\n" + 
					"LEFT JOIN usr u\n" + 
					"\n" + 
					"on ci.devId = u.devid\n" + 
					"\n" + 
					"where u.username = '"+username+"'\n" + 
					"\n" + 
					"ORDER BY ci.cellID,ci.state";
					   	
			PreparedStatement pstmt=con.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String devID=rs.getString(1);
				String cellID=rs.getString(2);
				String time=rs.getString(3);
				String GPS_longitude=rs.getString(4);
				String GPS_latitude=rs.getString(5);
				Integer carNum=rs.getInt(6);
				String state=rs.getString(7);
				
				List<GuijiInfo> list = map.get(cellID);
				if(list == null) {
					list = new ArrayList<GuijiInfo>();
				}
				GuijiInfo di=new GuijiInfo(devID,cellID,time,GPS_longitude,GPS_latitude,carNum,state);
				test.add(di);
				list.add(di);  	
				map.put(cellID, list);
			}
			System.out.println(map);
			return map;
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(con);
		}
		return map;

	}
	
	public Map<String,ResultInfo> chuliData(Map<String,List<GuijiInfo>> map) {
		
		Map<String,ResultInfo> result = new HashMap<String, ResultInfo>();
		
		
		for(Entry<String, List<GuijiInfo>> entry : map.entrySet()){
			ResultInfo resultInfo = new ResultInfo();
			List<GuijiInfo> value = entry.getValue();
			List<TaskInfo> list = new ArrayList<TaskInfo>();
			for(GuijiInfo guiji : value) {
				TaskInfo task = new TaskInfo( guiji.getGPS_lontitude(), guiji.getGPS_latitude(),guiji.gettime());
				list.add(task);
			}
			Integer speed = value.stream().collect(Collectors.summingInt(GuijiInfo::getCarNum));
			resultInfo.setSpeed(speed);
			resultInfo.setList(list);
			result.put(entry.getKey(), resultInfo);
		}
		
		return result;
		
	}

}

