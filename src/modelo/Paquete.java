/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Paquete {
    private String guia;
    private String remitente;
    private String destinatario;
    private String direccion;
    private double peso;
    private String tipo;
    private String estado;
    private String fecha;
    
    public Paquete(String guia, String remitente, String destinatario, String direccion, double peso, String tipo, String estado, String fecha) {
        this.guia = guia;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.peso = peso;
        this.tipo = tipo;
        this.estado = estado;
        this.fecha = fecha;
    }

    public String getGuia() {
        return guia;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPeso() {
        return peso;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha;
    }
}



