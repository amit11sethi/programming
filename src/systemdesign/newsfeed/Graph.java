package systemdesign.newsfeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Graph {
	private volatile static Graph g = null;
	ConcurrentHashMap<Integer, List<Integer>> adj;
	
	private Graph() {
		adj = new ConcurrentHashMap<Integer , List<Integer>>();
	}
	
	static void loadGraph() {
		g.setEdge(0, 1);
		g.setEdge(0, 2);
		g.setEdge(1, 2);
		g.removeEdge(0, 2);
		g.printGraph();
	}
	public static Graph getInstance() {
        if (g == null) {                // Single Checked
            synchronized (Graph.class) {
                if (g == null) {        // Double checked
                    g = new Graph();
                    loadGraph();
                }
            }
        }
        return g;
}




	void setEdge(int org, int dest) {

		if (adj.containsKey(org)) {

			List<Integer> list = adj.get(org);
			list.add(dest);
			adj.put(org, list);
		}

		else {

			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(dest);
			adj.put(org, list);
		}
	}

	boolean isNeighbour(int org, int dest) {

		if (!adj.containsKey(org)) {
			return false;
		}

		for (Integer i : adj.get(org)) {

			if (i == dest) {
				return true;
			}
		}

		return false;
	}

	void removeEdge(int org, int dest) {

		if (!adj.containsKey(org)) {
			System.out.println("Error.Enter a valid vertex");
		}

		else {

			Iterator<Integer> it = adj.get(org).iterator();
			while (it.hasNext()) {
				int ele = it.next();
				if (ele == dest) {
					it.remove();
				}
			}
		}
	}
	
	List<Integer> getFriends(int org) {
		return adj.get(org);
	}

	void printGraph() {

		for (Integer i : adj.keySet()) {

			List<Integer> list = adj.get(i);
			System.out.println("Key - " + i + "---> " + list);
		}
	}

	public static void main(String args[]) {
		g = getInstance();

		System.out.println(g.isNeighbour(0, 2));
	}
}
