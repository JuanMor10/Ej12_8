package com.jmorcas;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Juan Mora
 * @version: 09/04/2022
 */
public class Auxiliar {
    /**
     * Metodo que devuelve un arraylist de las personas
     * @param path localizacion del archivo
     * @return  lista de personas del csv
     */
    public static List<Persona> readPersonasCSV(Path path){
        List<Persona> listaPersonas=new ArrayList<>();
        try (BufferedReader bufferedReader=new BufferedReader(new FileReader(String.valueOf(path)))){
            while (bufferedReader.ready()){
                String[] personaPartida=bufferedReader.readLine().split(",");
                if(!personaPartida[0].isEmpty())
                listaPersonas.add(new Persona(personaPartida[0],personaPartida[1],
                        personaPartida[2],personaPartida[4],personaPartida[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaPersonas;
    }

    /**
     * Metodo que añade a las personas de una lista que recibe por parametro
     * @param personaList personas que recibe
     */
    public static void writeCSVPersonas(List<Persona> personaList){
        try (BufferedWriter bf=new BufferedWriter(new FileWriter("personal_fecha_hora.csv"))){
            for (Persona persona:personaList){
                bf.write(persona.toString());
                bf.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que devuelve un array de los archivos de un XML
     * @param file ruta del arhivo
     * @return array de las personas del xml
     */
    public static ArrayList<Persona> listaPersonasXML(Path file){
        ArrayList<Persona> listaxml=new ArrayList<>();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db=dbf.newDocumentBuilder();
            Document doc=db.parse(new File(String.valueOf(file)));
            Element raiz=doc.getDocumentElement();
            NodeList list=raiz.getElementsByTagName("persona");
            for (int i=0;i<list.getLength();i++) {
                String[] nodos=list.item(i).getTextContent().split("\n");
                listaxml.add(new Persona(nodos[2],nodos[4],nodos[1],nodos[3],nodos[5]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaxml;
    }

    /**
     * Metodo statico que lee un archivo JSON y devuelve un array de esto
     * Para esta parte importamos la libreria json-simple-1.1
     * @param file ruta del archivo
     * @return array de los datos del archivo json
     */
    public static ArrayList<Persona> listaPersonasJSON(Path file){
        JSONParser jsonParser=new JSONParser();
        ArrayList<Persona> persona=new ArrayList<>();
        try {
            FileReader archivo=new FileReader(String.valueOf(file));
            JSONArray jsonArray= (JSONArray) jsonParser.parse(archivo);
            //  System.out.println(jsonArray.get(0));
            ;
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject=(JSONObject) jsonArray.get(i);
                persona.add(new Persona((String) jsonObject.get("firstName"), (String) jsonObject.get("lastName"),
                        (String)  jsonObject.get("email"),(String) jsonObject.get("country"),(String) jsonObject.get("gender")));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return persona;
    }

}
