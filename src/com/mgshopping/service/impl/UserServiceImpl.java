package com.mgshopping.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mgshopping.bean.UserBean;
import com.mgshopping.dao.UserDao;
import com.mgshopping.dao.impl.UserDaoImpl;
import com.mgshopping.service.UserService;

public class UserServiceImpl implements UserService{

//	public static void main(String[] args) {
//		canlogin("ming","123456");
//	}
	@Override
	public  boolean canlogin(UserBean user) {
		// TODO Auto-generated method stub
		if (user==null||user.equals("")) {
			return false;
		}
		if (user.getUsername()==null||user.getUsername().equals("")||user.getPassword()==null||user.getPassword().equals("")) {
			return false;
		}
		return true;
		
	}

	@Override
	public boolean islogin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerUser(UserBean user) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		if (userDao.insertUser(user)) {
			return true;
		}
		return false;
	}

	@Override
	public UserBean UserInfo(String username,String password) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		UserBean user = userDao.selectList(username, password);
		return user;
	}
		
}
