package service.serviceImpl;

import bean.TypeBig;
import bean.TypeSmall;
import dao.DBHelper;
import service.TypeSmallService;

public class TypeSmallServiceImpl implements TypeSmallService {

	@Override
	public int findIdByTypeName(String filetype) {
		TypeSmall unique = DBHelper.unique("select * from typesmall where type = ?", TypeSmall.class, filetype);
		if(unique != null){
			return unique.getSid();
		}else{
			return -1;
		}
	}

}
