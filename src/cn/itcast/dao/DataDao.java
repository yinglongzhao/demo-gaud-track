package cn.itcast.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.entity.GuijiInfo;
import cn.itcast.entity.ResultInfo;

public interface DataDao {
		public String PrintAllData(String time_start,String end_start);
		public String PrintAllDataMobile();
		public String PrintGivenCarDataMobile(String carid);
		public String PrintTraceMobile(String start_time,String end_time,String carid);
		
		
		//��ȡ��ǰ�û��ĳ������еĹ켣��Ϣ
		public Map<String,List<GuijiInfo>> getAllGuiji(String username);
		
		public Map<String,ResultInfo> chuliData(Map<String,List<GuijiInfo>> map);
}
