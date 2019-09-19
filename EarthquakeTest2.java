package com.lynden.gmapsfx;
//SUP

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EarthquakeTest2 {
	
	private final Earthquake.DepthSort depthSort = new Earthquake.DepthSort();
	private final Earthquake.StatusSort statusSort = new Earthquake.StatusSort();
	private final Earthquake.DateSort dateSort = new Earthquake.DateSort();
	private final Earthquake.PlaceSort placeSort = new Earthquake.PlaceSort();
	private final Earthquake.MagSort magSort = new Earthquake.MagSort();
	
	
	@Test
	public void setInfoTest() throws Exception{
		Earthquake quake = new Earthquake();
		quake.setTime("2019-03-04 12:20:02");
		assertEquals("Dates should match", "March 04 2019 12:20:02", quake.getTime());
	}
	
	@Test
	public void setLatitudeTest() {
		Earthquake quake = new Earthquake();
		quake.setLatitude(180.03);
		assertEquals(180.03, quake.getLatitude(), 0);
	}
	
	@Test
	public void setLongitudeTest() {
		Earthquake quake = new Earthquake();
		quake.setLongitude(102.03);
		assertEquals(102.03, quake.getLongitude(), 0);
	}
	
	@Test
	public void setDepthTest() {
		Earthquake quake = new Earthquake();
		quake.setDepth(5.02);
		assertEquals(5.02, quake.getDepth(), 0);
	}
	
	@Test
	public void setMagTest() {
		Earthquake quake = new Earthquake();
		quake.setMag(3.02);
		assertEquals(3.02, quake.getMag(), 0);
	}
	
	@Test
	public void setMagTypeTest() {
		Earthquake quake = new Earthquake();
		quake.setMagType("md");
		assertEquals("md", quake.getMagType());
	}
	
	@Test
	public void setNstTest() {
		Earthquake quake = new Earthquake();
		quake.setNst(5);
		assertEquals(5, quake.getNst());
	}
	
	@Test
	public void setGapTest() {
		Earthquake quake = new Earthquake();
		quake.setGap(75);
		assertEquals(75, quake.getGap(), 0);
	}
	
	@Test
	public void setDminTest() {
		Earthquake quake = new Earthquake();
		quake.setDmin(0.02589);
		assertEquals(0.02589, quake.getDmin(), 0);
	}
	
	@Test
	public void setRmsTest() {
		Earthquake quake = new Earthquake();
		quake.setRms(0.04);
		assertEquals(0.04, quake.getRms(), 0);
	}
	
	@Test
	public void setNetTest() {
		Earthquake quake = new Earthquake();
		quake.setNet("nc");
		assertEquals("nc", quake.getNet());
	}
	
	@Test
	public void setIdTest() {
		Earthquake quake = new Earthquake();
		quake.setId("nc73138410");
		assertEquals("nc73138410", quake.getID());
	}

	@Test
	public void setUpdatedTest() throws Exception{
		Earthquake quake = new Earthquake();
		quake.setUpdated("2019-03-06 12:20:03");
		assertEquals("March 06 2019 12:20:03", quake.getUpdated());
	}
	
	@Test
	public void setPlaceTest() {
		Earthquake quake = new Earthquake();
		quake.setPlace("4km E of Mammoth Lakes, CA");
		assertEquals("4km E of Mammoth Lakes, CA", quake.getPlace());
	}
	
	@Test
	public void setTypeTest() {
		Earthquake quake = new Earthquake();
		quake.setPlace("earthquake");
		assertEquals("earthquake", quake.getType());
	}
	
	@Test
	public void setHorizontalErrorTest() {
		Earthquake quake = new Earthquake();
		quake.setHorizontalError(.34);
		assertEquals(.34, quake.getHorizontalError(), 0);
	}
	
	@Test
	public void setDepthErrorTest() {
		Earthquake quake = new Earthquake();
		quake.setDepthError(.55);
		assertEquals(.55, quake.getDepthError(), 0);
	}
	
	@Test
	public void setMagErrorTest() {
		Earthquake quake = new Earthquake();
		quake.setMagError(0.422);
		assertEquals(0.422, quake.getMagError(), 0);
	}
	
	@Test
	public void setMagNstTest() {
		Earthquake quake = new Earthquake();
		quake.setMagNst(15);
		assertEquals(15, quake.getMagNst());
	}
	
	@Test
	public void setStatusTest() {
		Earthquake quake = new Earthquake();
		quake.setStatus("reviewed");
		assertEquals("reviewed", quake.getStatus());
	}
	
	@Test
	public void setLocationSourceTest() {
		Earthquake quake = new Earthquake();
		quake.setLocationSource("nc");
		assertEquals("nc", quake.getLocationSource());
	}
	
	@Test
	public void setMagSourceTest() {
		Earthquake quake = new Earthquake();
		quake.setMagSource("nc");
		assertEquals("nc", quake.getMagSource());
	}
	
	@Test
	public void depthSortLessTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setDepth(5);
		e2.setDepth(10);
		
		int result = depthSort.compare(e1, e2);
		assertTrue("Expected to be true", result <= -1);
	}
	
	@Test
	public void depthSortGreaterTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setDepth(5);
		e2.setDepth(10);
		
		int result = depthSort.compare(e2, e1);
		assertTrue("Expected to be true", result >= 1);
	}
	
	@Test
	public void depthSortEqualTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setDepth(10);
		e2.setDepth(10);
		
		int result = depthSort.compare(e2, e1);
		assertTrue("Expected to be true", result == 0);
	}
	
	@Test
	public void magSortLessTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setMag(5);
		e2.setMag(10);
		
		int result = magSort.compare(e1, e2);
		assertTrue("Expected to be true", result <= -1);
	}
	
	@Test
	public void magSortGreaterTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setMag(5);
		e2.setMag(10);
		
		int result = magSort.compare(e2, e1);
		assertTrue("Expected to be true", result >= 1);
	}
	
	@Test
	public void magSortEqualTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setMag(10);
		e2.setMag(10);
		
		int result = magSort.compare(e2, e1);
		assertTrue("Expected to be true", result == 0);
	}
	
	@Test
	public void placeSortLessTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setPlace("11, CA");
		e2.setPlace("11, DA");
		
		int result = placeSort.compare(e1, e2);
		assertTrue("Expected to be true", result <= -1);
	}
	
	@Test
	public void placeSortGreaterTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setPlace("11, BA");
		e2.setPlace("11, CA");
		
		int result = placeSort.compare(e2, e1);
		assertTrue("Expected to be true", result >= 1);
	}
	
	@Test
	public void placeSortEqualTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setPlace("11, CA");
		e2.setPlace("11, CA");
		
		int result = placeSort.compare(e1, e2);
	
		assertTrue("Expected to be true", result == 0);
	}
	
	@Test
	public void statusSortLessTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setStatus("automatic");
		e2.setStatus("bautomatic");
		
		int result = statusSort.compare(e1, e2);
		assertTrue("Expected to be true", result <= -1);
	}
	
	@Test
	public void statusSortGreaterTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setStatus("bautomatic");
		e2.setStatus("cautomatic");
		
		int result = statusSort.compare(e2, e1);
		assertTrue("Expected to be true", result >= 1);
	}
	
	@Test
	public void statusSortEqualTest() {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setStatus("automatic");
		e2.setStatus("automatic");
		
		int result = statusSort.compare(e1, e2);
	
		assertTrue("Expected to be true", result == 0);
	}
	
	@Test
	public void dateSortLessTest() throws Exception {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setTime("2019-02-10 12:00:00");
		e2.setTime("2019-02-11 12:00:00");
		
		int result = dateSort.compare(e1, e2);
		assertTrue("Expected to be true", result <= -1);
	}
	
	@Test
	public void dateSortGreaterTest() throws Exception {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setTime("2019-02-11 12:00:00");
		e2.setTime("2019-02-12 12:00:00");
		
		int result = dateSort.compare(e2, e1);
		assertTrue("Expected to be true", result >= 1);
	}
	
	@Test
	public void dateSortEqualTest() throws Exception {
		Earthquake e1 = new Earthquake();
		Earthquake e2 = new Earthquake();
		
		e1.setTime("2019-02-11 12:00:00");
		e2.setTime("2019-02-11 12:00:00");
		
		int result = dateSort.compare(e1, e2);
	
		assertTrue("Expected to be true", result == 0);
	}
}