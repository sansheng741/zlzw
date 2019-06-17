package service.serviceImpl;

import java.util.List;

import bean.Type;
import dao.DBHelper;
import service.TypeService;

public class TypeServiceImpl implements TypeService{

	@Override
	public Long add(Type type) {
		String sql = "insert into type(gid,bid,sid1,sid2,sid3) values(?,?,?,?,?)";
		List<List<Object>> insert = DBHelper.insert(sql, type.getGid(),type.getBid(),type.getSid1(),type.getSid2()
				,type.getSid3());
		return (Long) insert.get(0).get(0);
	}

}
