package com.mycompany.evidenciamaven;

import com.google.gson.Gson;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class cita 
{
    private int id;
    private String nombre, fecha, hora, motivo;
    private medico medico;
    private paciente paciente;
    
    private static List <cita> citas = new ArrayList<>();;
    
    private static String jsonfile = "citas.json";

    public cita(int id, String nombre, String fecha, String hora, String motivo, medico medico, paciente paciente) 
    {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.medico = medico;
        this.paciente = paciente;
    }
    
    public cita() {}
    
    public int getId() {
        
        return id;
    }
    public String getNombre() {
        
        return nombre;
    }
    public String getFecha() {
        
        return fecha;
    }
    public String getMotivo() {
        
        return motivo;
    }
    public String getHora() {
        
        return hora;
    }
    public medico getMedico() {
        
        return medico;
    }
    public paciente getPaciente() {
        
        return paciente;
    }
    public static List<cita> getCitas() {
        
        return citas;
    }
    
    public void setNombre(String nombre) {
        
        this.nombre = nombre;
    }
    public void setFecha(String fecha) {
        
        this.fecha = fecha;
    }
    public void setId(int id) {
        
        this.id = id;
    }
    public void setHora(String hora) {
        
        this.hora = hora;
    }
    public void setMotivo(String motivo) {
        
        this.motivo = motivo;
    }
    public void setMedico(medico medico) {
        
        this.medico = medico;
    }
    public void setPaciente(paciente paciente) {
        
        this.paciente = paciente;
    }
    
    
    public static void setCitas(List<cita> citas) {
        
        cita.citas = citas;
    }

    
    public void agregaDatosIniciales()
    {
        try
        {
            File file = new File(jsonfile);
            medico medico = new medico("Internista",1,"Raúl","Aguilar",45,'M',"admin","raula@hospital.com");
            if(file.canExecute() == false)
            {citas.add(semilla);}
            
            else
            {
                BufferedReader lector = new BufferedReader(new FileReader(file));
                StringBuilder json = new StringBuilder();

                String cadena;

                while ((cadena = lector.readLine()) != null)
                {
                    //Se guarda la línea
                    json.append(cadena);
                    Gson gson = new Gson();
                    cita cita = gson.fromJson(json.toString(), cita.class);
                    citas.add(cita);
                }
                
                if(citas.isEmpty())
                {citas.add(semilla);}
            }

            System.out.println("Registro.");
        }
        catch(Exception e)
        {System.out.println("Error: " + e.getMessage());}
    }
    
    public void creaCita()
    {
        try
        {
            int id;
            String nombre,fecha, hora, motivo;

            id = citas.size() + 1;
            nombre = JOptionPane.showInputDialog("Nombre de la cita:");
            motivo = JOptionPane.showInputDialog("Motivo de la cita:");
            fecha = LocalDate.now().toString();
            hora = LocalTime.now().toString();
            medico medico = buscaMedico();
            if (medico == null)
            {throw new Exception("No existe ningún médico con tal ID");}
            paciente paciente = buscaPaciente();
            if (paciente == null)
            {throw new Exception("No existe ningún paciente con tal ID");}
            
            cita cita = new cita(id, nombre, fecha, hora, motivo, medico, paciente);
            
            citas.add(cita);
            
            System.out.println("Update.");
        }
        catch(Exception e)
        {System.out.println("Error: " + e.getMessage());}
    }
    
    public static paciente buscaPaciente()
    {
        paciente paciente = null;
            
        try
        {
            int id_paciente = Integer.parseInt(JOptionPane.showInputDialog("ID del paciente:"));
            paciente metodos_paciente = new paciente();
            boolean existe_paciente = metodos_paciente.getPacientes().stream().anyMatch(x -> x.getId() == id_paciente);
            if(existe_paciente == true)
            {
                System.out.println("Paciente encontrado en la lista de pacientes.");
                paciente = metodos_paciente.getPacientes().get(id_paciente);
            }
            else
            {System.out.println("Paciente no encontrado en la lista de pacientes.");}
        }
        catch (Exception e)
        {System.out.println("No se pudo referenciar al paciente por el error: " + e.getMessage());}

        return paciente;
    }
    
    public void guardaCita()
    {
        String jsonCita;
        
        try
        {
            Gson gson = new Gson();
                        
            FileWriter fileWriter = new FileWriter(jsonfile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            for (int x = 0; x < citas.size(); x++)
            {
                jsonCita = gson.toJson(citas.get(x));
                printWriter.print(jsonCita);
            }
            
            printWriter.close();
            
            System.out.println("Las citas han sido guardadas correctamente.");
        }
        catch (Exception e)
        {System.out.println("No se pudieron guardar las citas en el archivo JSON por el error: " + e.getMessage());}
    }
    
    public void cargarJSON()
    {
        try
        {
            File file = new File(jsonfile);
        
            BufferedReader lector = new BufferedReader(new FileReader(file));
            StringBuilder json = new StringBuilder();

            String cadena;

            System.out.println("Las citas encontrados en el archivo " + jsonfile + " son: ");
            System.out.println("");
            
            while ((cadena = lector.readLine()) != null)
            {
                json.append(cadena);
                Gson gson = new Gson();
                cita cita = gson.fromJson(json.toString(), cita.class);
                cita.despliega();
                System.out.println("");
            }
        }
        catch (Exception e)
        {System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());}
    }
    
    public static void consultaCitas()
    {
        try
        {
            System.out.println("Se han registrado las siguientes citas: ");
            for (cita x : citas)
            {
                x.despliega();
                System.out.println("");
            }
            System.out.println("Se han terminado de mostrar todas las citas registradas.");
        }
        catch (Exception e)
        {System.out.println("No se pudieron desplegar las citas por el error: " + e.getMessage());}
    }
    
    public static void buscaCita(int id, String usuario)
    {
        try
        {
            System.out.println("Se encontraron las siguientes citas para el " + usuario.toLowerCase() + " #" + id);
            switch(usuario)
            {                
                case "Paciente":
                {
                    List <cita> citasPaciente = citas.stream().filter(paciente -> paciente.getId() == id).collect(Collectors.toList());
                    for (cita x : citasPaciente)
                    {
                        x.despliega();
                        System.out.println("");
                    }
                    break;
                }
                
                case "Médico":
                {
                    List <cita> citasMedico = citas.stream().filter(medico -> medico.getId() == id).collect(Collectors.toList());
                    for (cita x : citasMedico)
                    {
                        x.despliega();
                        System.out.println("");                        
                    }
                    break;
                }
            }
            
            System.out.println("Se han terminado de mostrar las citas del " + usuario.toLowerCase() + 
                    " #" + id + ".");
        }
        catch (Exception e)
        {System.out.println("No se pudieron encontrar las citas por el error: " + e.getMessage());}
    }
}
