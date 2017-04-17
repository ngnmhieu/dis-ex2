package de.dissose17;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordPasswordField;
    private char[] password;

    public Login() {
        password = "test".toCharArray();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if(Arrays.equals(this.password, passwordPasswordField.getPassword())) {
            System.out.println("Login successful!");
            dispose();
            showEstateAgentView();

        } else {
            System.out.println("wrong pw");
        }
    }

    public void showEstateAgentView(){
        new EstateAgentView();
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
