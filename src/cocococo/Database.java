package cocococo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

   Connection con = null;
   
   public Database(){
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("드라이버 적용 확인");
         String url = "jdbc:mysql://localhost:3306/java_p?serverTimezone=Asia/Seoul";
         con = DriverManager.getConnection(url, "root", "1234");
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버 미적용 : 빌드 패치에 커넥터를 추가하시오");
      }
      catch (SQLException e) {
         System.out.println("데이터베이스 접속 실패 : 아이디, 패스워드, 데이터베이스 설정 등을 확인하시오");
      }
   }
   //insert, update, delete 쿼리문
   public int executeUpdate(String sql) {
      Statement ps = null;
      System.out.println("전송된 쿼리문 : " + sql);
      try {
         ps = con.createStatement();
         return ps.executeUpdate(sql);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return -1;
   }
   //select 쿼리문
   public ResultSet executeQuery(String sql) {
      Statement ps = null;
      System.out.println("전송된 쿼리문 : " + sql);
      try {
         ps = con.createStatement();
         return ps.executeQuery(sql);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }   
}