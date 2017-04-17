package de.dissose17;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by marco on 4/17/17.
 */
public class Application {
    private JButton estateAgentButton;
    private JButton estatesButton;
    private JButton contractsButton;
    private JPanel Panel;

    public Application() {
        estateAgentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                showLogin();
            }
        });
        estatesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });
        contractsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });
    }

    public void showEstateAgentView(){

    }
    public void showLogin(){
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Application");
        frame.setContentPane(new Application().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
