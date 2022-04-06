package IIImidProject;

import IIImidProject.MyTableEmployees;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureGetter extends JFrame {
    Image image;
    Toolkit toolKit;
    public PictureGetter(int row){
        super("CustomerPicture"); //呼叫父類建構函式
        Container container=getContentPane();  //得到容器
        container.setLayout(new FlowLayout()); //設定佈局管理器
        toolKit=getToolkit(); //得到工具包
        URL url = MyTableEmployees.getEmpPhotoURL(row);
        image=toolKit.getImage(url); //獲取影象
        repaint(); //重繪螢幕
        setSize(1000,1000); //設定視窗尺寸
        setVisible(true); //設定視窗可視
        setDefaultCloseOperation(2); //關閉視窗時退出程式
    }
    public void paint(Graphics g){
        super.paint(g);
        if (image!=null){
            g.drawImage(image,0,0,this);
        }
    }
}

