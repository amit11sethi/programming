import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Permutation {
   static int kk= 0;
	public static void main(String[] args) {
		permute(new int[] {1,2,3,4});
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		permute(new int[] {1,2,3}, 0, res);
		System.out.println("Sorted Permute");
		res.forEach(item -> System.out.println(Arrays.toString(item.toArray())));
		
    }


public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    Queue<List<Integer>> Q = new LinkedList<List<Integer>>();
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(nums[0]);
    Q.add(temp);
    if(temp.size() == nums.length) res.add(0, temp);
    for(int i=1;i<nums.length; i++) {
        while(!Q.isEmpty() && Q.peek().size() == i) {
          List<Integer> item = Q.poll();
          for(int j=0; j <= i; j++) { kk++;
            List<Integer> list = new ArrayList<Integer>(item);
            list.add(j, nums[i]);
            if(list.size() == nums.length) res.add(0, list);
            else Q.add(list);
          }
        }
    }
    res.forEach(item -> System.out.println(Arrays.toString(item.toArray())));
    System.out.println(kk);
    
return res;    
}

public static void permute(int[] nums, int index, List<List<Integer>> res) {
	System.out.println("nums  = " + Arrays.toString(nums) + "  index = " + index);
	if(index == nums.length) {
		List<Integer> list = IntStream.of(nums).boxed().collect(Collectors.toList());
		res.add(list);
	}
	for(int i = index; i <nums.length;i++) {
		swap(nums, i, index);
		permute(nums, index+1, res);
		swap(nums, i, index);
	}
}

public static void swap(int[] nums, int i , int j) {
	int temp = nums[i];
	nums[i] = nums[j];
	nums[j] = temp;
}


}
