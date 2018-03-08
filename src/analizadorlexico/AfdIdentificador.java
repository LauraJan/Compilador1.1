/**
 * Created by israel on 07/03/18.
 * @author Israel Moreno -- Laura Perez -- Fernando Lazaro
 */
package analizadorlexico;
public class AfdIdentificador {

    public int 	    contador = 0;
    public char[] 	caracter;
    public boolean  aceptado;

    public AfdIdentificador(String cadena)
    {
        this.caracter   = cadena.toCharArray();
        q0(contador);
    }

    private boolean q0(int contador) {
        aceptado = false;
        if(contador < caracter.length)
            if(caracter[contador] == '_' || (caracter[contador]>='a' && caracter[contador]<='z'))
                q1(contador + 1);
            else if(caracter[contador] >= '0' || caracter[contador]<='9')
                q3(contador + 1);
        return aceptado;
    }

    private boolean q1(int contador)
    {
        aceptado = true;
        if(contador < caracter.length)
            if ((caracter[contador]>='a' && caracter[contador]<='z') || (caracter[contador]>='0' && caracter[contador]<='9'))
                q1(contador + 1);
            else
                q3(contador +1);
        return aceptado;
    }

    private boolean q3(int contador)
    {
        aceptado = false;
        if(contador < caracter.length)
            if (caracter[contador]>='0' && caracter[contador]<='9')
                q3(contador+1);
            else
                q3(contador+1);
        return aceptado;
    }
}
