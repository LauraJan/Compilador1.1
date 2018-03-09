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
    public String aux;
    public String codigo;
    boolean existe;
    String rutaAr;
    JFileChooser fc;
    
    public void abreArchi(){
       aux = "";
       codigo = "";
        try {
               
               fc = new JFileChooser();//Se utiliza la clase JFilechooser para elegir el archivo que se abrirá
               FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                               ".txt","txt");//Se define el filtro para que sólo pueda abrir archivos de extensión .txt
               fc.setFileFilter(filtro);//Se asigna el filtro
               fc.showOpenDialog(fc);//Se abre la ventana del fileChooser
               archivoSelected = fc.getSelectedFile(); //Se asigna el nombre del archivo a la variable archivoSelected
               fc.addChoosableFileFilter(filtro);//Se agrega el filtro para el archivo seleccionado
               int status = 0;
               if(archivoSelected.getName().endsWith(".txt")){
               if (fc != null) {
                       FileReader archivos = new FileReader(archivoSelected);
                       BufferedReader lee = new BufferedReader(archivos);
                       aux = lee.readLine();
                       while (aux != null) {
                               codigo += aux + "\n";
                               existe = true;
                               rutaAr = archivoSelected.getAbsolutePath();
                       }
                       lee.close();
                       archivos.close();
               }
               }
               else{
                   JOptionPane.showMessageDialog(null, "ERROR", "Solo se acepta formato txt", 0);
               }

                if(status== JFileChooser.CANCEL_OPTION ){
                   JOptionPane.showMessageDialog(null, "No eligio ningun archivo", "Error", 0);
               }
       } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, ex + ""
                               + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
                               JOptionPane.WARNING_MESSAGE);
       }

    }
    
    public String devolver(){
        return codigo;
    }
    
    public void guardarArchi(JTextArea txtEntrada) throws InterruptedException {
		try {
			FileWriter fw = new FileWriter(archivoSelected);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.write(txtEntrada.getText());
			pw.append("");
			pw.close();
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(null,
					"Programa escrito", "Información",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Se encontro un ERROR, no se guardo el archivo",
					"Información", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
    public void mostrar(){
      try { 
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\Lau Rodríguez\\Desktop\\AnalizadorS&L\\DefinicionLenguajePHP.pdf"); 
        } 
        catch (Exception e){ 
        JOptionPane.showMessageDialog(null, "Error al Abrir el Archivo", "ERROR", JOptionPane.ERROR_MESSAGE); 
        }    
    }
    
    public void LeerArchivo(JTextArea area) throws IOException{
        int contIDs=0;
        
        File fichero = new File ("programa.txt");
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(area.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
 }

