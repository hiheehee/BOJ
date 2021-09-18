import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5373 {
	
	static String U[][] = {{"w","w","w"}, {"w","w","w"}, {"w","w","w"}};
	static String F[][] = {{"r","r","r"}, {"r","r","r"}, {"r","r","r"}};
	static String D[][] = {{"y","y","y"}, {"y","y","y"}, {"y","y","y"}};
	static String B[][] = {{"o","o","o"}, {"o","o","o"}, {"o","o","o"}};
	static String L[][] = {{"g","g","g"}, {"g","g","g"}, {"g","g","g"}};
	static String R[][] = {{"b","b","b"}, {"b","b","b"}, {"b","b","b"}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			init();
			for(int i = 0; i < n; i++) {
				String str[] = st.nextToken().split("");
				if(str[0].equals("U")) {
					up(str[1]);
				}else if(str[0].equals("D")){
					down(str[1]);
				}else if(str[0].equals("F")) {
					front(str[1]);
				}else if(str[0].equals("B")) {
					back(str[1]);
				}else if(str[0].equals("L")) {
					left(str[1]);
				}else if(str[0].equals("R")) {
					right(str[1]);
				}
			}
			print();
		}
	}
	
	static void print() {
		System.out.println(U[0][0]+U[0][1]+U[0][2]);
		System.out.println(U[1][0]+U[1][1]+U[1][2]);
		System.out.println(U[2][0]+U[2][1]+U[2][2]);
		
		/* All print
		System.out.println("      "+U[0][0]+" "+U[0][1]+" "+U[0][2]);
		System.out.println("      "+U[1][0]+" "+U[1][1]+" "+U[1][2]);
		System.out.println("      "+U[2][0]+" "+U[2][1]+" "+U[2][2]);

		System.out.println(L[0][0]+" "+L[0][1]+" "+L[0][2]+" "+F[0][0]+" "+F[0][1]+" "+F[0][2]+" "+R[0][0]+" "+R[0][1]+" "+R[0][2]+" "+B[2][2]+" "+B[2][1]+" "+B[2][0]);
		System.out.println(L[1][0]+" "+L[1][1]+" "+L[1][2]+" "+F[1][0]+" "+F[1][1]+" "+F[1][2]+" "+R[1][0]+" "+R[1][1]+" "+R[1][2]+" "+B[1][2]+" "+B[1][1]+" "+B[1][0]);
		System.out.println(L[2][0]+" "+L[2][1]+" "+L[2][2]+" "+F[2][0]+" "+F[2][1]+" "+F[2][2]+" "+R[2][0]+" "+R[2][1]+" "+R[2][2]+" "+B[0][2]+" "+B[0][1]+" "+B[0][0]);
		
		System.out.println("      "+D[0][0]+" "+D[0][1]+" "+D[0][2]);
		System.out.println("      "+D[1][0]+" "+D[1][1]+" "+D[1][2]);
		System.out.println("      "+D[2][0]+" "+D[2][1]+" "+D[2][2]);*/
	}
	
	static void init() {
		for(int i = 0; i < 3; i++) for(int j = 0; j < 3; j++) U[i][j] = "w";
		for(int i = 0; i < 3; i++) for(int j = 0; j < 3; j++) F[i][j] = "r";
		for(int i = 0; i < 3; i++) for(int j = 0; j < 3; j++) D[i][j] = "y";
		for(int i = 0; i < 3; i++) for(int j = 0; j < 3; j++) B[i][j] = "o";
		for(int i = 0; i < 3; i++) for(int j = 0; j < 3; j++) L[i][j] = "g";
		for(int i = 0; i < 3; i++) for(int j = 0; j < 3; j++) R[i][j] = "b";
	}
	
	static void up(String d) {
		if(d.equals("+")) { // 시계 방향
			String first[] = {B[2][2], B[2][1], B[2][0]};

			B[2][2] = L[0][0];
			B[2][1] = L[0][1];
			B[2][0] = L[0][2];

			L[0][0] = F[0][0];
			L[0][1] = F[0][1];
			L[0][2] = F[0][2];
			F[0][0] = R[0][0];
			F[0][1] = R[0][1];
			F[0][2] = R[0][2];
			
			R[0][0] = first[0];
			R[0][1] = first[1];
			R[0][2] = first[2];
		}else { // 반 시계 방향
			String first[] = {B[2][0], B[2][1], B[2][2]};
			B[2][0] = R[0][2];
			B[2][1] = R[0][1];
			B[2][2] = R[0][0];
			
			R[0][2] = F[0][2];
			R[0][1] = F[0][1];
			R[0][0] = F[0][0];
			
			F[0][2] = L[0][2];
			F[0][1] = L[0][1];
			F[0][0] = L[0][0];
			
			L[0][2] = first[0];
			L[0][1] = first[1];
			L[0][0] = first[2];
		}
		cube("u", d);
	}
	
	static void down(String d) {
		if(d.equals("+")) { // 시계 방향
			String first[] = {F[2][2], F[2][1], F[2][0]};
			F[2][2] = L[2][2];
			F[2][1] = L[2][1];
			F[2][0] = L[2][0];
			
			L[2][2] = B[0][0];
			L[2][1] = B[0][1];
			L[2][0] = B[0][2];
			
			B[0][0] = R[2][2];
			B[0][1] = R[2][1];
			B[0][2] = R[2][0];
			
			R[2][2] = first[0];
			R[2][1] = first[1];
			R[2][0] = first[2];
		}else { // 반 시계 방향
			String first[] = {F[2][0], F[2][1], F[2][2]};
			F[2][0] = R[2][0];
			F[2][1] = R[2][1];
			F[2][2] = R[2][2];
			
			R[2][0] = B[0][2];
			R[2][1] = B[0][1];
			R[2][2] = B[0][0];
			
			B[0][2] = L[2][0];
			B[0][1] = L[2][1];
			B[0][0] = L[2][2];
			
			L[2][0] = first[0];
			L[2][1] = first[1];
			L[2][2] = first[2];
		}
		cube("d", d);
	}
	
	static void front(String d) {
		if(d.equals("+")) { // 시계 방향
			String first[] = {U[2][2], U[2][1], U[2][0]};
			U[2][2] = L[0][2];
			U[2][1] = L[1][2];
			U[2][0] = L[2][2];
			
			L[0][2] = D[0][0];
			L[1][2] = D[0][1];
			L[2][2] = D[0][2];
			
			D[0][0] = R[2][0];
			D[0][1] = R[1][0];
			D[0][2] = R[0][0];
			
			R[2][0] = first[0];
			R[1][0] = first[1];
			R[0][0] = first[2];
		}else { // 반 시계 방향
			String first[] = {U[2][0], U[2][1], U[2][2]};
			U[2][0] = R[0][0];
			U[2][1] = R[1][0];
			U[2][2] = R[2][0];
			
			R[0][0] = D[0][2];
			R[1][0] = D[0][1];
			R[2][0] = D[0][0];
			
			D[0][2] = L[2][2];
			D[0][1] = L[1][2];
			D[0][0] = L[0][2];
			
			L[2][2] = first[0];
			L[1][2] = first[1];
			L[0][2] = first[2];
		}
		cube("f", d);
	}
	
	static void back(String d) {
		if(d.equals("+")) { // 시계 방향
			String first[] = {D[2][2], D[2][1], D[2][0]};
			D[2][2] = L[2][0];
			D[2][1] = L[1][0];
			D[2][0] = L[0][0];
			
			L[2][0] = U[0][0];
			L[1][0] = U[0][1];
			L[0][0] = U[0][2];
			
			U[0][0] = R[0][2];
			U[0][1] = R[1][2];
			U[0][2] = R[2][2];
			
			R[0][2] = first[0];
			R[1][2] = first[1];
			R[2][2] = first[2];
		}else { // 반 시계 방향
			String first[] = {D[2][0], D[2][1], D[2][2]};
			D[2][0] = R[2][2];
			D[2][1] = R[1][2];
			D[2][2] = R[0][2];
			
			R[2][2] = U[0][2];
			R[1][2] = U[0][1];
			R[0][2] = U[0][0];
			
			U[0][2] = L[0][0];
			U[0][1] = L[1][0];
			U[0][0] = L[2][0];
			
			L[0][0] = first[0];
			L[1][0] = first[1];
			L[2][0] = first[2];
		}
		cube("b", d);
	}
	
	static void left(String d) {
		if(d.equals("+")) { // 시계 방향
			String first[] = {B[0][0], B[1][0], B[2][0]};
			B[0][0] = D[0][0];
			B[1][0] = D[1][0];
			B[2][0] = D[2][0];
			
			D[0][0] = F[0][0];
			D[1][0] = F[1][0];
			D[2][0] = F[2][0];
			
			F[0][0] = U[0][0];
			F[1][0] = U[1][0];
			F[2][0] = U[2][0];
			
			U[0][0] = first[0];
			U[1][0] = first[1];
			U[2][0] = first[2];
		}else { // 반 시계 방향
			String first[] = {U[0][0], U[1][0], U[2][0]};
			U[0][0] = F[0][0];
			U[1][0] = F[1][0];
			U[2][0] = F[2][0];
			
			F[0][0] = D[0][0];
			F[1][0] = D[1][0];
			F[2][0] = D[2][0];
			
			D[0][0] = B[0][0];
			D[1][0] = B[1][0];
			D[2][0] = B[2][0];
			
			B[0][0] = first[0];
			B[1][0] = first[1];
			B[2][0] = first[2];
		}
		cube("l", d);
	}
	
	static void right(String d) {
		if(d.equals("+")) { // 시계 방향
			String first[] = {U[0][2], U[1][2], U[2][2]};
			U[0][2] = F[0][2];
			U[1][2] = F[1][2];
			U[2][2] = F[2][2];
			
			F[0][2] = D[0][2];
			F[1][2] = D[1][2];
			F[2][2] = D[2][2];
			
			D[0][2] = B[0][2];
			D[1][2] = B[1][2];
			D[2][2] = B[2][2];
			
			B[0][2] = first[0];
			B[1][2] = first[1];
			B[2][2] = first[2];
		}else { // 반 시계 방향
			String first[] = {B[0][2], B[1][2], B[2][2]};
			B[0][2] = D[0][2];
			B[1][2] = D[1][2];
			B[2][2] = D[2][2];
			
			D[0][2] = F[0][2];
			D[1][2] = F[1][2];
			D[2][2] = F[2][2];
			
			F[0][2] = U[0][2];
			F[1][2] = U[1][2];
			F[2][2] = U[2][2];
			
			U[0][2] = first[0];
			U[1][2] = first[1];
			U[2][2] = first[2];
		}
		cube("r", d);
	}
	
	static void cube(String cur, String d) {
		String str[][];
		
		if(cur.equals("u")) {
			str = U;
		}else if(cur.equals("d")){
			str = D;
		}else if(cur.equals("f")) {
			str = F;
		}else if(cur.equals("b")) {
			str = B;
		}else if(cur.equals("l")) {
			str = L;
		}else {
			str = R;
		}
		
		if(d.equals("+")) {
			int count = 2;
			while(count-- > 0) {
				// Clock
				String i = str[0][2];
				str[0][2] = str[0][1];
				str[0][1] = str[0][0];
				str[0][0] = str[1][0];
				str[1][0] = str[2][0];
				str[2][0] = str[2][1];
				str[2][1] = str[2][2];
				str[2][2] = str[1][2];
				str[1][2] = i;
			}
		}else {
			int count =2;
			while(count-- > 0) {
				// banClock
				String i = str[0][0];
				str[0][0] = str[0][1];
				str[0][1] = str[0][2];
				str[0][2] = str[1][2];
				str[1][2] = str[2][2];
				str[2][2] = str[2][1];
				str[2][1] = str[2][0];
				str[2][0] = str[1][0];
				str[1][0] = i;
			}
		}
		
		if(cur.equals("u")) {
			U = str;
		}else if(cur.equals("d")){
			D = str;
		}else if(cur.equals("f")) {
			F = str;
		}else if(cur.equals("b")) {
			B = str;
		}else if(cur.equals("l")) {
			L = str;
		}else {
			R = str;
		}
	}

}