package de.dissose17;

import de.dissose17.data.EstateAgent;
import de.dissose17.service.EstateAgentService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by marco on 4/17/17.
 */
public class EstateAgentView {
    private JPanel panel1;
    private JTextField nameTextField;
    private JTextField adressTextField;
    private JTextField loginTextField;
    private JPasswordField passwordPasswordField;
    private JButton createButton;
    private JTable table1;
    private JTextField tableNameTextField;
    private JTextField tableAdressTextField;
    private JTextField tableLoginTextField;
    private JTextField tablePasswordTextField;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField tableIDTextField;

    private EstateAgentService EAService;

    public EstateAgentView() {
        EAService = new EstateAgentService();
        JFrame frame = new JFrame("EstateAgentView");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                EAService.createAccount(nameTextField.getText(), adressTextField.getText(), loginTextField.getText(), passwordPasswordField.getPassword().toString());
            }
        });
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                int row = table1.getSelectedRow();
                tableIDTextField.setText((String) table1.getModel().getValueAt(row, 0));
                tableNameTextField.setText((String) table1.getModel().getValueAt(row, 1));
                tableAdressTextField.setText((String) table1.getModel().getValueAt(row, 2));
                tableLoginTextField.setText((String) table1.getModel().getValueAt(row, 3));
                tablePasswordTextField.setText((String) table1.getModel().getValueAt(row, 4));
            }

        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                EAService.save(new EstateAgent(Integer.parseInt(tableIDTextField.getText()), tableNameTextField.getText(), tableAdressTextField.getText(), tableLoginTextField.getText(), tablePasswordTextField.getText()));

            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                EAService.delete(new EstateAgent(Integer.parseInt(tableIDTextField.getText()), tableNameTextField.getText(), tableAdressTextField.getText(), tableLoginTextField.getText(), tablePasswordTextField.getText()));
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        EAService = new EstateAgentService();
        Vector<EstateAgent> es = EAService.getAllEstateAgent();
        Vector<String> cols = new Vector<>();
        cols.add("ID");
        cols.add("Name");
        cols.add("Address");
        cols.add("Login");
        cols.add("Password");
        // Add Data to Table
        TableModel m = new DefaultTableModel(cols, 0);
        table1 = new JTable(m){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        for(EstateAgent agent : es){
            model.addRow(new String[]{agent.getId().toString(), agent.getName(), agent.getAddress(), agent.getLogin(), agent.getPassword()});

        }
    }
}
