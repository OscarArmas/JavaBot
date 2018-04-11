package practica6;
import java.lang.reflect.Array;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class botUno {
    ServerSocket server;
    Socket socket;
    int puerto =9000;
    DataOutputStream salida;
    BufferedReader entrada;
    String EntardadeTexto;
    String Respuesta[]=new String[10];
    String Preguntas[]=new String[10];
    public botUno(){
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


    }
    public void Iniciar(){

            try {
                server = new ServerSocket(puerto);
                socket = new Socket();
                socket = server.accept();
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String mensaje = entrada.readLine();
                System.out.println(mensaje);
                EntardadeTexto = mensaje;

                for (int i = 0; i <= Respuesta.length; i++) {

                    if (EntardadeTexto.equals(Preguntas[i])) {
                        salida = new DataOutputStream(socket.getOutputStream());
                        salida.writeUTF(Respuesta[i]);
                    }

                }

                socket.close();

            } catch (Exception e) {
            }
    }
    public static void main(String[] args){

       botUno c= new botUno();
       c.Iniciar();
    }
}
