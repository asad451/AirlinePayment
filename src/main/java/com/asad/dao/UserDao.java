package com.asad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;    
import com.asad.beans.User;    
   

public class UserDao {
	//JDBCTemplate variable to execute SQL queries
	JdbcTemplate template;
	    
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}    
	
	//This Function is called when the user enter's his card details in HTML form 
	//Executes SQL INSERT query
	public int save(User u){
		
		String cname = u.getName();
		String cnumber = u.getCardNumber();
		String exp = u.getExpDate();
		String cvv = u.getCVV();
		
	    String sql="INSERT INTO users1 (card_name, card_exp, card_cvv, card_number) VALUES (?,?,?,?)";    
	    return template.update(sql, new Object[] {cname, exp, cvv, cnumber});    
	}
	
	//This Function is called to retrieve the value of user card details from the database 
	//Executes SQL SELECT query and returns a whole row
	public User getUser(int uid) {
		
		String sql = "SELECT * FROM users1 WHERE iduser = ?";
		
		return template.queryForObject(sql, new Object[] {uid}, new UserMapper());
	}
	
	//RowMapper class used to map row retrieved from result set to the User class
	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User u = new User();
			
			u.setName(rs.getString("card_name"));
			u.setExpDate(rs.getString("card_exp"));
			u.setCVV(rs.getString("card_cvv"));
			u.setCardNumber(rs.getString("card_number"));
			
			return u;
		}
	}
}   