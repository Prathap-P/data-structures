//Stack implementation

package DS;
import java.util.*;

public class Stack <T>{
	
	Node start;
	
	private class Node{
		T value;
		Node next;
		
		Node(T value){
			this.value= value;
			this.next= null;
		}
	
	}
	
	public Stack(){
		this.start= null;
	}
	
	public boolean isEmpty(){
		return (this.start == null ? true : false);
	}
	
	public T top(){
		if(!isEmpty()){
			return this.start.value;
		}
		System.out.println("Stack is Empty");
		return null;
	}
	
	public void push(T value){
		Node toInsert= new Node(value);
		toInsert.next= this.start;
		this.start= toInsert;
	}
	
	public T pop(){
		if(!isEmpty()){
			T toReturn= this.start.value;
			this.start= this.start.next;
			return toReturn;
		}
		System.out.println("Stack is Empty");
		return null;
	}
	
	public static void main(String[] args){
		Stack<Integer> test= new Stack<>();
		test.push(53);
		test.push(523);
		System.out.println(test.top());
		System.out.println(test.pop());
		System.out.println(test.top());
		test.push(380);
		System.out.println(test.pop());
		System.out.println(test.top());
		test.push(12);
		System.out.println(test.top());
	}
}

