/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Usuario {
    private String nombre;
    private String password;
    private String rol; 

    public Usuario(String nombre, String password, String rol) {
        this.nombre = nombre;
        this.rol = rol;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }
}
