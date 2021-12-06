package com.mycompany.evidenciamaven;

import javax.swing.*;

public class Main 
{
    public static void main (String[] args) throws Exception
    {
        
        try
        {
            int id_usuario;
            String contraseña;            
            int intentos = 3;
            boolean credenciales = false;
            String [] usuarios = {"Persona", "Médico", "Paciente", "Salir"};
            String tipoUsuario;
            paciente paciente = new paciente();
            medico medico = new medico();
            persona persona = new persona();
            cita cita = new cita();
            
            System.out.println("");

            persona.agregaDatosIniciales();
            paciente.agregaDatosIniciales();
            medico.agregaDatosIniciales();
            cita.agregaDatosIniciales();
            
            System.out.println("");
            
            do
            {
                id_usuario = Integer.parseInt(JOptionPane.showInputDialog("Escriba su Usuario:"));
                contraseña = JOptionPane.showInputDialog("Escribe tu contraseña:");                
                int salir = 0;
                
                tipoUsuario = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                        + "tu tipo de usuario", "Ingreso", JOptionPane.DEFAULT_OPTION, 
                        null, usuarios, usuarios[0]);
                
                System.out.println("Eres: " + tipoUsuario);

                switch(tipoUsuario)
                {
                    case "Persona": 
                    {credenciales = persona.ingresar(id_usuario, contraseña);}
                    
                    case "Médico": 
                    {credenciales = medico.ingresar(id_usuario, contraseña);}
                    
                    case "Paciente": 
                    {credenciales = paciente.ingresar(id_usuario, contraseña);}
                }

                if (credenciales == false)
                {
                    intentos -= 1;
                     JOptionPane.showMessageDialog(null, "Intentar de nuevo", 
                             "Registro inexistente", JOptionPane.ERROR_MESSAGE);    
                }
            }
            while(intentos != 0 && credenciales != true); 
            
            if (intentos == 0)
            {throw new Exception("Runout");}
            
            menu(id_usuario, tipoUsuario, persona, medico, paciente, cita);
            
            System.out.println("");
                        
            System.out.println("\n");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage() + "," + ""
            , "Fin", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void menu(int id, String tipoUsuario, persona persona, medico medico, paciente paciente, cita cita)
    {       
  
        try
        {
      
            int salir = 0;

            while(salir == 0)
            {
                switch(tipoUsuario)
                {
                    case "Persona": 
                    {
                        String [] opciones = {"Crear nuevo usuario", "Cargar usuarios JSON", "Visualizar usuarios creados",
                        "Enviar un mensaje", "Crear nuevo paciente", "Cargar pacientes JSON", "Visualizar pacientes creados",
                        "Crear nuevo médico", "Cargar médicos JSON", "Visualizar médicos creados", "Crear nueva cita",
                        "Cargar citas JSON", "Visualizar citas creadas","Salir"};
                        String opcionElegida;
                        
                        opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                                + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION, 
                                null, opciones, opciones[0]);

                        System.out.println("Has elegido: " + opcionElegida);
                        
                        switch(opcionElegida)
                        {
                            case "Crear nuevo usuario": 
                            {
                                persona.creaPersona();
                                System.out.println("");
                                break;
                            }
                            
                            case "Cargar usuarios": 
                            {
                                
                                System.out.println("");
                                 persona.cargarJSON();
                                break;
                            }      
                            case "Cargar pacientes JSON": 
                            {
                                paciente.cargarJSON();
                                System.out.println("");
                                break;
                            }
                            
                            case "Visualizar pacientes creados":
                            {
                                paciente.consultaUsuarios();
                                System.out.println("");
                                break;
                            }
                            
                            case "Crear nuevo médico": 
                            {
                                medico.creaPersona();
                                System.out.println("");
                                break;
                            }
                            
                            case "Cargar médicos JSON": 
                            {
                                medico.cargarJSON();
                                System.out.println("");
                                break;
                            }
                            
                            case "Visualizar médicos creados": 
                            {
                                medico.consultaUsuarios();
                                System.out.println("");
                                break;
                            }
                            
                            case "Crear nueva cita": 
                            {
                                cita.creaCita();
                                System.out.println("");
                                break;
                            }
                            
                            case "Cargar citas JSON": 
                            {
                                cita.cargarJSON();
                                System.out.println("");
                                break;
                            }
                            
                            case "Visualizar citas creadas": 
                            {
                                cita.consultaCitas();
                                System.out.println("");
                                break;
                            }
                        
                            
                            case "Salir": 
                            {
                                salir = 1;
                                break;
                            }
                        }
                    }
                    
                    case "Médico": 
                    {
                        String [] opciones = {"Enviar un mensaje", "Crear nuevo paciente", "Cargar pacientes", "Visualizar pacientes creados",
                        "Visualizar pacientes asignados", "Consultar paciente", "Crear nueva cita", "Cargar citas",
                        "Visualizar citas creadas", "Visualizar citas asignadas", "Salir"};
                        String opcionElegida;
                        
                        opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                                + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION, 
                                null, opciones, opciones[0]);
                        
                        switch(opcionElegida)
                        {
                            case "Enviar un mensaje": 
                            {
                                persona.enviaMensaje();
                                System.out.println("");
                                break;
                            }
                            
                            case "Crear nuevo paciente": 
                            {
                                paciente.creaPersona();
                                System.out.println("");
                                break;
                            }
                            
                            case "Cargar pacientes JSON": 
                            {
                                paciente.cargarJSON();
                                System.out.println("");
                                break;
                            }
                            
                            case "Visualizar pacientes creados":
                            {
                                paciente.consultaUsuarios();
                                System.out.println("");
                                break;
                            }

                            case "Cargar citas JSON": 
                            {
                                cita.cargarJSON();
                                System.out.println("");
                                break;
                            }
                            
                            case "Visualizar citas creadas":
                            {
                                cita.consultaCitas();
                                System.out.println("");
                                break;
                            }
                            
                            case "Visualizar citas asignadas": 
                            {
                                cita.buscaCita(id,tipoUsuario);
                                System.out.println("");
                                break;
                            }
                 
                            case "Salir": 
                            {
                                salir = 1;
                                break;
                            }
                        }
                    }
                    
                    case "Paciente": 
                    {
                        String [] opciones = {"Enviar un mensaje", "Asistir a cita", "Salir"};
                        String opcionElegida;
                        
                        opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                                + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION, 
                                null, opciones, opciones[0]);
                        
                        switch(opcionElegida)
                        {
                            case "Enviar un mensaje": 
                            {
                                persona.enviaMensaje();
                                System.out.println("");
                                break;
                            }
                            
                            case "Asistir a cita": 
                            {
                                paciente.asistirCita(id);
                                System.out.println("");
                                break;
                            }
       
                            case "Salir": 
                            {
                                salir = 1;
                                break;
                            }
                        }
                    }
                }
            }

            
        }
        catch(Exception e)
        {e.getMessage();}
    }
}
