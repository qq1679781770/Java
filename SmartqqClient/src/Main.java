import com.jsxnh.smartqqclient.Login;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("Tree.rowHeight",42);
            System.out.println(System.getProperty("file.encoding"));
            System.out.println("h..............................");
            System.getProperties().list(System.out);
        }
        catch(Exception e) {
            //TODO exception
        }
        new Login().lunch();
    }

}
