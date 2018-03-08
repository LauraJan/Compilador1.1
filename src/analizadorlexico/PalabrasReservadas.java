/**
 * Created by israel on 07/03/18.
 * @author Israel Moreno -- Laura Perez -- Fernando Lazaro
 */
package analizadorlexico;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PalabrasReservadas {

    RandomAccessFile    fichero = null;
    String              cadena;
    StringBuilder       auxBuilder;
    boolean             encontro;
    long                pos;
    int                 indice;


    public boolean analizar(String palabra) {
        encontro = false;
        try {
            fichero = new RandomAccessFile("palabrasReservadas", "rw");

            cadena = fichero.readLine();
            while (cadena != null) {
                if(cadena.indexOf(palabra) != -1)
                    if (cadena.split(" ")[0].equalsIgnoreCase(palabra))
                        encontro = true;
                pos = fichero.getFilePointer();
                cadena = fichero.readLine();
            }
            return encontro;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Aqui hay algo 1");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Aqui hay algo 2");
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Aqui hay algo 3");
            }
        }
        return encontro;
    }
}
