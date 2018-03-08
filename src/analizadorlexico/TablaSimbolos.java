package analizadorlexico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by israel on 07/03/18.
 * @author Israel Moreno -- Laura Perez -- Fernando Lazaro
 */

public class TablaSimbolos {

    RandomAccessFile archivo;
    int tamanioRegistro = 190;

    public void crearArchivo(File archivo) throws FileNotFoundException, IOException
    {
        this.archivo = new RandomAccessFile(archivo, "rw");
    }

    public void escribirArchivo(int posicion, String nombre, String tipo, String categoria, String valor) throws IOException
    {
        if (posicion >= 0)
            if (tamanioByte(nombre, tipo, categoria, valor) > tamanioRegistro)
                System.out.println("Tamaño Excedido");
            else
            {
                archivo.seek(posicion*tamanioRegistro);
                archivo.writeUTF(nombre);
                archivo.writeUTF(tipo);
                archivo.writeUTF(categoria);
                archivo.writeUTF(valor);
                System.out.println(posicion+"---"+tamanioRegistro);
            }
    }
    public void cerrar () throws IOException
    {
        archivo.close();
    }

    public void eliminar (File fichero)
    {
        if (!fichero.exists()) {
            System.out.println("El archivo no existe.");
        } else {
            fichero.delete();
            System.out.println("El archivo fue eliminado.");
        }
    }

    public int tamanioByte(String nombre, String tipo, String categoria, String valor)
    {
        int bytes = nombre.length()*2 + tipo.length()*2 + categoria.length()*2 +valor.length()*2 ;
        return bytes;
    }

}
