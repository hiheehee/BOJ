import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			int l = 0;
			int r = str.length()-1;
			boolean IsPalind = true;
			boolean IsPseudo = false;
			
			for(int j = 0; j <= str.length()/2; j++) {
				if(!IsPseudo && str.charAt(l) != str.charAt(r)) {
					if(str.charAt(l+1) == str.charAt(r) && str.charAt(l) != str.charAt(r-1)) {
						IsPalind = false;
						IsPseudo = true;
						l++;
					}else if(str.charAt(l+1) != str.charAt(r) && str.charAt(l) == str.charAt(r-1)) {
						IsPalind = false;
						IsPseudo = true;
						r--;
					}else if(str.charAt(l+1) == str.charAt(r) && str.charAt(l) == str.charAt(r-1)) {
						if (IsPseudoPalind(str, l+1, r)) {
							IsPalind = false;
							IsPseudo = true;
							break;
						}else if(IsPseudoPalind(str, l, r-1)){
							IsPalind = false;
							IsPseudo = true;
							break;
						}else {
							IsPalind = false;
							IsPseudo = false;
							break;
						}
					}else {
						IsPalind = false;
						IsPseudo = false;
						break;
					}
				}else if(IsPseudo && str.charAt(l) != str.charAt(r)) {
					IsPalind = false;
					IsPseudo = false;
					break;
				}
				l++;
				r--;
			}
			
			if(IsPalind) {
				System.out.println(0);
			}else if(IsPseudo) {
				System.out.println(1);
			}else {
				System.out.println(2);
			}
		}

	}
	
	static boolean IsPseudoPalind(String s, int l, int r) {
		boolean check = true;
		while(l < r) {
			if(s.charAt(l) != s.charAt(r)) {
				check = false;
			}
			l++;
			r--;
		}
		return check;
	}

}
