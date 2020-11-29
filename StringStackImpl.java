import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T> {
	
	Node<T> firstNode;
	public int size = 0;
	
	public boolean isEmpty() { //Checks if the queue is empty
		return firstNode == null;
	}
	
	public void push(T item) { 
		Node <T> node = new Node<>(item);
		if ( isEmpty() ) { // firstNode and lastNode refer to same String
			firstNode = node;
		} else { // lastNode's nextNode refers to new node
			node.nextNode = firstNode;
			firstNode = node;
		}
		size++;
	}
	
	public T pop() throws NoSuchElementException {
		if ( isEmpty() ) { // throw exception if List is empty
			throw new NoSuchElementException();
		}
		T removedItem = firstNode.data; // retrieve data being removed
		firstNode = firstNode.nextNode;
		size--;
		return removedItem; // return removed node's data
	}
	
	public T peek() throws NoSuchElementException {
		if ( isEmpty() ) {
			throw new NoSuchElementException();
		} else {
			return firstNode.data; //it returns the top of the stack node's data 
		}
	}
	
	//Prints the whole stack's node's data starting from the top of the stack
	public void printStack(PrintStream stream) { 
		Node<T> current = firstNode;
		while( current != null ) {
			stream.println(current.data ); //Prints the whole stack's node's data
			current = current.nextNode;
		}
	}
	
	public int size() { //prints the size of the stack
		return size;
	}
	
}