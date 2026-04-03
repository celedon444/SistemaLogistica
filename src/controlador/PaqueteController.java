package controlador;

import dao.PaqueteDAO;
import modelo.Paquete;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.Timestamp; // <--- NUEVO: Para manejar el tiempo exacto

public class PaqueteController {

    private PaqueteDAO dao;

    public PaqueteController() {
        this.dao = new PaqueteDAO();
    }

    /**
     * Método para el Inventario (Actualizado para mostrar fecha de sistema)
     */
    public DefaultTableModel consultarInventario() {
        String[] titulos = {"Guía", "Remitente", "Destinatario", "Dirección", "Peso (kg)", "Tipo", "Estado", "Fecha Registro"};

        DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Paquete> lista = dao.listarTodos();

        Object[] fila = new Object[8];
        for (Paquete p : lista) {
            fila[0] = p.getGuia();
            fila[1] = p.getRemitente();
            fila[2] = p.getDestinatario();
            fila[3] = p.getDireccion();
            fila[4] = p.getPeso();
            fila[5] = p.getTipo();
            // Aquí podrías usar calcularEstadoPorTiempo(p.getFechaSistema()) si quieres que el inventario también sea automático
            fila[6] = p.getEstado();
            fila[7] = p.getFechaSistema(); // <--- CAMBIO: Ahora usa el Timestamp
            modeloTabla.addRow(fila);
        }

        return modeloTabla;
    }

    /**
     * Método para el Registro: Ahora ya NO pide la fecha, MySQL la pone sola.
     */
    public boolean guardarPaquete(String guia, String rem, String dest, String dir, String pesoStr, String tipo, String est) {

        if (guia.isEmpty() || rem.isEmpty() || dest.isEmpty() || pesoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Faltan datos obligatorios.");
            return false;
        }

        try {
            double peso = Double.parseDouble(pesoStr.trim().replace(",", "."));
            // El constructor de Paquete ahora no necesita la fecha manual
            Paquete nuevoPaquete = new Paquete();
            nuevoPaquete.setGuia(guia);
            nuevoPaquete.setRemitente(rem);
            nuevoPaquete.setDestinatario(dest);
            nuevoPaquete.setDireccion(dir);
            nuevoPaquete.setPeso(peso);
            nuevoPaquete.setTipo(tipo);
            nuevoPaquete.setEstado(est);

            return dao.registrar(nuevoPaquete);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El peso ingresado no es un número válido.");
            return false;
        }
    }

    /**
     * MÉTODO MÁGICO: Este es el que hace los cálculos para la vista de usuario.
     */
    public String calcularEstadoPorTiempo(Timestamp fechaSistema) {
        if (fechaSistema == null) {
            return "EN BODEGA";
        }

        long ahora = System.currentTimeMillis();
        long registro = fechaSistema.getTime();
        long diferenciaMillis = ahora - registro;

        // CAMBIO: Ahora dividimos solo por 1000 para obtener SEGUNDOS
        long segundosPasados = diferenciaMillis / 1000;

        // Umbrales rápidos para demostración (ajústalos a tu gusto)
        if (segundosPasados < 10) {
            return "EN BODEGA"; // Primeros 10 segundos
        }
        if (segundosPasados < 20) {
            return "EN DESPACHO"; // De los 10 a los 20 segundos
        }
        if (segundosPasados < 30) {
            return "EN RUTA"; // De los 20 a los 30 segundos
        }

        return "ENTREGADO"; // Después de 30 segundos
    }

    // Agrega esto dentro de tu clase PaqueteController
    public Paquete buscarPaquetePorGuia(String guia) {
        return dao.buscarPorGuia(guia);
    }
}
