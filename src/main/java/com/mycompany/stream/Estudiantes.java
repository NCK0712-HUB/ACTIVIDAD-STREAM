/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.stream;

/**
 *
 * @author NicolasAndresPovedaO
 */
public class Estudiantes {

    String nombre;
    String programa;
    double nota;

    public Estudiantes(String nombre, String programa, double nota) {
        this.nombre = nombre;
        this.programa = programa;
        this.nota = nota;

    }

    public String getNombre() {
        return nombre;
    }

    public String getPrograma() {
        return programa;
    }

    public double getNota() {
        return nota;
    }

}
