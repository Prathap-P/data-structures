// Linked list implemtation

class LinkedList{
	Node head;
	
	static class Node{
		int data;
		Node next;
		
		Node(int data){
			this.data= data;
			next= null;
		}
		
	}
	
	LinkedList(int data){
		InsertFirst(data);
	}
	
	public int returnSize(){
		int size= 0;
		Node temp= head;
		while(temp != null){
			size += 1;
			temp= temp.next;
		}
		return size;
	}
	
	//Returns the reference to the node with the specified position
	public Node returnNode(int index){
		int count= -1;
		Node temp= head;
		while(temp!= null){
			++count;
			if(count == index){
				return temp;
			}
			temp= temp.next;
		}
		return null;
	}
	
	public void InsertFirst(int data){
		Node temp= new Node(data);
		temp.next= this.head;
		this.head= temp;
	}

	public void InsertLast(int data){
		Node temp= new Node(data);
		Node curr_last= head;
		while(curr_last != null){
			if(curr_last.next == null){
				curr_last.next= temp;
			}
		}
	}
	
	public void RemoveFirst(){
		head= head.next;
	}

	public void Insert(int pos, int data){
		if (pos == 0)
			InsertFirst(data);
		else{
			Node before= returnNode(pos-1);
			Node newNode= new Node(data);
			newNode.next= before.next;
			before.next= newNode;
		}
		System.out.println( data +" inserted at position "+ pos );
	}

	public void PrintList(){
		Node temp= head;
		System.out.println("Printing the entire list");
		while(temp != null){
			System.out.println(temp.data);
			temp= temp.next;
		}
	}

	public void Remove(int pos){
		if (pos == 0)	RemoveFirst();
		else{
			Node pre= returnNode(pos-1);
			pre.next= pre.next.next;
			System.out.println("Data removed at position "+ pos);
		}
	}
}


public class Linked_List{
	public static void main(String[] args){
		var output= System.out;
		LinkedList link= new LinkedList(3);
		link.Insert(1, 433);
		link.Insert(2, 3);
		link.Insert(3, 33);
		link.Remove(2);
		link.PrintList();
	}
}