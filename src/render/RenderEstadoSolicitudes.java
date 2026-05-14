/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package render;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author camil
 */

public class RenderEstadoSolicitudes
        extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        Component celda
                = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column
                );

        String estado = value.toString();

        // =====================================================
        // COLOR SEGÚN ESTADO
        // =====================================================
        if (estado.equals("PENDIENTE")) {

            celda.setBackground(Color.YELLOW);

        } else if (estado.equals("ACEPTADA")) {

            celda.setBackground(Color.GREEN);

        } else if (estado.equals("RECHAZADA")) {

            celda.setBackground(Color.RED);

        } else {

            celda.setBackground(Color.WHITE);
        }

        return celda;
    }
}
