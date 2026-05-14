/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.ConexionBD;
import modelo.SolicitudEnvio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author camil
 */
public class SolicitudEnvioDAO {

    /**
     * ===================================================== REGISTRAR SOLICITUD
     * EN MYSQL =====================================================
     */
    public boolean registrarSolicitud(
            SolicitudEnvio solicitud
    ) {

        // =====================================================
        // SQL PARA INSERTAR LA SOLICITUD
        // =====================================================
        String sql
                = "INSERT INTO solicitudes_envio("
                + "remitente,"
                + "destinatario,"
                + "ciudad_origen,"
                + "ciudad_destino,"
                + "direccion_entrega,"
                + "peso,"
                + "tipo_envio,"
                + "estado"
                + ") "
                + "VALUES(?,?,?,?,?,?,?,?)";

        try (
                // =====================================================
                // CONEXIÓN A MYSQL
                // =====================================================
                Connection con = ConexionBD.conectar(); // =====================================================
                // PREPARAR SQL
                // =====================================================
                 PreparedStatement ps = con.prepareStatement(sql)) {

            // =====================================================
            // ENVIAR DATOS AL INSERT
            // =====================================================
            ps.setString(
                    1,
                    solicitud.getRemitente()
            );

            ps.setString(
                    2,
                    solicitud.getDestinatario()
            );

            ps.setString(
                    3,
                    solicitud.getCiudadOrigen()
            );

            ps.setString(
                    4,
                    solicitud.getCiudadDestino()
            );

            ps.setString(
                    5,
                    solicitud.getDireccionEntrega()
            );

            ps.setDouble(
                    6,
                    solicitud.getPeso()
            );

            ps.setString(
                    7,
                    solicitud.getTipoEnvio()
            );

            ps.setString(
                    8,
                    solicitud.getEstado()
            );

            // =====================================================
            // EJECUTAR INSERT
            // =====================================================
            int resultado = ps.executeUpdate();

            // =====================================================
            // SI SE INSERTÓ CORRECTAMENTE
            // =====================================================
            return resultado > 0;

        } catch (SQLException e) {

            // =====================================================
            // MOSTRAR ERROR
            // =====================================================
            System.out.println(
                    "Error registrar solicitud: "
                    + e.getMessage()
            );

            return false;
        }
    }

    /**
     * ===================================================== LISTAR TODAS LAS
     * SOLICITUDES =====================================================
     */
    public ResultSet listarSolicitudes() {

        ResultSet resultado = null;

        try {

            // =====================================================
            // CONEXIÓN A MYSQL
            // =====================================================
            Connection con = ConexionBD.conectar();

            // =====================================================
            // SQL PARA CONSULTAR TODAS LAS SOLICITUDES
            // =====================================================
            String sql = "SELECT * FROM solicitudes_envio";

            // =====================================================
            // PREPARAR CONSULTA
            // =====================================================
            PreparedStatement ps
                    = con.prepareStatement(sql);

            // =====================================================
            // EJECUTAR CONSULTA
            // =====================================================
            resultado = ps.executeQuery();

        } catch (SQLException e) {

            // =====================================================
            // MOSTRAR ERROR
            // =====================================================
            System.out.println(
                    "Error listar solicitudes: "
                    + e.getMessage()
            );
        }

        return resultado;
    }

    public String generarGuia() {

        // =====================================================
        // LETRAS DISPONIBLES
        // =====================================================
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // =====================================================
        // VARIABLE PARA GUARDAR LA GUÍA
        // =====================================================
        String guia = "";

        // =====================================================
        // GENERAR 2 LETRAS ALEATORIAS
        // =====================================================
        for (int i = 0; i < 2; i++) {

            int posicion = (int) (Math.random() * letras.length());

            guia += letras.charAt(posicion);
        }

        // =====================================================
        // GENERAR 3 NÚMEROS ALEATORIOS
        // =====================================================
        int numero = (int) (Math.random() * 900) + 100;

        guia += numero;

        // =====================================================
        // RETORNAR GUÍA COMPLETA
        // =====================================================
        return guia;
    }

    public boolean aceptarSolicitud(
            int idSolicitud,
            String guia
    ) {

        // =====================================================
        // SQL PARA ACTUALIZAR SOLICITUD
        // =====================================================
        String sql
                = "UPDATE solicitudes_envio "
                + "SET estado = ?, guia = ? "
                + "WHERE id_solicitud = ?";

        try (
                // =====================================================
                // CONEXIÓN MYSQL
                // =====================================================
                Connection con = ConexionBD.conectar(); // =====================================================
                // PREPARAR SQL
                // =====================================================
                 PreparedStatement ps = con.prepareStatement(sql)) {

            // =====================================================
            // ENVIAR DATOS AL UPDATE
            // =====================================================
            ps.setString(1, "ACEPTADA");

            ps.setString(2, guia);

            ps.setInt(3, idSolicitud);

            // =====================================================
            // EJECUTAR UPDATE
            // =====================================================
            int resultado = ps.executeUpdate();

            return resultado > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error aceptar solicitud: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public boolean rechazarSolicitud(
            int idSolicitud
    ) {

        // =====================================================
        // SQL PARA RECHAZAR SOLICITUD
        // =====================================================
        String sql
                = "UPDATE solicitudes_envio "
                + "SET estado = ? "
                + "WHERE id_solicitud = ?";

        try (
                // =====================================================
                // CONEXIÓN MYSQL
                // =====================================================
                Connection con = ConexionBD.conectar(); // =====================================================
                // PREPARAR SQL
                // =====================================================
                 PreparedStatement ps = con.prepareStatement(sql)) {

            // =====================================================
            // NUEVO ESTADO
            // =====================================================
            ps.setString(1, "RECHAZADA");

            // =====================================================
            // ID SOLICITUD
            // =====================================================
            ps.setInt(2, idSolicitud);

            // =====================================================
            // EJECUTAR UPDATE
            // =====================================================
            int resultado = ps.executeUpdate();

            return resultado > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error rechazar solicitud: "
                    + e.getMessage()
            );

            return false;
        }
    }

}
