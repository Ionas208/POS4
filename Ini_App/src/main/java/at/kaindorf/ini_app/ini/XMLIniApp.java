/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.ini_app.ini;

import at.kaindorf.ini_app.pojos.Window;
import java.awt.Dimension;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 10jon
 */
public class XMLIniApp {

    private Document doc;
    private Map<String, Window> windows;
    private Path path;

    private static XMLIniApp instance;

    public static int DEFAULT_WIDTH = 400;
    public static int DEFAULT_HEIGTH = 300;

    public static XMLIniApp getXMLIniApp() throws ParserConfigurationException, SAXException, SAXException, IOException {
        if (XMLIniApp.instance == null) {
            XMLIniApp.instance = new XMLIniApp();
        }
        return XMLIniApp.instance;
    }

    private XMLIniApp() throws ParserConfigurationException, SAXException, IOException {
        //Parse doc
        path = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "at", "kaindorf", "ini_app", "res", "config.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        try {
            doc = builder.parse(path.toFile());
        } catch (FileNotFoundException ex) {
            doc = builder.newDocument();
            doc.appendChild(doc.createElement("windows"));
        }

        //Read windows
        NodeList nodes = doc.getDocumentElement().getElementsByTagName("window");
        windows = new HashMap<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Window w = new Window((Element) node);
                windows.put(((Element) node).getAttribute("name"), w);
            }
        }

        clearDoc();
    }

    public void initWindow(JFrame frame) {
        //Get or create window
        Window w = windows.get(frame.getTitle());
        if (w == null) {
            w = new Window(null, null, DEFAULT_WIDTH, DEFAULT_HEIGTH);
            windows.put(frame.getTitle(), w);
        }

        //Set attributes to frame
        frame.setSize(w.getWidth(), w.getHeight());
        if (w.getXPos() == null || w.getYPos() == null) {
            frame.setLocationRelativeTo(null);
        } else {
            frame.setLocation(w.getXPos(), w.getYPos());
        }
    }

    public void updateWindow(JFrame frame) {
        Window w = windows.get(frame.getTitle());

        //Update size
        Dimension d = frame.getSize();
        w.setWidth((int) d.getWidth());
        w.setHeight((int) d.getHeight());

        //Update locaiton
        Point p = frame.getLocation();
        w.setXPos((int) p.getX());
        w.setYPos((int) p.getY());
    }

    public void saveWindows() throws TransformerConfigurationException, TransformerException, FileNotFoundException {
        //Write window list to document
        for (Entry<String, Window> e : windows.entrySet()) {
            String name = e.getKey();
            Window w = e.getValue();
            Element windowElement = doc.createElement("window");
            windowElement.setAttribute("name", name);
            w.appendChildren(windowElement, doc);
            doc.getDocumentElement().appendChild(windowElement);
        }

        //Create Tranformer
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //Clear file
        PrintWriter writer = new PrintWriter(path.toFile());
        writer.print("");
        writer.close();

        //Write to file
        Result result = new StreamResult(path.toFile());
        transformer.transform(new DOMSource(doc), result);
    }

    private void clearDoc() {
        while (doc.getDocumentElement().hasChildNodes()) {
            doc.getDocumentElement().removeChild(doc.getDocumentElement().getFirstChild());
        }
    }
}
