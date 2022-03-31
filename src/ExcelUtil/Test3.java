package ExcelUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class Test3 {
    public static PreparedStatement pstmt;
    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/iii", prop);

            // 3. SQL statement
            pstmt = conn.prepareStatement(
                    "INSERT INTO animals (animal_id,animal_place,animal_kind,animal_colour,animal_sex,animal_bodytype,animal_age,animal_update,album_file,animal_Variety) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)");

            // 1. 抓 json 回來
            fetchJSON();
            // 2. parse json
            // 3. insert into
            System.out.println("OK");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void fetchJSON() {
        try {
            URL url = new URL("https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
            String line; StringBuffer sb = new StringBuffer();
            while ( (line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            conn.disconnect();

            parseJSON(sb.toString());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void parseJSON(String json) {
        try {
            JSONArray root = new JSONArray(json);
            for (int i=0; i<root.length(); i++) {
                JSONObject row = root.getJSONObject(i);
                int id = row.getInt("animal_id");
                String place = row.getString("animal_place");
                String kind = row.getString("animal_kind");
                String color = row.getString("animal_colour");
                String sex = row.getString("animal_sex");
                String bodytype = row.getString("animal_bodytype");
                String age = row.getString("animal_age");
                String update = row.getString("animal_update");
                String img = row.getString("album_file");
                String variety = row.getString("animal_Variety");

                insertData(id, place, kind, color, sex, bodytype, age, update, img, variety);

            }
        }catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void insertData(int id, String place,String kind,String color,String sex,
                                   String bodytype,String age,String update,String img,String variety) {
        try {
            pstmt.setInt(1,id);
            pstmt.setString(2,place);
            pstmt.setString(3,kind);
            pstmt.setString(4,color);
            pstmt.setString(5,sex);
            pstmt.setString(6,bodytype);
            pstmt.setString(7,age);
            pstmt.setString(8,update);
            pstmt.setString(9,img);
            pstmt.setString(10,variety);


            // 4. execute SQL statement
            pstmt.executeUpdate();
        }catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
