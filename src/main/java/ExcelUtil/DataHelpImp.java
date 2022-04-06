package ExcelUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataHelpImp implements DataHelp{
    @Override
    public List<String[]> getData() {
        List<String[]> dataList = new ArrayList<String[]>();
        try {
            URL url = new URL("https://data.nhi.gov.tw/resource/mask/maskdata.csv");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
            String line;
            while ( (line = reader.readLine()) != null) {
                String[] strArr = line.split(",");
                dataList.add(strArr);
            }
            reader.close();

            conn.disconnect();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return dataList;
    }
}
