package systemdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Design a system for implementation of stock market. Buyer and seller case.
 * stock server will receive buy request and sell request. A sell can be
 * successful when it matches the quantity and price for same stock. There will
 * be many request for buyer and seller . Need to select the appropriate one.
 * Which data structure will be used ? how to handle concurrency issue?class
 * diagram?
 * 
 * @author sethia
 *
 */
class User {
	Stockmarket sm;

	User(Stockmarket s1) {
		sm = s1;
	}

	public void sendBuyRequest(String s, int p, int q) {
		Request req = new Request(p, q);
		sm.buyRequestHandler(s, req);
	}

	public void sendSellRequest(String s, int p, int q) {
		Request req = new Request(p, q);
		sm.sellRequestHandler(s, req);
	}

}

class Request implements Comparable<Request> {

	int price;
	int quantity;

	Request(int p, int q) {
		price = p;
		quantity = q;
	}

	@Override
	public int compareTo(Request o) {
		if (price == o.price && quantity == o.quantity)
			return 0;
		return 1;
	}
}

interface Stockmarket {
	public void buyRequestHandler(String stock, Request req);

	public void sellRequestHandler(String stock, Request req);
}

public class StockServer implements Stockmarket {
	ConcurrentHashMap<String, List<Request>> BuyRequest = new ConcurrentHashMap<>();
	ConcurrentHashMap<String, List<Request>> SellRequest = new ConcurrentHashMap<>();

	public void buyRequestHandler(String s, Request req) {
		boolean requestCompleted = false;
		if (SellRequest.containsKey(s) && SellRequest.get(s).contains(req)) {
			requestCompleted = true;
			SellRequest.get(s).remove(req);
		} else if (BuyRequest.containsKey(s))
			BuyRequest.get(s).add(req);
		else {
			List<Request> r = new ArrayList<Request>();
			r.add(req);
			BuyRequest.put(s, r);
		}
	}

	public void sellRequestHandler(String s, Request req) {
		boolean requestCompleted = false;
		if (BuyRequest.containsKey(s) && BuyRequest.get(s).contains(req)) {
			requestCompleted = true;
			BuyRequest.get(s).remove(req);
		} else if (SellRequest.containsKey(s))
			SellRequest.get(s).add(req);
		else {
			List<Request> r = new ArrayList<Request>();
			r.add(req);
			SellRequest.put(s, r);
		}
	}
}
