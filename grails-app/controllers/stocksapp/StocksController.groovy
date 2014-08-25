package stocksapp
import stocksPackage.StocksDatabase;
import stocksPackage.StocksDatabase.AccountInfos;
import stocksPackage.Stocks;
import stocksPackage.Stocks.StockTAP;

class StocksController {

	StocksDatabase db;
	Stocks app;
	
	int appCounter = 0;
    def index() { 
		
		if (appCounter == 0){
			db = new StocksDatabase();
			app = new Stocks();
		}
		appCounter++;
	}
	
	def login(){
		
		String userName = String.valueOf(params.userNM)
		String passWord = String.valueOf(params.passWD)
		
		boolean accountCheck = db.checkAccount(userName, passWord);
		if (accountCheck == true){
			String aID = db.getAccountID(userName);
			
			
			redirect(action: "accountInfo", params: [userID: aID])
		}
		else
			flash.error = "Login unsuccessful, please try again"
		
	}
	
	def createAccount(){
		String userName = String.valueOf(params.userNm)
		String passWord = String.valueOf(params.passWd)
		
		boolean createAccount = db.createAccount(userName, passWord);
		if (createAccount == true)
			redirect(action: "accountInfo", params: [userID: db.getAccountID(userName)])
		else
			flash.error = "User name already exists, please choose another. "
			
	}
	def accountInfo(){
		String aID = params.userID;
		List<AccountInfos> accountInform = db.getAccountInfo(aID);
		StockTAP[] stocksInfo;
		double marketValue = 0;
		if (accountInform == null)
			stocksInfo = null;
		else{	
			stocksInfo = new StockTAP[accountInform.size()];
			int i = 0;
			for (AccountInfos e: accountInform){
			
			stocksInfo[i] = new StockTAP();
			stocksInfo[i].ticker = e.ticker;
			stocksInfo[i].quantity = e.quantity;
			stocksInfo[i].price = app.getPrice(e.ticker);
			marketValue += stocksInfo[i].quantity*stocksInfo[i].price;
			i++;
			
			}
		}
		//also calculate the market value of the portfolio and return it as another value in the 
		//bottom statement
		return [stocksInfo: stocksInfo, marketValue: marketValue, accountID: aID]
		
	}
	
	def addTicker(){
		
		System.out.println("ticker: "+params.ticker);
		System.out.println("quantity: "+params.quantity);
		System.out.println("userID: "+params.accountID);
		
		String ticker = String.valueOf(params.ticker);
		double quantity = Double.parseDouble(params.quantity);
		String accountID = String.valueOf(params.accountID);
		System.out.println("accountID: "+accountID);
		int uID = Integer.parseInt(accountID);
		db.addTicker(ticker, quantity, uID);
		
		redirect(action: "accountInfo", params: [userID: accountID])
		
	}
	
	
}
