package com.mycompany.evidenciamaven;

/**
 *
 * @author Pachanga Mama
 */
import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class medico extends persona {

    private String especialidad;

   
    private static List<medico> medicos = new ArrayList<>();


    private static String ARCHIVO = "medicos.json";

    private static int id = 1;
    
    
    public medico(String especialidad, int id, String nombre,
            String apellido, int edad, char genero,
            String contraseña, String email) {
        
        
        super(id, nombre, apellido, edad, genero, contraseña, email);
        this.especialidad = especialidad;
    }

  
    public medico() {
    }


    public String getEspecialidad() {
        
        return especialidad;
    }
    public static List<medico> getMedicos() {
        
        return medicos;
    }
    
    public void setEspecialidad(String especialidad) {
        
        this.especialidad = especialidad;
    }
    public static void setMedicos(List<medico> medicos) {
        medico.medicos = medicos;
    }


    public void agregaDatosIniciales() {

        try {
          
            File file = new File(ARCHIVO);
          
            medico semilla = new medico("General", 1, "Raúl", "Aguilar", 45, 'M', "admin", "raulag@hotmail.com");

        
            if (file.canExecute() == false) {
                medicos.add(semilla);
            } 
            else {
             
                BufferedReader lector = new BufferedReader(new FileReader(file));
               
                StringBuilder json = new StringBuilder();

                String cadena;

            
                while ((cadena = lector.readLine()) != null) {
                    json.append(cadena);
                    
                    Gson gson = new Gson();
                    medico medico = gson.fromJson(json.toString(), medico.class);
                    medicos.add(medico);
                }

                if (medicos.isEmpty()) {
                    medicos.add(semilla);
                }
            }

            System.out.println("Los médicos iniciales han sido guardados.");
        } 
        catch (Exception e) {
            System.out.println("No se pudieron guardar los médicos semilla correctamente por el error: " + e.getMessage());
        }
    }
    
    public void guardaPersona() {
        String jsonMedico;


        try {
            Gson gson = new Gson();

            FileWriter fileWriter = new FileWriter(ARCHIVO);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int x = 0; x < medicos.size(); x++) {
                jsonMedico = gson.toJson(medicos.get(x));
                printWriter.print(jsonMedico);
            }

            printWriter.close();

            System.out.println("Los médicos han sido guardados correctamente.");
        } 
        catch (Exception e) {
            System.out.println("No se pudieron guardar los médicos en el archivo JSON por el error: " + e.getMessage());
        }
    }
    public boolean ingresar(int id, String contraseña) throws Exception {
        try {
            boolean existe = medicos.stream().anyMatch(x
                    -> x.getId() == id && x.getContraseña().equals(contraseña));
            return existe;
        } 
        catch (Exception e) {
            throw new Exception("No se pudo validar al médico.");
        }
    }

    public void cargarJSON() {
      
        try {
            File file = new File(ARCHIVO);

            BufferedReader lector = new BufferedReader(new FileReader(file));
            StringBuilder json = new StringBuilder();

            String cadena;

            System.out.println("Los médicos encontrados en el archivo " + ARCHIVO + " son: ");
            System.out.println("");

            while ((cadena = lector.readLine()) != null) {
                json.append(cadena);
                Gson gson = new Gson();
                medico medico = gson.fromJson(json.toString(), medico.class);
                medico.despliega();
                System.out.println("");
            }
        }
        catch (Exception e) {
            System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());
        }
    }

    public void despliega() {
        try {
            System.out.println("ID del médico: 1" );
            System.out.println("Nombre del médico: Raúl Aguilar");
            System.out.println("Edad: 45" );
            System.out.println("Género del médico: M" );
            System.out.println("Correo del médico: raulag@hospital.com" ) ;
            System.out.println("Contraseña del médico: admin" );
            System.out.println("Especialidad del médico: General");
        }
        catch (Exception e) {
            System.out.println("No se pudo mostrar el médico por el error: " + e.getMessage());
        }
    }

    public static void buscaPacientes(int id) {
   
        try {
            System.out.println("Se encontraron los siguientes pacientes del médico #" + id);
            List<paciente> pacientesMedico = paciente.getPacientes().stream().filter(medico -> medico.getId() == id).collect(Collectors.toList());
            for (paciente x : pacientesMedico) {
                x.despliega();
                System.out.println("");
            }

            System.out.println("Se han terminado de mostrar los pacientes del médico #" + id + ".");
        } 
        catch (Exception e) {
            System.out.println("No se pudieron filtrar los pacientes por el error: " + e.getMessage());
        }
    }

    
}
