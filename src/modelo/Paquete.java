package modelo;

import java.sql.Timestamp; // <--- IMPORTANTE: Esta librería permite manejar tiempo exacto

/**
 * Representa la entidad Paquete con soporte para cronómetro de tiempo real.
 */
public class Paquete {
    private String guia;
    private String remitente;
    private String destinatario;
    private String direccion;
    private double peso;
    private String tipo;
    private String estado;
    
    // Cambiamos 'String fecha' por 'Timestamp fechaSistema' para poder hacer cálculos
    private Timestamp fechaSistema; 

    // 1. Constructor vacío
    public Paquete() {
    }

    // 2. Constructor con parámetros (Actualizado con Timestamp)
    public Paquete(String guia, String remitente, String destinatario, String direccion, double peso, String tipo, String estado, Timestamp fechaSistema) {
        this.guia = guia;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.peso = peso;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaSistema = fechaSistema;
    }

    // 3. Métodos Getter y Setter
    public String getGuia() { return guia; }
    public void setGuia(String guia) { this.guia = guia; }

    public String getRemitente() { return remitente; }
    public void setRemitente(String remitente) { this.remitente = remitente; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // NUEVO GETTER Y SETTER PARA EL TIEMPO
    public Timestamp getFechaSistema() { return fechaSistema; }
    public void setFechaSistema(Timestamp fechaSistema) { this.fechaSistema = fechaSistema; }

    @Override
    public String toString() {
        return "Paquete{" + "guia=" + guia + ", estado=" + estado + ", fecha=" + fechaSistema + '}';
    }
}