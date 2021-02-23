/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.plf_uebung.io.json;

import at.kaindorf.plf_uebung.beans.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 10jon
 */
public class JSONAccess {
    public static List<Product> getProducts(String path) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(path), Product[].class));
    }
}
