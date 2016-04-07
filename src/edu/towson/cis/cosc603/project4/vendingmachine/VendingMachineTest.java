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
public class VendingMachineTest {
	VendingMachine myVendingMachine;
	VendingMachineItem snickers;
	VendingMachineItem cookie;
	VendingMachineItem voidItem;
	String invalidCode;
	VendingMachineException vme;
	
	@Before
	public void setup() throws Exception{
		
	invalidCode = "ABCD";
	myVendingMachine = new VendingMachine();
	snickers = new VendingMachineItem("snickers", 1.25);
	cookie = new VendingMachineItem("Cookie", 1.55);
	voidItem = new VendingMachineItem("voidItem", 0.00);
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	/* 
	 * In the code below, an item is added to the Vending Machine and is checked if it is present or not.
	 * The test case is executed successfully.
	*/
	public void testAddItem_Case1() {
		myVendingMachine.addItem(snickers, VendingMachine.A_CODE);  
		assertEquals(snickers, myVendingMachine.getItem(VendingMachine.A_CODE)); 
	}

	@Test
	/* 
	 * In the code below, an item is added in one slot and test case is checks to see if an item is present in the Vending Machine in another slot.
	 * The test case failed.
	*/
	public void testAddItem_Case2() {
		myVendingMachine.addItem(snickers, VendingMachine.A_CODE);
		assertEquals(snickers,myVendingMachine.getItem(VendingMachine.A_CODE));
	}
	
	@Test
	/*
	 * In the code below, two items are added to the same slot. An exception is handled showing the
	 *  VendingMachineException: slot is already occupied.
	 */
	(expected = VendingMachineException.class)
	public void testAddItem_Case3() throws Exception{
		myVendingMachine.addItem(snickers, VendingMachine.A_CODE);  
		assertEquals(snickers, myVendingMachine.getItem(VendingMachine.A_CODE));
		myVendingMachine.addItem(cookie, VendingMachine.B_CODE); 
		assertEquals(cookie, myVendingMachine.getItem(VendingMachine.B_CODE));
	}	
	
	@Test
	/*
	 * In the code below, a voidItem and an invalid code is given and tested.
	 * An exception is handled by throwing the VendingMachineException: Invalid code for vending machine item.
	*/
	(expected = VendingMachineException.class)
	public void testAddItem_Case4() throws Exception {
		myVendingMachine.addItem(voidItem, VendingMachine.A_CODE);  
		assertEquals(voidItem, myVendingMachine.getItem(VendingMachine.A_CODE));
	}
	

	@Test (expected = VendingMachineException.class)
	/*
	 * In the code below, an item that is added and removed form an empty slot.
	 * The test case executes properly.
	 */
	public void testRemoveItem_Case1() throws Exception{
		myVendingMachine.addItem(snickers, VendingMachine.A_CODE);  
		myVendingMachine.removeItem(VendingMachine.A_CODE);
		assertEquals(snickers, myVendingMachine.removeItem(VendingMachine.A_CODE));
	}
		
	@Test (expected = VendingMachineException.class)
	/*
	 * In the code below, an item is removed from an empty slot.
	 * The test case throws an exception.
	 */
	public void testRemoveItem_Case2() throws Exception {
		String temp= ""; 
		myVendingMachine.removeItem(VendingMachine.A_CODE);
		assertEquals(true, myVendingMachine.removeItem(temp));
	}

	@Test (expected = VendingMachineException.class)
	/*
	 * In the code below, an item is removed for an invalid code, the VendingMachineException thrown is: Invalid code for vending machine item.
	 */
	public void testRemoveItem_Case3() throws Exception {
		myVendingMachine.removeItem(invalidCode);
		assertEquals(true, myVendingMachine.removeItem(invalidCode));
	}
	
	@Test
	/*
	 * In the code below, a value > 0 (in this case 20) is passed and the test case is executed successfully.
	 */
	public void testInsertMoney_Case1() {
		myVendingMachine.insertMoney(20);
		assertEquals(20, myVendingMachine.getBalance(),0.01);
	}
	
	@Test
	/*
	 * In the code below, a value = 0 is passed and the test case is executed successfully.
	 */
	public void testInsertMoney_Case2() {
		myVendingMachine.insertMoney(0);
		assertEquals(0, myVendingMachine.getBalance(),0.01);
	}
	
	@Test(expected = VendingMachineException.class)
	/*
	 * In the code below, a value < 0 (in this case -20) is given and the exception is handled.
	 */
	public void testInsertMoney_Case3() throws Exception{
		
		myVendingMachine.insertMoney(-20.0);
		assertEquals(-20, myVendingMachine.getBalance(),0.01);
	}
	
	@Test 
	/*
	 * In this method, the getBalance method is called and the balance is displayed, No parameters are passed
	 * The test executes successfully.
	 */
	public void testGetBalance()  {
		myVendingMachine.insertMoney(5);
		myVendingMachine.getBalance();
		assertEquals(5 ,myVendingMachine.getBalance(),0.1);
	}
	
	@Test 
	/*
	 * In this method, an item is added to the slot A of vending machine and a purchase is made and verified. 
	 * The test case is executed successfully.
	 */
	public void testMakePurchase_Case1()  {
		myVendingMachine.insertMoney(5);
		myVendingMachine.addItem(snickers, VendingMachine.A_CODE);  
		myVendingMachine.makePurchase(VendingMachine.A_CODE);
		// balance is 5.00-1.25 = 3.75. Purchase is made, and balance is 3.75
		assertEquals(3.75,myVendingMachine.getBalance(),.01);		
	}
	
	@Test (expected = VendingMachineException.class)
	/*
	 * In this method, an item is removed from the slot, showing that it is an empty slot and a purchase is made. 
	 * The test case throws an exception for making purchase from an empty slot.
	 */
	public void testMakePurchase_Case2() throws Exception {
		myVendingMachine.removeItem(VendingMachine.A_CODE);
		myVendingMachine.makePurchase(VendingMachine.A_CODE);
		assertEquals(false, myVendingMachine.makePurchase(VendingMachine.A_CODE));
	}
	
	@Test (expected = VendingMachineException.class)
	/*
	 * In this method, a purchase is made for an invalid code that is not present in the Vending Machine.
	 * An exception is thrown: Invalid code for vending machine and is handled accordingly.
	 */
	public void testMakePurchase_Case3() throws Exception {
		myVendingMachine.makePurchase(invalidCode);
		assertEquals(false, myVendingMachine.makePurchase(invalidCode));
	}
	
	@Test 
	public void testReturnChange() {
		/*
		 * In this method, the balance is set to 0 and the change is returned. The test case execute successfully.
		 */
		myVendingMachine.insertMoney(5);
		myVendingMachine.addItem(snickers, VendingMachine.A_CODE);  
		myVendingMachine.makePurchase(VendingMachine.A_CODE);
		assertEquals(3.75, myVendingMachine.returnChange(),0.01);
	}

	
	
	}
