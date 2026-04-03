package dao;

import conexion.ConexionBD;
import modelo.Paquete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PaqueteDAO {

    // MÉTODO 1: Registrar (Ahora más limpio, sin enviar fecha manual)
    public boolean registrar(Paquete paquete) {
        // QUITAMOS 'fecha_registro' del INSERT porque ahora MySQL usa 'fecha_sistema' automáticamente
        String sql = "INSERT INTO paquetes (guia_rastreo, remitente, destinatario, direccion_entrega, peso, tipo_envio, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paquete.getGuia());
            ps.setString(2, paquete.getRemitente());
            ps.setString(3, paquete.getDestinatario());
            ps.setString(4, paquete.getDireccion());
            ps.setDouble(5, paquete.getPeso());
            ps.setString(6, paquete.getTipo());
            ps.setString(7, paquete.getEstado());

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO 2: Listar (Capturando el Timestamp para el cronómetro)
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
                
                // AQUÍ ESTÁ EL CAMBIO: Leemos la fecha como Timestamp
                p.setFechaSistema(rs.getTimestamp("fecha_sistema"));

                lista.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // MÉTODO 3: Buscar uno solo (Lo necesitaremos para la vista de Rastreo)
    public Paquete buscarPorGuia(String guia) {
        String sql = "SELECT * FROM paquetes WHERE guia_rastreo = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, guia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paquete p = new Paquete();
                    p.setGuia(rs.getString("guia_rastreo"));
                    p.setRemitente(rs.getString("remitente"));
                    p.setDestinatario(rs.getString("destinatario"));
                    p.setDireccion(rs.getString("direccion_entrega"));
                    p.setPeso(rs.getDouble("peso"));
                    p.setTipo(rs.getString("tipo_envio"));
                    p.setEstado(rs.getString("estado"));
                    p.setFechaSistema(rs.getTimestamp("fecha_sistema"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }
}