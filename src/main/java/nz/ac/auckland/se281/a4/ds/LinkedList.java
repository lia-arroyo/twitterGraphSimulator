package nz.ac.auckland.se281.a4.ds;

import java.util.NoSuchElementException;
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
	 * @throws NoSuchElementException   if the element does not exist in the
	 *                                  LinkedList
	 */
	private Node locateNode(int pos) throws InvalidPositionException, NoSuchElementException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		Node<T> node = new Node<T>(element);

		// checking if this is the first element to be added to list
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
		Node t = new Node(element);
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

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
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos     an integer, which is the position
	 * @param element the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
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
	 * @param n the node passed in
	 * @return true if it's the first element, otherwise it returns false
	 */
	private boolean checkFirstElement(Node<T> n) {
		// checking if this is the first element
		if (head == null) {
			// setting both head and tail to be the passed in element
			head = n;
			tail = n;
			return true;

		} else {
			return false;
		}

	}
}