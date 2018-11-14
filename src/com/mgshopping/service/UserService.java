package com.mgshopping.service;

import java.util.List;

import com.mgshopping.bean.UserBean;

public interface UserService {
	public boolean canlogin(UserBean user);
	public boolean islogin();
	public boolean registerUser(UserBean user);
	public UserBean UserInfo(String username,String password);
//	public UserBean autologin();
}