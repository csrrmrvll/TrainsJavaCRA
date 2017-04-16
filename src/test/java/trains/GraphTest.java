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
	
	private static final Town					A			= new Town("A");
	private static final Town					B			= new Town("B");
	private static final Town					C			= new Town("C");
	private static final Town					D			= new Town("D");
	private static final Town					E			= new Town("E");
	
	private static final int					DIST_AB		= 5;
	private static final int					DIST_AD		= 5;
	private static final int					DIST_AE		= 7;
	private static final int					DIST_BC		= 4;
	private static final int					DIST_CD		= 8;
	private static final int					DIST_CE		= 2;
	private static final int					DIST_DC		= 8;
	private static final int					DIST_DE		= 6;
	private static final int					DIST_EB		= 3;
	private static final int					DIST_ED		= 22;
	
	private static final Road					AB			= new Road(A, B, DIST_AB);
	private static final Road					BC			= new Road(B, C, DIST_BC);
	private static final Road					CD			= new Road(C, D, DIST_CD);
	private static final Road					DC			= new Road(D, C, DIST_DC);
	private static final Road					DE			= new Road(D, E, DIST_DE);
	private static final Road					AD			= new Road(A, D, DIST_AD);
	private static final Road					CE			= new Road(C, E, DIST_CE);
	private static final Road					EB			= new Road(E, B, DIST_EB);
	private static final Road					ED			= new Road(E, D, DIST_ED);
	private static final Road					AE			= new Road(A, E, DIST_AE);
	
	private static final List<Road>				GRAPH_INPUT	= Arrays.asList(AB, BC, CD, DC, DE, AD, CE, EB, AE);
	
	private static final Route					ABC			= new Route(Arrays.asList(AB, BC));
	private static final Route					AD2			= new Route(Arrays.asList(AD));
	private static final Route					ADC			= new Route(Arrays.asList(AD, DC));
	private static final Route					AEBCD		= new Route(Arrays.asList(AE, EB, BC, CD));
	private static final Route					AED			= new Route(Arrays.asList(AE, ED));
	
	private static final Map<Route, Integer>	DISTANCES	= new HashMap<>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DISTANCES.put(ABC, DIST_AB + DIST_BC);
		DISTANCES.put(AD2, DIST_AD);
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
	
	private static <T> void assertAndPrint(T expected, T actual, int testNumber) {
		Assert.assertEquals(expected, actual);
		System.out.println(testNumber + ". " + actual);
	}
	
	private static final void testRouteDistance(Route route, int testNumber) throws NoSuchRouteError {
		final Graph graph = new Graph(GRAPH_INPUT);
		final int actual = graph.getRouteDistance(route);
		final int expected = DISTANCES.get(route);
		GraphTest.assertAndPrint(expected, actual, testNumber);
	}
	
	@Test
	public final void testRouteDistanceABC() {
		try {
			GraphTest.testRouteDistance(ABC, 1);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceAD() {
		try {
			GraphTest.testRouteDistance(AD2, 2);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceADC() {
		try {
			GraphTest.testRouteDistance(ADC, 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceAEBCD() {
		try {
			GraphTest.testRouteDistance(AEBCD, 4);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testRouteDistanceAED() {
		try {
			GraphTest.testRouteDistance(AED, 5);
		} catch (NoSuchRouteError e) {
			System.out.println(5 + ". " + e.getMessage());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	private static final void testNumberOfTrips(Town from, Town to, RouteCondition sc, int expected, int testNumber) {
		try {
			final Graph graph = new Graph(GRAPH_INPUT);
			final int actual = graph.getNumberOfTrips(from, to, sc);
			GraphTest.assertAndPrint(expected, actual, testNumber);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testNumberOfTripsFromCToCWithThreeStopsMaximum() {
		GraphTest.testNumberOfTrips(C, C, new LessThanOrEqualToLimitRouteCondition(C, 3), 2, 6);
	}
	
	@Test
	public final void testNumberOfTripsFromAToCWithFourStops() {
		GraphTest.testNumberOfTrips(A, C, new EqualToLimitRouteCondition(C, 4), 3, 7);
	}
	
	private static final void testShortestRoute(Town from, Town to, int expected, int testNumber) {
		try {
			final Graph graph = new Graph(GRAPH_INPUT);
			final int actual = graph.getShortestRoute(from, to);
			GraphTest.assertAndPrint(expected, actual, testNumber);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public final void testShortestRouteFromAToC() {
		GraphTest.testShortestRoute(A, C, 9, 8);
	}
	
	@Test
	public final void testShortestRouteFromBToB() {
		GraphTest.testShortestRoute(B, B, 9, 9);
	}
	
	@Test
	public final void testNumberOfTripsFromCToCWithDistanceLessThanThirty() {
		GraphTest.testNumberOfTrips(C, C, new LessThanLimitRouteCondition(C, 30), 7, 10);
	}
}
