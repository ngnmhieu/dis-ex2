package de.dissose17;

import de.dissose17.data.Apartment;
import de.dissose17.data.Estate;
import de.dissose17.data.EstateAgent;
import de.dissose17.data.House;
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
    private JTextField tableIDTextField;
    private JTable table2;
    private EstateService EService;
    private EstateAgentService EAService;

    public EstateView(EstateAgentService ea, EstateService es) {
        EAService = ea;
        EService = es;
        JFrame frame = new JFrame("EstateView");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                int row = table1.getSelectedRow();
                if(row < table1.getRowCount() && row >= 0) {
                    // mirror data
                    apartmentRadioButton1.setSelected(true);
                    tableIDTextField.setText((String) table1.getModel().getValueAt(row, 0));
                    tableCityTextField.setText((String) table1.getModel().getValueAt(row, 1));
                    tablePostalCodeTextField.setText((String) table1.getModel().getValueAt(row, 2));
                    tableStreetTextField.setText((String) table1.getModel().getValueAt(row, 3));
                    tableStreetnumberTextField.setText((String) table1.getModel().getValueAt(row, 4));
                    tableSquareAreaTextField.setText((String) table1.getModel().getValueAt(row, 5));
                    tableFloorTextField.setText((String) table1.getModel().getValueAt(row, 6));
                    tableRentTextField.setText((String) table1.getModel().getValueAt(row, 7));
                    tableRoomsTextField.setText((String) table1.getModel().getValueAt(row, 8));
                    balconyCheckBox1.setSelected(Boolean.parseBoolean((String)table1.getModel().getValueAt(row, 9)));
                    kitchenCheckBox1.setSelected(Boolean.parseBoolean((String)table1.getModel().getValueAt(row, 10)));

                }
            }

        });

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
                int row = table2.getSelectedRow();
                if(row < table2.getRowCount() && row >= 0) {
                    // mirror data
                    houseRadioButton1.setSelected(true);
                    tableIDTextField.setText((String) table2.getModel().getValueAt(row, 0));
                    tableCityTextField.setText((String) table2.getModel().getValueAt(row, 1));
                    tablePostalCodeTextField.setText((String) table2.getModel().getValueAt(row, 2));
                    tableStreetTextField.setText((String) table2.getModel().getValueAt(row, 3));
                    tableStreetnumberTextField.setText((String) table2.getModel().getValueAt(row, 4));
                    tableSquareAreaTextField.setText((String) table2.getModel().getValueAt(row, 5));
                    tableFloorsTextField.setText((String) table2.getModel().getValueAt(row, 6));
                    tablePriceTextField.setText((String) table2.getModel().getValueAt(row, 7));
                    gardenCheckBox1.setSelected(Boolean.parseBoolean((String) table2.getModel().getValueAt(row, 8)));

                }
            }

        });
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //
                if (apartmentRadioButton.isSelected()) {
                    //Create Apartment
                    EService.saveApartment(new Apartment(null, cityTextField.getText(), Integer.parseInt(postalCodeTextField.getText()),
                            streetTextField.getText(), Integer.parseInt(streetnumberTextField.getText()), Integer.parseInt(squareAreaTextField.getText()),
                            EAService.getCurrentAgent(), Integer.parseInt(floorTextField.getText()), Double.parseDouble(rentTextField.getText()), Integer.parseInt(roomsTextField.getText()), balconyCheckBox.isSelected(),kitchenCheckBox.isSelected()));
                    addDatatoTableApartment(table1);
                } else if (houseRadioButton.isSelected()) {
                    // Create House
                    EService.saveHouse(new House(null, cityTextField.getText(), Integer.parseInt(postalCodeTextField.getText()),
                            streetTextField.getText(), Integer.parseInt(streetnumberTextField.getText()), Integer.parseInt(squareAreaTextField.getText()),
                            EAService.getCurrentAgent(), Integer.parseInt(floorsTextField.getText()), Double.parseDouble(priceTextField.getText()),
                            gardenCheckBox.isSelected()));
                    addDatatoTableHouse(table2);
                }
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                    if(apartmentRadioButton1.isSelected()){
                        EService.saveApartment(new Apartment(Integer.parseInt(tableIDTextField.getText()), tableCityTextField.getText(), Integer.parseInt(tablePostalCodeTextField.getText()),
                                tableStreetTextField.getText(), Integer.parseInt(tableStreetnumberTextField.getText()), Integer.parseInt(tableSquareAreaTextField.getText()),
                                EAService.getCurrentAgent(), Integer.parseInt(tableFloorTextField.getText()), Double.parseDouble(tableRentTextField.getText()), Integer.parseInt(tableRoomsTextField.getText()), balconyCheckBox1.isSelected(),kitchenCheckBox1.isSelected()));
                        addDatatoTableApartment(table1);
                    } else if (houseRadioButton1.isSelected()){
                        EService.saveHouse(new House(Integer.parseInt(tableIDTextField.getText()), tableCityTextField.getText(), Integer.parseInt(tablePostalCodeTextField.getText()),
                                tableStreetTextField.getText(), Integer.parseInt(tableStreetnumberTextField.getText()), Integer.parseInt(tableSquareAreaTextField.getText()),
                                EAService.getCurrentAgent(), Integer.parseInt(tableFloorsTextField.getText()), Double.parseDouble(tablePriceTextField.getText()),
                                gardenCheckBox1.isSelected()));
                        addDatatoTableHouse(table2);
                    }
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(apartmentRadioButton1.isSelected()){
                    EService.deleteEstate(EService.getApartment(Integer.parseInt(tableIDTextField.getText())));
                    addDatatoTableApartment(table1);
                } else if (houseRadioButton1.isSelected()){
                    EService.deleteEstate(EService.getHouse(Integer.parseInt(tableIDTextField.getText())));
                    addDatatoTableHouse(table2);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Vector<String> cols = new Vector<>();
        cols.add("ID");
        cols.add("City");
        cols.add("Postal Code");
        cols.add("Street");
        cols.add("Streetnr.");
        cols.add("Square Area");
        cols.add("Floor");
        cols.add("Rent");
        cols.add("Rooms");
        cols.add("Balcony");
        cols.add("Kitchen");


        // Add Data to Table
        TableModel m = new DefaultTableModel(cols, 0);
        table1 = new JTable(m){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        addDatatoTableApartment(table1);

        cols = new Vector<>();
        cols.add("ID");
        cols.add("City");
        cols.add("Postal Code");
        cols.add("Street");
        cols.add("Streetnr.");
        cols.add("Square Area");
        cols.add("Floors");
        cols.add("Price");
        cols.add("Garden");


        // Add Data to Table
        m = new DefaultTableModel(cols, 0);
        table2 = new JTable(m){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        addDatatoTableHouse(table2);
    }

    private void addDatatoTableApartment(JTable table) {
        Vector<Apartment> as = EService.getAllApartmentsForAgent(EAService.getCurrentAgent().getId());
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for(Apartment apartment : as){
            model.addRow(new String[]{apartment.getId().toString(), apartment.getCity(), String.valueOf(apartment.getPostalCode()), apartment.getStreet(), String.valueOf(apartment.getStreetNumber()),
                    String.valueOf(apartment.getSquareArea()), String.valueOf(apartment.getFloor()), String.valueOf(apartment.getRentPrice()), String.valueOf(apartment.getNumRooms()),
                    String.valueOf(apartment.hasBalcony()), String.valueOf(apartment.hasBuiltInKitchen())});

        }
    }

    private void addDatatoTableHouse(JTable table) {
        Vector<House> hs = EService.getAllHousesForAgent(EAService.getCurrentAgent().getId());
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(House house : hs){
            model.addRow(new String[]{house.getId().toString(), house.getCity(), String.valueOf(house.getPostalCode()), house.getStreet(), String.valueOf(house.getStreetNumber()),
                    String.valueOf(house.getSquareArea()), String.valueOf(house.getNumFloors()), String.valueOf(house.getPrice()), String.valueOf(house.hasGarden())});

        }
    }
}
