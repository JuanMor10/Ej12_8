package com.jmorcas;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: Juan Mora
 * @version: 23/04/2022
 */
public class PruebaJson {
    public static void main(String[] args) {
        String json="";
        try {
            BufferedReader br=new BufferedReader(new FileReader("personal.json"));
            String linea="";

            while ((linea= br.readLine())!=null) {
                json+=linea;
                Gson gson=new Gson();
                json=gson.toJson(json);

            }
        br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
