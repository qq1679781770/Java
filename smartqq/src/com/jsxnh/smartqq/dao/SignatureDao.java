package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Signature;

public interface SignatureDao {

	//��Ӹ���ǩ����¼
	public void addSigatureDao(Signature signature);
	//�����û��ĸ���ǩ����¼
	public List<Signature> findSignatureByid(Integer user_id);
}
