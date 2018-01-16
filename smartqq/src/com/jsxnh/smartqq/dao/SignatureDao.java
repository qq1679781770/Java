package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Signature;

public interface SignatureDao {

	//添加个性签名记录
	public void addSigatureDao(Signature signature);
	//查找用户的个性签名记录
	public List<Signature> findSignatureByid(Integer user_id);
}
