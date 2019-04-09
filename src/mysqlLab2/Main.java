package mysqlLab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static Connection connection() throws SQLException, ClassNotFoundException{
		//1.加载驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2.得到连接
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?serverTimezone=UTC"
				,"root","jmq.990467");
	      return conn;
	}
	public static void queryone(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();
		 String sql="select ESSN from works_on where PNO = '"+parameters.get(0)+"'";
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ESSN\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1));
				}
	}
	public static void querytwo(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();
		 String sql="select ENAME from works_on,project,employee "
		 		+ "where PNAME = '"+parameters.get(0)+"' and works_on.ESSN=employee.ESSN "
		 				+ "and project.PNO=works_on.PNO";
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ENAME\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1));
				}
	}
	public static void querythree(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();		 
		 String sql="select ENAME , ADDRESS from employee,department"
		 		+ " where employee.DNO=department.DNO and "
		 		+ "DNAME = '"+parameters.get(0)+"'";
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ENAME\tADDRESS\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1)+"\t"+rs.getString(2));
				}
	}
	public static void queryfour(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();	 
		 String sql="select ENAME , ADDRESS from employee,department"
			 		+ " where employee.DNO=department.DNO and "
			 		+ "DNAME = '"+parameters.get(0)+"' and SALARY < "+Integer.valueOf(parameters.get(1));
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ENAME\tADDRESS\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1)+"\t"+rs.getString(2));
				}
	}
	public static void queryfive(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();	 
		 String sql="select DISTINCT ENAME from works_on ,employee "
		 		+ "where employee.ESSN=works_on.ESSN and works_on.ESSN "
		 		+ "not in (select ESSN from works_on where "
		 		+ "PNO = '"+parameters.get(0)+"')";
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ENAME\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1));
				}
	}
	public static void querysix(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();
		 String sql="select ENAME,DNAME from employee, department"
		 		+ " where employee.DNO = department.DNO and "
		 		+ "SUPERSSN in (select ESSN from employee where ENAME "
		 		+ "='"+parameters.get(0)+"')";
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ENAME\tDNAME\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1)+"\t"+rs.getString(2));
				}
	}
	public static void queryseven(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();
		 String sql="select DISTINCT ESSN from works_on where PNO = '"+parameters.get(0)+"' "
		 		+ "and ESSN in (select DISTINCT ESSN from works_on where PNO"
		 		+ "='"+parameters.get(1)+"')";
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ESSN\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1));
				}
	}
	public static void queryeight(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();
		 String sql="select DNAME from department,employee where "
		 		+ "department.DNO=employee.DNO group by employee.DNO "
		 		+ "having avg(SALARY) < "+Integer.valueOf(parameters.get(0));
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("DNAME\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1));
				}
	}
	public static void querynine(ArrayList<String> parameters) throws ClassNotFoundException, SQLException {
		 Connection conn=connection();
		 String sql="select ENAME from works_on,employee"
		 		+ " where employee.ESSN=works_on.ESSN group by works_on.ESSN having "
		 		+ " count(PNO)> "+(Integer.valueOf(parameters.get(0))-1)+" and sum(HOURS)"
		 				+ " < "+(Integer.valueOf(parameters.get(1))+1);
		 PreparedStatement psmt=conn.prepareStatement(sql);
		 ResultSet rs=psmt.executeQuery();
		 System.out.print("ENAME\n");
		 while(rs.next())
			{
			 System.out.println(rs.getString(1));
				}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	  System.out.println("请输入命令(格式为company_query –q <Number> -p [Parameters])");
	  Scanner input=new Scanner(System.in);
	  input.next();
	  input.next();
	  int n=input.nextInt();
	// System.out.println(n);
	  if(n==1) {
		  input.next();
		  String pString=input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		 // System.out.println(pArrayList.get(0));
		  queryone(pArrayList);
		  input.close();
	  }
	  else if(n==2) {
		  input.next();
		  String pString=input.next();
		  String pString1=input.next();
		  if(pString1!=";") {
			  pString=pString+" "+pString1;
		  }
		//  System.out.println(pString1);
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  pArrayList.add(pString);
		  querytwo(pArrayList);
		  input.close();
	  }
	  else if(n==3) {
		  input.next();
		  String pString=input.next()+" "+input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  querythree(pArrayList);
		  input.close();
	  }
	  else if(n==4) {
		  input.next();
		  String pString=input.next()+" "+input.next();
		  String pString1=input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  pArrayList.add(pString1);
		  queryfour(pArrayList);
		  input.close();
	  }
	  else if(n==5) {
		  input.next();
		  String pString=input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  queryfive(pArrayList);
		  input.close();
	  }
	  else if(n==6) {
		  input.next();
		  String pString=input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  querysix(pArrayList);
		  input.close();
	  }
	  else if(n==7) {
		  input.next();
		  String pString=input.next();
		  String pString1=input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  pArrayList.add(pString1);
		  queryseven(pArrayList);
		  input.close();
	  }
	  else if(n==8) {
		  input.next();
		  String pString=input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  queryeight(pArrayList);
		  input.close();
	  }
	  else if(n==9) {
		  input.next();
		  String pString=input.next();
		  String pString1=input.next();
		  ArrayList<String> pArrayList=new ArrayList<String>();
		  pArrayList.add(pString);
		  pArrayList.add(pString1);
		  querynine(pArrayList);
		  input.close();
	  }
	}
}
