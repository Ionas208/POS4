/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.ini_app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author 10jon
 */
@Data
@AllArgsConstructor
public class Window {
    
    private Integer xPos;
    private Integer yPos;
    
    private int width;
    private int height;
    
    public Window(Element element){
        xPos = Integer.parseInt(element.getElementsByTagName("xpos").item(0).getTextContent());
        yPos = Integer.parseInt(element.getElementsByTagName("ypos").item(0).getTextContent());
        
        width = Integer.parseInt(element.getElementsByTagName("width").item(0).getTextContent());
        height = Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent());
    }
    
    public void appendChildren(Element windowElement, Document doc){
        Element xPosElement = doc.createElement("xpos");
        xPosElement.setTextContent(this.xPos+"");
        windowElement.appendChild(xPosElement);
        
        Element yPosElement = doc.createElement("ypos");
        yPosElement.setTextContent(this.yPos+"");
        windowElement.appendChild(yPosElement);
        
        Element widthElement = doc.createElement("width");
        widthElement.setTextContent(this.width+"");
        windowElement.appendChild(widthElement);
        
        Element heightElement = doc.createElement("height");
        heightElement.setTextContent(this.height+"");
        windowElement.appendChild(heightElement);
    }
    
}
