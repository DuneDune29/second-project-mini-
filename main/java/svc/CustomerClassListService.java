package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CustomerDAO;
import vo.ClassBean;
import vo.User;

public class CustomerClassListService {


	

	public int getClassListCount(String cus_id) throws Exception {
		int listCount = 0;
		Connection con = getConnection();
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		customerDAO.setConnection(con);
		listCount = customerDAO.selectClassListCount(cus_id);
		close(con);
		return listCount;

	}

	public ArrayList<ClassBean> getClassList(String cus_id, int page, int limit) throws Exception {

		ArrayList<ClassBean> articleList = null;
		Connection con = getConnection();
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		customerDAO.setConnection(con);
		articleList = customerDAO.selectCustomerClassList(cus_id, page, limit);

		close(con);
		return articleList;

	}

	public ArrayList<Integer> getHeadCount(String cus_id, int page, int limit) throws Exception{
		
		ArrayList<ClassBean> articleList = null;
		Connection con = getConnection();
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		customerDAO.setConnection(con);
		articleList = customerDAO.selectCustomerClassList(cus_id, page, limit);
		ArrayList<Integer> headCounts = new ArrayList<Integer>();
			
		
		for (int i = 0; i < articleList.size(); i++) {
			headCounts.add(i,customerDAO.headCount(articleList.get(i).getCL_ID()));
		
		}
		close(con);
		
		
		return headCounts;
	}

}
