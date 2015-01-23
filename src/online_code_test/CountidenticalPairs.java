package online_code_test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CountidenticalPairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] A = new int[6];
		A[0] = 3;
		A[1] = 5;
		A[2] = 6;
		A[3] = 3;
		A[4] = 3;
		A[5] = 5;

		System.out.println(new CountidenticalPairs().solution(A));
	}

	public int solution(int[] A) {
		// write your code in Java SE 8
		
		// use key-value store to store the unique pair
		LinkedHashMap<Integer,ArrayList<Integer>> lhm = new LinkedHashMap<Integer,ArrayList<Integer>>();
		for (int i=0; i<A.length; i++) {
			if (lhm.containsKey(A[i])) {
				lhm.get(A[i]).add(i);
			}
			else {
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(i);
				lhm.put(A[i], a);
			}
		}	
		
		//generate pairs
		long pairs = 0;
		java.util.Iterator<Integer> it = lhm.keySet().iterator();
		while(it.hasNext()) {
			int key = it.next();
			//System.out.println(lhm.get(key));
			ArrayList<Integer> a = lhm.get(key);
			for (int i =1 ; i<a.size() ; i++) {
				pairs += i; 
			}
			if (pairs >= 1000000000) {
				pairs = 1000000000;
				break;
			}
		}
		
		return (int) pairs;
	}
}
