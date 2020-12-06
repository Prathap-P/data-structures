//Binary Tree Implementation
package DS;
import java.util.*;

public class BinarySearchTree{
	
	public class Node{
		int key;
		Node left_BST;
		Node right_BST;
		
		Node(int key){
			this.key= key;
			this.left_BST= null;
			this.right_BST= null;
		}
	}
	
	Node start;
	int length;

	public BinarySearchTree(){
		this.start= null;
		this.length= 0;
	}
	
	private Node CreateNode(int key){
		Node temp= new Node(key);
		return temp;
	}
	
	public int size(){
		return this.length;
	}
	public void preOrderPrint(){
		Print(this.start, 1);
	}
	
	public void inOrderPrint(){
		Print(this.start, 2);
	}


	public void postOrderPrint(){
		Print(this.start, 3);
	}

	public void breadthFirst(){
		Print(this.start, 4);
	}
	
	private void Print(Node node,  int whichOrder){
		if(node == null) return;
		switch(whichOrder){
			case 1:
				System.out.print(node.key + " ");
				Print(node.left_BST, 1);
				Print(node.right_BST, 1);
				break;

			case 2:
				Print(node.left_BST, 2);
				System.out.print(node.key + " ");
				Print(node.right_BST, 2);
				break;

			case 3:
				Print(node.left_BST, 3);
				Print(node.right_BST, 3);
				System.out.print(node.key + " ");
				break;

			case 4:
				Deque<Node> queue= new ArrayDeque<>();
				if(this.start != null){
					queue.offerFirst(this.start);
					while(!queue.isEmpty()){
						Node temp= queue.pollFirst();
						System.out.println(temp.key);
						if(temp.left_BST != null)
							queue.offerLast(temp.left_BST);
						if(temp.right_BST != null)
							queue.offerLast(temp.right_BST);
					}
				}
				break;
		}
	}
	
	public int largestValue(Node node){
		if(node.right_BST != null)
			return largestValue(node.right_BST);
		return node.key;
	}
	
	public void insert(int key){
		
		if(this.start == null){
			this.start= CreateNode(key);
			this.length++;
		}
		
		else if(!Insert(this.start, key))
			System.out.println("Duplicates are not allowed");

		else this.length++;
		
	}
	private boolean Insert(Node node, int key){
		if(key < node.key)
			
			if(node.left_BST != null){
				Insert(node.left_BST, key);
				return true;
			}
			
			else{
				node.left_BST= CreateNode(key);
				return true;
			}
			
		else if(key > node.key)
			
			if(node.right_BST != null){
				Insert(node.right_BST, key);
				return true;
			}
			
			else{
				node.right_BST= CreateNode(key);
				return true;
			}
			
		return false;

	}
	
	public void delete(int key){
		if(this.start != null){
			Node temp= Delete(this.start, key);
			if(temp == null)
				System.out.println("No Such Element Found");
			else{
				this.start= temp;
				this.length--;
			}
		}	
		
		else
			System.out.println("Tree is empty");
	}
	
	public Node Delete(Node node, int key){
		
		if(key< node.key){
		
			if(node.left_BST != null){
				node.left_BST= Delete(node.left_BST, key);
				return node;
			}
			
			else
				return null;
		}
		

		if(key> node.key){
			
			if(node.right_BST != null){
				node.right_BST= Delete(node.left_BST, key);
				return node;
			}
			
			else
				return null;
		}
		
		else {
			
			if(node.left_BST == null)
				return node.right_BST;

			else if(node.right_BST == null)
				return node.left_BST;
			
			else{
				int temp= largestValue(node.left_BST);
				node.key= temp;
				node.left_BST= Delete(node.left_BST, temp);
			}
		}
		return null;
	}
	
}



