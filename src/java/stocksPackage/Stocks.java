package stocksPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Stocks {

	class StockTAP{
		String ticker;
		double price; 
		double quantity;
	}
		public double getPrice(String key) throws IOException{
			String stockPrice = "";
			String ticker = key.toLowerCase();
			String website = "http://finance.yahoo.com/q;_ylt=AugwQt0Hk_iaaowuO255_2uiuYdG;_ylu=X3oDMTBxdGVyNzJxBHNlYwNVSCAzIERlc2t0b3AgU2VhcmNoIDEx;_ylg=X3oDMTBsdWsyY2FpBGxhbmcDZW4tVVMEcHQDMgR0ZXN0Aw--;_ylv=3?s="
					+ ticker
					+ "&uhb=uhb2&type=2button&fr=uh3_finance_vert_gs";

			URL financeYahoo = new URL(website);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					financeYahoo.openStream()));

			String line;

			while ((line = in.readLine()) != null) {
				if (line.contains("<span id=\"yfs_l84_" + ticker + "\">") == true) 
				{
					int firstFound = line.indexOf("<span id=\"yfs_l84_"
							+ ticker + "\">");
					int secondFound = line.indexOf("</span>",
							firstFound + 1);
					String spanNode = line.substring(firstFound,
							secondFound + 7);
					int secondArrow = spanNode.indexOf('>');
					int thirdArrow = spanNode.indexOf('<', secondArrow + 1);
					stockPrice = spanNode.substring(secondArrow + 1,thirdArrow);
					
				}
			}
			double finalPrice = Double.parseDouble(stockPrice);
			return finalPrice;
		}
}
