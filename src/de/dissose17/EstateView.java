package de.dissose17;

import de.dissose17.data.Apartment;
import de.dissose17.data.Estate;
import de.dissose17.data.EstateAgent;
import de.dissose17.service.EstateAgentService;
import de.dissose17.service.EstateService;

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
public class EstateView {
    private JTable table1;
    private JPanel panel1;
    private JTextField cityTextField;
    private JTextField postalCodeTextField;
    private JTextField streetTextField;
    private JTextField streetnumberTextField;
    private JTextField squareAreaTextField;
    private JButton createButton;
    private JTextField tableCityTextField;
    private JTextField tablePostalCodeTextField;
    private JTextField tableStreetTextField;
    private JTextField tableStreetnumberTextField;
    private JTextField tableSquareAreaTextField;
    private JRadioButton apartmentRadioButton;
    private JRadioButton houseRadioButton;
    private JTextField floorTextField;
    private JTextField rentTextField;
    private JTextField roomsTextField;
    private JCheckBox kitchenCheckBox;
    private JCheckBox balconyCheckBox;
    private JTextField floorsTextField;
    private JTextField priceTextField;
    private JCheckBox gardenCheckBox;
    private JRadioButton apartmentRadioButton1;
    private JRadioButton houseRadioButton1;
    private JTextField tableFloorTextField;
    private JTextField tableRentTextField;
    private JTextField tableRoomsTextField;
    private JCheckBox kitchenCheckBox1;
    private JCheckBox balconyCheckBox1;
    private JTextField tableFloorsTextField;
    private JTextField tablePriceTextField;
    private JCheckBox gardenCheckBox1;
    private JButton updateButton;
    private JButton deleteButton;
    private EstateService EService;

    public EstateView() {
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                int row = table1.getSelectedRow();
                if(row < table1.getRowCount() && row >= 0) {
                    // mirror data
                    //tableIDTextField.setText((String) table1.getModel().getValueAt(row, 0));
                    //tableNameTextField.setText((String) table1.getModel().getValueAt(row, 1));
                    //tableAdressTextField.setText((String) table1.getModel().getValueAt(row, 2));
                    //tableLoginTextField.setText((String) table1.getModel().getValueAt(row, 3));
                    //tablePasswordTextField.setText((String) table1.getModel().getValueAt(row, 4));
                }
            }

        });
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //
                if (apartmentRadioButton.isSelected()) {
                    //TODO: Save aparment
                    //EService.saveApartment(new Apartment());
                } else if (houseRadioButton.isSelected()) {
                    //TODO: Save House
                    //EService.saveHouse(new House());
                }
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                    if(apartmentRadioButton.isSelected()){
                        //TODO: Save aparment
                        //EService.saveApartment(new Apartment());
                    } else if (houseRadioButton.isSelected()){
                        //TODO: Save House
                        //EService.saveHouse(new House());
                    }
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //TODO: DELETE
                //EService.deleteEstate(new Estate());
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        EService = new EstateService(new EstateAgentService());
        //Vector<EstateAgent> es = EAService.getAllEstateAgent();
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
        addDatatoTable(table1);
    }

    private void addDatatoTable(JTable table) {
        Vector<Estate> es = EService.getAllEstates();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Estate agent : es){
            model.addRow(new String[]{agent.getId().toString(), agent.getName(), agent.getAddress(), agent.getLogin(), agent.getPassword()});

        }
    }
    }
}
