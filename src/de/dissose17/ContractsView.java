package de.dissose17;

import de.dissose17.data.*;
import de.dissose17.service.ContractService;
import de.dissose17.service.EstateService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

/**
 * Created by marco on 4/18/17.
 */
public class ContractsView {
    private JTextField placeTextField;
    private JTextField startDateTextField;
    private JTextField durationTextField;
    private JRadioButton tenancyRadioButton;
    private JRadioButton purchaseRadioButton;
    private JTextField noOfInstallmentsTextField;
    private JTextField additionalCostTextField;
    private JTextField interestRateTextField;
    private JTable table1;
    private JButton signButton;
    private JTable table2;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField firstNameTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField dateTextField;
    private JComboBox comboBox2;
    private JButton createButton;
    private JComboBox comboBox3;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private ContractService CService;
    private EstateService EService;

    public ContractsView(ContractService cs, EstateService es) {
        CService = cs;
        EService = es;
        JFrame frame = new JFrame("ContractsView");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        addPersonData(comboBox2);
        addApartmentData(comboBox3);
        addHouseData(comboBox1);
        //addDatatoTablePurchase(table1);
        //addDatatoTableTenancy(table2);
        signButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                // CREATE CONTRACT
                if(purchaseRadioButton.isSelected()) {
                    House h = EService.getHouse(Integer.parseInt(getIdFromBox(comboBox1)));
                    Person p = CService.getPerson(Integer.parseInt(getIdFromBox(comboBox2)));
                    PurchaseContract c = new PurchaseContract(parseDateString(dateTextField.getText()), placeTextField.getText(),
                            Integer.parseInt(noOfInstallmentsTextField.getText()), Double.parseDouble(interestRateTextField.getText()),
                            h, p);
                    CService.savePurchaseContract(c);
                    addDatatoTablePurchase(table1);
                } else if (tenancyRadioButton.isSelected()){
                    Apartment a = EService.getApartment(Integer.parseInt(getIdFromBox(comboBox3)));
                    Person p = CService.getPerson(Integer.parseInt(getIdFromBox(comboBox2)));
                    TenancyContract c = new TenancyContract(parseDateString(dateTextField.getText()), placeTextField.getText(),
                            parseDateString(startDateTextField.getText()), Integer.parseInt(durationTextField.getText()),
                            Double.parseDouble(additionalCostTextField.getText()), p, a);
                    CService.saveTenancyContract(c);
                    addDatatoTableTenancy(table2);
                }
            }
        });
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                // CREATE PERSON
                CService.addPerson(firstNameTextField.getText(), nameTextField.getText(), addressTextField.getText());
                addPersonData(comboBox2);
            }
        });
    }

    private Date parseDateString(String s){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try{
            Date result =  new Date(df.parse(s).getTime());
            return result;
        }
        catch(ParseException e){
            e.printStackTrace();
            // log the exception
            throw new RuntimeException(e);
        }
    }

    private String getIdFromBox(JComboBox box){
        String item = box.getSelectedItem().toString();
        item = item.substring(item.indexOf("(") + 1);
        item = item.substring(0, item.indexOf(")"));
        return item;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        // Person ComboBox
        DefaultComboBoxModel Persons = new DefaultComboBoxModel();
        comboBox2 = new JComboBox(Persons);

        // House Box
        DefaultComboBoxModel Houses = new DefaultComboBoxModel();
        comboBox1 = new JComboBox(Houses);

        // Apartment Box
        DefaultComboBoxModel Apartments = new DefaultComboBoxModel();
        comboBox3 = new JComboBox(Apartments);

        // Purchase Table
        Vector<String> cols = new Vector<>();
        cols.add("ContractNo");
        cols.add("Date");
        cols.add("Place");
        cols.add("Installments");
        cols.add("Rate");


        // Add Data to Table
        TableModel m = new DefaultTableModel(cols, 0);
        table1 = new JTable(m){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        addDatatoTablePurchase(table1);


        // Tenancy Table
        cols = new Vector<>();
        cols.add("ContractNo");
        cols.add("Date");
        cols.add("Place");
        cols.add("Start");
        cols.add("Duration");
        cols.add("AdditionalCost");

        // Add Data to Table
        m = new DefaultTableModel(cols, 0);
        table2 = new JTable(m){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        addDatatoTableTenancy(table2);
    }

    public void addHouseData(JComboBox box){
        List<House> houses = EService.getAllHouses();
        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        model.removeAllElements();
        for(House house : houses){
            model.addElement(house.getStreet() + "("+ house.getId() +")");
        }
    }

    public void addApartmentData(JComboBox box){
        List<Apartment> apartments = EService.getAllApartments();
        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        model.removeAllElements();
        for(Apartment apartment : apartments){
            model.addElement(apartment.getStreet() + "("+ apartment.getId() +")");
        }
    }

    public void addPersonData(JComboBox box){
        List<Person> persons = CService.getAllPerson();
        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        model.removeAllElements();
        for(Person person : persons){
            model.addElement(person.getFirstName() + "("+ person.getId() +")");
        }
    }

    public void addDatatoTablePurchase(JTable table){
        List<PurchaseContract> cs = CService.getPurchaseContracts();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(PurchaseContract purchase : cs){
            model.addRow(new String[]{String.valueOf(purchase.getContractNo()), purchase.getDate().toString(), String.valueOf(purchase.getPlace()), String.valueOf(purchase.getNumInstallments()),
                    String.valueOf(purchase.getInterestRate())});

        }
    }

    public void addDatatoTableTenancy(JTable table){
        List<TenancyContract> cs = CService.getTenancyContracts();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(TenancyContract tenancy : cs){
            model.addRow(new String[]{String.valueOf(tenancy.getContractNo()), tenancy.getDate().toString(), String.valueOf(tenancy.getPlace()),
                    tenancy.getStartDate().toString(), String.valueOf(tenancy.getDuration()), String.valueOf(tenancy.getAdditionalCosts())});

        }
    }
}
