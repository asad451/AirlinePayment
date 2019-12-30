package com.asad.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asad.beans.RSA;
import com.asad.beans.User;
import com.asad.dao.UserDao;

@Controller
public class PaymentController {

	@Autowired    
	UserDao dao;	
	

	/*
	 * To run encryption code, need to uncomment this code
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)  
	public String save(HttpServletRequest req, HttpServletResponse resp){

		//Getting params from payment form 
		String c_name = req.getParameter("c_name");
		String c_number = req.getParameter("c_num");
		String c_exp = req.getParameter("c_date");
		String c_cvv = req.getParameter("c_cvv");

		RSA rsa = new RSA();

		//Encrypting using RSA after converting string params to byte[]
		byte [] b_name = rsa.encrypt(c_name.getBytes());
		byte [] b_number = rsa.encrypt(c_number.getBytes());
		byte [] b_exp = rsa.encrypt(c_exp.getBytes());
		byte [] b_cvv = rsa.encrypt(c_cvv.getBytes());

		//converting byte[] back to string to store in a database
		String s_name = Base64.getEncoder().encodeToString(b_name);
		String s_number = Base64.getEncoder().encodeToString(b_number);
		String s_exp = Base64.getEncoder().encodeToString(b_exp);
		String s_cvv = Base64.getEncoder().encodeToString(b_cvv);

		//Appending the values of n and d to use later for decryption
		String d_n_val = rsa.append();
		String appended_s_name = d_n_val + s_name;
		String appended_s_number = d_n_val + s_number;
		String appended_s_exp = d_n_val + s_exp;
		String appended_s_cvv = d_n_val + s_cvv;

		//Setting the user object to store the values in database
		User usr = new User();
		usr.setName(appended_s_name);
		usr.setCardNumber(appended_s_number);
		usr.setExpDate(appended_s_exp);
		usr.setCVV(appended_s_cvv);

		dao.save(usr);    
		return "successful";    	
	}		
	

	/*
	 * To run decryption code, need to uncomment this code
	 */
//	@RequestMapping(value="/save",method = RequestMethod.POST)  
//	public String save(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException{
//		
//		//Encrypting using RSA after converting string params to bytes
//		RSA rsa = new RSA();
//		
//		//Getting values from the database against a logged in (particular) user
//		//Using hardcode values as login functionality is not the part of this application
//		String db_name = dao.getUser(20).getName();
//		String db_number = dao.getUser(20).getCardNumber();
//		String db_exp = dao.getUser(20).getExpDate();
//		String db_cvv = dao.getUser(20).getCVV();
//		
//		//Un-appending string saved in database to retrieve the values of d and n for decryption
//		String c_name_withoutDN = rsa.unAppend(db_name);
//		String c_number_withoutDN = rsa.unAppend(db_number);
//		String c_exp_withoutDN = rsa.unAppend(db_exp);
//		String c_cvv_withoutDN = rsa.unAppend(db_cvv);
//		
//		//Converting n and d to BigInteger
//		BigInteger new_n = rsa.valueOfN(db_name);
//		BigInteger new_d = rsa.valueOfD(db_name);
//		
//		//Converting String to byte [] for decryption
//		byte[] decodedString1 = Base64.getDecoder().decode(new String(c_name_withoutDN).getBytes("UTF-8"));
//		byte[] decodedString2 = Base64.getDecoder().decode(new String(c_number_withoutDN).getBytes("UTF-8"));
//		byte[] decodedString3 = Base64.getDecoder().decode(new String(c_exp_withoutDN).getBytes("UTF-8"));
//		byte[] decodedString4 = Base64.getDecoder().decode(new String(c_cvv_withoutDN).getBytes("UTF-8"));
//
//		//decrypting values retrieved from the database
//		byte [] b_name = rsa.decrypt(decodedString1, new_d, new_n);
//		byte [] b_number = rsa.decrypt(decodedString2, new_d, new_n);
//		byte [] b_exp = rsa.decrypt(decodedString3, new_d, new_n);
//		byte [] b_cvv = rsa.decrypt(decodedString4, new_d, new_n);
//
//		//converting the resulting byte[] values to string for authentication (To check if the user has entered the same values that are stored in database)
//		String s_name = new String(b_name);
//		String s_number = new String(b_number);
//		String s_exp = new String(b_exp);
//		String s_cvv = new String(b_cvv);
//				
//		System.out.println(s_name);
//		System.out.println(s_number);
//		System.out.println(s_exp);
//		System.out.println(s_cvv);
//		
//		//If the values of user and stored in databse are same, print "Successful payment" else "Unsuccessful payment"
//		if (s_name.equals(req.getParameter("c_name")) && s_number.equals(req.getParameter("c_num")) && s_exp.equals(req.getParameter("c_date")) && s_cvv.equals(req.getParameter("c_cvv"))) {
//			return "successful";
//		}
//		else {
//			return "unsuccessful";
//		}				
//    }
}
