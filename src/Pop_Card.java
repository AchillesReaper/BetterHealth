import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pop_Card {
    public JFrame frame = new JFrame("Add Health Fund for Customer");
    public JPanel pnlContent, pnlLeft, pnlRight;
    public JTextField tfCustomerID = new JTextField();
    public JTextField tfFirstName = new JTextField();
    public JTextField tfLastName = new JTextField();
    public JTextField tfCardID = new JTextField();
    public JTextField tfIssuer = new JTextField();
    public JTable tbCard;
    public MediCard mediCard;



    public Pop_Card(String customerID, String firstName, String lastName){
        frame.setBounds(0,0,520,420);
        frame.setLayout(null);
        frame.setResizable(false);

        constructLeftPanel(customerID,firstName,lastName);
        constructRightPanel(customerID);
        constructContentPanel(customerID);

    }

    public void constructLeftPanel(String customerID, String firstName, String lastName){
        pnlLeft = new JPanel();
        pnlLeft.setBounds(10,200,240,180);
        pnlLeft.setLayout(null);
        pnlLeft.setBackground(Color.lightGray);

        JLabel titleLabel = new JLabel("Customer");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel cstmIdLabel = new JLabel("Customer ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");

        tfCustomerID.setText(customerID);
        tfCustomerID.setEditable(false);
        tfFirstName.setText(firstName);
        tfFirstName.setEditable(false);
        tfLastName.setText(lastName);
        tfLastName.setEditable(false);

        titleLabel.setBounds(10,10,100,30);
        cstmIdLabel.setBounds(10,40,100,30);
        firstNameLabel.setBounds(10,70,100,30);
        lastNameLabel.setBounds(10,100,100,30);
        tfCustomerID.setBounds(100,40,100,30);
        tfFirstName.setBounds(100,70,100,30);
        tfLastName.setBounds(100,100,100,30);

        pnlLeft.add(titleLabel);
        pnlLeft.add(cstmIdLabel);
        pnlLeft.add(tfCustomerID);
        pnlLeft.add(firstNameLabel);
        pnlLeft.add(tfFirstName);
        pnlLeft.add(lastNameLabel);
        pnlLeft.add(tfLastName);

        frame.add(pnlLeft);
        frame.setVisible(true);
    }

    public void constructRightPanel(String customerID){
        if(pnlRight != null){
            frame.remove(pnlRight);
        }
        pnlRight = new JPanel();
        pnlRight.setBounds(260,200,240,180);
        pnlRight.setLayout(null);
        pnlRight.setBackground(Color.lightGray);

        JLabel titleLabel = new JLabel("New Card Information");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel lbIssuer = new JLabel("Card Issuer:");
        JLabel lbCardID = new JLabel("Card ID:");

        JButton btnAddCard = new JButton("Add Card");
        btnAddCard.addActionListener(e -> {
            if(newCardValidation(customerID)){
                DB_CRUD.addCardToDB(mediCard);
            }
            constructContentPanel(customerID);
            constructRightPanel(customerID);
            frame.add(pnlRight);
            frame.add(pnlContent);
            frame.setVisible(true);

        });

        JButton btnDeleteCard = new JButton("Delete Card");
        btnDeleteCard.addActionListener(e -> {
            if (tfCardID.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Please select a card","Reminder",JOptionPane.INFORMATION_MESSAGE);
            } else if (tfCardID.getText().equals("cash")){
                JOptionPane.showMessageDialog(null,"Cash is a default payment method.\nCannot be deleted!","Reminder",JOptionPane.INFORMATION_MESSAGE);
            } else {
                int input = JOptionPane.showConfirmDialog(null,"Confirm delete card? (ID = "+tfCardID.getText()+")?");
                if (input == 0){
                    DB_CRUD.deleteCard(customerID, tfCardID.getText());
                    constructContentPanel(customerID);
                    frame.add(pnlContent);
                    frame.setVisible(true);
                }
            }
        });

        titleLabel.setBounds(10,10,160,30);
        lbIssuer.setBounds(10,40,100,30);
        lbCardID.setBounds(10,70,100,30);
        tfIssuer.setBounds(110,40,120,30);
        tfCardID.setBounds(110,70,120,30);
        btnAddCard.setBounds(60,110,120,30);
        btnDeleteCard.setBounds(60,140,120,30);


        pnlRight.add(titleLabel);
        pnlRight.add(lbIssuer);
        pnlRight.add(lbCardID);
        pnlRight.add(tfIssuer);
        pnlRight.add(tfCardID);
        pnlRight.add(btnAddCard);
        pnlRight.add(btnDeleteCard);

        frame.add(pnlRight);
        frame.setVisible(true);
    }

    public void constructContentPanel(String customerID){
        if (pnlContent != null){
            frame.remove(pnlContent);
        }
        pnlContent = new JPanel();
        String queryString = "select * from cards where customerID = '" + customerID + "'";
        pnlContent.setBounds(10,10,490,180);
        pnlContent.setLayout(null);
        pnlContent.setBackground(Color.lightGray);

        JLabel titleLabel = new JLabel("Cards of the customer");
        titleLabel.setBounds(10,10,200,30);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        String[] tableTitle = {"Customer ID", "Card ID", "Issuer"};
        Object[][] data = DB_CRUD.searchCard(queryString);
        if (data.length == 0){
            mediCard = new MediCard(customerID,"cash", "CASH");
            DB_CRUD.addCardToDB(mediCard);
            data = DB_CRUD.searchCard(queryString);
        }
        tbCard = new JTable(data,tableTitle);
        tbCard.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tbCard.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JScrollPane scpCard = new JScrollPane(tbCard);
        scpCard.setBounds(10,40,470,120);
        tbCard.setFillsViewportHeight(true);
        tbCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbCard.getSelectedRow();
//                String customerID = (tbCard.getModel().getValueAt(row,0)).toString();
                tfCardID.setText((tbCard.getModel().getValueAt(row,1)).toString());
                tfIssuer.setText((tbCard.getModel().getValueAt(row,2)).toString());
                constructRightPanel(customerID);
            }
        });

        pnlContent.add(titleLabel);
        pnlContent.add(scpCard);

        frame.add(pnlContent);
        frame.setVisible(true);
    }

    public Boolean newCardValidation(String customerID){
        if (tfCardID.getText().length()<2 || tfIssuer.getText().length()<2){
            JOptionPane.showMessageDialog(null,"Please check following input:\nCard ID \nIssuer","Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            mediCard = new MediCard(customerID,tfCardID.getText(), tfIssuer.getText().toUpperCase());
            System.out.println(tfIssuer.getText().toUpperCase());
            return true;
        }
    }

}
