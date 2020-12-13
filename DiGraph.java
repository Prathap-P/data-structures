package DS;

import java.util.*;

public class DiGraph {
   public Vertex start;
   public int count;

	public static class Vertex {
	   public int data;
	   public boolean processed;
	   public Vertex nxtVertex;
	   public Arc nxtArc;
	   public int inDegree;
	   public int outDegree;

	   public Vertex(int var1) {
		  this.data = var1;
	   }
	}

	public class Arc {
	   public Vertex toVertex;
	   public int weight;
	   public Arc next;

		public Arc(Vertex var1, int var2) {
			this.toVertex = var1;
			this.weight = var2;
		}
	}

   public DiGraph() {}

   public DiGraph(int data){
		this.start = new Vertex(data);
		this.count = 1;
	}

	public void insertVertex(int data){
		++this.count;
		
		if (this.start == null) {
			this.start = new Vertex(data);
			return;
		} 
		
		else {
			Vertex toInsert = new Vertex(data);
			if (this.start.data > data) {
				toInsert.nxtVertex = this.start;
				this.start = toInsert;
			} 
			
			else {
				Vertex insertAfter = this.insertPoint(this.start, data);
				toInsert.nxtVertex = insertAfter.nxtVertex;
				insertAfter.nxtVertex = toInsert;
			}
			
		}
	}

	//Return the insertion point as per value : data 
	public Vertex insertPoint(Vertex toSearch, int data) {
		Vertex pre = toSearch;
		Vertex curr = toSearch;

		while(curr != null && curr.data < data){
			pre = curr;
			curr = curr.nxtVertex;
		}

		return pre;	
   }

   public void insertArc(int from, int to, int weight) {
      Vertex fromVertex = this.returnVertex(from);
      Vertex toVertex = this.returnVertex(to);
	  
		if (fromVertex != null && toVertex != null) {
      
			++fromVertex.outDegree;
			++toVertex.inDegree;
			
			Arc arcList = fromVertex.nxtArc;
			Arc toInsert = new DS.DiGraph.Arc(toVertex, weight);
			
			if (arcList == null){
				fromVertex.nxtArc = toInsert;
			}

			else if (arcList.toVertex.data > toVertex.data){
				toInsert.next = arcList;
				fromVertex.nxtArc = toInsert;
			}
			
			else{
				Arc insertAfter = this.insertPoint(arcList, toVertex.data);
				toInsert.next = insertAfter.next;
				insertAfter.next = toInsert;
			}
		
		}
   }

	public Arc insertPoint(Arc toSearch, int data) {
		Arc pre = toSearch;
		Arc curr = toSearch; 

		while(curr != null && curr.toVertex.data < data){ 	
			pre = curr;
			curr = curr.next;
		}

      return pre;
   }

	public Vertex returnVertex(int data) {
		Vertex forTraversal= this.start;
	  
		while(forTraversal != null && forTraversal.data <= data){
			if(forTraversal.data == data)
				return forTraversal;
         
			forTraversal = forTraversal.nxtVertex;
		}

		return null;
	}

	public void deleteVertex(int data) {
		if (this.start == null)return;

		Vertex pre = this.start;
		Vertex curr= this.start;
      
	
		while(curr != null && curr.data != data){		
			pre = curr;
			curr = curr.nxtVertex;
		}

		if (curr == null)return;

		//deletes all the indegrees		
		this.deleteIndegrees(curr.data);
		//deletes all the outdegrees
		curr.nxtArc = null;
        
		if (curr.inDegree > 0 && curr.outDegree > 0)
			return;
		
		if(pre == curr)
			this.start = pre.nxtVertex;
		
		else
			pre.nxtVertex = curr.nxtVertex;
	}

	public void deleteIndegrees(int data){
		Vertex forTraversal= this.start;
		
		while(forTraversal != null){
			deleteArc(forTraversal.data, data);
			
			forTraversal= forTraversal.nxtVertex;
		}
	}

	public void deleteArc(int fromData, int toData) {
		Vertex fromVertex = this.returnVertex(fromData);
		Vertex toVertex = this.returnVertex(toData);
		
		if(fromVertex == null)return;

		if (fromVertex.nxtArc == null)return;
		
		Arc pre = fromVertex.nxtArc;
		Arc curr= fromVertex.nxtArc;

		while(curr!= null && curr.toVertex.data != toData){
			pre = curr;
			curr= curr.next;
		}

		if (curr == null)return;
		
		if (curr == pre)
			fromVertex.nxtArc = curr.next;
		else
			pre.next = curr.next;
	
		--fromVertex.outDegree;
		--toVertex.inDegree;
	}

	public void printList() {
		Vertex forTraversal = this.start;
	  
		while(forTraversal != null){
			System.out.print(forTraversal.data + "-> ");

			Arc arcList = forTraversal.nxtArc;
			while(arcList != null){
				System.out.print(arcList.toVertex.data + "(" + arcList.weight + ") -> ");
				
				arcList = arcList.next;
			}

			System.out.print("null\n");
			forTraversal = forTraversal.nxtVertex;
		}

	}	

	public void printDFT(){
		Vertex forTraversal= this.start;
		
		while(forTraversal != null){
			forTraversal.processed = false;
			forTraversal= forTraversal.nxtVertex;
		}

		forTraversal= this.start;

		Deque<Vertex> queue = new ArrayDeque(); 
		
		while(forTraversal != null){
			
			if(!forTraversal.processed){
				queue.push(forTraversal);

				while(!queue.isEmpty()) {
					Vertex toPush = (Vertex)queue.pop();
					System.out.println(toPush.data);
					toPush.processed = true;

					Arc arcs = toPush.nxtArc;
					while(arcs != null){
						if(!arcs.toVertex.processed)
							queue.push(arcs.toVertex);
						
						arcs= arcs.next;
					}
				}
            }
			forTraversal = forTraversal.nxtVertex;
		}

	}

	public void recurPrint() {
		Vertex forTraversal= this.start;
    
		while(forTraversal != null){
			forTraversal.processed = false;
			forTraversal = forTraversal.nxtVertex;
		}
		
		forTraversal = this.start;
		while(forTraversal != null){
			this.recurFunc(forTraversal);
			forTraversal = forTraversal.nxtVertex;
		}

	}	

	private void recurFunc(Vertex forTraversal) {
		if(forTraversal.processed) return;
		
		forTraversal.processed = true;
		System.out.println(forTraversal.data);

		Arc arcs = forTraversal.nxtArc;

		while(arcs!= null){
			this.recurFunc(arcs.toVertex);
			
			arcs = arcs.next;
		}
	}
	
	public void checkCycle() {
		Vertex forTraversal= this.start;
      
		while(forTraversal != null){
			forTraversal.processed = false;
			forTraversal = forTraversal.nxtVertex;
		}

		forTraversal= this.start;

		boolean cycleFound= false;
		
		while(forTraversal != null){
			if (this.recurCheck(forTraversal, false)){
				cycleFound = true;
				break;
			}
			
			forTraversal = forTraversal.nxtVertex;
		}

		if (cycleFound)
			System.out.println("Cycle Found");
		
		else
			System.out.println("No Cycles Found");

	}	

	private boolean recurCheck(Vertex toTraverse, boolean cycleFound){
		
		if(toTraverse.processed) {
			cycleFound = true;
			return cycleFound;
		}

		toTraverse.processed = true;

		DS.DiGraph.Arc toRecur = toTraverse.nxtArc;
         
		while(toRecur != null){
            if(!cycleFound) {
				cycleFound = this.recurCheck(toRecur.toVertex, cycleFound);
				break;
            }
			
			toRecur= toRecur.next;
		}

		toTraverse.processed = false;
		return cycleFound;
	}

	public void printBFT(){
		Vertex forTraversal= this.start;
      
		while(forTraversal != null){
			forTraversal.processed = false;
			forTraversal = forTraversal.nxtVertex;
		}

		forTraversal = this.start;
 		
		Deque<Vertex> temp= new ArrayDeque<>();
		
		while(forTraversal != null){
			
			if(forTraversal.processed == false){
				temp.offerLast(forTraversal);
				
				while(!temp.isEmpty()){
					Vertex toPush= temp.pop();
					System.out.println(toPush.data);
					toPush.processed= true;
					
					Arc arc= toPush.nxtArc;
					while(arc!= null){
						if(arc.toVertex.processed == false)
							temp.push(arc.toVertex);
							
						arc= arc.next;
					}
					
				}
			}
				
			forTraversal= forTraversal.nxtVertex;
		}
	}
	
}
