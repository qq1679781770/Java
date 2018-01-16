package com.jsxnh.smartqq.serviceImpl;

import com.jsxnh.smartqq.dao.CommandDao;
import com.jsxnh.smartqq.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService{

    @Autowired
    private CommandDao commandDao;

    @Override
    public String getFunction(String command) {
        return commandDao.getFunction(command);
    }
}
