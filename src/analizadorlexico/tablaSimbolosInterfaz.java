package analizadorlexico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * Created by israel on 07/03/18.
 * @author Israel Moreno -- Laura Perez -- Fernando Lazaro
 */

public class tablaSimbolosInterfaz extends JFrame{

    //Creacion de la tabla
    String[] nombresColumna 		= {"Posicion","Nombre","Tipo", "Categoria", "Valor"};
    Object[][] datosSimbolos		= new Object[0][4];

    public DefaultTableModel modeloTabla = new DefaultTableModel(datosSimbolos, nombresColumna);
    JTable tablaSimbolos 		  = new JTable(modeloTabla);
    JScrollPane spTabla			  = new JScrollPane(tablaSimbolos);

    int fila = 0;
    public tablaSimbolosInterfaz() {
        this.setSize(600,400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panelTabla();
        //meterDatosTabla();
    }

    private void panelTabla()
    {
        getContentPane().add(spTabla, BorderLayout.CENTER);
    }


    public void meterDatosTabla(int posicion, String nombre, String tipo, String categoria, String valor)
    {
        Object[]  rowInfo = {posicion, nombre, tipo, categoria, valor};
        modeloTabla.addRow(rowInfo);
    }

    public void LimpiarJTable(){
        modeloTabla.setRowCount(0);
    }
}
