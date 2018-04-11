package practicaNumero6;

import org.omg.CORBA.DATA_CONVERSION;
import org.omg.CORBA.DataOutputStream;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class frmCleinte extends JFrame implements ActionListener, Runnable{

    JTextField txtmensaje;
    JButton BtnEnviar;
    JTextArea textArea;
    public frmCleinte(){
        textArea=new JTextArea();
        textArea.setBounds(10,70,200,200);
        add(textArea);
        txtmensaje= new JTextField();
        txtmensaje.setBounds(10,10,200,20);
        add(txtmensaje);
        BtnEnviar=new JButton();
        BtnEnviar.setText("Enviar");
        BtnEnviar.setBounds(10,40,150,20);
        BtnEnviar.addActionListener(this);
        add(BtnEnviar);
        setLayout(null);
        setSize(400,400);
        setVisible(true);
        Thread hilo= new Thread(this);
        hilo.start();
    }
    public static void main(String[] args){
        new frmCleinte();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==BtnEnviar){
            try{
                Socket cli=new Socket("192.168.100.4",9099);
                java.io.DataOutputStream flujo= new java.io.DataOutputStream(cli.getOutputStream());
                flujo.writeUTF(txtmensaje.getText());
                cli.close();

            }catch (Exception ex){
                System.out.print("Error en Cliente: "+ ex.getMessage());

            }
        }
    }

    @Override
    public void run() {

       try{
           ServerSocket serve = new ServerSocket(9098);
           Socket cli;
           while (true){
               cli =serve.accept();
               DataInputStream FLUJO= new DataInputStream(cli.getInputStream());
               String msg= FLUJO.readUTF();
               textArea.append(msg);
           }

       }catch (Exception e){

       }



    }
}
