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
         System.out.println("����̹� ���� Ȯ��");
         String url = "jdbc:mysql://localhost:3306/java_p?serverTimezone=Asia/Seoul";
         con = DriverManager.getConnection(url, "root", "1234");
      } catch (ClassNotFoundException e) {
         System.out.println("����̹� ������ : ���� ��ġ�� Ŀ���͸� �߰��Ͻÿ�");
      }
      catch (SQLException e) {
         System.out.println("�����ͺ��̽� ���� ���� : ���̵�, �н�����, �����ͺ��̽� ���� ���� Ȯ���Ͻÿ�");
      }
   }
   //insert, update, delete ������
   public int executeUpdate(String sql) {
      Statement ps = null;
      System.out.println("���۵� ������ : " + sql);
      try {
         ps = con.createStatement();
         return ps.executeUpdate(sql);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return -1;
   }
   //select ������
   public ResultSet executeQuery(String sql) {
      Statement ps = null;
      System.out.println("���۵� ������ : " + sql);
      try {
         ps = con.createStatement();
         return ps.executeQuery(sql);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }   
}