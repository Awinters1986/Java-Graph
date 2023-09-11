/*  Date:           07/27/2020
 *  Filename:       UAFindMyClass.java
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class UAFindMyClass {
	private Node n;
	private LinkedList<Node> adjList[];
	private Queue<Node> q = new LinkedList();
	private int time;
	String gray = "gray";
	String white = "white";
	String black = "black";
	
	public static void main (String args[]) throws IOException {
		String console;
		UAFindMyClass fmc = new UAFindMyClass();
		int size = 100;
		Node nil = new Node();
		Node s = new Node();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while((console = br.readLine()) != null) {
			String[] tokens = console.split(" ");
			
		    String search = tokens[0];
		    String input = tokens[1];
		    String output = tokens[2];
		    
		    if(search == "bfs" || search == "BFS") {
		    	fmc.load(input);
		    	fmc.graph(A, s, nil);
		    	fmc.bfs(A, s, nil);
		    } else if(search == "dfs" || search == "DFS") {
		    	fmc.load(A, input);
		    	fmc.dfs();
		    } else {
		    	System.out.println("Error: No Search Method entered.");
		    }
		}
	}
	
	public void load(String filename) {
    	String line;
    	
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader(filename));
	    	
	    	while((line = br.readLine()) != null) {
                   for(int i = 0; i < line.length(); i++) {
                	   char c = (char) i;
                	   graph(c);
                   }
	    	}
	    	
	    	br.close();
	    	
    	} catch(Exception e) {
    		System.out.println("Error: " + e);
    	}
	}

	public void bfs(char[] A, Node s, Node nil) {
		Node u = new Node();
		Node v = new Node();
		for(int i:A) {
			u.color = white;
			u.distance = Float.POSITIVE_INFINITY;
			u.pred = nil;
		}
		s.color = gray;
		s.distance = 0;
		s.pred = nil;
		
		q = 0;
		enqueue(q, s);
		while(q != 0) {
			u = dequeue(q);
			for(int j:adjList[u]) {
				if (v.color == white) {
					v.color = gray;
					v.distance = u.distance + 1;
					v.pred = u;
					enqueue(q, v);
				}
			}
			u.color = black;
		}
	}
	
	public void enqueue(Queue<Node> q, Node x) {
		q[q.tail] = x;
		if(q.tail == q.length) {
			q.tail = 1;
		} else {
			q.tail = q.tail + 1;
		}
	}
	
	public Node dequeue(Queue<Node> q) {
		Node x = q[q.head];
		if (q.head == q.length) {
			q.head = 1;
		} else {
			q.head = q.head + 1;
		}
		return x;
	}
	
	public void dfs(char[] G, Node nil) {
		Node u = new Node();
		Node v = new Node();
		for(int i:G) {
			u.color = white;
			u.pred = nil;
		}
		
		int time = 0;
		
		for(int j:G) {
			if(u.color == white) {
				dfsvisit(G, u);
			}
		}
	}
	
	public void dfsvisit(char[] G, Node u) {
		time = time + 1;
		u.distance = time;
		u.color = gray;
		Node v = new Node();
		for(int i:adjList[u]) {
			if (v.color == white) {
				v.pred = u;
				dfsvisit(G, v);
			}
		}
		u.color = black;
		time = time + 1;
		u.finish = time;
	}
	
	public void graph(Node input) {
		n = input;
		adjList = new LinkedList[4];
		
		for(int i = 0; i < adjList.length; i++) {
			adjList[i] = new LinkedList();
	}
	
	public void addEdge(int u, Node v) {
		adjList[u].add(v);
	}
	
	public class Node {
		char data;
		String color;
		float distance;
		int finish;
		Node pred;
		Node head;
		Node tail;
		
		public Node() {
			this.color = "white";
			this.distance = 0;
			this.pred = null;
			this.head = null;
			this.tail = null;
			this.finish = 0;
		}
		
		public Node(char data) {
			this.data = data;
			this.color = "white";
			this.distance = 0;
			this.pred = null;
			this.head = null;
			this.tail = null;
			this.finish = 0;
		}
		
		public char getData() {
			return data;
		}

		public void setData(char data) {
			this.data = data;
		}
		
		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public float getDistance() {
			return distance;
		}

		public void setDistance(float distance) {
			this.distance = distance;
		}

		public Node getPred() {
			return pred;
		}

		public void setPred(Node pred) {
			this.pred = pred;
		}
		
		public Node getHead() {
			return head;
		}

		public void setHead(Node head) {
			this.head = head;
		}

		public Node getTail() {
			return tail;
		}

		public void setTail(Node tail) {
			this.tail = tail;
		}
		
		public int getFinish() {
			return finish;
		}

		public void setFinish(int finish) {
			this.finish = finish;
		}

	}
}
