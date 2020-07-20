//Christian Szablewski-Paz
//Section A
//I pledge my honor that I have abided by Stevens honor system.

import java.util.ArrayList;
import java.util.List;

public class ListCollection<E> {
  private int nodeCount;
  protected List<SingleLL<E>> collection;

  /**
   * Base constructor, initializes an empty ListCollection.
   */
  public ListCollection() {
	  collection = new ArrayList<SingleLL<E>>();
	  nodeCount = 0;
  }

  /**
   * Initializes a ListCollection with `numLists` lists.
   * 
   * @param numLists
   */
  public ListCollection(int numLists) {
	  if (numLists < 0) {
			throw new IllegalArgumentException("Parameter cannot be negative.");
	  }
	  collection = new ArrayList<SingleLL<E>>();
	  nodeCount = 0;
	  SingleLL<E> temp = new SingleLL<E>();
	  for(int i = 0; i < numLists; i++) {
		  collection.add(temp);
	  }
  }

  /**
   * @return The size of the `ListCollection`
   */
  public int size() {
	  return collection.size();
  }

  /**
   * Sets the nodeCount
   * 
   * @param nodeCount
   */
  public void setNodeCount(int nodeCount) {
	  this.nodeCount = nodeCount;
  }

  /**
   * @return the nodeCount
   */
  public int getNodeCount() {
	  return nodeCount;
  }

  /**
   * Adds the specified `SingleLL` to the end of the `ListCollection`
   * 
   * @param list
   */
  public void addList(SingleLL<E> list) {
	  collection.add(list);
	  nodeCount += list.size();
  }

  /**
   * Adds the specified `List` to the `ListCollection`
   * 
   * @param list
   * @complexity Your big-o and supporting explanation here
   * The time complexity is O(n) because the for loop runs 
   * n times where n is the length of the list
   */
  public void addList(List<E> list) {
      SingleLL temp = new SingleLL<>();
      
      for(int i = 0; i < list.size(); i++) {
    	  temp.append(list.get(i));
      }
      
	  collection.add(temp);
	  nodeCount += temp.size();
  }

  /**
   * Returns the list at the specified index
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @return the list
   */
  public SingleLL<E> getList(int listIndex) {
	  if (listIndex < 0 || listIndex >= this.size()) {
			throw new IllegalArgumentException("Index is out of bounds.");
	  }
	  return collection.get(listIndex);
  }

  /**
   * Adds an element to the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   * @complexity Your big-o and supporting explanation here
   * The time complexity will be O(n^2) because get is an O(n) time complexity and insert is also an O(n) time complexity.
   */
  public void addElem(int listIndex, int elemIndex, E elem) {
	  if (listIndex < 0 || listIndex >= this.size() || elemIndex < 0 
			  || elemIndex >= collection.get(listIndex).size()) {
			throw new IllegalArgumentException("Index is out of bounds.");
	  }
	  if(collection.size() == 0 && listIndex == 0){
		  SingleLL temp = new SingleLL<>();
		  temp.push(elem);
		  collection.add(temp);
	  }
	  else{
		  collection.get(listIndex).insert(elemIndex, elem);
	  }
	  nodeCount++;
  }

  /**
   * Sets the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   */
  public void setElem(int listIndex, int elemIndex, E elem) {
	  if (listIndex < 0 || listIndex >= this.size() || elemIndex < 0
			  || elemIndex >= collection.get(listIndex).size()) {
			throw new IllegalArgumentException("Index is out of bounds.");
	  }
	  
	  collection.get(listIndex).set(elemIndex, elem);
  }

  /**
   * Returns the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @return
   */
  public E getElem(int listIndex, int elemIndex) {
	  if (listIndex < 0 || listIndex >= this.size() || elemIndex < 0
			  || elemIndex >= collection.get(listIndex).size()) {
			throw new IllegalArgumentException("Index is out of bounds.");
	  }
	  
	  return collection.get(listIndex).get(elemIndex);
  }

  /**
   * Returns the `ListCollection` in string form, following STRICTLY the rule of
   * "[LIST1, LIST2, LIST3, ... ]" Ex: [[0, 1, 2], [3, 4, 5] [6, 7, 8]]
   */
  public String toString() {
	  StringBuilder x = new StringBuilder();
	  x.append("[");
	  for(int i = 0; i < this.size(); i++){
		  if(i != this.size() - 1) {
			  x.append(collection.get(i).toString() + ", " );
		  }
		  else {
			  x.append(collection.get(i).toString());
		  }
	  }
	  x.append("]");
	  return x.toString();
  }

  public static void main(String[] args) {
	 /* SingleLL<Integer> s1 = new SingleLL();
	  s1.append(0);
	  s1.append(1);
	  s1.append(2);
	  SingleLL<Integer> s2 = new SingleLL();
	  s2.append(3);
	  s2.append(4);
	  s2.append(5);
	  SingleLL<Integer> s3 = new SingleLL();
	  s3.append(6);
	  s3.append(7);
	  s3.append(8);
	  
	  ListCollection<Integer> x1 = new ListCollection<Integer>();
	  ListCollection<Integer> x2 = new ListCollection<Integer>(3);
	  
	  x1.addList(s1);
	  x1.addList(s2);
	  x1.addList(s3);
	  
	  System.out.println("addList Tester: "+ x1); //[[0, 1, 2], [3, 4, 5], [6, 7, 8]]
	  System.out.println("getList Tester: "+ x1.getList(0)); // [0, 1, 2]
	  x1.addElem(2, 3, 9);
	  System.out.println("addElem Tester: " + x1); //[[0, 1, 2], [3, 4, 5], [6, 7, 8, 9]]
	  x1.setElem(0, 0, 69);
	  System.out.println("setElem Tester: " + x1); //[[69, 1, 2], [3, 4, 5], [6, 7, 8, 9]]
	  System.out.println(x1.getElem(0,0)); //69
 
	  ListCollection x1 = new ListCollection();
	  x1.addElem(0, 0, 0);
	  
	  System.out.println(x1);*/
  } 
}
