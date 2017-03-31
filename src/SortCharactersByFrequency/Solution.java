package SortCharactersByFrequency;

/**
Given a string, sort it in decreasing order based on the frequency of characters.
**/

public class Solution {
    public String frequencySort(String s) {
        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<Map.Entry<Character,Integer>>((m1, m2) -> m2.getValue() - m1.getValue());
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            hm.put(c, hm.getOrDefault(c,0) + 1);
        }
        for(Map.Entry<Character,Integer> entry : hm.entrySet()) {
            pq.offer(entry);
        }
        char[] chars = new char[s.length()]; int j = 0;
        while(!pq.isEmpty() && j <= s.length()-1) {
            Map.Entry<Character, Integer> e = pq.poll();
            for(int i =1; i <= e.getValue(); i++)
              chars[j++] = e.getKey();
        }
        return String.valueOf(chars);
    }
}
