package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class MyPointTest {
	private MyPoint point1;
	private MyPoint pointVert;
 	private MyPoint point2;
	private MyPoint point2bis;
	private MyPoint point2ter;
	private MyPoint point2Neg;
	private MyPoint point3;
	private MyPoint pointNull;
	private MyPoint pointNaN;
	private MyPoint point4;

	@BeforeEach
	void setUp() throws Exception {
		point1 = new MyPoint();
		pointVert = new MyPoint(-1d,0d);
		point2 = new MyPoint(2d,2d);
		point2bis = new MyPoint(-2d,2d);
		point2ter = new MyPoint(2d,-2d);
		point2Neg= new MyPoint(-2d,-2d);
		point3 = new MyPoint(point2);
		pointNull = new MyPoint(null);
		pointNaN = new MyPoint(Math.sqrt(-1),Math.sqrt(-1));
		point4 = new MyPoint(pointNaN);
	}

	@AfterEach
	void tearDown() throws Exception {
		point1 = null;
		pointVert = null;
		point2 = null;
		point2bis = null;
		point2ter = null;
		point2Neg = null;
		point3 = null;
		pointNull = null;
		pointNaN = null;
		point4 = null;
	}

//===================================================================
// Test Constructeur
//===================================================================

	@Test
	void testMyPointDoubleDouble() {
		assertEquals (point2.getX(), 2);
		assertEquals (point2.getY(), 2);
		assertEquals (pointNull.getX(), 0d);
		assertEquals (pointNull.getY(), 0d);
	}
	
	@Test
	void testMyPointDoubleDoubleNaN() {
		assertEquals (pointNaN.getX(), point1.getX());
		assertEquals (pointNaN.getY(), point1.getY());
	}

	@Test
	void testMyPointMyPoint() {
		assertEquals (point2.getX(), point3.getX());
		assertEquals (point2.getY(), point3.getY());
	}
	
	@Test
	void testMyPointMyPointNaN() {
		assertEquals (point4.getX(), point1.getX());
		assertEquals (point4.getY(), point1.getY());
	}

//===================================================================
// Test Set x
//===================================================================

	@Test
	void testGetSetX() {
		point2.setX(3);
		assertEquals (point2.getX(), 3);
	}
	
	@Test
	void testGetSetXDoubleNaN() {
		point3.setX(Math.sqrt(-1));
		assertEquals (point3.getX(), 2);
	}

//===================================================================
// Test Set y
//===================================================================
	
	@Test
	void testGetSetY() {
		point2.setY(3);
		assertEquals (point2.getY(), 3);
	}
		
	@Test
	void testGetSetYDoubleNaN() {
		point3.setY(Math.sqrt(-1));
		assertEquals (point3.getY(), 2);
	}

//===================================================================
// Test Scale
//===================================================================
	
	
	@Test
	void testScale() {
		point2 = point2.scale(2);
		assertEquals (point2.getX(), 4);
		assertEquals (point2.getY(), 4);
	}
	
	@Test
	void testScaleNegatif() {
		point3 = point2.scale(-1);
		assertEquals (point3.getX(), -(point2.getX()));
		assertEquals (point3.getY(), -(point2.getY()));
	}
	
	@Test
	void testScaleZero() {
		point2 = point2.scale(0);
		assertEquals (point2.getX(), point1.getX());
		assertEquals (point2.getY(), point1.getY());
	}
	
	@Test
	void testScaleNaN() {
		point3 = point2.scale(Math.sqrt(-1));
		assertEquals (point3.getX(), point2.getX());
		assertEquals (point3.getY(), point2.getY());
	}

//===================================================================
// Test Symmetry Horizontal
//===================================================================
	
	@Test
	void testHorizontalSymmetry() {
		assertEquals (point2.horizontalSymmetry(point1).getX(), point2.getX());
		assertEquals (point2.horizontalSymmetry(point1).getY(), -(point2.getY()));
	}
	
	@Test
	void testHorizontalSymmetryNeg() {
		assertEquals (point2Neg.horizontalSymmetry(point1).getX(), point2Neg.getX());
		assertEquals (point2Neg.horizontalSymmetry(point1).getY(), -(point2Neg.getY()));
	}
	
	@Test
	void testHorizontalSymmetrySameAxe() {
		assertEquals (point2.horizontalSymmetry(point2bis).getX(), point2.getX());
		assertEquals (point2.horizontalSymmetry(point2bis).getY(), point2.getY());
	}
	
	@Test
	void testHorizontalSymmetryError() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    point2.horizontalSymmetry(null);
		  });
	}
	
	
//===================================================================
// Test Central Horizontal
//===================================================================

	@Test
	void testCentralSymmetry() {
		assertEquals(point2.centralSymmetry(point1).getX(), point2Neg.getX(), 0.0001);
		assertEquals(point2.centralSymmetry(point1).getY(), point2Neg.getY(), 0.0001);
	}
	
	@Test
	void testCentralSymmetryHimself() {
		assertEquals(point2.centralSymmetry(point2).getX(), point2.getX());
		assertEquals(point2.centralSymmetry(point2).getY(), point2.getY());
	}

	@Test
	void testCentralSymmetryNULL() {
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    point2.centralSymmetry(null);
		  });
	}

//===================================================================
// Test Compute Angle
//===================================================================
	
	@Test
	void testComputeAngle180() {
		assertEquals(Math.toDegrees(point1.computeAngle(pointVert)),180);
	}

	@Test
	void testComputeAngleDroit() {
		pointVert.setX(0);
		pointVert.setY(1);
		assertEquals(Math.toDegrees(point1.computeAngle(pointVert)),90);
	}
	
	@Test
	void testComputeAngleDroitN() {
		pointVert.setX(0);
		pointVert.setY(-1);
		assertEquals(Math.toDegrees(point1.computeAngle(pointVert)),270);
	}
	
	@Test
	void testComputeAngleZero() {
		pointVert.setX(1);
		assertEquals(Math.toDegrees(point1.computeAngle(pointVert)),0);
	}
	
//===================================================================
// Test GetMiddlePoint
//===================================================================
	
	@Test
	void testGetMiddlePoint() {
		assertEquals(point2.getMiddlePoint(point2Neg).getX(), point1.getX());
		assertEquals(point2.getMiddlePoint(point2Neg).getY(), point1.getY());
	}
	
	@Test
	void testGetMiddlePointNULL() {
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    point2.getMiddlePoint(null);
		  });
	}


//===================================================================
// Test Translate 
//===================================================================

	@Test
	void testTranslateDoubleDouble() {
		point1.translate(2d, 2d);
		assertEquals(point1.getX(), point2.getX());
		assertEquals(point1.getY(), point2.getY());
	}
	
	@Test
	void testTranslateNaNDouble() {
		point1.translate(Math.sqrt(-1), 2d);
		assertEquals(point1.getX(), point1.getX());
		assertEquals(point1.getY(), point1.getY());
	}
	
	@Test
	void testTranslateDoubleNaN() {
		point1.translate(1d, Math.sqrt(-1));
		assertEquals(point1.getX(), point1.getX());
		assertEquals(point1.getY(), point1.getY());
	}
	
	@Test
	void testTranslateNaNNaN() {
		point1.translate(Math.sqrt(-1), Math.sqrt(-2));
		assertEquals(point1.getX(), point1.getX());
		assertEquals(point1.getY(), point1.getY());
	}
	
//===================================================================
// Test Rotate Point
//===================================================================
	
	@Test
	void testRotatePoint() {
		assertEquals(point2.rotatePoint(point1, Math.PI/2).getX(), point2bis.getX(), 0.001);
		assertEquals(point2.rotatePoint(point1, Math.PI/2).getY(), point2bis.getY(), 0.001);
	}
	
	@Test
	void testRotatePointPi() {
		assertEquals(point2.rotatePoint(point1, Math.PI).getX(), point2Neg.getX(), 0.001);
		assertEquals(point2.rotatePoint(point1, Math.PI).getY(), point2Neg.getY(), 0.001);
	}
	
	@Test
	void testRotatePointTer() {
		assertEquals(point2.rotatePoint(point1, 3*Math.PI/2).getX(), point2ter.getX(), 0.001);
		assertEquals(point2.rotatePoint(point1, 3*Math.PI/2).getY(), point2ter.getY(), 0.001);
	}
	
	@Test
	void testRotatePoint2Pi() {
		assertEquals(point2.rotatePoint(point1, 2*Math.PI).getX(), point2.getX(), 0.001);
		assertEquals(point2.rotatePoint(point1, 2*Math.PI).getY(), point2.getY(), 0.001);
	}
	
	@Test
	void testRotatePointHimself() {
		assertEquals(point2.rotatePoint(point2, Math.PI).getX(), point2.getX(), 0.001);
		assertEquals(point2.rotatePoint(point2, Math.PI).getY(), point2.getY(), 0.001);
	}
	
	@Test
	void testRotatePoint3Pi() {
		assertEquals(point2.rotatePoint(point1, -1*Math.PI/2).getX(), point2ter.getX(), 0.001);
		assertEquals(point2.rotatePoint(point1, -1*Math.PI/2).getY(), point2ter.getY(), 0.001);
	}
	
	@Test
	void testRotatePointNull() {
		assertEquals(point2.rotatePoint(null, Math.PI/2), null);
	}
	
//===================================================================
// Test SetPoint
//===================================================================

	@Test
	public void testSetPoint() {
		Random randomNumberMock = Mockito.mock(Random.class);
    	when(randomNumberMock.nextInt()).thenReturn(5);
    	Random randomNumberMock2 = Mockito.mock(Random.class);
    	when(randomNumberMock2.nextInt()).thenReturn(6);
    	point2.setPoint(randomNumberMock, randomNumberMock2);
    	assertEquals(point2.getX(),5,0.00001);
		assertEquals(point2.getY(),6,0.00001);
	}

	@Test
	public void testSetPointNeg() {
		Random randomNumberMock = Mockito.mock(Random.class);
    	when(randomNumberMock.nextInt()).thenReturn(5);
    	Random randomNumberMock2 = Mockito.mock(Random.class);
    	when(randomNumberMock2.nextInt()).thenReturn(6);
    	point2Neg.setPoint(randomNumberMock, randomNumberMock2);
    	assertEquals(point2Neg.getX(),5,0.00001);
		assertEquals(point2Neg.getY(),6,0.00001);
	}

	@Test
	public void testSetPointTL() {
		Random randomNumberMock = Mockito.mock(Random.class);
    	when(randomNumberMock.nextInt()).thenReturn(5);
    	Random randomNumberMock2 = Mockito.mock(Random.class);
    	when(randomNumberMock2.nextInt()).thenReturn(6);
    	point1.setPoint(randomNumberMock, randomNumberMock2);
    	assertEquals(point1.getX(),5,0.00001);
		assertEquals(point1.getY(),6,0.00001);
	}

	
//===================================================================
// Test Translate
//===================================================================
	
	
	@Test
	public void testTranslateITranslation() {
		ITranslation mockService = mock(ITranslation.class);
		when(mockService.getTx()).thenReturn(3);
		when(mockService.getTy()).thenReturn(3);
		point2.translate(mockService);
		assertEquals(point2.getX(),5,0.00001);
		assertEquals(point2.getY(),5,0.00001);
	}

	@Test
	public void testTranslateITranslationNull() {
		point2.translate(null);
		assertEquals(point2.getX(),2,0.00001);
		assertEquals(point2.getY(),2,0.00001);
	}

}
