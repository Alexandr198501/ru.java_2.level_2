package ru.java2.lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


// Это я тренировался, можно не смотреть
public class EchoClient extends JFrame {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8181;

    private JTextArea chatAria;
    private JTextField msgInputField;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        final String END = "/end";
        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equalsIgnoreCase(END)) {
                        break;
                    }
                    chatAria.append(strFromServer);
                    chatAria.append("\n");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        ).start();
    }

    public void closeConnection(){
        try{
            in.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        if(!msgInputField.getText().trim().isEmpty()){
            try{
                out.writeUTF(msgInputField.getText());
                msgInputField.setText("");
                msgInputField.grabFocus();
            }catch (IOException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    public void prepareGUI(){
        setBounds(600, 300, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatAria = new JTextArea();
        chatAria.setEditable(false);
        chatAria.setLineWrap(true);
        add(new JScrollPane(chatAria), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        btnSendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        msgInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try{
                    out.writeUTF("/end");
                    closeConnection();
                }catch (IOException exc){
                    exc.printStackTrace();
                }
            }
        });
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EchoClient();
            }
        });
    }
}
