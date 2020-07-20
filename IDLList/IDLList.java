import java.util.ArrayList;

public class IDLList<E> {
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	//node class for data fields
	private static class Node<F>{
		F data;
		Node<F> next;
		Node<F> prev;
		
		Node(F elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		
		Node(F elem, Node<F> prev, Node<F> next) {
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}
	}
	
	//methods
	public IDLList() { //creates empty double linked list
		head = null;
		tail = null;
		indices = new ArrayList<Node<E>>();
		size = 0;
	}
	
	public boolean add(int index, E elem) { //adds element at specific index
		if (index > size || index < 0) {
			throw new IllegalStateException();
		}
		
		if (index == size) {
			this.append(elem);
			return true;
		}
		
		if (index == 0) {
			this.add(elem);
			return true;
		}
		
		Node<E> previousElement = indices.get(index-1);
		Node<E> temp = new Node<E>(elem, previousElement, previousElement.next);
		previousElement.next.prev = temp;
		previousElement.next = temp;
		indices.add(index, temp);
		size++;
		
		return true;
	}
	
	public boolean add(E elem) { //adds element to front
		if (size == 0) {
			head = new Node<E>(elem);
			tail = head;
			indices.add(0, head);
			size++;
			return true;
		}
		
		Node<E> temp = head;
		head = new Node<E>(elem, null, head);
		temp.prev = head;
		indices.add(0, head);
		size++;
		
		return true;
	}
	
	public boolean append(E elem) { //adds element to end of list
		if (size == 0) {
			add(elem);
			return true;
		}
			
		Node<E> temp = tail;
		tail = new Node<E>(elem, tail, null);
		temp.next = tail;
		indices.add(tail);
		size++;
		
		return true;
	}
	
	public E get(int index) { //returns element at given index of list
		return indices.get(index).data;
	}
	
	public E getHead() { //returns head.data
		return head.data;
	}
	
	public E getLast() { //returns tail.data
		return tail.data;
	}
	
	public int size() { //returns size
		return size;
	}
	
	public E remove() { //removes and returns element at the head
		if (size == 0) {
			throw new IllegalStateException();
		}
		
		if(size == 1) {
			Node<E> temp = head;
			head = null;
			tail = null;
			indices.clear();
			size--;
			return temp.data;
		}
		
		Node<E> temp = head;
		head = head.next;
		indices.remove(0);
		size--;
		
		return temp.data;
	}
	
	public E removeLast() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		
		if (size == 1) {
			Node<E> temp = tail;
			head = null;
			tail = null;
			indices.clear();
			size--;
			return temp.data;
		}
		
		Node<E> temp = tail;
		tail = tail.prev;
		tail.next = null;
		indices.remove(size - 1);
		size--;
		return temp.data;
	}

	public E removeAt(int index) {
		if (size == 0) {
			throw new IllegalStateException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == size - 1) {
			E temp = removeLast();
			return temp;
		}
		if (index == 0) {
			E temp = remove();
			return temp;
		}
		
		Node<E> temp = indices.get(index);
		Node<E> previousElement = temp.prev;
		Node<E> nextElement = temp.next;
		previousElement.next = nextElement;
		nextElement.prev = previousElement;			
		indices.remove(index);
		size--;
		return temp.data;
		
	}
	
	public boolean remove(E elem) {
		if (size == 0) {
			throw new IllegalStateException();
		}
		
		int count = 0;
		boolean found = false;
		while(count < size && !found) {
			if (indices.get(count).data.equals(elem)) {
				removeAt(count);
				found = true;
			}
			else {
				count++;
			}
		}
		return found;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		Node<E> current = head;
		s.append("[");
		while (current!=null) {
			if(current.next != null) {
				s.append(current.data.toString()+",");
				current = current.next;
			}
			else {
				s.append(current.data.toString());
				current = current.next;
			}
		}
		s.append("]");
		return s.toString();
	}

}
