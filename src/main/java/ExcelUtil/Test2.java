package main.java.ExcelUtil;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args){
        JButton btn = new JButton();
        try {
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/test", prop);

            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO TABLE01 (name,picpath,jbutton)"+
                            "VALUES (?,?,?)"
            );
            pstmt.setString(1,"ted");
            pstmt.setString(2,"https://www.collinsdictionary.com/images/full/dog_230497594.jpg");
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(bao);
            oout.writeObject(btn);
            byte[] s1Ary = bao.toByteArray();
            System.out.println(s1Ary.length);


            pstmt.setBinaryStream(3,new ByteArrayInputStream(s1Ary));
            int n = pstmt.executeUpdate();
            if(n>0){
                System.out.println("ok");
            }else{
                System.out.println("failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
