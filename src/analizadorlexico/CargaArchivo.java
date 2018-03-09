/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lau Rodríguez
 */
public class CargaArchivo {
    File archivoSelected=new File("programa.txt");
    public String aux; //variable aux que almacena cada línea del codigo fuente
    public String codigo;//variable codigo que almacena todo el programa fuente
    String rutaAr;//variable rutaAr que almacena la ruta del archivo 
    JFileChooser fc;//instancia de la clase JFileChooser
    
    public void abreArchi(){
       aux = "";//Se inicializa aux
       codigo = "";//Se inicializa codigo
        try {
               fc = new JFileChooser();//Se utiliza la clase JFilechooser para elegir el archivo que se abrirá
               FileNameExtensionFilter filtro = new FileNameExtensionFilter(".txt","txt");//Se define el filtro para que sólo pueda abrir archivos de extensión .txt
               fc.setFileFilter(filtro);//Se asigna el filtro
               fc.showOpenDialog(fc);//Se abre la ventana del fileChooser
               archivoSelected = fc.getSelectedFile(); //Se asigna el nombre del archivo a la variable archivoSelected
               fc.addChoosableFileFilter(filtro);//Se agrega el filtro para el archivo seleccionado
               int status = 0;//Variable para almacenar el estado de la ventana
               if(archivoSelected.getName().endsWith(".txt")){//Si el archivo seleccionado termina con la extensión .txt
                    if (fc != null) {//Si el filtro es diferente de nulo
                       FileReader archivos = new FileReader(archivoSelected);//Se utiliza la clase FileReader para abrir el archivo seleccionado
                       BufferedReader lee = new BufferedReader(archivos);//Se usa la clase BufferedReader para crear un buffer de lectura 
                       //Variable auxiliar que almacena la lectura de una línea
                       while ((aux = lee.readLine()) != null) {//Mientras que aux sea diferente de nulo, es decir que contenga caracteres
                            codigo += aux + "\n";//Variable codigo almacena acumulativamente linea por linea
                            rutaAr = archivoSelected.getAbsolutePath();//Obtiene la ruta absoluta del archivo seleccionado y se almacena en rutaAr
                       }
                       lee.close();//Se cierra el buffer de lectura
                       archivos.close();//Se cierra el archivo
                    }
               }else{//Si el archivo no termina en extensión .txt envía mensaje de error
                   JOptionPane.showMessageDialog(null, "ERROR", "Solo se acepta formato txt", 0);
               }
                if(status== JFileChooser.CANCEL_OPTION ){//Si se elige la opción de cancelar, manda mensaje que informa que no se ha seleccionado ningun archivo
                   JOptionPane.showMessageDialog(null, "No eligio ningun archivo", "Error", 0);
               }
       } catch (IOException ex){ //En caso de que encuentre excepción al abrir el archivo mande mensaje de adevertencia
               JOptionPane.showMessageDialog(null, ex + "\nNo se ha encontrado el archivo", "¡ARCHIVO NO LOCALIZADO!",
                               JOptionPane.WARNING_MESSAGE);
       }

    }
    
    public String devolver(){//Metodo para enviar el código y asignarlo al jtextarea
        return codigo;
    }
    //Método que sirve para escribir en el archivo los cambios realizados en el jtextarea
    public void guardarArchi(JTextArea txtEntrada) throws InterruptedException {
            try {
                    FileWriter fw = new FileWriter(archivoSelected);//Se abre el archivo seleccionado en modo escritura
                    BufferedWriter bw = new BufferedWriter(fw);//Se crea buffer de escritura
                    PrintWriter pw = new PrintWriter(bw);
                    pw.write(txtEntrada.getText());//Escribe el texto que obtiene del textArea 
                    //pw.append("");
                    pw.close();
                    bw.close();//Se cierra el buffer de escirtura
                    fw.close();//Se cierra el modo de escritura del archivo
                    JOptionPane.showMessageDialog(null,"Programa escrito", "Información",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Se encontro un ERROR, no se guardo el archivo", "Información", JOptionPane.INFORMATION_MESSAGE);
                    e.printStackTrace();
            }
    }
}
