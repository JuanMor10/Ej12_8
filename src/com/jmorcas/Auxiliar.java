package com.jmorcas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Juan Mora
 * @version : 09/04/2022
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
     * metodo para formar un arraylist de las personas de un JSON
     * @param path ruta
     * @return lista
     * @throws IOException excepcion
     */
    public static ArrayList<Persona> leerPersonasJson(Path path) throws IOException {
        Reader reader = Files.newBufferedReader(path);
        Type tipoLista=new TypeToken<List<Persona>>() {}.getType();
        ArrayList <Persona> personasJSON=new Gson().fromJson(reader,tipoLista);
        reader.close();
        return personasJSON;
    }

    public static void escribirCSV(ArrayList<Persona> lista){

            try (BufferedWriter bf=new BufferedWriter(new FileWriter("escribocsv.csv"))){
                for (Persona per:lista) {
                    bf.write(per.toString());
                    bf.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void escribirXML(ArrayList<Persona> lista) throws ParserConfigurationException, TransformerException {
        org.jdom2.Element personas = new org.jdom2.Element("personas");
        org.jdom2.Document doc = new org.jdom2.Document(personas);


        for (Persona per:lista){
            org.jdom2.Element persona = new org.jdom2.Element("persona");
            personas.addContent(persona);

            org.jdom2.Element firstName = new org.jdom2.Element("firstName");
            firstName.setText(per.getFirstName());
            org.jdom2.Element lastName = new org.jdom2.Element("lastName");
            lastName.setText(per.getLastName());
            org.jdom2.Element email = new org.jdom2.Element("email");
            email.setText(per.getEmail());
            org.jdom2.Element country = new org.jdom2.Element("country");
            country.setText(per.getCountry());
            org.jdom2.Element gender = new org.jdom2.Element("gender");
            gender.setText(per.getGender());

            persona.addContent(firstName);
            persona.addContent(lastName);
            persona.addContent(email);
            persona.addContent(country);
            persona.addContent(gender);

        }
        XMLOutputter xml=new XMLOutputter();
        xml.setFormat(Format.getPrettyFormat());
        try {
            xml.output(doc,new FileWriter("persona.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
