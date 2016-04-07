/**
 * 
 */
package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Chin
 *
 */
public class VendingMachineItemTest {
	String invalidCode;
	VendingMachine myVendingMachine;
	VendingMachineItem snickers;
	VendingMachineItem cookie;
	VendingMachineItem voidItem;
	VendingMachineItem defaultVendingMachineItem;
	VendingMachineException machineException;
		
	@Before
	public void setup() throws Exception{
			
	invalidCode = "ABCD";
	myVendingMachine = new VendingMachine();
	snickers = new VendingMachineItem("snickers", 1.25);
	cookie = new VendingMachineItem("Cookie", 1.55);
	voidItem = new VendingMachineItem("voidItem", 0.00);
	defaultVendingMachineItem = new VendingMachineItem("defaultItem", 10.00);
	machineException = new VendingMachineException();
	
		}

	@Test
	/*
	 * Test custom constructor
	 */
	public void testConstructor(){
		assertNotNull(snickers);
	}
	
	@Test
	/*
	 * In this method, item1 and item2 are defined along with their price respectively. 
	 * The test case is executed successfully.
	 */
	public void testVendingMachineItem_Case1() {
		VendingMachineItem myVendingMachineItem1 = new VendingMachineItem("item1", 5.00);
		
		assertEquals("item1", myVendingMachineItem1.getName());
	}
	@Test
	public void testVendingMachineItem_Case2(){
		VendingMachineItem myVendingMachineItem2 = new VendingMachineItem("item2", 3.00);
		assertEquals("item2", myVendingMachineItem2.getName());
	}
	
	@Test (expected = VendingMachineException.class)
	/*
	 * In this method, when an item is added with a negative price, a VendingMachineException is thrown 
	 * saying that Price cannot be less than zero and  handled.
	 */ 
	public void testVendingMachineException(){
		VendingMachineItem VendingMachineException1 = new VendingMachineItem("snickers", -0.00);
		//assertEquals(machineException, VendingMachineException1.getName());
		}
	
		
	
	
	@Test
	/*
	 * In this method, the VendingMachineItem for a 
	 * default vending machine item name is retrieved and the test cas is executed successfully.
	 */
	public void testGetName() {	
		defaultVendingMachineItem.getName();
		assertEquals("defaultItem", defaultVendingMachineItem.getName());
	}
	
	@Test
	/*
	 * In this method, the actual price of the item is retrieved and the test case is executed successfully.
	 */
	public void testGetPrice() {
		assertEquals(10, defaultVendingMachineItem.getPrice(),0.01);
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
		
}
