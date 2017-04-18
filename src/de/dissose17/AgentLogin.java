package de.dissose17;

import de.dissose17.service.EstateAgentService;
import de.dissose17.service.EstateService;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class AgentLogin extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private EstateAgentService EAService;
    private EstateService EService;

    public AgentLogin(EstateAgentService ea, EstateService es) {
        this.EAService = ea;
        this.EService = es;
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
        EstateAgentService EAService = new EstateAgentService();
        System.out.println(usernameTextField.getText());
        System.out.println(new String(passwordPasswordField.getPassword()));
        boolean auth = EAService.authenticate(usernameTextField.getText(), new String(passwordPasswordField.getPassword()));
        if(auth) {
            System.out.println("Login successful!");
            dispose();
            showEstateView(EAService, EService);

        } else {
            System.out.println("wrong pw");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void showEstateView(EstateAgentService ea, EstateService es){
        new EstateView(ea, es);
    }
}
