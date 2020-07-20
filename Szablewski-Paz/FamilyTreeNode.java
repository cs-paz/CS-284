import java.util.ArrayList;
import java.util.List;

public class FamilyTreeNode {
	private String lastName;
	private List<Person> members;
	public FamilyTreeNode left;
	public FamilyTreeNode right;

	/**
     	* Constructor: instantializes a new FamilyTreeNode
     	* given a lastName
     	*/
	public FamilyTreeNode(String lastName) {
      if (lastName == null) {
		throw new IllegalArgumentException("Fields can't be null");
      } 
      this.lastName = lastName;
      this.members = new ArrayList<Person>();
	}

	/**
     	* Returns the last name of the FamilyTreeNode
     	*/
	public String getLastName() {
		if(lastName == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
		return lastName;
	}

	/**
     	* Returns the arraylist of members in the FamilyTreeNode
     	*/
	public List<Person> getMembers() {
		if(members == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
		return members;
	}

	/*
	 * Returns true if there is an instance of Person in the FamilyTreeNode that has
	 * the same first and last name provided Return false otherwise
	 */
	public boolean doesFamilyMemberExist(String lastName, String firstName) {
		if(lastName == null || firstName == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
    	for(Person person: members){
    		if(person.getLastName().equals(lastName) &&
    		 person.getFirstName().equals(firstName)){
    			return true;
    		}
    	}
    	return false;
	}

	/**
	 * Returns true if there is an instance of Person in the FamilyTreeNode whose
	 * phone number matches the one provided Returns false otherwise
	 */
	public boolean doesNumberExist(String phoneNumber) {
		if(phoneNumber == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
    	for(Person person: members){
    		if(person.getPhoneNumber().equals(phoneNumber)){
    			return true;
    		}
    	}
    	return false;
	}

	/* 
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(String lastName, String firstName, String phoneNumber) {
		if(lastName == null || firstName == null || phoneNumber == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
		if(this.doesFamilyMemberExist(lastName, firstName)) {
			throw new IllegalArgumentException("Can't add already family member that already exists");
		}
		if(lastName != this.lastName) {
			throw new IllegalArgumentException("Can't add family member with a different last name");
		}
		if(this.doesFamilyMemberExist(lastName, firstName)) {
			throw new IllegalArgumentException("Can't add family member with the same first name as another");
		}
		if(this.doesNumberExist(phoneNumber)) {
			throw new IllegalArgumentException("Phone numbers must be unique");
		}
		Person add = new Person(lastName, firstName, phoneNumber);
		addFamilyMember(add);
		
	}

	/**
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(Person person) {
		if(person == null || person.getLastName() == null || 
		person.getFirstName() == null || person.getPhoneNumber() == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
		if(person.getLastName() != this.lastName) {
			throw new IllegalArgumentException("Can't add family member with a different last name");
		}
		if(this.doesFamilyMemberExist(person.getLastName(), person.getFirstName())) {
			throw new IllegalArgumentException("Can't add family member with the same first name as another");
		}
		if(this.doesNumberExist(person.getPhoneNumber())) {
			throw new IllegalArgumentException("Phone numbers must be unique");
		}
		
		members.add(person);
	}

	/*
	 * Returns the phone number of the person in the family with the given phone
	 * number Returns "Does not exist." if not found
	 */
	public String getPhoneNumberOfFamilyMember(String lastName, String firstName) {
		if(lastName == null || firstName == null) {
			throw new IllegalArgumentException();
		}
    	for(Person person: members){
    		if(person.getLastName().equals(lastName) &&
    		 person.getFirstName().equals(firstName)){
    			return person.getPhoneNumber();    		
    		}
    	}
		return "Does not exist.";
	}
	
	/*
	 * toString method Ex: [] [John Smith (5551234567), May Smith (5551234568),
	 * April Smith (5551234569), August Smith (5551234570)]
	 */
	public String toString() {
		String ret = "[";
		for(int i = 0; i < members.size(); i++){ 
			if(!(i == members.size() - 1)) {
				ret += members.get(i).toString() + ", ";
			}
			else {
				ret += members.get(i).toString();
			}
    		
    	}
		ret += "]";
		return ret;
	}
}

