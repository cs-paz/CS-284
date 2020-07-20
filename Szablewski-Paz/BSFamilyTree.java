import java.util.ArrayList;

/**
 * BSFamilyTree creates a tree for specific families. 
 */
public class BSFamilyTree {
    public FamilyTreeNode root;

    /**
     * Constructor: constructs an empty BSFamilyTree
     */
    public BSFamilyTree() {
    	root = null;
    }

    /**
     * takes in the last name and returns true if there
     * is a FamilyTreeNode with the given last name.
     */
    public boolean doesFamilyExist(String lastName) {
    	if(lastName == null) {
    		throw new IllegalArgumentException();
    	}
      	return findHelp(lastName, root);
    }
    
    //helper for doesFamilyExist
    private boolean findHelp(String lastName, FamilyTreeNode current) {
    	if(current == null) {
    		return false;
    	}
    	int n = current.getLastName().compareTo(lastName);	
    	if(n == 0) {
    		return true;
    	}
    	else {
	    	if(n < 0) {
	    		return findHelp(lastName, current.right);
	    	}
	    	else {
	    		return findHelp(lastName, current.left);
	    	}   
    	}
    }

    /**
     * Takes in a last name and creates a new instance of
     * FamilyTreeNode and adds it to the BSFamilyTree.
     */
    public void addFamilyTreeNode(String lastName) {
    	if(lastName == null) {
    		throw new IllegalArgumentException("Fields can't be null");
    	}
    	root = addHelp(lastName, root);
    }
    
  //helper for addFamilyTreeNode
    private FamilyTreeNode addHelp(String lastName, FamilyTreeNode current) {
    	if(current == null) {
    		return new FamilyTreeNode(lastName);
    	}
    	if(this.doesFamilyExist(lastName)) {
    		throw new IllegalArgumentException("Can't add a family of an already existing last name");
    	}
    	else {
    		int n = current.getLastName().compareTo(lastName);
    		if(n > 0) {
    			current.left = addHelp(lastName, current.left);
    			return current;
    		}
    		current.right = addHelp(lastName, current.right);
    		return current;
    	}
    }

    /**
     * Takes a last name and then finds that specific
     * family tree and then returns that FamilyTreeNode
     * If last name is not in tree, throws an exception.
     */
    public FamilyTreeNode getFamilyTreeNode(String lastName) {
       if(lastName == null) {
    	   throw new IllegalArgumentException("Fields can't be null");
       }
       FamilyTreeNode temp = root;
       return getHelp(lastName, temp);
       
    }
    
    //helper for getFamilyTreeNode
    private FamilyTreeNode getHelp(String lastName, FamilyTreeNode current) {
    	if(current == null) {
    		throw new IllegalArgumentException();
    	}
    	int n = current.getLastName().compareTo(lastName);	
    	if(n == 0) {
    		return current;
    	}
    	else {
	    	if(n < 0) {
	    		return getHelp(lastName, current.right);
	    	}
	    	else {
	    		return getHelp(lastName, current.left);
	    	}   
    	}
    }

    /**
     * Returns true if the input phone number exists in the BSFamilyTree
     * false otherwise.
     */
    public boolean doesNumberExist(String phoneNumber) {
    	if(root == null) {
    		return false;
    	}
    	return findNumberHelp(phoneNumber, root);
    }
    
    //helper for doesNumberExist
    private boolean findNumberHelp(String phoneNumber, FamilyTreeNode current) {
    	if(current == null) {
    		return false;
    	}
    	if(current.doesNumberExist(phoneNumber)) {
    		return true;
    	}
    	
    	else {
    		boolean rightSide = findNumberHelp(phoneNumber, current.right);
    		boolean leftSide = findNumberHelp(phoneNumber, current.left);
    		
    		if(rightSide) {
    			return true;
    		}
    		
    		return leftSide;
    	}
    }
    
    //method used for get phone number
    public boolean doesFamilyMemberExist(String lastName, String firstName) {
        if(root == null) {
            return false;
        }
        return findFamilyMemberHelp(lastName, firstName, root);
    }

    //helper for doesFamilyMemberExist
    public boolean findFamilyMemberHelp(String lastName, String firstName, FamilyTreeNode current) {
    	if(current == null) {
    		return false;
    	}
        if(current.doesFamilyMemberExist(lastName, firstName)) {
            return true;
        }
    	
    	else {
    		boolean rightSide = findFamilyMemberHelp(lastName, firstName, current.right);
    		boolean leftSide = findFamilyMemberHelp(lastName, firstName, current.left);
    		
    		if(rightSide) {
    			return true;
    		}
    		
    		return leftSide;
    	}
    }

    /**
     * Returns the string representation of the BSFamilyTree
     */
    public String toString() {
        return toString(root, 0).toString();
    }
    private StringBuilder toString(FamilyTreeNode current, int i) {
    	StringBuilder ret = new StringBuilder();
    	for(int j = 0; j < i; j++) {
    		ret.append("  ");
    	}
    	if(current == null) {
    		ret.append("null\n");
    	}
    	else {
    		ret.append(current.toString() + "\n");
    		ret.append(toString(current.left, i + 1));
    		ret.append(toString(current.right, i + 1));
    	}
    	
    	return ret;
    }
}
