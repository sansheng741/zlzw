package service.serviceImpl;

import bean.TypeBig;
import dao.DBHelper;
import service.TypeBigService;

public class TypeBigServiceImpl implements TypeBigService {

	@Override
	public int findIdByTypeName(String filetype) {
		TypeBig unique = DBHelper.unique("select bid from typebig where type = ?", TypeBig.class, filetype);
		return unique.getBid();
	}

}
