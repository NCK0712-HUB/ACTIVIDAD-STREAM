/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author NicolasAndresPovedaO
 */
public class STREAM {

    public static void main(String[] args) {

        List<Estudiantes> estudiantes = new ArrayList<>();

        estudiantes.add(new Estudiantes("Nicolas", "Software", 5.0));
        estudiantes.add(new Estudiantes("Juan", "Software", 3.8));
        estudiantes.add(new Estudiantes("Carolina", "Cocina", 4.9));
        estudiantes.add(new Estudiantes("Luis", "Contaduria", 2.8));
        estudiantes.add(new Estudiantes("Ana", "Software", 4.2));
        estudiantes.add(new Estudiantes("Pedro", "Lenguas", 3.5));
        estudiantes.add(new Estudiantes("Maria", "Contaduria", 4.0));
        estudiantes.add(new Estudiantes("Carlos", "Software", 2.5));
        estudiantes.add(new Estudiantes("Laura", "Lenguas", 4.7));
        estudiantes.add(new Estudiantes("Sofia", "Contaduria", 3.9));

        Scanner pp = new Scanner(System.in);

        listaEstudiantes(estudiantes);
        int opcion = -1;
        
        do {

            try {

                System.out.println("\nMenu de actividad stream");
                System.out.println("1. mostrar estudiantes aprobados");
                System.out.println("2. mostrar ordenados por nota descendente y por nombre");
                System.out.println("3. obtener los tres mejores estudiantes");
                System.out.println("4. ver promedio general");
                System.out.println("5. ver agrupado por programa y promedio por grupo.");
                System.out.println("6 ver aprobados y reprobados");
                System.out.println("0. salir:)");
                System.out.println("\n");
                opcion = pp.nextInt();
                System.out.println("\n");

                switch (opcion) {
                    case 1:
                        aprobados(estudiantes);
                        break;
                    case 2:
                        notaDescendente(estudiantes);
                        break;
                    case 3:
                        tresMejores(estudiantes);
                        break;
                    case 4:
                        promedioGeneral(estudiantes);
                        break;
                    case 5:
                        promedioPorPrograma(estudiantes);
                        break;
                    case 6:
                        particionarAprobados(estudiantes);
                        break;
                    case 0:
                        System.out.println("gracias por utlizar el programa");
                        break;
                    default:
                        System.out.println("ingrese una opcion valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero");
                pp.nextLine();
            }
        } while (opcion != 0);

    }

    public static void listaEstudiantes(List<Estudiantes> estudiantes) {
        System.out.println("LISTA DE ESTUDIANTES");

        estudiantes.forEach(e
                -> System.out.println(
                        e.getNombre() + " "
                        + e.getPrograma() + " "
                        + e.getNota()));

    }

    public static void aprobados(List<Estudiantes> estudiantes) {

    List<Estudiantes> aprobados = estudiantes.stream()
            .filter(e -> e.getNota() >= 3.0)
            .toList();

    System.out.println("APROBARON: " + aprobados.size());

    aprobados.forEach(e ->
            System.out.println(
                    e.getNombre() + " - " +
                    e.getPrograma() + " - " +
                    e.getNota()));
}

    public static void notaDescendente(List<Estudiantes> estudiantes) {
        System.out.println("NOTA DESCENDENTE Y NOMBRE");
        estudiantes.stream()
                .sorted(Comparator.comparing(Estudiantes::getNota)
                        .reversed()
                        .thenComparing(Estudiantes::getNombre))
                .forEach(e -> System.out.println(
                        e.getNombre() + " - "
                        + e.getPrograma() + " - "
                        + e.getNota()));
    }

    public static void tresMejores(List<Estudiantes> estudiantes) {
        System.out.println("LOS 3 MEJORES ESTUDIANDTES FUERON");
        estudiantes.stream()
                .sorted(Comparator.comparing(Estudiantes::getNota)
                        .reversed()
                        .thenComparing(Estudiantes::getNombre))
                .limit(3)
                .forEach(e -> System.out.println(
                        e.getNombre() + " - "
                        + e.getPrograma() + " - "
                        + e.getNota()));
    }

    public static void promedioPorPrograma(List<Estudiantes> estudiantes) {
        System.out.println("PROMEDIO DE CADA PROGRAMA");

        Map<String, Double> promedios = estudiantes.stream()
                .collect(
                        Collectors.groupingBy(
                                Estudiantes::getPrograma,
                                Collectors.averagingDouble(Estudiantes::getNota)
                        )
                );

        promedios.forEach((programa, promedio)
                -> System.out.println(programa + " promedio de:  " + promedio)
        );
    }

    public static void particionarAprobados(List<Estudiantes> estudiantes) {

        Map<Boolean, List<Estudiantes>> particion = estudiantes.stream()
                .collect(Collectors.partitioningBy(
                        e -> e.getNota() >= 3.0
                ));

        System.out.println("APROBADOS");

        particion.get(true).forEach(e
                -> System.out.println(
                        e.getNombre() + " - "
                        + e.getPrograma() + " - "
                        + e.getNota()));

        System.out.println("\nREPROBADOS");

        particion.get(false).forEach(e
                -> System.out.println(
                        e.getNombre() + " - "
                        + e.getPrograma() + " - "
                        + e.getNota()));
    }

    public static void promedioGeneral(List<Estudiantes> estudiantes) {
        double promedio = estudiantes.stream()
                .mapToDouble(Estudiantes::getNota)
                .average()
                .orElse(0);

        System.out.printf("Promedio general: %.2f%n", promedio);
    }
}
