package com.stringcalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {
	
	StringCalculator obj;
	
	@BeforeEach
	void init() {
		obj = new StringCalculator();
	}

	 @Test
	    public void testAddEmptyString() {
	        assertEquals("0", obj.add(""));
	    }

	    @Test
	    public void testAddOneNumber() {
	        assertEquals("1", obj.add("1"));
	    }

	    @Test
	    public void testAddTwoNumbers() {
	        assertEquals("3.3", obj.add("1.1,2.2"));
	    }

	    @Test
	    public void testAddMultipleNumbers() {
	        assertEquals("6", obj.add("1,2,3"));
	    }

	    @Test
	    public void testAddWithNewline() {
	        assertEquals("6", obj.add("1\n2,3"));
	    }

	    @Test
	    public void testAddWithCustomDelimiter() {
	        assertEquals("3", obj.add("//;\n1;2"));
	    }

	    @Test
	    public void testAddWithCustomDelimiterMultiple() {
	        assertEquals("6", obj.add("//|\n1|2|3"));
	    }

	    @Test
	    public void testAddWithNegativeNumber() {
	        assertEquals("Negative not allowed : -1", obj.add("-1,2"));
	    }

	    @Test
	    public void testAddWithMultipleNegativeNumbers() {
	        assertEquals("Negative not allowed : -4, -5", obj.add("2,-4,-5"));
	    }
}
