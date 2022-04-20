package com.jmorcas;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TestPersonal {
    /**
     * @hidden
     */
    public static void main(String[] args) {
        Personal personal = new Personal();
        Scanner sc = new Scanner(System.in);
        System.out.println("Diga el nombre del fichero");
        Path p = Paths.get(sc.nextLine());
        try {
            for (Persona persona : Auxiliar.readPersonasCSV(p)) {
                personal.addPersona(persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String numero;
        do {
            System.out.println("0. Salir de la aplicación.");
            System.out.println("1. Solicitar un país y nos muestre los objetos Persona de dicho país de la la instancia de Personal");
            System.out.println("2. Borrar un objeto Persona de la instancia de Personal dado el campo email");
            numero = sc.nextLine();
            menu(personal, sc, numero);
        } while (Integer.parseInt(numero) != 0);

        Auxiliar.writeCSVPersonas(personal.getListaPersonas());
        for (Persona perso:personal.getListaPersonas())
        System.out.println(perso);

        personal.addPersonasXML("personal.xml");
        for (Persona perso:personal.getListaPersonasxml(Paths.get("personal.xml")))
        System.out.println(perso);

        personal.addPersonasJSON(Paths.get("personal.json"));
        for (Persona perso:personal.getListaPersonasJSON()){
            System.out.println(perso);
        }


    }

    /**
     * metodo statico que muestra el menú
     * @param personal persona
     * @param sc scaner del menú
     * @param numero numero para barajar las opciones
     */
    private static void menu(Personal personal, Scanner sc, String numero) {
        switch (Integer.parseInt(numero)) {
            case 1:
                System.out.println("Diga el pais");
                System.out.println(personal.personasPais(sc.nextLine()));
                break;
            case 2:
                System.out.println("Borre la persona con el siguiente mail: ");
                personal.deletePersona(sc.nextLine());
                break;
            case 0:
                break;
        }
    }
}
