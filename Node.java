
// class to represent one node in a list
class Node<T> {
	
	T data;
	Node<T> nextNode;

	// constructor creates a Node that refers to String
	Node( T str ) {
		this( str, null );
	} 

	// constructor creates Node that refers to String and to next ListNode
	Node( T str, Node<T> node ) {
		data = str;
		nextNode = node;
	} 

	// return reference to data in node
	T getString() {
		return data; 
	} 

	// return reference to next node in list
	Node<T> getNext() {
		return nextNode; 
	} 
}