import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void testAdd() {
		Treap<Integer> test = new Treap<Integer>();
		assertTrue(test.add(1,1));
		assertTrue(test.add(2,2));
		assertTrue(test.add(3,3));
		assertTrue(test.add(4,4));
		assertTrue(test.add(5,5));
		assertFalse(test.add(1,1));
		assertFalse(test.add(3,3));
	}
	
	void testDelete() {
		Treap<Integer> test = new Treap<Integer>();
		test.add(1,1);
		test.add(2,2);
		test.add(3,3);
		test.add(4,4);
		test.add(5,5);
		assertTrue(test.delete(5));
		assertTrue(test.delete(3));
		assertFalse(test.delete(null));
		assertFalse(test.delete(10));
	}
	
	void testFind() {
		Treap<Integer> test = new Treap<Integer>();
		test.add(1,1);
		test.add(2,2);
		test.add(3,3);
		test.add(4,4);
		test.add(5,5);
		assertTrue(test.find(1));
		assertTrue(test.find(2));
		assertTrue(test.find(5));
		assertFalse(test.find(9));
		assertFalse(test.find(null));
		assertFalse(test.find(7));
	}
	
	void bigTest() {
		Treap<Integer> test = new Treap<Integer>();
		test.add(1,1);
		test.add(2,2);
		test.add(3,3);
		test.add(4,4);
		test.add(5,5);
		assertTrue(test.add(6,6));
		assertTrue(test.add(7,7));
		assertTrue(test.add(8,8));
		assertTrue(test.add(9,9));
		assertTrue(test.add(10,10));
		assertFalse(test.add(1,1));
		assertFalse(test.add(2,2));
		assertTrue(test.delete(10));
		assertTrue(test.delete(8));
		assertFalse(test.delete(20));
		assertFalse(test.delete(10));
		assertTrue(test.find(2));
		assertTrue(test.find(4));
		assertFalse(test.find(8));
		assertFalse(test.find(20));
	}
	
	void recommendedTest() {
		Treap<Integer> test = new Treap<Integer>();
		test.add(4,19);
		test.add(2,31);
		test.add(6,70);
		test.add(1,84);
		test.add(3,12);
		test.add(5,83);
		test.add(7,26);
		assertTrue(test.add(5,20));
		assertTrue(test.add(20,21));
		assertFalse(test.add(20, 90));
		assertFalse(test.add(55, 90));
		assertTrue(test.delete(20));
		assertFalse(test.delete(20));
		assertTrue(test.delete(4));
		assertFalse(test.delete(200));
		assertTrue(test.find(6));
		assertTrue(test.find(2));
		assertFalse(test.find(600));
		assertFalse(test.find(20));
		
	}

}