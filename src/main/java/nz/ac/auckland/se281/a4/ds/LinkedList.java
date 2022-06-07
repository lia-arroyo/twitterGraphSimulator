package nz.ac.auckland.se281.a4.ds;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {

	private Node<T> head; // the head of the linked list
	private Node<T> tail; // reference to the tail

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
		tail = head;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException {

		// ensuring position is within range of linked list size
		if (pos < 0 || pos > this.size() - 1) {
			throw new InvalidPositionException();

		} else {
			// starting iteration from head
			Node<T> currentNode = head;

			// iterating through linked list until we get to position
			for (int i = 0; i < pos; i++) {

				// setting the currentNode to be the next node
				currentNode = currentNode.getNext();
			}

			return currentNode;
		}
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		Node<T> node = new Node<T>(element);

		// checking if this is the first element to be added to list, and sets as head
		// and tail if true
		boolean isFirst = this.checkFirstElement(node);

		if (!isFirst) {
			// connect current head to the element
			node.setNext(head);

			// set as the new head
			head = node;
		}

	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> node = new Node<T>(element);

		// checking if this is the first element to be added, and sets head and tail if
		// true
		boolean isFirst = this.checkFirstElement(node);

		if (!isFirst) {
			// connecting node to tail
			tail.setNext(node);

			// setting as new tail
			tail = node;
		}
	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public T get(int pos) throws InvalidPositionException {

		// ensuring the position passed is within size of list
		if (pos > this.size() - 1 || pos < 0) {
			throw new InvalidPositionException();

		} else {
			// start iteration from head
			Node<T> currentNode = head;

			// iterating through each node
			for (int i = 0; i < pos; i++) {
				// setting current node to the next node
				currentNode = currentNode.getNext();
			}

			// returning the value of node at position
			return currentNode.getValue();
		}
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos     an integer, which is the position
	 * @param element the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size
	 */
	public void insert(int pos, T element) throws InvalidPositionException {

		// creating new node with the element's value
		Node<T> newNode = new Node<T>(element);

		// ensuring position is valid
		if (pos < 0 || pos > this.size()) {
			throw new InvalidPositionException();

		} else {

			// checking if insertion is at head
			if (pos == 0) {
				this.prepend(element);

			} else if (pos == this.size()) {
				// if inserting at tail
				this.append(element);

			} else { // if inserting in between

				// locating the node before the desired position
				Node<T> prevNode = this.locateNode(pos - 1);

				// saving the next node
				Node<T> nextNode = this.locateNode(pos);

				// setting the previous node's next to new node
				prevNode.setNext(newNode);

				// setting the new node's next to the nextnode
				newNode.setNext(nextNode);
			}
		}
	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size - 1
	 */
	public void remove(int pos) throws InvalidPositionException {

		// ensuring position is within bounds
		if (pos < 0 || pos > this.size() - 1) {
			throw new InvalidPositionException();

		} else {
			// getting node to be removed
			Node<T> nodeToRemove = this.locateNode(pos);

			// if removing the current head
			if (pos == 0) {
				// setting new head
				head = nodeToRemove.getNext();

			} else if (pos == this.size() - 1) {
				// if removing the current tail
				tail = this.locateNode(pos - 1);

				// removing link to nodeToRemove
				tail.setNext(null);

			} else {
				// if removing in between

				// saving before and after nodes
				Node<T> prevNode = this.locateNode(pos - 1);
				Node<T> nextNode = nodeToRemove.getNext();

				// linking prevNode to nextNode
				prevNode.setNext(nextNode);

			}

			// removing next
			nodeToRemove.setNext(null);

		}
	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {

		// count variable for size
		int count = 0;

		// current node
		Node<T> currentNode = head;

		// iterating through linkedlist until we hit a null
		while (currentNode != null) {

			// increment counter
			count++;

			// setting current node to be the next node
			currentNode = currentNode.getNext();

		}

		// returning the size of list
		return count;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}

	/**
	 * This method checks if the node is the first element, and sets it as the
	 * current head and tail if true.
	 * 
	 * @param node the node passed in
	 * @return true if it's the first element, otherwise it returns false
	 */
	private boolean checkFirstElement(Node<T> node) {
		// checking if this is the first element
		if (head == null) {
			// setting both head and tail to be the passed in element
			head = node;
			tail = node;
			return true;

		} else {
			return false;
		}

	}
}