//Queue implementation

package DS;
import java.util.*;

public class Queue<T>{
	
	Node start;
	Node end;
	
	private class Node{
		T value;
		Node next;
		
		Node(T value){
			this.value= value;
			this.next= null;
		}
	
	}
	
	public Queue(){
		this.start= null;
		this.end= null;
	}
	
	public boolean isEmpty(){
		return (this.start == null ? true : false);
	}
	
	public void push(T value){
		Node toInsert= new Node(value);
		if(end != null){
			this.end.next= toInsert;
			this.end= toInsert;
		}
		else {
			this.start= toInsert;
			this.end= this.start;
		}
	}
	
	public T pop(){
		if(!isEmpty()){
			T toReturn= this.start.value;
			this.start= this.start.next;
			return toReturn;
		}
		System.out.println("Queue is Empty");
		return null;
	}
	
	public T top(){
		if(!isEmpty()){
			return this.start.value;
		}
		System.out.println("Queue is Empty");
		return null;
	}
	
	public static void main(String[] args){
		Queue<Integer> test= new Queue<>();
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
		System.out.println(test.pop());
		System.out.println(test.top());
	}
}
