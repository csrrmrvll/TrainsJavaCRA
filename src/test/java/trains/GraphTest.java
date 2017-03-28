/**
 * 
 */
package trains;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import trains.Graph.NoSuchRouteError;

/**
 * @author csr
 *
 */
public class GraphTest {
	
	private static final String					A			= "A";
	private static final String					B			= "B";
	private static final String					C			= "C";
	private static final String					D			= "D";
	private static final String					E			= "E";
	
	private static final int					DIST_AB		= 5;
	private static final int					DIST_BC		= 4;
	private static final int					DIST_CD		= 8;
	private static final int					DIST_DC		= 8;
	private static final int					DIST_AD		= 5;
	private static final int					DIST_EB		= 3;
	private static final int					DIST_AE		= 7;
	
	private static final List<String>			GRAPH_INPUT	= Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5",
			"CE2", "EB3", "AE7");
	private static final String					ABC			= A + B + C;
	private static final String					AD			= A + D;
	private static final String					ADC			= A + D + C;
	private static final String					AEBCD		= A + E + B + C + D;
	private static final String					AED			= A + E + D;
	
	private static final Map<String, String>	DISTANCES	= new HashMap<>();
	
	private static final Graph					GRAPH		= new Graph(GRAPH_INPUT);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DISTANCES.put(ABC, String.valueOf(DIST_AB + DIST_BC));
		DISTANCES.put(AD, String.valueOf(DIST_AD));
		DISTANCES.put(ADC, String.valueOf(DIST_AD + DIST_DC));
		DISTANCES.put(AEBCD, String.valueOf(DIST_AE + DIST_EB + DIST_BC + DIST_CD));
		DISTANCES.put(AED, NoSuchRouteError.NO_SUCH_ROUTE);
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
	
	private final void testRouteDistance(String route) {
		try {
			final String actual = String.valueOf(GRAPH.getRouteDistance(route));
			final String expected = DISTANCES.get(route);
			Assert.assertEquals(expected, actual);
		} catch (NoSuchRouteError e) {
			System.out.println("Problem computing " + route + "'s distance: " + e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceABC() {
		this.testRouteDistance(ABC);
	}
	
	@Test
	public final void testRouteDistanceAD() {
		this.testRouteDistance(AD);
	}
	
	@Test
	public final void testRouteDistanceADC() {
		this.testRouteDistance(ADC);
	}
	
	@Test
	public final void testRouteDistanceAEBCD() {
		this.testRouteDistance(AEBCD);
	}
	
	@Test
	public final void testRouteDistanceAED() {
		this.testRouteDistance(AED);
	}
	
}
