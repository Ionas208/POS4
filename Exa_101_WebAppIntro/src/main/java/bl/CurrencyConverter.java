/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

/**
 *
 * @author 10jon
 */
public class CurrencyConverter {
    
    private static float[] CHANGE = {1.0f, 1.19f};
    private static String[] CHANGE_NAMES = {"Euro", "Dollar"};
    
    public static float convertFromEuroToIdx(float euroValue, int currencyIdx){
        return euroValue * CHANGE[currencyIdx];
    }
    
    public static String getCurrencyNameFromIdx(int currencyIdx){
        return CHANGE_NAMES[currencyIdx];
    }
}
