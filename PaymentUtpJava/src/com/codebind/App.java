package com.codebind;

import com.company.libUtp;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class App {
    private JTextField tokenField;
    private JTextField portField;
    private JLabel amountLabel;
    private JLabel balanceLabel;
    private JButton updateButton;
    private JPanel panelMain;
    private JLabel pkLabel;
    private JTextField pkField;
    private JTextField muchField;
    private JButton sendButton;


    public App() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                libUtp kek = new libUtp();

               String PORTIO = portField.getText();
                String TOKENIO = tokenField.getText();

                // kek.port = "20000";
                // kek.token = "0E71EEDD23090DD7399878D048F201F5";

                kek.port = PORTIO;
                kek.token = TOKENIO;

                String RESOLT = null;

                //last contact PK
                JSONObject PKeyJO = null; //full json string
                try {
                    PKeyJO = new JSONObject(kek.getOwnContact());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }



                System.out.println(PKeyJO); //CHECK FULL

                JSONObject PKeyOut; // result string

                PKeyOut = PKeyJO.getJSONObject("result");

                System.out.println(PKeyOut); // chek result

                // :)))

                //SONObject PKFinal = new JSONObject(PKeyOut);

                String PKey = PKeyOut.getString("pk");

                System.out.println(PKey);

               pkLabel.setText(PKey);

               //balance
               JSONObject BJO = null;
                try {
                    BJO = new JSONObject(kek.getBalance());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println(BJO);

             String balancio = BJO.get("result").toString();

                System.out.println(balancio);

                amountLabel.setText(balancio);

            }
        });


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                libUtp kek = new libUtp();



                String PORTIO = portField.getText();
                String TOKENIO = tokenField.getText();

                String toPK = pkField.getText();
                String toAm= muchField.getText();



              //  kek.port = "20000";
               //  kek.token = "0E71EEDD23090DD7399878D048F201F5";

              kek.port = PORTIO;
                kek.token = TOKENIO;

                String RESOLT = null;

                try {
                    kek.sendPayment("1",toPK, toAm,  "1");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                JSONObject BJO = null;
                try {
                    BJO = new JSONObject(kek.getBalance());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println(BJO);

                String balancio = BJO.get("result").toString();

                System.out.println(balancio);

                amountLabel.setText(balancio);




            }
        });
    }

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();


        frame.setSize(1080,130);
        frame.setVisible(true);




    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}
