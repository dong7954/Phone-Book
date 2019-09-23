package cocococo;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class cococo {
   public static void main(String[] args)throws IOException{
      Test_Ex06_Sub round = new Test_Ex06_Sub();
   }
}


class Test_Ex06_Sub extends Frame implements Serializable{
      
	Database db = new Database();
   private Dimension dimenS;//특정한 사각형 영역 관리
   private Dimension dimenF;
   
   private int xPos;
   private int yPos;
   
   private Label lbInsName  = new Label("이          름", Label.CENTER);
   private Label lbInsPhone = new Label("전화번호", Label.CENTER);
   private Label lbFndName  = new Label("검색이름", Label.CENTER);
   private Label lbFndPhone = new Label("검색번호", Label.CENTER);
   
   private TextField tfInsName  = new TextField(10);
   private TextField tfInsPhone = new TextField(13);
   private TextField tfFndName  = new TextField(10);
   private TextField tfFndPhone = new TextField(13);

   private Button btnInsert = new Button("등록");
   private Button btnDelete = new Button("삭제");
   private Button btnJ = new Button("조회");
   private Button btnSearch = new Button("검색");
   private Button btnExit   = new Button("종료");
   
   //디자인용
   private Button btnDunny1  = new Button(" ");
   private Button btnDunny2  = new Button(" ");
   private Button btnDunny3  = new Button(" ");
   
   private Button btnDunny4  = new Button(" ");
   private Button btnDunny5  = new Button(" ");
   
   
   public List phoneList = new List(100);
   
   
   
   public Test_Ex06_Sub(){
      super("전화번호관리");            
         
      try {
       
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      this.init();
      this.start();
      this.setSize(800, 400);

      dimenS = Toolkit.getDefaultToolkit().getScreenSize();
      dimenF = this.getSize();
            
      
      xPos = (int)(dimenS.getWidth()  / 2 - dimenF.getWidth()  / 2 );
      yPos = (int)(dimenS.getHeight() / 2 - dimenF.getHeight() / 2 );
      this.setLocation(xPos, yPos);
      this.setVisible(true);      
   }
   
   public void init(){
      // 화면 구성 넣은 부분
      
      //Dummy Button 설정
      btnDunny1.setVisible(false);
      btnDunny2.setVisible(false);
      btnDunny3.setVisible(false);
      btnDunny4.setVisible(false);
      btnDunny5.setVisible(false);      
      
      GridLayout grid = new GridLayout(1,2,5,5);      
      this.setLayout(grid);
      
      GridLayout pGrid1 = new GridLayout(2,1,5,5);
      Panel p1 = new Panel();
      p1.setLayout(pGrid1);	
      
      GridLayout pGrid2 = new GridLayout(2,1,5,5);
      Panel p2 = new Panel();
      p2.setLayout(pGrid2);
      
      
      GridLayout pGrid3 = new GridLayout(2,2,5,5);
      Panel p3 = new Panel();
      p3.setLayout(pGrid3);
                  
      p3.add(lbInsName);
      p3.add(tfInsName);
      p3.add(lbInsPhone);
      p3.add(tfInsPhone);
      
      p2.add(p3);
      
      
      GridLayout pGrid4 = new GridLayout(2,3,5,5);
      Panel p4 = new Panel();
      p4.setLayout(pGrid4);
      
      p4.add(btnInsert);
      p4.add(btnDelete);
      p4.add(btnJ);
      
      p4.add(btnDunny1);
      p4.add(btnDunny2);
      p4.add(btnDunny3);
      
      p2.add(p4);
      
      
      GridLayout pGrid5 = new GridLayout(2,1,5,5);
      Panel p5 = new Panel();
      p5.setLayout(pGrid5);      
      
      
      GridLayout pGrid6 = new GridLayout(2,2,5,5);
      Panel p6 = new Panel();
      p6.setLayout(pGrid6);      
      
            
      p6.add(lbFndName);
      p6.add(tfFndName);
      p6.add(lbFndPhone);
      p6.add(tfFndPhone);
      
      p5.add(p6);
         
      
      GridLayout pGrid7 = new GridLayout(2,2,5,5);
      Panel p7 = new Panel();
      p7.setLayout(pGrid7);
      
      p7.add(btnSearch);
      p7.add(btnExit);
      
      p7.add(btnDunny4);
      p7.add(btnDunny5);
      
      p5.add(p7);      
      
      p1.add(p2);
      p1.add(p5);
      
      this.add(p1);
      
      GridLayout pGrid8 = new GridLayout(1,1,5,5);
      Panel p8 = new Panel();
      p8.setLayout(pGrid8);
      
      p8.add(phoneList);
      
      this.add(p8);      
   }
   
   public void start(){
      // Event나 쓰레드 처리할 부분

      
      //입력
      btnInsert.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){            
            String str = tfInsName.getText().trim() + " : " + tfInsPhone.getText().trim();     
            phoneList.add(str);                
            String InsertQuery = "insert into Pnumber( name,number) values('" + tfInsName.getText() + "','" + tfInsPhone.getText() + "');";
            db.executeUpdate(InsertQuery);
            clearInsTxtField();
            clearFndTxtField();
         }
      });
      
      //삭제
      btnDelete.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){            
            int nPos = 0;
            nPos = phoneList.getSelectedIndex();
            phoneList.remove(nPos);
            clearInsTxtField();
            clearFndTxtField();            
         }
      });
      
      //조회
      btnJ.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){  
        	     	 
        	 try {
            	 String selectQuery = "select * from Pnumber";
    			 ResultSet rs = db.executeQuery(selectQuery);
    			 phoneList.clear();
    			 while (rs.next()) {
    				 String phoneinfo = rs.getString("name"); 
    				 phoneinfo += " : ";
    				 phoneinfo += rs.getString("number");
    				 phoneList.add(phoneinfo);
    				 
    				 
    			 }
        	 } catch(SQLException sqle) {
        		 clearInsTxtField();
 	            clearFndTxtField();
        	 }

        		
         }
      });
      
      //검색
      btnSearch.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            String   strSearchName = "";
            String[] str = new String[phoneList.getRows()];
            
            strSearchName = tfFndName.getText().trim();            
            str = phoneList.getItems();
         
           
            for(int i = 0; i < str.length; i++){
               if(strSearchName.equalsIgnoreCase(str[i].substring(0, 3))){
                  tfFndPhone.setText(str[i].substring(6));
               }
            }
         }
      });
      
      
   
      //종료
      btnExit.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            try {
            
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            System.exit(0);            
         }
      });         

      
      // 창 닫기
      this.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent e){
            System.exit(0);
         }
        });      
   }      
   
   //입력필드 초기화
   public void clearInsTxtField(){
      tfInsName.setText("");
      tfInsPhone.setText("");
   }
   
   //검색필드 초기화
   public void clearFndTxtField(){
      tfFndName.setText("");
      tfFndPhone.setText("");
   }   
  
 
   }
   
