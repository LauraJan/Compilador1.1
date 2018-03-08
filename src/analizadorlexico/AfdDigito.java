/**
 * Created by israel on 07/03/18.
 * @author Israel Moreno -- Laura Perez -- Fernando Lazaro
 */
package analizadorlexico;
public class AfdDigito {

    char caracter[];
    int  contador=0;
    boolean aceptado;

    public AfdDigito(String cadena)
    {
        this.caracter   = cadena.toCharArray();
        q0(contador);
    }

    private boolean q0(int contador)
    {
        aceptado = true;
        if(contador < caracter.length)
            if (caracter[contador] >= '0' && caracter[contador]<='9')
                q0(contador+1);
            else
                aceptado=false;
        return aceptado;
    }

}
