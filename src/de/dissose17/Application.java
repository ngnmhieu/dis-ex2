package de.dissose17;

import de.dissose17.service.EstateAgentService;
import de.dissose17.service.EstateService;

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
    public EstateAgentService EAService;
    public EstateService EService;

    public Application() {
        EAService = new EstateAgentService();
        EService = new EstateService(EAService);
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
                showAgentLogin();
            }
        });
        contractsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });
    }

    public void showLogin(){
        Login dialog = new Login(EAService, EService);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void showAgentLogin(){
        AgentLogin dialog = new AgentLogin(EAService, EService);
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
