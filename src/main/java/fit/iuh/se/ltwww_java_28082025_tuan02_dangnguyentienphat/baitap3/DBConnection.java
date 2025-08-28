package fit.iuh.se.ltwww_java_28082025_tuan02_dangnguyentienphat.baitap3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // MariaDB
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/UploadFileServletDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "sapassword";

    static {
        try {
        // Load driver chỉ một lần duy nhất
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws
            SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
