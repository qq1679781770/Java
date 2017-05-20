package com.jsxnh.smartqq.dao;
import com.jsxnh.smartqq.entities.TemporaryLogIn;

public interface TemporaryLogInDao {

	//添加临时登录记录
	public void  addTemporaryLogIn(TemporaryLogIn temporylogin);
	//登出时删除临时登录记录
    public void deleteTemporaryLogIn(Integer user_id);
    //查找用户的临时登录
    public TemporaryLogIn findlogIn(Integer user_id);
}
