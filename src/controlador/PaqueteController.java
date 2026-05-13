package controlador;

import dao.PaqueteDAO;
import dao.MovimientoDAO;
import modelo.Paquete;
import modelo.MovimientoPaquete;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.Timestamp;

public class PaqueteController {

    private PaqueteDAO dao;
    private MovimientoDAO movimientoDAO;

    // CONSTRUCTOR
    public PaqueteController() {

        dao = new PaqueteDAO();

        movimientoDAO = new MovimientoDAO();
    }

    /**
     * CONSULTAR INVENTARIO
     */
    public DefaultTableModel consultarInventario() {

        String[] titulos = {
            "Guía",
            "Ciudad Origen",
            "Ciudad Destino",
            "Remitente",
            "Destinatario",
            "Dirección",
            "Peso (kg)",
            "Tipo",
            "Estado",
            "Ubicación Actual",
            "Fecha Registro"
        };

        DefaultTableModel modeloTabla
                = new DefaultTableModel(null, titulos) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Paquete> lista = dao.listarTodos();

        Object[] fila = new Object[11];

        for (Paquete p : lista) {

            fila[0] = p.getGuia();
            fila[1] = p.getCiudadOrigen();
            fila[2] = p.getCiudadDestino();
            fila[3] = p.getRemitente();
            fila[4] = p.getDestinatario();
            fila[5] = p.getDireccion();
            fila[6] = p.getPeso();
            fila[7] = p.getTipo();
            fila[8] = p.getEstado();
            fila[9] = p.getUbicacionActual();
            fila[10] = p.getFechaSistema();

            modeloTabla.addRow(fila);
        }

        return modeloTabla;
    }

    /**
     * GUARDAR PAQUETE
     */
    public boolean guardarPaquete(
            String guia,
            String rem,
            String dest,
            String dir,
            String pesoStr,
            String tipo,
            String ciudadOrigen,
            String ciudadDestino) {

        // VALIDACIÓN
        if (guia.isEmpty()
                || rem.isEmpty()
                || dest.isEmpty()
                || pesoStr.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error: Faltan datos obligatorios."
            );

            return false;
        }

        try {

            double peso = Double.parseDouble(
                    pesoStr.trim().replace(",", ".")
            );

            // CREAR OBJETO PAQUETE
            Paquete nuevoPaquete = new Paquete();

            nuevoPaquete.setGuia(guia);
            nuevoPaquete.setRemitente(rem);
            nuevoPaquete.setDestinatario(dest);
            nuevoPaquete.setDireccion(dir);
            nuevoPaquete.setPeso(peso);
            nuevoPaquete.setTipo(tipo);
            nuevoPaquete.setEstado("En Bodega"); // El estado inicial ahora es automático
            nuevoPaquete.setCiudadOrigen(ciudadOrigen);
            nuevoPaquete.setCiudadDestino(ciudadDestino);

            // REGISTRAR PAQUETE
            boolean paqueteRegistrado
                    = dao.registrar(nuevoPaquete);

            // SI EL PAQUETE SE REGISTRÓ BIEN
            if (paqueteRegistrado) {

                // CREAR PRIMER MOVIMIENTO
                MovimientoPaquete movimiento
                        = new MovimientoPaquete();

                movimiento.setGuiaRastreo(guia);

                movimiento.setEstado("En Bodega");

                movimiento.setUbicacion(ciudadOrigen);

                movimiento.setDescripcion(
                        "Paquete registrado en el sistema"
                );

                // GUARDAR MOVIMIENTO
                movimientoDAO.registrarMovimiento(
                        movimiento
                );
            }

            return paqueteRegistrado;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "El peso ingresado no es un número válido."
            );

            return false;
        }
    }

    /**
     * CALCULAR ESTADO AUTOMÁTICO
     */
    public String calcularEstadoPorTiempo(
            Timestamp fechaSistema) {

        if (fechaSistema == null) {

            return "En Bodega";
        }

        long ahora = System.currentTimeMillis();

        long registro = fechaSistema.getTime();

        long diferenciaMillis = ahora - registro;

        long segundosPasados
                = diferenciaMillis / 1000;

        if (segundosPasados < 10) {

            return "En Bodega";
        }

        if (segundosPasados < 20) {

            return "En Despacho";
        }

        if (segundosPasados < 30) {

            return "En Ruta";
        }

        return "Entregado";
    }

    /**
     * BUSCAR PAQUETE POR GUÍA
     */
    public Paquete buscarPaquetePorGuia(
            String guia) {

        return dao.buscarPorGuia(guia);
    }

    /**
     * ACTUALIZAR ESTADO Y UBICACIÓN
     */
    public boolean actualizarEstadoPaquete(
            String guia,
            String estado,
            String ubicacion) {

        return dao.actualizarEstado(
                guia,
                estado,
                ubicacion
        );
    }
}
