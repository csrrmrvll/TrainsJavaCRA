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
	
	private static final String					AB			= A + B;
	private static final String					BC			= B + C;
	private static final String					CD			= C + D;
	private static final String					DC			= D + C;
	private static final String					DE			= D + E;
	private static final String					AD			= A + D;
	private static final String					CE			= C + E;
	private static final String					EB			= E + B;
	private static final String					AE			= A + E;
	
	private static final int					DIST_AB		= 5;
	private static final int					DIST_BC		= 4;
	private static final int					DIST_CD		= 8;
	private static final int					DIST_DC		= 8;
	private static final int					DIST_DE		= 6;
	private static final int					DIST_AD		= 5;
	private static final int					DIST_CE		= 2;
	private static final int					DIST_EB		= 3;
	private static final int					DIST_AE		= 7;
	
	private static final List<String>			GRAPH_INPUT	= Arrays.asList(AB + String.valueOf(DIST_AB),
			BC + String.valueOf(DIST_BC), CD + String.valueOf(DIST_CD), DC + String.valueOf(DIST_DC),
			DE + String.valueOf(DIST_DE), AD + String.valueOf(DIST_AD), CE + String.valueOf(DIST_CE),
			EB + String.valueOf(DIST_EB), AE + String.valueOf(DIST_AE));
	
	private static final String					ABC			= A + B + C;
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
			System.out.println(actual);
		} catch (NoSuchRouteError e) {
			System.out.println(e.getMessage());
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
	
	private final void testNumberOfTrips(String from, String to, StopCondition sc, int expected) {
		final int actual = GRAPH.getNumberOfTrips(from, to, sc);
		Assert.assertEquals(expected, actual);
		System.out.println(actual);
	}
	
	@Test
	public final void testNumberOfTripsFromCToC() {
		this.testNumberOfTrips(C, C, new LessThanOrEqualStopsCondition(3), 2);
	}
	
	@Test
	public final void testNumberOfTripsFromAToC() {
		this.testNumberOfTrips(A, C, new EqualToStopsCondition(4), 3);
	}
	
	private final void testShortestRoute(String from, String to, int expected) {
		final int actual = GRAPH.getShortestRoute(from, to);
		Assert.assertEquals(expected, actual);
		System.out.println(actual);
	}
	
	@Test
	public final void testShortestRouteFromBToB() {
		this.testShortestRoute(C, C, 9);
	}
	
	@Test
	public final void testShortestRouteFromAToC() {
		this.testShortestRoute(A, C, 9);
	}
}
