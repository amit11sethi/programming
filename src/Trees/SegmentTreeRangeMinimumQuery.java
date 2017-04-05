package Trees;

import java.util.Arrays;

public class SegmentTreeRangeMinimumQuery {
int st[];
	
  SegmentTreeRangeMinimumQuery(int arr[], int n)
    {
        // Allocate memory for segment tree
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
 
        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;
 
        st = new int[max_size]; // Memory allocation
 
        constructSTUtil(arr, 0, n - 1, 0);
    }
  
  void constructSTUtil(int arr[], int ss, int se, int si)
  {
      // If there is one element in array, store it in current node of
      // segment tree and return
      if (ss == se) {
          st[si] = arr[ss];
          //return arr[ss];
      } else {

      // If there are more than one elements, then recur for left and
      // right subtrees and store the sum of values in this node
      int mid = getMid(ss, se);
      constructSTUtil(arr, ss, mid, si * 2 + 1);
      constructSTUtil(arr, mid + 1, se, si * 2 + 2);
      st[si] = Math.min(st[si*2+1] , st[si*2+ 2]);
     }
  }
	
	
	
	
	
	// A utility function to get the middle index from corner indexes.
  int getMid(int s, int e) {
      return s + (e - s) / 2;
  }
  int getMinUtil(int ss, int se, int qs, int qe, int si)
  {
      // If segment of this node is a part of given range, then return
      // the sum of the segment
      if (qs <= ss && qe >= se)
          return st[si];

      // If segment of this node is outside the given range
      if (se < qs || ss > qe)
          return Integer.MAX_VALUE;

      // If a part of this segment overlaps with the given range
      int mid = getMid(ss, se);
      return Math.min(getMinUtil(ss, mid, qs, qe, 2 * si + 1) ,
    		  getMinUtil(mid + 1, se, qs, qe, 2 * si + 2));
  }
  
  int getMin(int n, int qs, int qe)
  {
      // Check for erroneous input values
      if (qs < 0 || qe > n - 1 || qs > qe) {
          System.out.println("Invalid Input");
          return -1;
      }
      return getMinUtil(0, n - 1, qs, qe, 0);
  }
  
  
  public static void main(String args[])
  {
      int arr[] = {1, 13, 5, 7, 9, 11};
      int n = arr.length;
      SegmentTreeRangeMinimumQuery  tree = new SegmentTreeRangeMinimumQuery(arr, n);
      System.out.println("Segment Array " + Arrays.toString(tree.st));

      // Build segment tree from given array

      // Print sum of values in array from index 1 to 3
      System.out.println("Min of values in given range = " +
                         tree.getMin(n, 1, 5));

      // Update: set arr[1] = 10 and update corresponding segment
      // tree nodes
      //tree.updateValue(arr, n, 1, 10);

      // Find sum after the value is updated
      /*System.out.println("Updated sum of values in given range = " +
              tree.getMin(n, 1, 2));*/
  }

}
