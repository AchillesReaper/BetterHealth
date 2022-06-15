import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pop_Card {
    public JFrame frame = new JFrame("Add Medi Card for Customer");
    public JPanel pnlLeft = new JPanel();
    public JPanel pnlRight = new JPanel();
    public JPanel pnlContent;

    public JTextField cstmIdTF = new JTextField();
    public JTextField firstNameTF = new JTextField();
    public JTextField lastNameTF = new JTextField();
    public JTextField cardIdTF = new JTextField();
    public JTextField issuerTF = new JTextField();
    public JTextField coveredAmtTF = new JTextField();
    public MediCard mediCard;

    public Object[][] data;

    public Pop_Card(String customerID, String firstName, String lastName){
        frame.setBounds(0,0,510,420);
        frame.setLayout(null);
        frame.setResizable(false);

        constructLeftPanel(customerID,firstName,lastName);
        constructRightPanel(customerID);
        constructContentPanel(customerID);

        frame.add(pnlLeft);
        frame.add(pnlRight);
        frame.add(pnlContent);
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

        String[] tableTitle = {"Customer ID", "Card ID", "Issuer","Covered Amount"};
        Object[][] data = DB_CRUD.searchCard(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null, "This customer does not have a medi card yet","Note!",JOptionPane.INFORMATION_MESSAGE);
        }
        JTable cardTable = new JTable(data,tableTitle);
        cardTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        cardTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JScrollPane scrollPan = new JScrollPane(cardTable);
        scrollPan.setBounds(10,40,470,120);
        cardTable.setFillsViewportHeight(true);

        pnlContent.add(titleLabel);
        pnlContent.add(scrollPan);

    }

    public void constructLeftPanel(String customerID, String firstName, String lastName){
        pnlLeft.setBounds(10,200,240,180);
        pnlLeft.setLayout(null);
        pnlLeft.setBackground(Color.lightGray);

        JLabel titleLabel = new JLabel("Customer");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel cstmIdLabel = new JLabel("Customer ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");

        cstmIdTF.setText(customerID);
        cstmIdTF.setEditable(false);
        firstNameTF.setText(firstName);
        firstNameTF.setEditable(false);
        lastNameTF.setText(lastName);
        lastNameTF.setEditable(false);

        titleLabel.setBounds(10,10,100,30);
        cstmIdLabel.setBounds(10,40,100,30);
        firstNameLabel.setBounds(10,70,100,30);
        lastNameLabel.setBounds(10,100,100,30);
        cstmIdTF.setBounds(100,40,100,30);
        firstNameTF.setBounds(100,70,100,30);
        lastNameTF.setBounds(100,100,100,30);

        pnlLeft.add(titleLabel);
        pnlLeft.add(cstmIdLabel);
        pnlLeft.add(cstmIdTF);
        pnlLeft.add(firstNameLabel);
        pnlLeft.add(firstNameTF);
        pnlLeft.add(lastNameLabel);
        pnlLeft.add(lastNameTF);
    }
    public void constructRightPanel(String customerID){
        pnlRight.setBounds(260,200,240,180);
        pnlRight.setLayout(null);
        pnlRight.setBackground(Color.lightGray);

        JLabel titleLabel = new JLabel("New Card Information");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel issuerLabel = new JLabel("Card Issuer:");
        JLabel cardIdLabel = new JLabel("Card ID:");
        JLabel cAmtLabel = new JLabel("Covered Amt:");

        JButton addNewCard = new JButton("Add Card");
        addNewCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newCardValidation(customerID)){
                DB_CRUD.addCardToDB(mediCard);
                }
                constructContentPanel(customerID);
                frame.add(pnlContent);
                frame.setVisible(true);

            }
        });

        titleLabel.setBounds(10,10,160,30);
        issuerLabel.setBounds(10,40,100,30);
        cardIdLabel.setBounds(10,70,100,30);
        cAmtLabel.setBounds(10,100,100,30);
        issuerTF.setBounds(110,40,120,30);
        cardIdTF.setBounds(110,70,120,30);
        coveredAmtTF.setBounds(110,100,120,30);
        addNewCard.setBounds(60,140,120,30);

        pnlRight.add(titleLabel);
        pnlRight.add(issuerLabel);
        pnlRight.add(issuerTF);
        pnlRight.add(cardIdLabel);
        pnlRight.add(cardIdTF);
        pnlRight.add(cAmtLabel);
        pnlRight.add(coveredAmtTF);
        pnlRight.add(addNewCard);

    }

    public Boolean newCardValidation(String customerID){
        if (cardIdTF.getText().length()<2 || issuerTF.getText().length()<2){
            JOptionPane.showMessageDialog(null,"Please check following input:\nCard ID \nIssuer","Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            mediCard = new MediCard(customerID,cardIdTF.getText(), issuerTF.getText(),coveredAmtTF.getText());
            return true;
        }
    }

}
