/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //establecemos el fondo blanco o vacío
        setBackground(null);
        //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //Establecemos las filas que queremos cambiar el color. == 0 para pares y != 0 para impares
        boolean oddRow = (row % 2 == 0);

        //Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        Color cSinEnviar = new Color(0,153,51);
        Color cEspera = new Color(0,102,102);
        Color cError = new Color(255,0,0);
       
        //Si las filas son pares, se cambia el color a gris
        /*if (table.getValueAt(row, 3).toString().equals("200")) {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        else if(table.getValueAt(row, 3).toString().equals("400")){
           setBackground(cEspera);
            setForeground(Color.white);
        }
        else if(table.getValueAt(row, 3).toString().equals("100")){
           setBackground(cError);
            setForeground(Color.white);
        }
        else {
           setBackground(cSinEnviar);
           setForeground(Color.white);
        }
        */
        if(table.getValueAt(row, 3).toString().equals("Error")){
          setBackground(cError);
          setForeground(Color.white);
        }
        else if(table.getValueAt(row, 3).toString().equals("Duplicado")){
          setBackground(cError);
          setForeground(Color.white);
        }
        else {
           setBackground(Color.white);
            setForeground(Color.black);
        }
        
        
        return this;
    }

}
