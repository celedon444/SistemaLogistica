package dao;

import conexion.ConexionBD;
import modelo.Paquete;
import java.sql.*;
import java.util.ArrayList; // IMPORTANTE: Para la lista
import java.util.List;      // IMPORTANTE: Para la lista
import javax.swing.JOptionPane;

public class PaqueteDAO {

    // MÉTODO 1: Para guardar (Registro)
    public boolean registrar(Paquete paquete) {
        String sql = "INSERT INTO paquetes (guia_rastreo, remitente, destinatario, direccion_entrega, peso, tipo_envio, estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paquete.getGuia());
            ps.setString(2, paquete.getRemitente());
            ps.setString(3, paquete.getDestinatario());
            ps.setString(4, paquete.getDireccion());
            ps.setDouble(5, paquete.getPeso());
            ps.setString(6, paquete.getTipo());
            ps.setString(7, paquete.getEstado());
            ps.setString(8, paquete.getFecha());

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de SQL al registrar: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO 2: Para leer (Inventario)
    public List<Paquete> listarTodos() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquetes";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paquete p = new Paquete();
                p.setGuia(rs.getString("guia_rastreo"));
                p.setRemitente(rs.getString("remitente"));
                p.setDestinatario(rs.getString("destinatario"));
                p.setDireccion(rs.getString("direccion_entrega"));
                p.setPeso(rs.getDouble("peso"));
                p.setTipo(rs.getString("tipo_envio"));
                p.setEstado(rs.getString("estado"));
                p.setFecha(rs.getString("fecha_registro"));

                lista.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de SQL al listar: " + e.getMessage());
        }
        return lista;
    }
}