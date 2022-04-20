package com.jmorcas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Juan Mora
 * @version : 10/04/2022
 */
public class TestPersonaXML {
    /**
     *
     * @hidden
     */
    public static void main(String[] args) {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        try {
            db=dbf.newDocumentBuilder();
            Document doc=db.parse(new File("personal.xml"));
            Element raiz=doc.getDocumentElement();
            NodeList list=raiz.getElementsByTagName("persona");
           // imprime(list);
            ArrayList<Persona> persona=new ArrayList<>();
            for (int i=0;i<list.getLength();i++){
               // System.out.println(list.item(i).getTextContent());
                String[] nodoPartido=list.item(i).getTextContent().split("\n");
                persona.add(new Persona(nodoPartido[4],nodoPartido[3],nodoPartido[2],nodoPartido[1],nodoPartido[5]));
            }
            for (Persona person:persona){
                System.out.println(person);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }
    /*
   public static void imprime(NodeList list){
        for (int i=0;i<list.getLength();i++){
            System.out.println(list.item(i).getNodeName());
            System.out.println(list.item(i).getTextContent());
          // if (list.item(i).hasChildNodes()){
          //     imprime(list.item(i).getChildNodes());
          // }
        }
    }*/
}
