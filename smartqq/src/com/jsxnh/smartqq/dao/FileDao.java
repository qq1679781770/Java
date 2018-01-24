package com.jsxnh.smartqq.dao;

import com.jsxnh.smartqq.entities.File;

import java.util.List;

public interface FileDao {
    public void receive(Integer send,Integer receive,String path,String filename);
    public void send(Integer id);
    public void feedback(Integer id);
    public List<File> findSend(Integer send);
    public List<File> findReceive(Integer receive);
}
