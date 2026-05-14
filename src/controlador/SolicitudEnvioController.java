/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.SolicitudEnvioDAO;
import modelo.SolicitudEnvio;
import javax.swing.JOptionPane;

/**
 *
 * @author camil
 */
public class SolicitudEnvioController {

    // =====================================================
    // OBJETO DAO
    // Sirve para conectarse con MySQL
    // =====================================================
    SolicitudEnvioDAO dao
            = new SolicitudEnvioDAO();

    /**
     * ===================================================== REGISTRAR SOLICITUD
     * DE ENVÍO =====================================================
     */
    public boolean registrarSolicitud(
            String remitente,
            String destinatario,
            String ciudadOrigen,
            String ciudadDestino,
            String direccion,
            String pesoTexto,
            String tipoEnvio
    ) {

        // =====================================================
        // VALIDAR CAMPOS VACÍOS
        // =====================================================
        if (remitente.isEmpty()
                || destinatario.isEmpty()
                || direccion.isEmpty()
                || pesoTexto.isEmpty()
                || ciudadOrigen.equals("Seleccionar...")
                || ciudadDestino.equals("Seleccionar...")
                || tipoEnvio.equals("Seleccionar...")) {

            JOptionPane.showMessageDialog(
                    null,
                    "Complete todos los campos"
            );

            return false;
        }

        try {

            // =====================================================
            // CONVERTIR PESO A DOUBLE
            // =====================================================
            double peso
                    = Double.parseDouble(
                            pesoTexto.replace(",", ".")
                    );

            // =====================================================
            // CREAR OBJETO SOLICITUD
            // =====================================================
            SolicitudEnvio solicitud
                    = new SolicitudEnvio();

            // =====================================================
            // ENVIAR DATOS AL MODELO
            // =====================================================
            solicitud.setRemitente(remitente);

            solicitud.setDestinatario(destinatario);

            solicitud.setCiudadOrigen(ciudadOrigen);

            solicitud.setCiudadDestino(ciudadDestino);

            solicitud.setDireccionEntrega(direccion);

            solicitud.setPeso(peso);

            solicitud.setTipoEnvio(tipoEnvio);

            // =====================================================
            // ESTADO INICIAL
            // =====================================================
            solicitud.setEstado("PENDIENTE");

            // =====================================================
            // GUARDAR EN MYSQL
            // =====================================================
            return dao.registrarSolicitud(
                    solicitud
            );

        } catch (NumberFormatException e) {

            // =====================================================
            // ERROR SI EL PESO NO ES NUMÉRICO
            // =====================================================
            JOptionPane.showMessageDialog(
                    null,
                    "El peso debe ser numérico"
            );

            return false;
        }
    }
}
