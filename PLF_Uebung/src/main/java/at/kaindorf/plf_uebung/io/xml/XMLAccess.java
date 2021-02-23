/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.plf_uebung.io.xml;

import at.kaindorf.plf_uebung.beans.Market;
import at.kaindorf.plf_uebung.beans.Product;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXB;

/**
 *
 * @author 10jon
 */
public class XMLAccess {
    public static List<Product> getProducts(String path){      
        return JAXB.unmarshal(new File(path), Market.class).getProducts();
    }
}
