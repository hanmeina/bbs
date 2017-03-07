package com.xjtu.bbs.test;

import java.util.List;

import com.xjtu.bbs.domain.Address;
import com.xjtu.bbs.domain.Type;
import com.xjtu.bbs.domain.User;
import com.xjtu.bbs.service.BbsService;
import com.xjtu.bbs.util.Md5Util;

public class BbsServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      BbsService bbsService = new BbsService();
  try {
//		List<Type> types = bbsService.findAllType();
//		for(Type type:types ){
//			System.out.println(type.getTitle());
//			
//		}
//	  User user = new User();
//	  user.setUsername("º«ÃÀÄÈ");
//	 
//	  user.setPassword("123456");
//      User user2 =  bbsService.findUserByUsernameAndPassword(user);
	  Address address = bbsService.findAddressByIP("0:0:0:0:0:0:0:1");
	  System.out.println(address.getLocation());
	  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
	}

}
