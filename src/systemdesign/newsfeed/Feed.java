package systemdesign.newsfeed;


public class Feed {
	String feedId;
	String feedType;
	String content;
	String metadata;
	volatile long  feedDate;
	
	public long getFeedDate() {
		return feedDate;
	}
	
	public int feedScore() {
		
		return 1;
		
	}

}

