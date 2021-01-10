import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PB {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < n; i++) {
			int t = Integer.parseInt(br.readLine());
			String str[] = br.readLine().split(" ");
			long result = 100001;
			
			for(int j = (int) Math.pow(2, t-1); j < (int) Math.pow(2, t); j++) {
				String bin[] = Integer.toString(j, 2).split("");
				
				int OneCount = 0;
				int ZeroCount = 0;
				long OneSum = Long.MAX_VALUE;
				long ZeroSum = Long.MAX_VALUE;
				
				for(int k = 0; k < bin.length; k++) {
					if(bin[k].equals("1")) {
						OneCount++;
					}else {
						ZeroCount++;
					}
				}
				
				if(OneCount == 3) {
					OneSum = 0;
					for(int a = 0; a < t; a++) {
						if(bin[a].equals("1")) {
							for(int b = a+1; b < t; b++) {
								if(bin[b].equals("1")) {
									for(int c = 0; c < 4; c++) {
										if(str[a].charAt(c) != str[b].charAt(c)) {
											OneSum++;
										}
									}
								}
							}
						}
					}
				}
				
				if(ZeroCount == 3) {
					ZeroSum = 0;
					for(int a = 0; a < t; a++) {
						if(bin[a].equals("0")) {
							for(int b = a+1; b < t; b++) {
								if(bin[b].equals("0")) {
									for(int c = 0; c < 4; c++) {
										if(str[a].charAt(c) != str[b].charAt(c)) {
											ZeroSum++;
										}
									}
								}
							}
						}
					}
				}
				//System.out.println(Integer.toString(j, 2)+ " "+ OneSum+" "+ZeroSum);
				result = Math.min(result, OneSum);
				result = Math.min(result, ZeroSum);
			}
			
			sb.append(result+"\n");

		}
	
		System.out.println(sb.toString());
	}

}
