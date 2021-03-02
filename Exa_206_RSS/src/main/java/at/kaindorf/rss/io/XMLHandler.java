/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.rss.io;

import at.kaindorf.rss.pojos.Channel;
import at.kaindorf.rss.pojos.RSS;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;

/**
 *
 * @author 10jon
 */
public class XMLHandler {
    public static Channel getChannel(String url) throws MalformedURLException{
        return JAXB.unmarshal(new URL(url), RSS.class).getChannel();
    }
    public static void main(String[] args) {
        try {
            System.out.println(XMLHandler.getChannel("https://www.derstandard.at/rss"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
