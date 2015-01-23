package online_code_test;

public class PermMissingElem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[]{1,2,3,4,5,6,7,8,9,10,12}));
	}

	public static int solution(int[] A) {
        // write your code in Java SE 8
        boolean[] tmp = new boolean[100002];//skip 0 and add N+1 bucket
        for (int i=0 ; i<A.length ; i++) {
            tmp[A[i]] = true; 
        }
        for (int i=1 ; i<=A.length ; i++) {
            if (!tmp[i]) return i;
        }
        return A.length+1;
    }
}
