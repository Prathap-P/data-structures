//AVL Tree Implementation
package DS;
import java.util.*;

public class Avl{
	
	public class Node{
		int key;
		int height;
		Node left;
		Node right;
		
		Node(int key){
			this.key= key;
			this.left= null;
			this.right= null;
			this.height= 1;
		}
	}
	
	Node start;
	int size;

	public Avl(){
		this.start= null;
		this.size= 0;
	}
	
	private Node CreateNode(int key){
		Node temp= new Node(key);
		return temp;
	}
	
	public int size(){
		return this.size;
	}
	public void preOrderPrint(){
		Print(this.start, 1);
		System.out.println();
	}
	
	public void inOrderPrint(){
		Print(this.start, 2);
		System.out.println();
	}

	public void sidewiseInOrder(){
		splPrint(this.start, 1);
	}

	private void sidewisePrint(Node node, int level){
		if(node == null)
			return;
		//call for left
		sidewisePrint(node.right, level+1);
		
		//PrintNode
		for(int i= 0;i< level-1;i++){
			System.out.print("\t");
		}
		System.out.print("(" + level + ") ");
		System.out.print(node.key);
		int tempBF= calcBF(node);
		switch(tempBF){
			case -1:
				System.out.print(" RH");
			break;

			case 0:
				System.out.print(" EH");
			break;

			case 1:
				System.out.print(" LH");
			break;
		}
		System.out.println();
		
		//call for right
		sidewisePrint(node.left, level+1);
	}

	public void postOrderPrint(){
		Print(this.start, 3);
		System.out.println();
	}
	
	public void breadthFirst(){
		Print(this.start, 4);
		System.out.println();
	}
	
	private void Print(Node node,  int whichOrder){
		if(node == null) return;
		switch(whichOrder){
			//Pre Order Traversal
			case 1:
				System.out.print(node.key + " ");
				if(node.left != null || node.right != null)
					System.out.print("( ");
				Print(node.left, 1);
				Print(node.right, 1);
				if(node.left != null || node.right != null)
					System.out.print(")");
				break;

			//In Order Traversal
			case 2:
				Print(node.left, 2);
				System.out.print(node.key + " ");
				Print(node.right, 2);
				break;

			//Post Order Traversal
			case 3:
				Print(node.left, 3);
				Print(node.right, 3);
				System.out.print(node.key + " ");
				break;
			
			//Breadth First Traversal
			case 4:
				Deque<Node> queue= new ArrayDeque<>();
				if(this.start != null){
					queue.offerFirst(this.start);
					while(!queue.isEmpty()){
						Node temp= queue.pollFirst();
						System.out.println(temp.key);
						if(temp.left != null)
							queue.offerLast(temp.left);
						if(temp.right != null)
							queue.offerLast(temp.right);
					}
				}
				break;
		}
	}
	
	public int largestValue(Node node){
		if(node.right != null)
			return largestValue(node.right);
		return node.key;
	}
	
	public void insert(int key){
		
		if(this.start == null){
			this.start= CreateNode(key);
			this.size++;
		}
		
		else {
			Node toCheck= Insert(this.start, key);
			if(toCheck== null)
				System.out.println("Duplicates are not allowed");
			else {
				this.start= toCheck;
				this.size++;
			}
		}
		
		
	}
	private Node Insert(Node node, int key){
		if(key < node.key){
			
			if(node.left != null){
				node.left= Insert(node.left, key);
				node= balance(node);
				return node;
			}
			
			//Base Case
			else{
				node.left= CreateNode(key);
				if(node.right != null)
					node.height = 1+ node.right.height;
				else 
					node.height= 1+ node.left.height;
				return node;
			}
		}
		
		else if(key > node.key){
			
			if(node.right != null){
				node.right= Insert(node.right, key);
				node= balance(node);
				return node;
			}
			
			//Base Case
			else{
				node.right= CreateNode(key);
				if(node.left != null)
					node.height = 1+ node.left.height;
				else 
					node.height= 1+ node.right.height;
				return node;
			}
			
		}
			
		return null;

	}
	
	private Node balance(Node node){
		int bf= calcBF(node);
		
		//Left Heigh	
		if(bf > 1){
			//left of left
			if(calcBF(node.left) == 1){
				node= rotateRight(node);
			}
			//right of left
			else if(calcBF(node.left) == -1){
				node.left= rotateLeft(node.left);
				node= rotateRight(node);
			}
		}
		
		//Right Heigh	
		else if(bf < -1){
			//right of right
			if(calcBF(node.right) == -1){
				node= rotateLeft(node);
			}
			
			//left of right  
			else if(calcBF(node.right) == 1){
				node.right= rotateRight(node.right);
				node= rotateLeft(node);
			}
		}
		return node;
	}
	
	//leaf node will not enter here
	private int calcBF(Node node){
		int bf;
		if(node.left == null && node.right == null)
			return 0;
		if(node.left == null){
			bf= -node.right.height;
			node.height= 1+ node.right.height;
		}
		else if(node.right == null){
			bf= node.left.height;
			node.height= 1+ node.left.height;
		}
		else {
			bf= node.left.height - node.right.height;
			node.height = 1+ Math.max(node.left.height , node.right.height);
		}
		return bf;
	}
	
	private Node rotateRight(Node node){
		Node copy= node.left;
		node.left= node.left.right; 
		copy.right= node;
		return copy;
	}

	private Node rotateLeft(Node node){
		Node copy= node.right;
		node.right= node.right.left; 
		copy.left= node;
		return copy;
	}

	public void delete(int key){
		if(this.start != null){
			Node temp= Delete(this.start, key);
			if(temp == null)
				System.out.println("No Such Element Found");
			else{
				this.start= temp;
				this.size--;
			}
		}	
		
		else
			System.out.println("Tree is empty");
	}
	
	public Node Delete(Node node, int key){
		
		if(key< node.key){
		
			if(node.left != null){
				node.left= Delete(node.left, key);
				node= balance(node);
				return node;
			}
			
			else
				return null;
		}
		

		if(key> node.key){
			
			if(node.right != null){
				node.right= Delete(node.left, key);
				node= balance(node);
				return node;
			}
			
			else
				return null;
		}
		
		else if(node.key == key) {
			
			if(node.left == null)
				return node.right;

			else if(node.right == null)
				return node.left;
			
			else{
				int temp= largestValue(node.left);
				node.key= temp;
				node.left= Delete(node.left, temp);
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		Avl test= new Avl();
		test.insert(4);
		test.insert(12);
		test.insert(24);
		test.insert(3);
		test.insert(40);
		test.insert(20);
		test.splInOrder();
	}
	
}



