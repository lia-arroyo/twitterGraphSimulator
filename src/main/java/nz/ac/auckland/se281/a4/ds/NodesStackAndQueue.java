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

		// checking if this is the first element
		boolean isFirst = this.checkFirstElement(n);

		if (!isFirst) {
			// connecting the element to the current head
			n.setNext(head);

			// setting the element to be current head
			head = n;
		}
	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {

		// ensuring we don't pop from an empty stack
		if (this.isEmpty()) {
			throw new EmptyStackException();

		} else {
			// saving the current head node before removing
			Node<T> currentHead = head;

			// replacing current head with the next
			head = currentHead.getNext();

			// returning the value
			return currentHead.getValue();
		}
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		// ensuring we don't peek from empty stack
		if (this.isEmpty()) {
			throw new EmptyStackException();

		} else {
			// returning the value of the current head
			return head.getValue();
		}
	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		Node<T> n = new Node<T>(element);

		// checking if element is first
		boolean isFirst = this.checkFirstElement(n);

		if (!isFirst) {

			// adding the element to the next of tail
			tail.setNext(n);

			// setting a new tail
			tail = n;
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
		if (this.isEmpty()) {
			// setting both head and tail to be the passed in element
			head = n;
			tail = n;
			return true;

		} else {
			return false;
		}

	}
}
