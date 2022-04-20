package com.jmorcas;

import java.util.Objects;

/**
 * @author: Juan Mora
 * @version: 09/04/2022
 */
public class Persona {
    /**
     * Atributos
     */
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String gender;

    /**
     * Constructor
     * @param firstName nombre
     * @param lastName apellido
     * @param email correo
     * @param country pais
     * @param gender genero
     */
    public Persona(String firstName, String lastName, String email, String country, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    /**
     * getter apellido
     * @return apellido
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter apellido 2
     * @return apellidod
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter de correo
     * @return correo
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter pais
     * @return pais
     */
    public String getCountry() {
        return country;
    }

    /**
     * getter de genero
     * @return genero
     */
    public String getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(firstName, persona.firstName) && Objects.equals(lastName, persona.lastName) && Objects.equals(email, persona.email) && Objects.equals(country, persona.country) && Objects.equals(gender, persona.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, country, gender);
    }

    /**
     * Metodo toString para hacerlo como un csv
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",getFirstName(), getLastName(), getEmail(), getCountry(),getGender());
    }
}
