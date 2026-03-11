package utils.dbUTILS;

import utils.ConfigReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Fetch {
    public static List<Map<String, String>> fetch(String query) {


        List<Map<String, String>> tableData = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(ConfigReader.read("dbURL"), ConfigReader.read("dbUsername"), ConfigReader.read("dbPassword"));

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            ResultSetMetaData rsm = resultset.getMetaData();


            while (resultset.next()) { //next() checks if there are any more rows in the ResultSet if yes it returns true and moves the cursor to the next row
                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    //getting the column names from resultsetmetadata
                    String key = rsm.getColumnLabel(i);
                    //getting the actual data from rs
                    String value = resultset.getString(i);
                    rowMap.put(key, value);
                }
                tableData.add(rowMap);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableData;

    }
}
