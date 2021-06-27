package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;


import dao.QnADAO;
import db.JdbcUtil;
import vo.Customer_bean;
import vo.User;

public class QnAInsertService {

   private QnADAO qnaDao = QnADAO.getInstance();
  
   
   public boolean insertQnA(String qna_writer_id, String qna_title, String qna_content) {
      try (Connection conn = JdbcUtil.getConnection()) {
    	  qnaDao.setConnection(conn);
    	  
    	  System.out.println("되나??");
    	  boolean isInsertSuccess = false;
  	
  		
  	
  		int insertCount = qnaDao.insertQnAList(qna_writer_id, qna_title,qna_content);
  		
  		if(insertCount > 0){
  			commit(conn);
  			isInsertSuccess=true;
  		}
  		else{
  			rollback(conn);
  		}
  		
  		close(conn);
  		return isInsertSuccess;
  		
  	
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }
}