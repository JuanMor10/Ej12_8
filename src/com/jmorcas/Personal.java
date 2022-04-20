package com.jmorcas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author : Juan Mora
 * @version : 09/04/2022
 */
public class Personal{
    /**
     * atributo
     */
    private ArrayList<Persona> listaPersonas;
    private ArrayList<Persona> listaPersonasJSON;
    private ArrayList<Persona> listaPersonasxml;

    public ArrayList<Persona> getListaPersonasxml(Path path) {
        return Auxiliar.listaPersonasXML(path);
    }

    public ArrayList<Persona> getListaPersonasJSON() {
        return listaPersonasJSON;
    }

    /**
     * constructor
     */
    public Personal() {
        this.listaPersonas = new ArrayList<>();
    }

    /**
     * Metodo para añadir personas
     * @param persona persona a añadir
     * @throws Exception
     */
    public void addPersona(Persona persona) throws Exception {
            listaPersonas.add(persona);
    }

    /**
     * metodo para sacar las personas de un pais
     * @param pais pais a comparar
     * @return lista de personas de paises
     */
    public ArrayList<Persona> personasPais(String pais) {
        ArrayList<Persona> arrayPorPais = new ArrayList<>();
        for (Persona persona : getListaPersonas()) {
            if (persona.getCountry().toLowerCase().equals(pais.toLowerCase()))
                arrayPorPais.add(persona);
        }
        return arrayPorPais;
    }

    /**
     * getter de la lista de personas
     * @return array de las personas
     */
    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    /**
     * metodo para eliminar personas
     * @param correo correo que tenga la persona para eliminar
     */
    public void deletePersona(String correo){
        Iterator<Persona> iterator=getListaPersonas().iterator();
        ArrayList<Persona> auxiliar=new ArrayList<>();
        while (iterator.hasNext()){
            Persona p=iterator.next();
            if (p.getEmail().equals(correo)){
               auxiliar.add(p);
            }
        }
        listaPersonas.removeAll(auxiliar);
    }

    /**
     * Metodo para añadir personas desde el archivo JSON
     * @param file ruta del archivo
     */
    public void addPersonasJSON(Path file){
        listaPersonasJSON=Auxiliar.listaPersonasJSON(file);
    }

    /**
     * Metodo para añadir personas desde un archivo XML
     * @param file nombre del archivo
     */
    public void addPersonasXML(String file){
       /* DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db=dbf.newDocumentBuilder();
            Document doc=db.parse(new File(file));
            Element raiz=doc.getDocumentElement();
            NodeList list=raiz.getElementsByTagName("persona");
            for (int i=0;i<list.getLength();i++) {
                String[] nodos=list.item(i).getTextContent().split("\n");
                System.out.println(Arrays.toString(nodos));
                addPersona(new Persona(nodos[2],nodos[4],nodos[1],nodos[3],nodos[5]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
         this.listaPersonas=Auxiliar.listaPersonasXML(Path.of(file));
    }

    /**
     * metodo toString del personal
     * @return String de todas las personas
     */
    @Override
    public String toString() {
        StringBuilder personas= new StringBuilder();
            for (Persona persona:listaPersonas){
                personas.append(persona.toString()).append('\n');
            }
        return personas.toString();
    }
}
