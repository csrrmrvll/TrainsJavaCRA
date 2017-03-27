/**
 * 
 */
package trains;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author csr
 *
 */
public class GraphTest {
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link trains.Graph#Graph(java.util.List)}.
	 */
	@Test
	public final void testGraph() {
		final List<String> nodes = Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
		final Graph graph = new Graph(nodes);
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link trains.Graph#getEdges()}.
	 */
	@Test
	public final void testGetEdges() {
		fail("Not yet implemented"); // TODO
	}
	
}
