package com.jsxnh.smartqq.serviceImpl;

import com.jsxnh.smartqq.dao.FileDao;
import com.jsxnh.smartqq.entities.File;
import com.jsxnh.smartqq.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileDao fileDao;

    @Override
    public void receive(Integer send, Integer receive, String path, String filename) {
        fileDao.receive(send,receive,path,filename);
    }

    @Override
    public void send(Integer id) {
        fileDao.send(id);
    }

    @Override
    public void feedback(Integer id) {
        fileDao.feedback(id);
    }

    @Override
    public List<File> findSend(Integer send) {
        return fileDao.findSend(send);
    }

    @Override
    public List<File> findReceive(Integer receive) {
        return fileDao.findReceive(receive);
    }

    @Override
    public List<File> findHasReceive(Integer receive) {
        return fileDao.findHasReceive(receive);
    }

    @Override
    public List<File> findHasSend(Integer send) {
        return fileDao.findHasSend(send);
    }

    @Override
    public File findFile(Integer id) {
        return fileDao.findFile(id);
    }
}
