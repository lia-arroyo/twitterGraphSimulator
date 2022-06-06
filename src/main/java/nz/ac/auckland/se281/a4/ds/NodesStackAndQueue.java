package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods
	private Node<T> tail;

	public NodesStackAndQueue() {
		head = null; // initially empty
		tail = head; // initially the head is the tail

	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 * 
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		Node<T> n = new Node<T>(element);

		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public Node pop() throws EmptyStackException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public Node peek() throws EmptyStackException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		Node n = new Node(element);

		// checking if this is the first element to be added
		if (this.isEmpty()) {
			head = n;

		} else {
			// adding the element to the next of tail
			tail.setNext(n);

			// setting a new tail
			tail = n;
		}
	}
}
