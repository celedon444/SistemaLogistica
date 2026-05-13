/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.ConexionBD;
import modelo.GestionReporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camil
 */
public class GestionReporteDAO {

    Connection con;

    PreparedStatement ps;

    ResultSet rs;

    ConexionBD cn = new ConexionBD();

    public List<GestionReporte> listarReportes() {

        List<GestionReporte> lista
                = new ArrayList<>();

        String sql
                = "SELECT * FROM reportes ORDER BY id_reporte DESC";

        try {

            con = cn.conectar();

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                GestionReporte gr
                        = new GestionReporte();

                gr.setIdReporte(
                        rs.getInt("id_reporte")
                );

                gr.setGuiaPaquete(
                        rs.getString("guia_paquete")
                );

                gr.setMotivo(
                        rs.getString("motivo")
                );

                gr.setDescripcion(
                        rs.getString("descripcion")
                );

                gr.setRutaEvidencia(
                        rs.getString("ruta_evidencia")
                );

                gr.setFechaRegistro(
                        rs.getTimestamp("fecha_registro")
                );

                gr.setEstado(
                        rs.getString("estado")
                );

                lista.add(gr);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al listar reportes: "
                    + e
            );
        }

        return lista;
    }

    /**
     * ACTUALIZAR ESTADO DEL REPORTE
     */
    public boolean actualizarEstadoReporte(
            int idReporte,
            String nuevoEstado
    ) {

        String sql
                = "UPDATE reportes "
                + "SET estado=? "
                + "WHERE id_reporte=?";

        try {

            con = cn.conectar();

            ps = con.prepareStatement(sql);

            ps.setString(1, nuevoEstado);

            ps.setInt(2, idReporte);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar reporte: "
                    + e
            );

            return false;
        }
    }

    public boolean existeReporteActivo(String guia) {

        String sql
                = "SELECT * FROM reportes "
                + "WHERE guia_paquete = ? "
                + "AND estado != 'Resuelto'";

        try {

            con = cn.conectar();

            ps = con.prepareStatement(sql);

            ps.setString(1, guia);

            rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println(
                    "Error verificando reporte: "
                    + e
            );

            return false;
        }
    }
}
