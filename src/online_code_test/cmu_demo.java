package online_code_test;

public class cmu_demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[]A = new int[]{-1,3,-4,5,1,-6,2,1};
		int[]A = new int[]{-2147483648,-2147483648,-2147483648,-2147483648,-2147483648,-2147483648,-2147483648};
		//int[]A=new int[100000];
		//for (int i=0 ; i<100000 ; i++) {
		//	A[i]=i;
		//}
		cmu_demo demo = new cmu_demo();
		System.out.println(demo.solution(A));
	}

	public int solution(int[] A) {
		// write your code in Java SE 8
		long sum=0;
		for (int i=0 ; i<A.length ; i++) {
			sum+=A[i];
		}
		long left=0, right=sum; //left=0 address sum of zero elements
		for (int i=0 ; i<A.length ; i++) {
			right-=A[i];
			if (left==right) {return i;}
			left+=A[i];
		}
		return -1;
	}

}
