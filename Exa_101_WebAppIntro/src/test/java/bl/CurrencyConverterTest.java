/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 10jon
 */
public class CurrencyConverterTest {
    
    public CurrencyConverterTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of convertFromEuroToIdx method, of class CurrencyConverter.
     */
    @org.junit.jupiter.api.Test
    public void testConvertFromEuroToIdx() {
        System.out.println("convertFromEuroToIdx");
        float euroValue = 1.0F;
        int currencyIdx = 1;
        float expResult = 1.19F;
        float result = CurrencyConverter.convertFromEuroToIdx(euroValue, currencyIdx);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCurrencyNameFromIdx method, of class CurrencyConverter.
     */
    @org.junit.jupiter.api.Test
    public void testGetCurrencyNameFromIdx() {
        System.out.println("getCurrencyNameFromIdx");
        int currencyIdx = 0;
        String expResult = "Euro";
        String result = CurrencyConverter.getCurrencyNameFromIdx(currencyIdx);
        assertEquals(expResult, result);
    }
    
}
