package com.mgshopping.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mgshopping.bean.UserBean;
import com.mgshopping.dao.UserDao;
import com.mgshopping.utils.DruidUtils;

public class UserDaoImpl implements UserDao{
	private  QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
	@Override
	public UserBean selectList(String username,String password) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username=? and password = ?";
		Object[] params = {username,password};
		UserBean user = null;
		try {
			user = queryRunner.query(sql,new BeanHandler<UserBean>(UserBean.class),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	@Override
	public Boolean insertUser(UserBean user) {
		// TODO Auto-generated method stub
		String sql = "insert into user (username,password,email,realname,status) values (?,?,?,?,?)";
		
		Object[] params = {user.getUsername(),user.getPassword(),user.getEmail(),user.getRealname(),user.getStatus()};
		
		try {
			queryRunner.update(sql,params);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
//	@Override
//	public UserBean selectUserInfo() {
//		// TODO Auto-generated method stub
//		String sql = "select * from user where username"
//		return null;
//	}
	
}
