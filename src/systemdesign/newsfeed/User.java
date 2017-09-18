package systemdesign.newsfeed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

public class User {
	int id;
	String name;
	volatile long registrationDate;
	SortedSet<Feed> publishedFeeds;
	volatile long lastvisitedDate;
	public User(int id, String name) {
	  this.id = id;
	  this.name = name;
	  this.registrationDate = System.currentTimeMillis();
	  this.lastvisitedDate = this.registrationDate - 7*24*60*60*1000;
	  this.publishedFeeds = new TreeSet<Feed>((a,b) -> Long.compare(b.getFeedDate() ,a.getFeedDate()));
	}

	public List<Feed> pullFeeds() {
	  List<Feed> res = new ArrayList<>();
	  List<Integer> myFriends = Graph.getInstance().getFriends(id);
	  PriorityQueue<Feed> friendsUpdates = new PriorityQueue<>((a,b) -> a.feedScore() - b.feedScore());
	  for(int friendId: myFriends) {
		  User friend = UserService.getInstance().getUserbyId(friendId);
		  friendsUpdates.addAll(friend.getRecentFeeds(lastvisitedDate));
	  }
	  return res;
	}
	  
	  List<Feed> getRecentFeeds(long date) {
		  Feed search = new Feed();
		  search.feedDate = date;
		  ArrayList<Feed> searchList = new ArrayList<>(publishedFeeds);
		  Comparator<Feed> dateComparator = new Comparator<Feed>() {
			  @Override
				public int compare(Feed o1, Feed o2) {
					return Long.compare(o2.getFeedDate(), o1.getFeedDate());
				}
		  };
		  int startIndex = Collections.binarySearch(searchList,search, dateComparator);
		  return searchList.subList(Math.abs(startIndex), searchList.size());
	  }

}
