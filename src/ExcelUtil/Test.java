package ExcelUtil;

import IIImidProject.NorthwindBackOffice;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class Test {
    public static void main(String [] args){
        String s = NorthwindBackOffice.jtf2.getText();
        System.out.println(s);
    }
}
