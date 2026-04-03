package controlador;

import dao.PaqueteDAO;
import modelo.Paquete;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PaqueteController {

    // 1. SIEMPRE declara las variables (atributos) al principio
    private PaqueteDAO dao;

    // 2. SIEMPRE el constructor debe ir arriba para inicializar las herramientas
    public PaqueteController() {
        this.dao = new PaqueteDAO();
    }

    /**
     * Método para el Inventario: Transforma la lista del DAO en un modelo para JTable
     */
    public DefaultTableModel consultarInventario() {
        String[] titulos = {"Guía", "Remitente", "Destinatario", "Dirección", "Peso (kg)", "Tipo", "Estado", "Fecha"};

        // Creamos el modelo y bloqueamos la edición de celdas
        DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        // Pedimos los datos al DAO
        // IMPORTANTE: Asegúrate de que en PaqueteDAO.java hayas creado el método 'listarTodos()'
        List<Paquete> lista = dao.listarTodos();

        Object[] fila = new Object[8];
        for (Paquete p : lista) {
            fila[0] = p.getGuia();
            fila[1] = p.getRemitente();
            fila[2] = p.getDestinatario();
            fila[3] = p.getDireccion();
            fila[4] = p.getPeso();
            fila[5] = p.getTipo();
            fila[6] = p.getEstado();
            fila[7] = p.getFecha();
            modeloTabla.addRow(fila);
        }

        return modeloTabla;
    }

    /**
     * Método para el Registro: Valida y envía datos al DAO
     */
    public boolean guardarPaquete(String guia, String rem, String dest, String dir, String pesoStr, String tipo, String est, String fecha) {

        if (guia.isEmpty() || rem.isEmpty() || dest.isEmpty() || pesoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Faltan datos obligatorios.");
            return false;
        }

        try {
            double peso = Double.parseDouble(pesoStr.trim().replace(",", "."));
            Paquete nuevoPaquete = new Paquete(guia, rem, dest, dir, peso, tipo, est, fecha);
            return dao.registrar(nuevoPaquete);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El peso ingresado no es un número válido.");
            return false;
        }
    }
}