/**
 * 
 */
package trains;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author csr
 *
 */
public class GraphTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testGraph() {
		final List<String> nodes = Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
		final Graph graph = new Graph(nodes);
		Collections.sort(nodes);
		final StringBuilder sb = new StringBuilder();
		for (String s : nodes) {
			sb.append(s);
		}
		final String expected = sb.toString();
		System.out.println(expected);
		final String actual = graph.toString();
		Assert.assertEquals(expected, actual);
	}
	
}
