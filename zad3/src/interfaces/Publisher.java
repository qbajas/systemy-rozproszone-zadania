package interfaces;

import java.util.Set;

import main.Auction;

public interface Publisher {

	public void newHighestBid(Auction a);
	
	public Set<Auction> getAuctions();
	
}
