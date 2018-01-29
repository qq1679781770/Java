package com.jsxnh.component;

import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class ListCellRenderer1 implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JSONObject json = (JSONObject)value;
        myPanel m = new myPanel();
        m.getUser().setText(json.getString("username"));
        m.getFilename().setText(json.getString("filename"));
        m.getStatus().setText(json.getInt("status")==1?"待接收":"已接收");
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

        public JLabel getStatus() {
            return status;
        }

        public void setStatus(JLabel status) {
            this.status = status;
        }

        private JLabel status;

        public myPanel(){

            setLayout(null);
            setPreferredSize(new Dimension(800,36));
            setOpaque(false);
            user = new JLabel();
            user.setBounds(20,4,150,28);
            filename = new JLabel();
            filename.setBounds(220,4,400,28);
            status = new JLabel();
            status.setBounds(650,4,120,28);
            add(user);
            add(filename);
            add(status);
        }


    }
}
