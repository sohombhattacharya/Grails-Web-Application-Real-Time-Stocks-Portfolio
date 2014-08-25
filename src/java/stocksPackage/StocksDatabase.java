package stocksPackage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StocksDatabase {
	Connection con;
	int getAccountInfoCounter = 0;

	
	class AccountInfos{
		String ticker;
		double quantity; 
		
		AccountInfos(){}
	}
	
	StocksDatabase(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:login_dsm");
		}
	
		catch (Exception e){
		//think of something to figure out what exception it is and return somthing to 
		//front-end so that user knows what to do 
			System.out.println(e);

		}
	}
	public void runQuery(String command){
		try{
			Statement st = con.createStatement();
			st.executeUpdate(command);
		}
		catch (Exception e){
			
			//think of something to figure out what exception it is and return somthing to 
			//front-end so that user knows what to do 
			System.out.println(e);
			}
	}
	
	public boolean checkAccount(String u, String p){
		
		boolean result = false; 
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users where UserNM='"+u+"' and PassWD='"+p+"'");//add where clause
			while (rs.next()){
				String userNM = rs.getString(1);
				String passWD = rs.getString(2);
				if (u.equals(userNM) && p.equals(passWD)){
					System.out.println("match");
					result = true; 
				}
			}
		}
		catch (Exception e){
			
			//think of something to figure out what exception it is and return somthing to 
			//front-end so that user knows what to do 
			System.out.println(e);

			}
		return result;
		
	}
	
	public boolean createAccount(String u, String p){
		boolean result = false; 
		try{
			Statement st = con.createStatement();
			st.executeUpdate("insert users values ('"+u+"', '"+p+"')");
			result = true; 
		}
		catch (Exception e){
			
			//think of something to figure out what exception it is and return somthing to 
			//front-end so that user knows what to do 
			System.out.println(e);
			

			}
		return result;
		
	}
	
	public String getAccountID(String u){
		String aID = "";
		
		try{
			Statement st = con.createStatement();
			ResultSet ras = st.executeQuery("select * from users where UserNM='"+u+"'");
			while (ras.next()){
				String testId = ras.getString(3);
				aID = testId;
				System.out.println("test");
				break;
			}
		}
		catch (Exception e){
			
			//think of something to figure out what exception it is and return somthing to 
			//front-end so that user knows what to do 
			System.out.println(e);

			}
		
		return aID;
	}
	
	public List<AccountInfos> getAccountInfo(String aID){
		
		List<AccountInfos> infoRows = new ArrayList<AccountInfos>();
		System.out.println("XXXXXXX =" + aID);
		ResultSet rs=null;
		Statement st=null;
		try{
			System.out.println("creating st");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println("creating rs");
			rs = st.executeQuery("select * from positions where AccountID="+aID);
				
			while (rs.next()){
				String ticker = rs.getString(2);
				double quantity = rs.getDouble(3);
				AccountInfos one = new AccountInfos();
				one.ticker = ticker;
				one.quantity = quantity;
				infoRows.add(one);

			}
			

		}
		catch (Exception e){
			
			//think of something to figure out what exception it is and return somthing to 
			//front-end so that user knows what to do 
			System.out.println(e);

			}
		finally{
			try {
			rs.close();
			st.close();
			} catch( Exception e ){}
		}
		
		return infoRows;
		
	}
	
	public boolean addTicker(String ticker, double quantity, int aID){
		
		boolean result = true; 
		
		try{
			Statement st = con.createStatement();
			st.executeUpdate("insert positions values ("+aID+", '"+ticker+"', "+quantity+")");
		}
		catch (Exception e){
			
			//think of something to figure out what exception it is and return somthing to 
			//front-end so that user knows what to do 
			System.out.println(e);
			result = false; 
			}
		
		
		return result; 
	}
	
}
