import javax.swing.*;
import java.awt.*;

public class Pop_Card {
    public JFrame frame = new JFrame();
    public JPanel pnlLeft = new JPanel();
    public JPanel pnlRight = new JPanel();
    public JPanel pnlContnet = new JPanel();

    public JTextField cstmIdTF = new JTextField();
    public JTextField firstNameTF = new JTextField();
    public JTextField lastNameTF = new JTextField();
    public JTextField cardIdTF = new JTextField();
    public JTextField issuerTF = new JTextField();
    public JComboBox holderCB;

    public Object[][] data;

    public Pop_Card(String customerID, String firstName, String lastName){
        frame.setBounds(0,0,520,480);
        frame.setLayout(null);

        constructLeftPanel(customerID,firstName,lastName);
        constructRightPanel(customerID);
        constructContentPanel("select * from cards");

        frame.add(pnlLeft);
        frame.add(pnlRight);
        frame.add(pnlContnet);
        frame.setVisible(true);
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
        JLabel holderLabel = new JLabel("Holder:");

        String[] holderNo = {"","1","2","3","4","5","6","7","8"};
        holderCB = new JComboBox(holderNo);

        JButton addNewCard = new JButton("Add Card");

        titleLabel.setBounds(10,10,160,30);
        issuerLabel.setBounds(10,40,80,30);
        cardIdLabel.setBounds(10,70,80,30);
        holderLabel.setBounds(10,100,80,30);
        issuerTF.setBounds(100,40,120,30);
        cardIdTF.setBounds(100,70,120,30);
        holderCB.setBounds(100,100,120,30);
        addNewCard.setBounds(60,140,120,30);

        pnlRight.add(titleLabel);
        pnlRight.add(issuerLabel);
        pnlRight.add(issuerTF);
        pnlRight.add(cardIdLabel);
        pnlRight.add(cardIdTF);
        pnlRight.add(holderLabel);
        pnlRight.add(holderCB);
        pnlRight.add(addNewCard);

    }
    public void constructContentPanel(String queryString){
        pnlContnet.setBounds(10,10,490,180);
        pnlContnet.setLayout(null);
        pnlContnet.setBackground(Color.lightGray);

        JLabel titleLabel = new JLabel("Cards of the customer");
        titleLabel.setBounds(10,10,200,30);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        String[] tableTitle = {"Customer ID", "Card ID", "Issuer", "Holder"};
        Object[][] data = DB_CRUD.searchCard(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null, "The customer does not have a medi card yet","Note!",JOptionPane.INFORMATION_MESSAGE);
        }
        JTable cardTable = new JTable(data,tableTitle);
        cardTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        cardTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JScrollPane scrollPan = new JScrollPane(cardTable);
        scrollPan.setBounds(10,40,470,120);
        cardTable.setFillsViewportHeight(true);

        pnlContnet.add(titleLabel);
        pnlContnet.add(scrollPan);

    }
}
