import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ10875 {
	static int dxy[][] = {{1,0},{0,-1},{-1,0},{0,1}};
    static long l;
    static ArrayList<Line> al = new ArrayList<>();
    static long cx;
    static long cy;
    static int cd = 0;
    static long result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		l = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		cx = cy = 0;
		ArrayList<String> move = new ArrayList<>();
		al.add(new Line(-l-1, l+1, l+1, l+1));
		al.add(new Line(l+1, -l-1, l+1, l+1));
		al.add(new Line(-l-1, -l-1, l+1, -l-1));
		al.add(new Line(-l-1, -l-1, -l-1, l+1));
		
		for(int i = 0; i < n; i++) {
			move.add(br.readLine());
		}
		long count;
		String dir = "";
		for(int i = 0; i < n+1; i++) {
			
			if(i == n) {
				count = Long.MAX_VALUE;
				dir = "L";
			}else {
				String str[] = move.get(i).split(" ");
				dir = str[1];
				count = Long.parseLong(str[0]); // 움직일 수 있는 시간 개수
			}
			long t = Long.MAX_VALUE; // 부딪치기 전까지 움직일 수 있는 거리
			for(int j = 0; j < al.size(); j++) {
				if(al.get(j).direction.equals("HORIZON")) { // 가로 y1 == y2
					if(cd == 0){ // 오른
						if (cy == al.get(j).y1 && cx < al.get(j).x1) {
							t = Math.min(t, al.get(j).x1 - cx);
						}
					}else if(cd == 1) { // 아래
						if (al.get(j).x1 <= cx && cx <= al.get(j).x2  && al.get(j).y1 < cy) { 
							t = Math.min(t, cy - al.get(j).y1);
						}
					}else if(cd == 2) { // 왼
						if (cy == al.get(j).y1 && al.get(j).x2 < cx) {
							t = Math.min(t, cx - al.get(j).x2);
						}
					}else if(cd == 3) { // 위
						if (al.get(j).x1 <= cx && cx <= al.get(j).x2  && cy < al.get(j).y1) { 
							t = Math.min(t, al.get(j).y1 - cy);
						}
					}
				}else { // 세로 x1 == x2
					if(cd == 0){ // 오른
						if (al.get(j).y1 <= cy && cy <= al.get(j).y2  && cx < al.get(j).x1) { 
							t = Math.min(t, al.get(j).x1 - cx);
						}
					}else if(cd == 1) { // 아래
						if (cx == al.get(j).x1 && al.get(j).y2 < cy) {
							t = Math.min(t, cy - al.get(j).y2);
						}
					}else if(cd == 2) { // 왼
						if (al.get(j).y1 <= cy && cy <= al.get(j).y2 && al.get(j).x1 < cx) { 
							t = Math.min(t, cx - al.get(j).x1);
						}
					}else if(cd == 3) { // 위
						if (cx == al.get(j).x1 && cy < al.get(j).y1) {
							t = Math.min(t, al.get(j).y1 - cy);
						}
					}
				}
			}
			
			if(count < t) { // 안닿음
				al.add(new Line(cx, cy, cx+dxy[cd][0]*count, cy+dxy[cd][1]*count));
				cx = cx+dxy[cd][0]*count;
				cy = cy+dxy[cd][1]*count;
				result += count;
				if(dir.equals("L")) {
					cd -= 1;
					if(cd < 0) cd += 4;
					cd %= 4;
				}else {
					cd += 1;
					cd %= 4;
				}
			}else { // 닿음
				result += t;
				break;
			}
		}

		System.out.println(result);
	}

	static class Line {
		private long x1;
		private long y1;
		private long x2;
		private long y2;
		private String direction = "";

		public Line(long x1, long y1, long x2, long y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			if(x1 == x2) { // 세로 
				direction = "VERTICAL";
			}else { // 가로
				direction = "HORIZON";
			}
			set_point();
		}
		
		public void set_point() {
			if(this.x2 < this.x1) {
				long temp = this.x2;
				this.x2 = this.x1;
				this.x1 = temp;
			}
			if(this.y2 < this.y1) {
				long temp = this.y2;
				this.y2 = this.y1;
				this.y1 = temp;
			}
		}
	}

}
