package com.mgshopping.dao;

import java.util.List;

import com.mgshopping.bean.UserBean;

public interface UserDao {
	public UserBean selectList(String username,String password); 
	public Boolean insertUser(UserBean user);
//	public UserBean selectUserInfo();
}
