package com.jsxnh.smartqq.daoImpl;

import com.jsxnh.smartqq.dao.CommandDao;
import com.jsxnh.smartqq.entities.Command;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommandImpl implements CommandDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public String getFunction(String command) {
        String hql = "from Command a where a.command=?";
        Command c = (Command) getSession().createQuery(hql).setParameter(0,command).uniqueResult();
        if(c!=null){
            return c.getFunction();
        }else{
            return "Error";
        }
    }
}
