package com.jsxnh.smartqq.daoImpl;

import com.jsxnh.smartqq.dao.FileDao;
import com.jsxnh.smartqq.entities.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class FileDaoImpl implements FileDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void receive(Integer send, Integer receive, String path, String filename) {
        File f = new File();
        f.setSend(send);
        f.setReceive(receive);
        f.setPath(path);
        f.setFilename(filename);
        f.setSend_time(new Date());
        f.setStatus(1);
        getSession().save(f);
    }

    @Override
    public void send(Integer id) {
        String hql = "update File a set a.status=2,a.receive_time=? where a.id=?";
        getSession().createQuery(hql).setParameter(0,new Date()).setParameter(1,id).executeUpdate();
    }

    @Override
    public void feedback(Integer id) {
        String hql = "update File a set a.status=3 where a.id=?";
        getSession().createQuery(hql).setParameter(0,id).executeUpdate();
    }

    @Override
    public List<File> findSend(Integer send) {
        String hql = "from File a where a.send=? and a.status=2";
        return getSession().createQuery(hql).setParameter(0,send).list();
    }

    @Override
    public List<File> findReceive(Integer receive) {
        String hql = "from File a where a.receive=? and a.status=1";
        return getSession().createQuery(hql).setParameter(0,receive).list();
    }
}
