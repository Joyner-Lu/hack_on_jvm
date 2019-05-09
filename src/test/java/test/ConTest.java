package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConTest {

    public Connection getConnection() throws Exception {
        //select count(*) from v$process; 连接
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@10.10.15.76:1521:XE";
        String user = "sys AS SYSDBA";
        String password = "oracle";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void main(String[] args) throws Exception {
        ConTest conTest = new ConTest();
        Connection con = conTest.getConnection();
        try {
            String sql = "select *  from test t where rownum < 101";
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //每次取20行记录
            int fetchSize = 20;
            stmt.setFetchSize(fetchSize);
            int row_num = 0;
            ResultSet rs = stmt.executeQuery();
            //休息10s钟，观察抓包的情况。
            sleep(10);
            System.out.println("休息结束");
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                row_num++;
                System.out.println("row_num-->" + row_num + ",name:" + name);
                if (row_num >= fetchSize) {
                    row_num = 0;
                }
                sleep(1);//休息1秒，方便查看抓包数据
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }


    }

    /**
     * 休息，单位是秒
     *
     * @param l
     */
    public static void sleep(long l) {
        try {
            Thread.sleep(l * 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
