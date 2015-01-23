package online_code_test;

//Kaeru Jump 
//http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=2223

public class KaeruJump {

	char l_sym='o';
	char s_sym ='.';		
	char[][] A;
	int ci = -1, cj = -1, leaf = 0;
	char f = 'F';
	StringBuffer sb = new StringBuffer();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KaeruJump().run();
	}

	void printMap() {
		System.out.println("---# of leaf:" + leaf + "---");
		for (int i=0 ; i <A.length ; i++) {
			for (int j=0 ; j<A[0].length ; j++) {
				System.out.print(A[i][j]);
				if(j!=A[0].length-1) {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
		System.out.println(sb.toString());
	}
	
	void run() {
		//A = new char[][] { { '.', 'o', '.' }, { '.', 'o', '.' },
		//		{ '.', 'o', '.' }, { '.', 'U', '.' } };
		//A = new char[][] { { 'o', 'o', '.' }, { '.', '.', '.' },
		//		{ '.', 'o', 'o' }, { '.', 'U', 'o' } };
		//A = new char[][] { { 'D', '.', '.' }, { '.', '.', '.' },
		//		{ '.', 'o', 'o' }, { '.', 'o', 'o' } };
		
		//A = new char[][]{{'.','.'},{'.','U'}};
		
		A = new char[][]{
		{'.','o','.','.','.','.','o','.','.','.'},
		{'o','.','o','o','.','.','.','.','.','.'},
		{'.','.','o','o','.','.','o','o','.','.'},
		{'.','.','o','.','.','.','.','.','.','.'},
		{'.','.','o','o','.','.','o','o','.','.'},
		{'.','.','o','.','.','.','o','.','o','.'},
		{'o','.','.','U','.','o','.','.','.','.'},
		{'o','o','.','.','.','.','.','.','o','o'},
		{'o','o','.','.','.','.','.','.','.','.'},
		{'o','o','.','.','o','o','.','.','.','.'}
		};
		
		/*
		A = new char[][]{
        {'s','L','s','s','s','s','L','s','s','s'},
        {'L','s','L','L','s','s','s','s','s','s'},
        {'s','s','L','L','s','s','L','L','s','s'},
        {'s','s','L','s','s','s','s','s','s','s'},
        {'s','s','L','L','s','s','L','L','s','s'},
        {'s','s','L','s','s','s','L','s','L','s'},
        {'L','s','s','U','s','L','s','s','s','s'},
        {'L','L','s','s','s','s','s','s','L','L'},
        {'L','L','s','s','s','s','s','s','s','s'},
        {'L','L','s','s','L','L','s','s','s','s'}
		};*/
		
		System.out.println(solution());
	}

	String solution() {

		// find the current frog position and status and count the leaf number
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				// System.out.println(i+" "+j);
				if (A[i][j] == l_sym) {
					leaf++;
				}
				if (A[i][j] != s_sym && A[i][j] != l_sym) {
					ci = i;
					cj = j;
					f = A[i][j];
				}
			}
		}
		//System.out.println(ci + " " + cj + " " + f+" "+leaf);
		//this.printMap();
		if (rec()) return sb.toString();
		return "failed to find the solution";
	}

	boolean rec() {
		// start search for the solution
		// f = current frog status {U,L,R,D}
		//if (leaf == 0) return true;
		//this.printMap();
		switch (f) {
		case 'U':
			if (goUp())
				return true;
			else if (goLeft())
				return true;
			else if (goRight())
				return true;
			return false;
		case 'L':
			if (goLeft())
				return true;
			else if (goDown())
				return true;
			else if (goUp())
				return true;
			return false;
		case 'R':
			if (goRight())
				return true;
			else if (goDown())
				return true;
			else if (goUp())
				return true;
			return false;
		case 'D':
			if (goLeft())
				return true;
			if (goRight())
				return true;
			else if (goDown())
				return true;
			return false;
		}
		return false;
	}

	boolean goLeft() {
		// test left possibility
		// store current status for recovery
		char tmpf = f;
		int tmpi = ci, tmpj = cj;
		int goi, goj;
		for (int i = cj - 1; i >= 0; i--) {
			if (A[ci][i] == l_sym) {
				leaf--;
				f = 'L'; // next direction
				sb.append(f);
				if (leaf == 0)
					return true; // end the search
				// update map
				A[ci][cj] = s_sym; // current position do not have leaf anymore
				cj = i; // next position
				A[ci][cj] = f;
				goi = ci; // record next position for recovery
				goj = cj; // record next position for recovery
				//this.printMap();
				if (!rec()) {
					// this move is totally a mistake, begin recovery
					ci = tmpi;
					cj = tmpj;
					A[goi][goj] = l_sym;
					f = tmpf;
					if (sb.length() > 0) {
						sb.setLength(sb.length() - 1);
					}
					leaf++;
					return false;
				}
				return true;
			}
		}
		return false;
	}

	boolean goUp() {
		// test up possibility
		// store current status for recovery
		char tmpf = f;
		int tmpi = ci, tmpj = cj;
		int goi, goj;
		for (int i = ci - 1; i >= 0; i--) {
			//System.out.println(i+" "+cj);
			//System.out.println(A[2][1]);
			if (A[i][cj] == l_sym) {
				leaf--;
				f = 'U'; // next direction
				sb.append(f);
				if (leaf == 0)
					return true; // end the search
				// update map
				A[ci][cj] = s_sym; // current position do not have leaf anymore
				ci = i; // next position
				A[ci][cj] = f;
				goi = ci; // record next position for leaf recovery
				goj = cj; // record next position for leaf recovery
				//this.printMap();
				if (!rec()) {
					// this move is totally a mistake, begin recovery
					ci = tmpi;
					cj = tmpj;
					A[goi][goj] = l_sym;
					f = tmpf;
					if (sb.length() > 0) {
						sb.setLength(sb.length() - 1);
					}
					leaf++;
					return false;
				}
				return true;
			}
		}
		return false;
	}

	boolean goRight() {
		// test right possibility
		// store current status for recovery
		char tmpf = f;
		int tmpi = ci, tmpj = cj;
		int goi, goj;
		for (int i = cj + 1; i < A[ci].length; i++) {
			if (A[ci][i] == l_sym) {
				leaf--;
				f = 'R'; // next direction
				sb.append(f);
				if (leaf == 0)
					return true; // end the search
				// update map
				A[ci][cj] = s_sym; // current position do not have leaf anymore
				cj = i; // next position
				A[ci][cj] = f;
				goi = ci; // record next position for leaf recovery
				goj = cj; // record next position for leaf recovery
				//this.printMap();
				if (!rec()) {
					// this move is totally a mistake, begin recovery
					ci = tmpi;
					cj = tmpj;
					A[goi][goj] = l_sym;
					f = tmpf;
					if (sb.length() > 0) {
						sb.setLength(sb.length() - 1);
					}
					leaf++;
					return false;
				}
				return true;
			}
		}
		return false;
	}

	boolean goDown() {
		// test down possibility
		// store current status for recovery
		char tmpf = f;
		int tmpi = ci, tmpj = cj;
		int goi, goj;
		for (int i = ci+1; i < A.length; i++) {
			if (A[i][cj] == l_sym) {
				leaf--;
				f = 'D'; // next direction
				sb.append(f);
				if (leaf == 0)
					return true; // end the search
				// update map
				A[ci][cj] = s_sym; // current position do not have leaf anymore
				ci = i; // next position
				A[ci][cj] = f;
				goi = ci; // record next position for leaf recovery
				goj = cj; // record next position for leaf recovery
				//this.printMap();
				if (!rec()) {
					// this move is totally a mistake, begin recovery
					ci = tmpi;
					cj = tmpj;
					A[goi][goj] = l_sym;
					f = tmpf;
					if (sb.length() > 0) {
						sb.setLength(sb.length() - 1);
					}
					leaf++;
					return false;
				}
				return true;
			}
		}
		return false;
	}
}