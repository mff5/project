package burger;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLBurgerConnect {
    public static Connection makeConnection() throws Exception {
        String filePath = "D:/study/db/src/burger/burger.properties";
        Properties properties = new Properties();
        properties.load(new FileReader(filePath));

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Connection con = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("데이터베이스 드라이버 로드 성공");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("데이터베이스 접속 성공");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("데이터베이스 드라이버 로드 실패");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터베이스 연결 성공");
        }
        return con;
    }
}
