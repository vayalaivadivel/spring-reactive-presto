package org.sakki.sample.dao;

import java.sql.*;
import java.util.Properties;
import com.facebook.presto.jdbc.PrestoConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class PrestoDAO {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.user}")
    private String jdbcUser;


    public void findTotalRecords() {
        Connection connection = null;
        Statement statement = null;
        try {

            Class.forName("com.facebook.presto.jdbc.PrestoDriver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, "");
            PreparedStatement stmt = connection.prepareStatement("select * from geo_names limit 2");
            System.out.println("=======Connected==========");
            ResultSet resultSet = stmt.executeQuery();
            System.out.println("=======query executed==========");
//            while (resultSet.next()) {
//                int id = resultSet.getInt("auth_id");
//                String name = resultSet.getString("auth_name");
//                System.out.print("ID: " + id + ";\nName: " + name + "\n");
//            }


        } catch (Exception exception) {
            System.out.println("===error due to ===="+exception.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(jdbcUrl,jdbcUser,null);
        try {
            trySetProperties(conn);
        } catch(SQLException ex) {
           System.out.println("------------"+ex);
        }
        return conn;
    }

    @Autowired
    private Properties prestoProps;

    private void trySetProperties(Connection connection) throws SQLException {
        PrestoConnection pc = connection.unwrap(PrestoConnection.class);
        prestoProps.forEach((k,v) -> pc.setSessionProperty((String)k, String.valueOf(v)));
    }
}