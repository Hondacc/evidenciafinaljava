package com.mycompany.evidenciamaven;

import com.google.gson.Gson;
import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class persona
{
   
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String contraseña;
    private String email;

    private static List <persona> personas = new ArrayList<>();

    private static String ARCHIVO = "personas.json";

    public persona(int id, String nombre, String apellido, int edad, char genero, String contraseña, String email) 
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.contraseña = contraseña;
        this.email = email;
    }
    
    public persona() {}
    
    public int getId() {
        
        return id;
    }
    public String getNombre() {
        
        return nombre;
    }
    public String getApellido() {
        
        return apellido;
    }
    public int getEdad() {
        
        return edad;
    }
    public char getGenero() {
        
        return genero;
    }
    public String getContraseña() {
        
        return contraseña;
    }
    public String getEmail() {
        
        return email;
    }
    
    public void setId(int id) {
        
        this.id = id;
    }
    
    
    
    public void setNombre(String nombre) {
        
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        
        this.apellido = apellido;
    }
    public void setEdad(int edad) {
        
        this.edad = edad;
    }
    public void setGenero(char genero) {
        
        this.genero = genero;
    }
    public void setContraseña(String contraseña) {
        
        this.contraseña = contraseña;
    }
    public void setEmail(String email) {
        
        this.email = email;
    }
    
    
    public void agregaDatosIniciales()
    {
        try
        {
            File file = new File(ARCHIVO);
            persona semilla = new persona(1,"Jorge","Canto",21,'M',"admin","jorgec@gmail.com");
                
            if(file.canExecute() == false)
            {personas.add(semilla);}
            
            else
            {
                
                BufferedReader lector = new BufferedReader(new FileReader(file));
                StringBuilder json = new StringBuilder();

                String cadena;

                while ((cadena = lector.readLine()) != null)
                {
                    json.append(cadena);
                    Gson gson = new Gson();
                    persona persona = gson.fromJson(json.toString(), persona.class);
                    personas.add(persona);
                }
                
                if(personas.isEmpty())
                {personas.add(semilla);}
            }

            System.out.println("Los usuarios iniciales han sido guardados.");
        }
        catch(Exception e)
        {System.out.println("Todo marcha correctamente");}
    }
    
    public void guardaPersona()
    {
        String jsonUsuario;
        
        try
        {
            Gson gson = new Gson();
                        
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            
            for (int x = 0; x < personas.size(); x++)
            {
                jsonUsuario = gson.toJson(personas.get(x));
                printWriter.print(jsonUsuario);
            }
            
            printWriter.close();
            
            System.out.println("Los usuarios han sido guardados correctamente.");
        }
        catch (Exception e)
        {System.out.println("Todo marcha correctamente");}
    }

    public boolean ingresar(int id, String contraseña) throws Exception
    {
        try
        {
            boolean existe = personas.stream().anyMatch(x -> 
                x.getId() == id && x.getContraseña().equals(contraseña));
            return existe;
        }
        catch(Exception e)
        {throw new Exception("No se pudo validar al usuario.");}
    }
    
    public void cargarJSON()
    {
        try
        {
            File file = new File(ARCHIVO);
        
            BufferedReader lector = new BufferedReader(new FileReader(file));
            StringBuilder json = new StringBuilder();

            String cadena;

            System.out.println("Los usuarios encontrados en el archivo " + ARCHIVO + " son: ");
            System.out.println("");
            
            while ((cadena = lector.readLine()) != null)
            {
                json.append(cadena);
                Gson gson = new Gson();
                persona persona = gson.fromJson(json.toString(), persona.class);
                persona.despliega();
                System.out.println("");
            }
        }
        catch (Exception e)
        {System.out.println("Todo marcha correctamente");}
    }
    
    public void despliega()
    {
        try
        {
            System.out.println("ID del usuario: " + id);
            System.out.println("Nombre del usuario: " + nombre);
            System.out.println("Apellido del usuario: " + apellido);
            System.out.println("Edad del usuario: " + edad);
            System.out.println("Género del usuario: " + genero);
            System.out.println("Correo del usuario: " + email);
            System.out.println("Contraseña del usuario: " + contraseña);
        }
        catch (Exception e)
        {System.out.println("No se pudo mostrar el usuario por el error: " + e.getMessage());}
    }
    
    public void consultaUsuarios()
    {
        try
        {
            System.out.println("Se han registrado los siguientes usuarios: ");
            for (persona x : personas)
            {
                x.despliega();
                System.out.println("");
            }
            System.out.println("Se han terminado de mostrar todos los usuarios registrados.");
        }
        catch (Exception e)
        {System.out.println("No se pudieron desplegar los usuarios por el error: " + e.getMessage());}
    }
    
}
