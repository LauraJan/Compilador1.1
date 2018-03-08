/**
 * Created by israel on 07/03/18.
 * @author Israel Moreno -- Laura Perez -- Fernando Lazaro
 */
package analizadorlexico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.StringTokenizer;

public class Interfaz extends JFrame implements ActionListener{
    /**
     *Definicion de elementos gráficos
     */
    JMenuBar    menuBar;
    JMenu       menu;
    JButton     btnRun, btnTable, btnCode;
    JTextArea   txtCode, txtOutput;
    JScrollPane spCode, spOutput;

    StringTokenizer     st, stAux;
    PalabrasReservadas  pr;
    Operadores          operador;
    AfdIdentificador    id;
    AfdDigito           digito;
    AfdCadena           cadenita;
    tablaSimbolosInterfaz tabla = new tablaSimbolosInterfaz();

    String token, tokenAnt, tokenAux;
    String OutPut = "", algo="";


    /**
     * Constructor para iniciar los componentes en la ventana principal
     */
    public Interfaz()
    {
        this.setTitle("Analizador Léxico");
        this.setSize(700, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabla = new tablaSimbolosInterfaz();

        panelSuperior();
        panelCentral();
        panelInferior();
    }


    /**
     * Diseño del panel Superior (Botones de analizar, tabla de simbolos y Guardar Codigo)
     */
    private void panelSuperior()
    {
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior11 = new JPanel();
        panelSuperior.setLayout(new FlowLayout(10));

        btnRun      = new JButton("Analizar");
        btnTable    = new JButton("Tabla Simbolos");
        btnCode     = new JButton("Guardar Código");

        btnRun.setIcon(new ImageIcon("Images/play.png"));

        btnRun.addActionListener(this);
        btnTable.addActionListener(this);
        btnCode.addActionListener(this);

        panelSuperior11.add(btnRun);
        panelSuperior11.add(btnTable);
        panelSuperior11.add(btnCode);
        panelSuperior.add(panelSuperior11, BorderLayout.WEST);
        this.getContentPane().add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Diseño del Panel central. Sección dónde se teclea el código
     */
    private void panelCentral()
    {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(1,1));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        txtCode     = new JTextArea();
        spCode      = new JScrollPane(txtCode);
        spCode.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        panelCentral.add(spCode);
        this.getContentPane().add(panelCentral, BorderLayout.CENTER);
    }


    /**
     * Diseño del panelInferior. Sección dónde se muestran errores
     */
    private void panelInferior()
    {
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1,1));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        txtOutput   = new JTextArea(5,0);
        spOutput    = new JScrollPane(txtOutput);
        spOutput.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        txtOutput.setEditable(false);

        panelInferior.add(spOutput);
        this.getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }


    /**
     *Eventos para botones
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnRun) {
            txtOutput.setText(OutPut);
            tokenizar();
            //analizar();
        }
        else if(e.getSource() == btnTable)
        {
            tabla.setVisible(true);
        }
        else if(e.getSource() == btnCode)
        {
            JFileChooser jF1= new JFileChooser();
            try{
                if(jF1.showSaveDialog(null)==jF1.APPROVE_OPTION){
                    BufferedWriter bw = new BufferedWriter (new FileWriter(jF1.getSelectedFile().getAbsolutePath()));
                    PrintWriter pw = new PrintWriter (bw);
                    pw.write(txtCode.getText());
                    bw.close();
                    pw.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }

    }

    public void analizar()
    {
        String cadena;
        cadena              = txtCode.getText();
        st                  = new StringTokenizer(cadena, " \n");
        stAux               = st;
        pr                  = new PalabrasReservadas();
        operador            = new Operadores();
        tokenAnt            = "";
        tabla.LimpiarJTable();

        while(st.hasMoreElements())
        {
            token   = st.nextToken();
            tokenAux=token;
            
            System.out.println(token);
        System.out.println(tokenAnt);
            id      = new AfdIdentificador(token);
            digito  = new AfdDigito(token);
            cadenita= new AfdCadena(token);

            if (pr.analizar(token))
            {
                manejoArchivo(funcionHash(token),token,"--","PR", "--");
                System.out.println(token+" es una palabra reservada");
            }

            else if(id.aceptado)
            {
                if (tokenAnt.equals("int") || tokenAnt.equals("double") || tokenAnt.equals("float") || tokenAnt.equals("char") || tokenAnt.equals("string"))
                    if (stAux.hasMoreElements() && stAux.nextToken().equals("="))
                        if (stAux.hasMoreElements())
                            manejoArchivo(funcionHash(token), token, tokenAnt, "ID", stAux.nextToken());
                        else
                            manejoArchivo(funcionHash(token), token, tokenAnt, "ID", "---");
                    else
                        manejoArchivo(funcionHash(token), token, tokenAnt, "ID", "---");
                else
                    manejoArchivo(funcionHash(token), token, "--","ID","---");

                System.out.println(token+" es un identificador");
            }
            else if(digito.aceptado)
            {
                manejoArchivo(funcionHash(token), token, "--","DIGITO","---");
                System.out.println(token + " es un dígito");
            }
            else if(operador.analizar(token))
            {
                manejoArchivo(funcionHash(token),token,"--","OPERADOR", "--");
                System.out.println(token+" es un operador");
            }
            else
                OutPut += token+" no esta en el lenguaje\n";
            tokenAnt = token;
        }
        JOptionPane.showMessageDialog(null,"Análisis completado");
        txtOutput.setText("---Análisis completado--- \n");
        txtOutput.setText(OutPut);
    }

    public int funcionHash(String token)
    {
        int numero1=0;
        int numero2=0;
        int numero;
        int x;
        int y;
        for (x=0;x<token.length();x+=2)
        {
            System.out.println(token.charAt(x) + " = " + token.codePointAt(x));
            numero1 += token.codePointAt(x);
        }

        for(y=1; y<token.length(); y+=2) {
            System.out.println(token.charAt(y) + " = " + token.codePointAt(y));
            numero2 += (token.codePointAt(y));
        }
        numero = numero1-numero2;
        if (numero < 0)
            numero *= -1;
        System.out.println(numero);
        return numero;
    }

    public void manejoArchivo(int hash, String nombre, String tipo, String categoria, String valor)
    {
        try {
            TablaSimbolos ts    = new TablaSimbolos();
            ts.eliminar(new File("tablaSimbolos.txt"));
            tabla.meterDatosTabla(hash,nombre, tipo, categoria, valor);
            ts.crearArchivo(new File("tablaSimbolos.txt"));
            ts.escribirArchivo(hash,nombre, tipo, categoria, valor);
            ts.cerrar();
        }
        catch (IOException ex)
        {
            System.out.println();
        }
    }

    private void tokenizar() {

        String codigo   = txtCode.getText();
        String token    = "";
        
        operador            = new Operadores();
        pr                  = new PalabrasReservadas();
        
        for(int i = 0; i < codigo.length(); i++) {
            if (Character.toString(codigo.charAt(i)).equals(" ") || Character.toString(codigo.charAt(i)).equals("\n")
                    || (i == codigo.length()-1)){
                
                id      = new AfdIdentificador(token);
                digito  = new AfdDigito(token);
                cadenita= new AfdCadena(token);

                if (pr.analizar(token))
                {
                    manejoArchivo(funcionHash(token),token,"--","PR", "--");
                    System.out.println(token+" es una palabra reservada");
                }

                else if(id.aceptado)
                {
                    manejoArchivo(funcionHash(token), token, "--","ID","---");
                    System.out.println(token+" es un identificador");
                }
                else if(digito.aceptado)
                {
                    manejoArchivo(funcionHash(token), token, "--","DIGITO","---");
                    System.out.println(token + " es un dígito");
                }
                else if(operador.analizar(token))
                {
                    manejoArchivo(funcionHash(token),token,"--","OPERADOR", "--");
                    System.out.println(token+" es un operador");
                }
                else
                    OutPut += token+" no esta en el lenguaje\n";
                token = "";
            }
            else
                token += Character.toString(codigo.charAt(i));
        }
        JOptionPane.showMessageDialog(null,"Análisis completado");
        txtOutput.setText("---Análisis completado--- \n");
        txtOutput.setText(OutPut);

    }
}
