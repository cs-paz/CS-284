//I pledge my honor that I have abided by Stevens Honor Code

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
	public Map<Character, BSFamilyTree> directory;

	/**
     	* Creates a new phone book with an empty directory.
     	*/
	public PhoneBook() {
		Character alphabet[] = 
		{'A','B','C','D','E','F','G','H',
		'I','J','K','L','M','N','O','P','Q',
		'R','S','T','U','V','W','X','Y','Z'};
		
		directory = new HashMap<Character, BSFamilyTree>();
		for(int i = 0; i < alphabet.length; i++) {
			directory.put(alphabet[i], new BSFamilyTree());
		}
	}

	/*
	 * Returns the instance of BSFamilyTree at the indicated letter
	 * Must accept lowercase letters as well as uppercase letters
	 */
	public BSFamilyTree getFamilyTree(char letter) {
		Character alphabet[] = 
			{'A','B','C','D','E','F','G','H',
			'I','J','K','L','M','N','O','P','Q',
			'R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i',
			'j','k','l','m','n','o','p','q','r',
			's','t','u','v','w','x','y','z'};
		for(int i = 0; i < alphabet.length; i++) {
			if(letter == alphabet[i]) {
				break;
			}
			if(i == alphabet.length - 1) {
				throw new IllegalArgumentException("Argument must be an uppercase or lowercase letter");
			}
		}
		char key = Character.toUpperCase(letter);
		return directory.get(key);
	}

	/*
	 * Adds a FamilyTreeNode to the PhoneBook
	 */
	public void addFamily(String lastName) {
		if(lastName == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
		char key = Character.toUpperCase(lastName.charAt(0));
		directory.get(key).addFamilyTreeNode(lastName);
	}

	/*
	 * Adds a Person to the PhoneBook
	 * If a FamilyTreeNode with the given last name doesn't currently exist, create the FamilyTreeNode
	 */
	public void addPerson(String lastName, String firstName, String phoneNumber) {
		if(lastName == null || firstName == null || phoneNumber == null) {
			throw new IllegalArgumentException("Fields can't be null");
		}
		//change this for loop
		for(int i = 65; i < 91; i++) {
            if(getFamilyTree((char)i).doesNumberExist(phoneNumber) || getFamilyTree((char)i).doesFamilyMemberExist(lastName, firstName)) {
                throw new IllegalArgumentException("Either phone number or family member already exists");
            }
        }
		char key = Character.toUpperCase(lastName.charAt(0));
		if(!(directory.get(key).doesFamilyExist(lastName))) {
			directory.get(key).addFamilyTreeNode(lastName);
		}
		
		directory.get(key).getFamilyTreeNode(lastName).
		addFamilyMember(lastName, firstName, phoneNumber);
	}

	/*
	 * Finds the phone number of a person
	 * Returns 'Does not exist.' if not found.
	 */
	public String getPhoneNumber(String lastName, String firstName) {
		
		char key = Character.toUpperCase(lastName.charAt(0));
		if(!directory.get(key).doesFamilyMemberExist(lastName, firstName)) {
			return "Does not exist.";
		}
		return directory.get(key).getFamilyTreeNode(lastName).
		getPhoneNumberOfFamilyMember(lastName, firstName);
	}

    	/**
     	* String representation of PhoneBook
     	*/
	public String toString() {
		Character alphabet[] = 
			{'A','B','C','D','E','F','G','H',
			'I','J','K','L','M','N','O','P','Q',
			'R','S','T','U','V','W','X','Y','Z'};
        String ret = "";

        for(Character letter: alphabet) {
            ret += letter + "\n" + directory.get(letter).toString();
        }

        return ret;
    }
}
