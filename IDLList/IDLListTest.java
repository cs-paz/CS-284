import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;


public class IDLListTest {
	
	@Test
	public void testAdd() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.add(1);
		assertEquals("[1]", tester.toString());	
		tester.add(2);
		assertEquals("[2,1]", tester.toString());
	}
	
	@Test
	public void testAddAtIndex() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.add(0, 1);
		assertEquals("[1]", tester.toString());	
		tester.add(2);
		tester.add(3);
		tester.add(4);
		tester.add(5);
		tester.add(3, 6);
		assertEquals("[5,4,3,6,2,1]", tester.toString());
		}
	
	@Test
	public void testAppend() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.append(1);
		assertEquals("[1]", tester.toString());
		tester.append(2);
		assertEquals("[1,2]", tester.toString());
		tester.append(3);
		assertEquals("[1,2,3]", tester.toString());
	}
	
	@Test
	public void testGet() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.add(1);
		tester.add(2);
		tester.add(3);
		tester.add(4);
		tester.add(null);
		assertSame(1, tester.get(4));
		assertSame(4, tester.get(1));
		assertSame(2, tester.get(3));
		assertSame(null, tester.get(0));
	}
	
	@Test
	public void testGetHead() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.append(1);
		tester.append(5);
		tester.append(3);
		assertSame(1, tester.getHead());
		tester.add(6);
		assertSame(6, tester.getHead());
	}
	
	@Test
	public void testGetLast() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.append(1);
		tester.append(3);
		assertSame(3, tester.getLast());
		tester.append(5);
		assertSame(5, tester.getLast());
	}
	
	@Test
	public void testSize() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.add(1);
		tester.add(5);
		assertSame(2, tester.size());
		tester.add(3);
		assertSame(3, tester.size());
		tester.remove();
		assertSame(2, tester.size());
	}
	
	@Test
	public void testRemove() {
		IDLList<String> tester = new IDLList<String>();
		tester.add("hello");
		tester.add("how");
		tester.add("are");
		tester.add("you");
		tester.remove();
		assertEquals("[are,how,hello]", tester.toString());
		tester.append("last");
		assertEquals("[are,how,hello,last]", tester.toString());
	}
	
	@Test
	public void testRemoveLast() {
		IDLList<Character> tester = new IDLList<Character>();
		tester.add('a');
		tester.removeLast();
		assertEquals("[]", tester.toString());
		tester.add('b');
		tester.add('a');
		tester.removeLast();
		assertEquals("[a]", tester.toString());
	}
	
	@Test
	public void testRemoveAt() {
		IDLList<Integer> tester = new IDLList<Integer>();
		tester.add(1);
		tester.add(2);
		tester.add(3);
		tester.removeAt(1);
		assertEquals("[3,1]", tester.toString());
		tester.add(5);
		tester.removeAt(0);
		assertEquals("[3,1]", tester.toString());
	}
	
	@Test
	public void testRemoveElement() {
		IDLList<Character> tester = new IDLList<Character>();
		tester.add('w');
		tester.add('x');
		tester.add('y');
		tester.add('z');
		tester.remove('w');
		tester.remove('z');
		assertEquals("[y,x]", tester.toString());
	}


}
