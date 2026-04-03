package modelo;

/**
 * Representa la entidad Paquete en el sistema.
 * Aplicamos POO: Encapsulamiento y Abstracción.
 */
public class Paquete {
    // Atributos privados (Encapsulamiento)
    private String guia;
    private String remitente;
    private String destinatario;
    private String direccion;
    private double peso;
    private String tipo;
    private String estado;
    private String fecha;

    // 1. Constructor vacío (Necesario para frameworks y flexibilidad)
    public Paquete() {
    }

    // 2. Constructor con parámetros (Para crear objetos rápidamente)
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

    // 3. Métodos Getter y Setter (Acceso controlado a los datos)
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

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    // 4. Método toString (Útil para depuración/Logs)
    @Override
    public String toString() {
        return "Paquete{" + "guia=" + guia + ", destinatario=" + destinatario + ", estado=" + estado + '}';
    }
}