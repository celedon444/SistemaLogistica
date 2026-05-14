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
}
