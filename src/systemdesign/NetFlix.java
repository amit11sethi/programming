package systemdesign;

public class NetFlix {
    VideoLibrary library = new VideoLibrary();
    UserManager userManager = new UserManager();
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Provides
	VideoPlayer getVideoPlayer() {
		return new HdVideoPlayer();
	}

}

//Lets say we want to scale the Video Library. Here HashMap is acting as our storage system. In distributed system we should be able to add new nodes 
// Here we will add new Maps List<HashMap<Integer, Video> hm. Apply MD5 hash on key. getListIndexforKey(keyHashCode)
// 
class LibraryNode {
	int key;
	Video v;
}


class VideoLibrary {
	HashMap<Integer, Video> hm;
	addVideo(Video v) {
		
	} 
	removeVideo(Video v) {
		
	}
}
 class Video {
	 int Id;
	 String details;
	 List<String> tags;
 }
 
 class User {
	 int userId;
	 String details;
	 List<String> tags;
	 VideoPlayer v;
	 renewMemberShip() { 
	 }
	 @Inject
	 setVideoPlayer(VideoPlayer v);
	 v.play(Video v);
	 
	 
 }
 
 class UserManager {
	 HashMap<Integer, User> hm;
	 addUser(int id, string details) {
		 
	 }
	 find(int id) {
		 
	 }
	 remove(int id) {
		 
	 }
 }
 interface VideoPlayer {
	 void play(Video v);
 }
 
 class HdPlayer implements VideoPlayer {
	 
 }
 
 class FullHdPlayer implements VideoPlayer {
	 
 }
 
 /**
 Dynamo’s partitioning scheme relies on consistent
 hashing to distribute the load across multiple storage hosts. In
 consistent hashing [10], the output range of a hash function is
 treated as a fixed circular space or “ring” (i.e. the largest hash
 value wraps around to the smallest hash value). Each node in the
 system is assigned a random value within this space which
 represents its “position” on the ring. Each data item identified by
 a key is assigned to a node by hashing the data item’s key to yield
 its position on the ring, and then walking the ring clockwise to
 find the first node with a position larger than the item’s position. 
 Thus, each node becomes responsible for the region in the ring
between it and its predecessor node on the ring. The principle
advantage of consistent hashing is that departure or arrival of a
node only affects its immediate neighbors and other nodes remain
unaffected.
The basic consistent hashing algorithm presents some challenges.
First, the random position assignment of each node on the ring
leads to non-uniform data and load distribution. Second, the basic
algorithm is oblivious to the heterogeneity in the performance of
nodes. To address these issues, Dynamo uses a variant of
consistent hashing (similar to the one used in [10, 20]): instead of
mapping a node to a single point in the circle, each node gets
assigned to multiple points in the ring. To this end, Dynamo uses
the concept of “virtual nodes”. A virtual node looks like a single
node in the system, but each node can be responsible for more
than one virtual node. Effectively, when a new node is added to
the system, it is assigned multiple positions (henceforth, “tokens”)
in the ring. The process of fine-tuning Dynamo’s partitioning
scheme is discussed in Section 6.
Using virtual nodes has the following advantages:
• If a node becomes unavailable (due to failures or routine
maintenance), the load handled by this node is evenly
dispersed across the remaining available nodes.
• When a node becomes available again, or a new node is
added to the system, the newly available node accepts a
roughly equivalent amount of load from each of the other
available nodes.
• The number of virtual nodes that a node is responsible can
decided based on its capacity, accounting for heterogeneity
in the physical infrastructure. 


Hinted HandOff
if node A is temporarily down or
unreachable during a write operation then a replica that would
normally have lived on A will now be sent to node D. This is done
to maintain the desired availability and durability guarantees. The
replica sent to D will have a hint in its metadata that suggests
which node was the intended recipient of the replica (in this case
A). Nodes that receive hinted replicas will keep them in a
separate local database that is scanned periodically. Upon
detecting that A has recovered, D will attempt to deliver the
replica to A. Once the transfer succeeds, D may delete the object
from its local store without decreasing the total number of replicas
in the system.
Using hinted handoff, Dynamo ensures that the read and write
operations are not failed due to temporary node or network
failures. Applications that need the highest level of availability
can set W to 1, which ensures that a write is accepted as long as a
single node in the system has durably written the key it to its local
store. Thus, the write request is only rejected if all nodes in the
system are unavailable.

 