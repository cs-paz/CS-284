import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AnagramsTest {
	
	@Test
	void testGeneral() {
		Anagrams test = new Anagrams ();
		try {
			test.processFile("words_alpha.txt"); 
		} 
		catch (IOException exception) {
			exception.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = test.getMaxEntries();
		assertEquals(maxEntries.get(0).getKey(), 236204078);
		assertEquals(maxEntries.get(0).getValue().size(), 15);
		
		// to whoever is reading this, sorry I didn't really know what to test
		// because all of the methods besides getMaxEntries() didn't return anything
		// and were meant to be private
	}
	

}