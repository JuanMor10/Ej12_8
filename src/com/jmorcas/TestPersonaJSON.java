package com.jmorcas;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author : Juan Mora
 * @version : 10/04/2022
 */
public class TestPersonaJSON {

    public static void main(String[] args) {
        ArrayList<Persona> persona=new ArrayList<>();
      // JSONParser jsonParser=new JSONParser();
      //
      // try {
      //     FileReader archivo=new FileReader("personal.json");
      //     JSONArray jsonArray= (JSONArray) jsonParser.parse(archivo);
      //     //  System.out.println(jsonArray.get(0));
      //     ;
      //  /*   for (int i=0;i<jsonArray.size();i++){
      //         JSONObject jsonObject=(JSONObject) jsonArray.get(i);
      //         persona.add(new Persona((String) jsonObject.get("firstName"), (String) jsonObject.get("lastName"),
      //                 (String)  jsonObject.get("email"),(String) jsonObject.get("country"),(String) jsonObject.get("gender")));
      //     }*/
      // } catch (IOException | ParseException e) {
      //     e.printStackTrace();
      // }
      // addPersonasJSon(persona);
      // for (Persona person:persona){
      //     System.out.printf(" %s \n",person.toString());
      // }
        try {

            for (Persona pers:Auxiliar.leerPersonasJson(Paths.get("personal.json"))){
                System.out.println(pers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para añadir personas desde un fichero JSON
     * @param persona lista a la que añadiremos las personas
     */
    private static void addPersonasJSon(ArrayList<Persona> persona) {
        JSONParser jsonParser=new JSONParser();
        //ArrayList<Persona> persona=new ArrayList<>();
        try {
            FileReader archivo=new FileReader("personal.json");
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
        //return persona;
    }
}
