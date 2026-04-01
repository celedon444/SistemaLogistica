package main;

import conexion.ConexionBD;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando prueba de conexión ---");
        
        // Llamamos al método conectar de tu clase ConexionBD
        Connection c = ConexionBD.conectar();
        
        if (c != null) {
            System.out.println("¡PRUEBA SUPERADA! Tu sistema ya habla con la nube.");
        } else {
            System.out.println("Algo falló. Revisa que la contraseña sea la correcta.");
        }
    }
}