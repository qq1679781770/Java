package com.jsxnh.component;

import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class ListCellRenderer3 implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JSONObject json = (JSONObject)value;
        myPanel m = new myPanel();
        m.getUser().setText(json.getString("username"));
        m.getFilename().setText(json.getString("filename"));
        return m;
    }


    private class myPanel extends JPanel{
        private JLabel user;

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

        private JLabel filename;
        public myPanel(){
            setLayout(null);
            setPreferredSize(new Dimension(800,36));
            setOpaque(false);
            user = new JLabel();
            user.setBounds(20,4,200,28);
            filename = new JLabel();
            filename.setBounds(250,4,500,28);
            add(user);
            add(filename);
        }


    }
}
