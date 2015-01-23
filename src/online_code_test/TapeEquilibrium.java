package online_code_test;
class TapeEquilibrium {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]A=new int[]{-10,-20,-30,-40,100};
		System.out.println(TapeEquilibrium.solution(A));
	}
	
 public static int solution(int[] A) {
     // write your code in Java SE 8
     int sum = 0;
     for (int i=0 ; i< A.length ; i++) {
         sum+=A[i];
     }
     int right = 0, min=Integer.MAX_VALUE, abs=-1;
     for (int i=A.length-1 ; i>0  ; i--) {
    	 right+=A[i];
    	 abs = Math.abs((sum-right)-right);
    	 if (abs < min) min=abs;
     }
     return min;
 }
}