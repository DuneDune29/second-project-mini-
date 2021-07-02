package svc.manager;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDAO;
import db.JdbcUtil;

public class PassUpdateService {

	private CustomerDAO customerDao = CustomerDAO.getInstance();
	  
	   
	   public boolean passUpdate(String cus_pwd, String cus_id) {
	      try (Connection conn = JdbcUtil.getConnection()) {
	    	  customerDao.setConnection(conn);
	    	  
	    	  System.out.println("되나??");
	    	  boolean isUpdateSuccess = false;
	  	
	  		
	  	
	  		int updateCount = customerDao.passUpdate(cus_pwd, cus_id);
	  		
	  		if(updateCount > 0){
	  			commit(conn);
	  			isUpdateSuccess=true;
	  		}
	  		else{
	  			rollback(conn);
	  		}
	  		
	  		close(conn);
	  		return isUpdateSuccess;
	  		
	  	
	      } catch (SQLException e) {
	         throw new RuntimeException(e);
	      }
	   }
	}

