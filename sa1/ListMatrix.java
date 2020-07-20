import java.util.List;

public class ListMatrix extends ListCollection<Integer> {
  private int rows;
  private int columns;
  //protected List<SingleLL<E>> collection

  /**
   * Initializes a `ListMatrix` with the specified number of rows and columns. By
   * default, ALL elements are set to 0.
   * 
   * @param rows
   * @param columns
   */
  public ListMatrix(int rows, int columns) {
	  super();
	  if(rows <= 0 || columns <= 0) {
		  throw new IllegalArgumentException("Parameters must be positive numbers.");
	  }
	  this.rows = rows;
	  this.columns = columns;
	  
	  for(int i = 0; i < rows; i++) {
		 SingleLL<Integer> temp = new SingleLL<Integer>();
		 for(int j = 0; j < columns; j++) {
			 temp.append(0);
		 }
		 
		this.addList(temp);
	 }
  }

  /**
   * @return the number of rows
   */
  public int numRows() {
    return this.rows;
  }

  /**
   * 
   * @return the number of columns
   */
  public int numColumns() {
    return this.columns;
  }

  /**
   * Adds the `ListMatrix` to `ListMatrix other`, storing the result in the caller
   * (this)
   * 
   * @throws IllegalArgumentException if dimensions do not peoperly coincide
   * @param other
   * @complexity Your big-o and supporting explanation here
   * It will have an O(n^2) time complexity because it is running in
   *  a double for loop for the length of two different lists,
   *  and setElem is O(1)
   */
  public void add(ListMatrix other) {
	  if(this.columns != other.numColumns() || this.rows != other.numRows()) {
		  throw new IllegalArgumentException("Rows and columns must be equal."); //this and other.
	  }
	  
	  for(int i = 0; i < rows; i++) {
		  for(int j = 0; j < columns; j++) {
			  this.setElem(i , j, this.getElem(i, j) + other.getElem(i, j));
		  }
	  }
	  
  }

  /**
   * Returns the transpose of the matrix
   * 
   * @param matrix
   * @return matrix tranpose
   */
  public static ListMatrix transpose(ListMatrix matrix) {
	  ListMatrix ret = new ListMatrix(matrix.numColumns(), matrix.numRows());
	  for(int i = 0; i < matrix.numRows(); i++) {
		  for(int j = 0; j < matrix.numColumns(); j++) {
			  ret.setElem(j, i, matrix.getElem(i, j));
		  }
	  }
	  
	  return ret;
  }

  /**
   * Multiplies the `ListMatrix` with `ListMatrix other`, returning the result as
   * a new `ListMatrix.
   * 
   * @throws IllegalArgumentException if dimensions do not peoperly coincide
   * @param other
   * @return
   */
  public ListMatrix multiply(ListMatrix other) {
	  if(this.rows != other.numColumns()) {
		  throw new IllegalArgumentException("this.rows and other.columns must be equal");
	  }
	  
	  ListMatrix ret = new ListMatrix(rows, other.columns);
	  
	  for(int i = 0; i < ret.numRows(); i++) {
		  for(int j = 0; j < ret.numColumns(); j++) {
			  for(int k = 0; k < other.numRows(); k++ ) {
				  ret.setElem(i , j, ret.getElem(i, j) 
				  + ( this.getElem(i, k) * other.getElem(k, j)));
			  }
		  }
	  }
	  
	  return ret;
  }

 /* public static void main(String[] args) {
	  ListMatrix x1 = new ListMatrix(3, 3);
	  ListMatrix x2 = new ListMatrix(3, 3);
	  ListMatrix y1 = new ListMatrix(3, 3);
	  ListMatrix y2 = new ListMatrix(3, 3);
	  ListMatrix z1 = new ListMatrix(2, 3);
	  
	  x2.setElem(0, 0, 0);
	  x2.setElem(0, 1, 1);
	  x2.setElem(0, 2, 2);
	  x2.setElem(1, 0, 3);
	  x2.setElem(1, 1, 4);
	  x2.setElem(1, 2, 5);
	  x2.setElem(2, 0, 6);
	  x2.setElem(2, 1, 7);
	  x2.setElem(2, 2, 8);
	  
	  
	  
	  for(int i = 0; i < 3; i++) {
		  for(int j = 0; j < 3; j++) {
			  y1.setElem(i, j, 2);
			  y2.setElem(i, j, 2);
		  }
	  }
	  
	  for(int i = 0; i < 2; i++) {
		  for(int j = 0; j < 3; j++) {
			  z1.setElem(i, j, 2);
		  }
	  }
	  
	  
	  
	  System.out.println(x1);
	  System.out.println(x2);
	  x1.add(x2);
	  System.out.println(x1);
	  System.out.println(y1.multiply(y2));
	  System.out.println(z1);
	  System.out.println(transpose(z1));
	  System.out.println(transpose(transpose(z1)));
	  
  }*/
  
}
