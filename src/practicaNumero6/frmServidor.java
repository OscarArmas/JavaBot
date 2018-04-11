package practicaNumero6;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class frmServidor extends JFrame implements Runnable {
    JTextArea txtmensajes;
    String Respuesta[]=new String[10];
    String Preguntas[]=new String[10];
    String EntardadeTexto;



    public frmServidor(){
        this.setTitle("SERVIDOR");
        ////////////////////////////////////
        Respuesta[0]="Hola, soy javaBot";
        Respuesta[1]="vivo en la Pc";
        Respuesta[2]="Me creeo Oscar";
        Respuesta[3]="Adios, nos vemos pronto";
        Respuesta[4]="No soy humano, soy puro codigo";
        Respuesta[5]="Dime Javabooot";
        Respuesta[6]="La carnita Asada";
        /////////Preguntas
        Preguntas[0]="Hola";
        Preguntas[1]="Donde vives";
        Preguntas[2]="Quien te creo";
        Preguntas[3]="adios";
        Preguntas[4]="Eres humano?";
        Preguntas[5]="Como te digo";
        Preguntas[6]="Se va hacer?";
        //////////////////////////////////7
        txtmensajes= new JTextArea();
        txtmensajes.setBounds(10,10,400,400);
        add(txtmensajes);
        setLayout(null);
        setSize(400,400);
        setVisible(true);
        Thread hilo= new Thread(this);
        hilo.start();
    }

    public static void main(String[]args){
        new frmServidor();

    }

    @Override
    public void run() {

        try {
            ServerSocket servidor = new ServerSocket(8016);
            Socket cli;
                while (true) {
                    cli = servidor.accept();
                    DataInputStream flujo = new DataInputStream(cli.getInputStream());
                    String msg = flujo.readUTF();
                    txtmensajes.append("\n" + msg);
                    EntardadeTexto = msg;
                    cli.close();
                    if(msg.equalsIgnoreCase("FIN")){
                        servidor.close();
                        break;
                    }
                    Socket envia= new Socket("192.168.100.4",8017);
                    for(int i=0; i<=7;i++) {
                        if (EntardadeTexto.equals(Preguntas[i])) {
                            DataOutputStream clicenvia = new DataOutputStream(envia.getOutputStream());
                            clicenvia.writeUTF(Respuesta[i]);
                            clicenvia.close();
                        }
                    }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
