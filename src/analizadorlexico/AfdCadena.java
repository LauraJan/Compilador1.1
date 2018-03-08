/**
 * Created by israel on 07/03/18.
 * @author Israel Moreno -- Laura Perez -- Fernando Lazaro
 */
package analizadorlexico;
public class AfdCadena {

    char[] cadena;
    int contador = 0;
    int tamanio = 50;
    boolean aceptado = false;

    public AfdCadena(String cadena)
    {
        this.cadena = cadena.toCharArray();
        q0(contador+1);
    }

    public boolean  q0(int contador)
    {
        aceptado = true;
        if(cadena.length<=tamanio)
        {
            if (contador < cadena.length) {
                if (cadena[contador] == '"') {
                    q1(contador + 1);
                } else if (cadena[contador] >= 'a' && cadena[contador] <= 'z') {
                    q4(contador + 1);//ERROR
                }
            }
        }
        return aceptado;
    }

    public boolean  q1(int contador)
    {
        aceptado = true;
        if(cadena.length<=tamanio)
        {
            if (contador < cadena.length) {
                if (cadena[contador] == '"') {
                    q4(contador + 1);//ERROR
                } else if (cadena[contador] >= 'a' && cadena[contador] <= 'z') {
                    q2(contador + 1);
                }
            }
        }
        return aceptado;
    }

    public boolean  q2(int contador)
    {
        aceptado = true;
        if(cadena.length<=tamanio)
        {
            if (contador < cadena.length) {
                if (cadena[contador] == '"') {
                    q3(contador + 1);
                } else if (cadena[contador] >= 'a' && cadena[contador] <= 'z') {
                    q2(contador + 1);
                }
            }
        }
        return aceptado;
    }

    public boolean  q3(int contador)
    {
        aceptado = true;
        if(cadena.length<=tamanio)
        {
            if (contador < cadena.length) {
                if (cadena[contador] == '"') {
                    q4(contador + 1); //ERROR
                } else if (cadena[contador] >= 'a' && cadena[contador] <= 'z') {
                    q4(contador + 1);//ERROR
                }
            }
        }
        return aceptado;
    }

    public boolean  q4(int contador)
    {
        aceptado = false;
        if(cadena.length<=tamanio)
        {
            if (contador < cadena.length) {
                if (cadena[contador] == '.') {
                    q4(contador + 1); //ERROR
                } else if (cadena[contador] >= 'a' && cadena[contador] <= 'z') {
                    q4(contador + 1); //ERROR
                }
            }
        }
        return aceptado;
    }

}
