//Christian Szablewski-Paz
//I pledge my honor that I have abided by Stevens Honor Code

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>>{
	private class Node<E>{
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		/**
		 * 
		 * @param data
		 * @param priority
		 */
		
		public Node(E data, int priority) {
			if (data == null) {
				throw  new IllegalArgumentException();
			} 
			else {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			} 
		} 
		
		/**
		 * 
		 * @return newParent
		 * newParent is parent node after method is called
		 */
		
		public Node<E> rotateRight() {
			Node<E> newParent = this.left;
			Node<E> left  = newParent.right;
			newParent.right = this;
			this.left = left;
			return newParent;
		} 

		/**
		 * 
		 * @return newParent
		 * newParent is parent node after method is called
		 */
		
		public Node<E> rotateLeft() {
			Node<E> newParent = this.right;
			Node<E> right = newParent.left;
			newParent.left = this;
			this.right = right;
			return newParent;
		} 
		
		public String toString(){
			return ("[" + this.data + "," + this.priority + "]");
		}
	} 
	
	private Random priorityGenerator;
	private Node<E> root;
	
	/**
	 * initializes Treap object
	 */
	
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	} 
	
	/**
	 *  initializes Treap object with random seed
	 * @param seed
	 */
	
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	} 
	
	/**
	 * Helper function for add()
	 * @param currentNode
	 * @param path
	 */
	
	private void reheap(Node<E> currentNode, Stack<Node<E>> path) {
		while (!path.isEmpty()) { 
			Node<E> parentNode = path.pop();
			if (parentNode.priority < currentNode.priority){
				if (parentNode.data.compareTo(currentNode.data) > 0) {
					currentNode = parentNode.rotateRight();
				} 
				else {
					currentNode = parentNode.rotateLeft();
				} 
				if (!path.isEmpty()) {
					if (path.peek().left == parentNode) {
						path.peek().left = currentNode;
					} 
					else {
						path.peek().right = currentNode;
					} 
				} 
				else { 
					this.root = currentNode;
				} 
			} 
			else {
				break;
			} 
		} 
	} 
	
	/**
	 * Returns true if node is successfully added to Treap
	 * Returns false otherwise
	 * @param key
	 * @param priority
	 * @return 
	 */
	
	public boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	} 
	
	public boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} 
		else {
			Node<E> addNode = new Node<E>(key, priority);
			Stack<Node<E>> path = new Stack<Node<E>>();
			Node<E> currentNode = root;
			while (currentNode != null) {
				if (currentNode.data.compareTo(key) == 0) {
					return false;
				} 
				else {
					if (currentNode.data.compareTo(key) < 0) {
						path.push(currentNode);
						if (currentNode.right == null) {
							currentNode.right = addNode;
							reheap(addNode, path);
							return true;
						}
						else {
							currentNode = currentNode.right;
						}
					} 
					else {
						path.push(currentNode);
						if (currentNode.left == null) {
							currentNode.left = addNode;
							reheap(addNode, path);
							return true;
						} 
						else {
							currentNode = currentNode.left;
						}
					} 
				}
			} 
			return false;
		} 
	} 
	
	/**
	 * Returns true if node is successfully deleted
	 * Returns false otherwise: (key is null, or doesn't exist)
	 * @param key
	 * @return
	 */
	
	public boolean delete(E key){
		if(key == null){
			return false;
		}
		if(!find(key)){
			return false;
		} 
		
		Node<E> parentNode = root;
		Node<E> currentNode = root;
		while(currentNode.data.compareTo(key) != 0){
			parentNode = currentNode;
			if(currentNode.data.compareTo(key) < 0){
				currentNode = currentNode.right;
			} 
			else {
				currentNode = currentNode.left;
			}
		}
		while(currentNode.right != null || currentNode.left != null){
			if(parentNode.right == currentNode){
				if(currentNode.right == null || currentNode.left.priority > currentNode.right.priority){
					parentNode.right = currentNode.left;
					parentNode = currentNode.left;
					currentNode.rotateRight();
				} 
				else{
					parentNode.right = currentNode.right;
					parentNode = currentNode.right;
					currentNode.rotateLeft();
				}
			} 
			else{ 
				if(currentNode.left == null || currentNode.left.priority < currentNode.right.priority){
					parentNode.left = currentNode.right;
					parentNode = currentNode.right;
					currentNode.rotateLeft();
				} 
				else {
					parentNode.left = currentNode.left;
					parentNode = currentNode.left;
					currentNode.rotateRight();
				} 
			}
		}
		
		if(parentNode.right == null){
			parentNode.left = null;
		} 
		else if(parentNode.left == null){
			parentNode.right = null;
		} 
		else {
			if(parentNode.right.data == key){
				parentNode.right = null;
			} 
			else{
				parentNode.left = null;
			}
		}
	
		return true;	
	}
	/**
	 * Returns true if key exists
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node<E> root, E key) {
		if (root == null) {	
			return false;
		} 
		else {
			if (root.data.compareTo(key) == 0) {
				return true;
			} 
			else {
				return (find(root.right, key) || find(root.left, key));
			} 
		} 
	} 

	/**
	 * 
	 * @param key
	 * @return find(root, key)
	 */
	
	public boolean find(E key) {
		return find(root, key);
	} 
	
	/**
	 * Returns a string representation of a treap
	 * @param currentNode
	 * @param i
	 * @return ret
	 */

	private StringBuilder toString(Node<E> currentNode, int i) {
		StringBuilder ret = new StringBuilder();
		for(int j = 0; j < i; j++) {
			ret.append("-");
		}
		
		if(currentNode == null) {
			ret.append("null\n");
		}
		else {
			ret.append(currentNode.data.toString() + "\n");
			ret.append(toString(currentNode.left, i + 1));
			ret.append(toString(currentNode.right, i - 1));
		}
		
		return ret;
	}
	
	public String toString() {
		return toString(root, 0).toString();
	}
	
} 

