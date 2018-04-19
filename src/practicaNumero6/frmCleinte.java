import javax.swing.*;
import java.awt.*;
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
        textArea.setBounds(10,110,360,1000);
        textArea.setFont(new Font("Dialog", Font.BOLD, 36));

        txtmensaje= new JTextField();
        txtmensaje.setBounds(10,10,360,40);
        textArea.setFont(new Font("Dialog", Font.BOLD, 30));

        BtnEnviar=new JButton();
        BtnEnviar.setText("Enviar");
        BtnEnviar.setBounds(10,60,145,20);
        BtnEnviar.setSize(100,40);
        BtnEnviar.addActionListener(this);

        add(BtnEnviar,"Center");
        add(txtmensaje,"North");
        add(textArea,"South");
        this.setTitle("CLIENTE");
        setLayout(null);
        setSize(400,1100);
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
                Socket cli=new Socket("192.168.100.7",8024);
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
           ServerSocket serve = new ServerSocket(8025);
           Socket cli;
           while (true){
               cli =serve.accept();
               DataInputStream FLUJO= new DataInputStream(cli.getInputStream());
               String msg= FLUJO.readUTF();
               textArea.append("\nUser: " + txtmensaje.getText()+"\n");
               textArea.append(msg);
           }

       }catch (Exception e){

       }



    }
}
