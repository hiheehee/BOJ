import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ5639 {
	static Node root;
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		root = new Node(Integer.parseInt(br.readLine()));

		String n;
		
		while((n = br.readLine()) != null) {
			Node nextNode = new Node(Integer.parseInt(n));
			putNode(root, nextNode);
		}
		
		postOrder(root);
		System.out.println(sb.toString());
		
	}
	
	// 후위 순회
	static void postOrder(Node node) {
		if(node.left != null) postOrder(node.left);
		if(node.right != null) postOrder(node.right);
		sb.append(node.num+"\n");
	}
	
	static void putNode(Node startNode, Node inputNode) {
		if(inputNode.num < startNode.num) {
			if(startNode.left != null) {
				putNode(startNode.left, inputNode);
			}else {
				startNode.left = inputNode;
			}
		}else {
			if(startNode.right != null) {
				putNode(startNode.right, inputNode);
			}else {
				startNode.right = inputNode;
			}
		}
	}
	
	static class Node {
		int num;
		Node left;
		Node right;
		
		Node(int num){
			this.num = num;
		}
	}

}
