import java.util.ArrayList;
import java.util.List;

public class AllPermInSortedOrder {
	static int kk = 0;
	public static void main(String[] args) {
		AllPermInSortedOrder obj = new AllPermInSortedOrder();
		char a[] = { 'A', 'B', 'C' };
		obj.perm(a, a.length, new ArrayList<Character>(), 0, new Boolean[] { false, false, false });
	}

	private void perm(char[] a, int len, List<Character> lis, int depth, Boolean[] visited) {
		if (depth > len)
			return;

		if (depth == len) {
			System.out.println(lis.subList(0, len));
			return;
		}

		for (int i = 0; i < len; i++) { kk++;
			if (!visited[i]) {
				lis.add(depth, a[i]);
				visited[i] = true;
				perm(a, len, lis, depth + 1, visited);
				visited[i] = false;
			}
		}
		System.out.println(kk);
	}
}