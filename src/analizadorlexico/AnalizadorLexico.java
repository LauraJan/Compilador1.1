/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
/**
 *
 * @author israel
 */
public class AnalizadorLexico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{

            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        }catch(Exception ex){
            ex.toString();
        }
        new Interfaz().setVisible(true);
    }
    
}
