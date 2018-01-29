package com.jsxnh.component;

import com.jsxnh.smartqqclient.UserPanel;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCellRenderer2 implements ListCellRenderer {

    private UserPanel u;
    public  ListCellRenderer2(UserPanel i){
        u = i;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JSONObject json = (JSONObject)value;
        myPanel m = new myPanel();
        m.getUser().setText(json.getString("username"));
        m.getFilename().setText(json.getString("filename"));
        m.getItem().setText(json.getInt("status")==1?"点击接收":"已接收");
        return m;
    }


    private class myPanel extends JPanel{

        private JLabel user;
        private JLabel filename;

        public JLabel getUser() {
            return user;
        }

        public void setUser(JLabel user) {
            this.user = user;
        }

        public JLabel getFilename() {
            return filename;
        }

        public void setFilename(JLabel filename) {
            this.filename = filename;
        }

        private JLabel item;

        public JLabel getItem() {
            return item;
        }

        public void setItem(JLabel item) {
            this.item = item;
        }

        public myPanel(){

            setLayout(null);
            setPreferredSize(new Dimension(800,36));
            setOpaque(false);
            user = new JLabel();
            user.setBounds(20,4,150,28);
            filename = new JLabel();
            filename.setBounds(220,4,400,28);
            item = new JLabel();
            item.setBounds(650,4,120,28);
            add(user);
            add(filename);
            add(item);
        }


    }
}
