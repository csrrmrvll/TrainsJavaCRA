/**
 * 
 */
package trains;

import java.util.ArrayList;
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
	
	private static final Town					A			= new Town("A");
	private static final Town					B			= new Town("B");
	private static final Town					C			= new Town("C");
	private static final Town					D			= new Town("D");
	private static final Town					E			= new Town("E");
	
	private static final int					DIST_AB		= 5;
	private static final int					DIST_BC		= 4;
	private static final int					DIST_CD		= 8;
	private static final int					DIST_DC		= 8;
	private static final int					DIST_DE		= 6;
	private static final int					DIST_AD		= 5;
	private static final int					DIST_CE		= 2;
	private static final int					DIST_EB		= 3;
	private static final int					DIST_AE		= 7;
	
	private static final List<String>			GRAPH_INPUT	= Arrays.asList("AB" + String.valueOf(DIST_AB),
			"BC" + String.valueOf(DIST_BC), "CD" + String.valueOf(DIST_CD), "DC" + String.valueOf(DIST_DC),
			"DE" + String.valueOf(DIST_DE), "AD" + String.valueOf(DIST_AD), "CE" + String.valueOf(DIST_CE),
			"EB" + String.valueOf(DIST_EB), "AE" + String.valueOf(DIST_AE));
	
	private static final Graph					GRAPH		= new Graph(GRAPH_INPUT);
	
	private static final Route					ABC			= new Route(new ArrayList<>(Arrays.asList(B, C)));
	private static final Route					AD			= new Route(new ArrayList<>(Arrays.asList(D)));
	private static final Route					ADC			= new Route(new ArrayList<>(Arrays.asList(D, C)));
	private static final Route					AEBCD		= new Route(new ArrayList<>(Arrays.asList(E, B, C, D)));
	private static final Route					AED			= new Route(new ArrayList<>(Arrays.asList(E, D)));
	
	private static final Map<Route, Integer>	DISTANCES	= new HashMap<>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DISTANCES.put(ABC, DIST_AB + DIST_BC);
		DISTANCES.put(AD, DIST_AD);
		DISTANCES.put(ADC, DIST_AD + DIST_DC);
		DISTANCES.put(AEBCD, DIST_AE + DIST_EB + DIST_BC + DIST_CD);
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
	
	private static <T> void assertAndPrint(T expected, T actual) {
		Assert.assertEquals(expected, actual);
		System.out.println(actual);
	}
	
	private static final void testRouteDistance(Route route) throws NoSuchRouteError {
		final int actual = GRAPH.getRouteDistance(A, route);
		final int expected = DISTANCES.get(route);
		GraphTest.assertAndPrint(expected, actual);
	}
	
	@Test
	public final void testRouteDistanceABC() {
		try {
			GraphTest.testRouteDistance(ABC);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceAD() {
		try {
			GraphTest.testRouteDistance(AD);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceADC() {
		try {
			GraphTest.testRouteDistance(ADC);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceAEBCD() {
		try {
			GraphTest.testRouteDistance(AEBCD);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceAED() {
		try {
			GraphTest.testRouteDistance(AED);
		} catch (NoSuchRouteError e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	private static final void testNumberOfTrips(Town from, Town to, StopCondition sc, int expected) {
		try {
			final int actual = GRAPH.getNumberOfTrips(from, to, sc);
			GraphTest.assertAndPrint(expected, actual);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testNumberOfTripsFromCToCWithLessThanThreeStops() {
		GraphTest.testNumberOfTrips(C, C, new LessThanOrEqualStopCondition(3), 2);
	}
	
	@Test
	public final void testNumberOfTripsFromCToCWithDistanceLessThanThirty() {
		GraphTest.testNumberOfTrips(C, C, new LessThanStopCondition(30), 7);
	}
	
	@Test
	public final void testNumberOfTripsFromAToC() {
		GraphTest.testNumberOfTrips(A, C, new EqualToStopCondition(4), 3);
	}
	
	private static final void testShortestRoute(Town from, Town to, int expected) {
		try {
			final int actual = GRAPH.getShortestRoute(from, to);
			GraphTest.assertAndPrint(expected, actual);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testShortestRouteFromAToC() {
		GraphTest.testShortestRoute(A, C, 9);
	}
	
	private static final void testShortestCyclicRoute(Town from, int expected) {
		try {
			final int actual = GRAPH.getShortestCyclicRoute(from);
			GraphTest.assertAndPrint(expected, actual);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testShortestRouteFromBToB() {
		GraphTest.testShortestCyclicRoute(B, 9);
	}
}
