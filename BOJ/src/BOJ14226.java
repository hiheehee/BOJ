import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14226 {
	static boolean visited[][] = new boolean[1001][1001];
	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(br.readLine());
		
		bfs(s);

	}

	static void bfs(int end) {
		Queue<emoticon> q = new LinkedList<>();
		q.offer(new emoticon(0, 1, 0));
		visited[0][1] = true;
		
		while(!q.isEmpty()) {
			emoticon cur = q.poll();
			
			if(cur.screen == end) {
				System.out.println(cur.second);
				return;
			}
			
			// 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
			q.offer(new emoticon(cur.screen, cur.screen, cur.second + 1));
			
			// 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
			if(cur.clipboard != 0 && cur.screen + cur.clipboard <= end &&  !visited[cur.clipboard][cur.screen + cur.clipboard]) {
				q.offer(new emoticon(cur.clipboard, cur.screen+cur.clipboard, cur.second + 1));
				visited[cur.clipboard][cur.screen + cur.clipboard] = true;
			}
			
			// 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
			if(1 <= cur.screen && !visited[cur.clipboard][cur.screen - 1]) {
				q.offer(new emoticon(cur.clipboard, cur.screen - 1, cur.second + 1));
				visited[cur.clipboard][cur.screen - 1] = true;
			}
		}
		
	}
}

class emoticon {
	int clipboard = 0;
	int screen = 0;
	int second = 0;
	
	emoticon(int clipboard, int screen, int second){
		this.clipboard = clipboard;
		this.screen = screen;
		this.second = second;
	}
}
