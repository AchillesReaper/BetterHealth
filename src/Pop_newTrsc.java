import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class Pop_newTrsc {
    public JFrame frame = new JFrame("New Transaction");
    public JPanel pnlCustomerInfo = new JPanel(null);
    public JPanel pnlTrscTime = new JPanel(null);
    public JPanel pnlServiceList = new JPanel(null);
    public JPanel pnlMediCard = new JPanel(null);
    public JPanel pnlPreview;
    public String dateY = "";
    public String dateM = "";
    public String dateD = "";
    public String serviceID = "";
    public String serviceName = "";
    public String serviceContent = "";
    public String servicePrice = "";
    public String cardID = "";
    public String cardCover = "";
    public String cashPayment = "";

    public String customerID, firstName, lastName;

    public Pop_newTrsc(String customerID){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.customerID = customerID;
        Object[][] subData;
        String queryString;

        queryString = "select * from customer WHERE customerID = '" + customerID + "'";
        subData = DB_CRUD.searchCustomer(queryString);
        firstName = (String) subData[0][1];
        lastName = (String) subData[0][2];


        frame.setBounds(0,0,660,620);
        frame.setLayout(null);
//        frame.setResizable(false);
        constCustomerInfoPanel();
        constTrscTimePanel();
        constServiceListPanel();
        constMediCardPanel();
        constPreviewPanel();
    }
    public void constCustomerInfoPanel(){
//        pnlCustomerInfo = new JPanel(null);
        pnlCustomerInfo.setBounds(10,10,640,60);
        pnlCustomerInfo.setBackground(Color.lightGray);

        JLabel lbCstmTitle = new JLabel("Selected Customer");
        lbCstmTitle.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel lbCustomerID = new JLabel("Customer ID:");
        JLabel lbFirstName = new JLabel("First Name:");
        JLabel lbLastName = new JLabel("Last Name:");

        JTextField tfCustomerID = new JTextField(customerID);
        JTextField tfFirstName = new JTextField(firstName);
        JTextField tfLastName = new JTextField(lastName);

        tfCustomerID.setEditable(false);
        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);

        lbCstmTitle.setBounds(10,5,160,25);
        lbCustomerID.setBounds(10,25,90,30);    //10 + 90 -> 100
        tfCustomerID.setBounds(100,25,110,30);  //-> 100 + 110 -> 210
        lbFirstName.setBounds(220,25,90,30);    //210 -> 220 + 90 ->310
        tfFirstName.setBounds(310,25,110,30);   //-> 310 + 110 -> 420
        lbLastName.setBounds(430,25,90,30);     //420 -> 430 + 90 ->520
        tfLastName.setBounds(520,25,110,30);    //->520

        pnlCustomerInfo.add(lbCstmTitle);
        pnlCustomerInfo.add(lbCustomerID);
        pnlCustomerInfo.add(lbFirstName);
        pnlCustomerInfo.add(lbLastName);
        pnlCustomerInfo.add(tfCustomerID);
        pnlCustomerInfo.add(tfFirstName);
        pnlCustomerInfo.add(tfLastName);

        frame.add(pnlCustomerInfo);
        frame.setVisible(true);
    }

    public void constTrscTimePanel(){
//        pnlTrscTime = new JPanel(null);
        pnlTrscTime.setBounds(10,75,640,60);
        pnlTrscTime.setBackground(Color.lightGray);

        JLabel lbDateTitle = new JLabel("Transaction Date");
        lbDateTitle.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel lbYear = new JLabel("Year:");
        JLabel lbMonth = new JLabel("Month:");
        JLabel lbDay = new JLabel("Day:");

        //JComboBox setting
        String[] dayCB = new String[32];
        String[] monthCB = new String[13];
        String[] yearCB = new String[101];
        dayCB[0] = "";
        monthCB[0] = "";
        yearCB[0] = "";
        for (int i = 1; i < 32; i++){
            dayCB[i] = Integer.toString(i);
        }
        for (int i = 1; i < 13; i++){
            monthCB[i] = Integer.toString(i);
        }
        for (int i = 1; i < 101; i++) {
            yearCB[i] = Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - i + 1);
        }
        JComboBox cbDateD = new JComboBox(dayCB);
        JComboBox cbDateM = new JComboBox(monthCB);
        JComboBox cbDateY = new JComboBox(yearCB);
        cbDateD.addActionListener(e -> {
            dateD = (String) cbDateD.getSelectedItem();
            constPreviewPanel();
        });
        cbDateM.addActionListener(e -> {
            dateM = (String) cbDateM.getSelectedItem();
            constPreviewPanel();
        });
        cbDateY.addActionListener(e -> {
            dateY = (String) cbDateY.getSelectedItem();
            constPreviewPanel();
        });


        lbDateTitle.setBounds(10,5,160,25);
        lbDay.setBounds(10,25,90,30);    //10 + 90 -> 100
        cbDateD.setBounds(100,25,110,30);  //-> 100 + 110 -> 210
        lbMonth.setBounds(220,25,90,30);    //210 -> 220 + 90 ->310
        cbDateM.setBounds(310,25,110,30);   //-> 310 + 110 -> 420
        lbYear.setBounds(430,25,90,30);     //420 -> 430 + 90 ->520
        cbDateY.setBounds(520,25,110,30);    //->520

        pnlTrscTime.add(lbDateTitle);
        pnlTrscTime.add(lbDay);
        pnlTrscTime.add(lbMonth);
        pnlTrscTime.add(lbYear);
        pnlTrscTime.add(cbDateD);
        pnlTrscTime.add(cbDateM);
        pnlTrscTime.add(cbDateY);

        frame.add(pnlTrscTime);
        frame.setVisible(true);
    }

    public void constServiceListPanel(){
//        pnlServiceList = new JPanel(null);
        pnlServiceList.setBounds(10,140,350,250);
        pnlServiceList.setBackground(Color.lightGray);

        JLabel lbServiceTitle = new JLabel("Service Available");
        lbServiceTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbServiceTitle.setBounds(10,0,200,30);

        String queryString = "select * from services WHERE availability = 'Yes'";
        Object[][] data = DB_CRUD.searchService(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"No service is currently available.","Note",JOptionPane.PLAIN_MESSAGE);
        }

        String[] colName = {"ID", "Name", "Content","Price"};
        JTable tbExtService = new JTable(data, colName);
        tbExtService.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbExtService.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbExtService.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbExtService.getSelectedRow();
                serviceID = tbExtService.getModel().getValueAt(row, 0).toString();
                serviceName = tbExtService.getModel().getValueAt(row, 1).toString();
                serviceContent = tbExtService.getModel().getValueAt(row, 2).toString();
                servicePrice = tbExtService.getModel().getValueAt(row, 3).toString();
                constPreviewPanel();
            }
        });
        JScrollPane scpExtService = new JScrollPane(tbExtService);
        scpExtService.setBounds(10,30,330,210);
        tbExtService.setFillsViewportHeight(true);

        pnlServiceList.add(lbServiceTitle);
        pnlServiceList.add(scpExtService);

        frame.add(pnlServiceList);
        frame.setVisible(true);
    }

    public void constMediCardPanel(){
//        pnlMediCard = new JPanel(null);
        pnlMediCard.setBounds(10,395,350,180);
        pnlMediCard.setBackground(Color.lightGray);

        JLabel lbMediTitle = new JLabel("Medi Cards of this Customer");
        lbMediTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbMediTitle.setBounds(10,0,200,30);

        String[] tableTitle = {"Cstm ID", "Card ID", "Issuer","Covered $"};
        String queryString = "select * from cards where customerID = '" + customerID + "'";
        Object[][] data = DB_CRUD.searchCard(queryString);
        if (data.length == 0){
            cardID = "--";
            cardCover = "0";
            JOptionPane.showMessageDialog(null, "This customer does not have a medi card yet","Note!",JOptionPane.INFORMATION_MESSAGE);
        }
        JTable tbCard = new JTable(data,tableTitle);
        tbCard.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tbCard.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbCard.getSelectedRow();
                cardID = tbCard.getModel().getValueAt(row,1).toString();
                cardCover = tbCard.getModel().getValueAt(row,3).toString();
                constPreviewPanel();
            }
        });

        JScrollPane scpCard = new JScrollPane(tbCard);
        scpCard.setBounds(10,30,330,140);
        tbCard.setFillsViewportHeight(true);


        pnlMediCard.add(lbMediTitle);
        pnlMediCard.add(scpCard);

        frame.add(pnlMediCard);
        frame.setVisible(true);
    }
    public void constPreviewPanel(){
        if(pnlPreview != null){frame.remove(pnlPreview);}
        pnlPreview = new JPanel(null);
        pnlPreview.setBounds(365,140,285,435);
        pnlPreview.setBackground(Color.lightGray);

        JLabel lbPreviewTitle = new JLabel("Transaction Preview");
        lbPreviewTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbPreviewTitle.setBounds(10,0,200,30);

        JLabel lbDate = new JLabel("Date:");
        JLabel lbCustomerID = new JLabel("CustomerID:");
        JLabel lbFirstName = new JLabel("Fist Name:");
        JLabel lbLastName = new JLabel("LastName:");
        JLabel lbServiceID = new JLabel("Service ID:");
        JLabel lbServiceName = new JLabel("Service Name:");
        JLabel lbServicePrice = new JLabel("Price:");
        JLabel lbPayment = new JLabel("Payment detail:");
        lbPayment.setFont(new Font("SansSerif", Font.BOLD, 12));
        JLabel lbMediCardUsed = new JLabel("Medi-Card:");
        JLabel lbCoveredAmount = new JLabel("Covered Amt:");
        JLabel lbCashPaid = new JLabel("Cash Paid:");

        JTextField tfDate = new JTextField(dateD+"/"+dateM+"/"+dateY,6);
        JTextField tfCustomerID = new JTextField(customerID+"",6);
        JTextField tfFirstName = new JTextField(firstName+"",6);
        JTextField tfLastName = new JTextField(lastName+"",6);
        JTextField tfServiceID = new JTextField(serviceID+"",6);
        JTextField tfServiceName = new JTextField(serviceName+"",6);
        JTextField tfServicePrice = new JTextField(servicePrice+"",6);
        JTextField tfMediCardUsed = new JTextField(cardID+"",6);
        JTextField tfCoveredAmount = new JTextField(cardCover+"",6);
        JTextField tfCashPaid = new JTextField();
        try{
            int price = Integer.parseInt(servicePrice);
            int coverAmt = Integer.parseInt(cardCover);
            int cashPmt = price - coverAmt;
            cashPayment = String.valueOf(cashPmt);
            tfCashPaid.setText(cashPayment);
        } catch (NumberFormatException e) {
            System.out.println("Service or card not yet selected");
//            throw new RuntimeException(e);
        }

        tfDate.setEditable(false);
        tfCustomerID.setEditable(false);
        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);
        tfServiceID.setEditable(false);
        tfServiceName.setEditable(false);
        tfServicePrice.setEditable(false);
        tfMediCardUsed.setEditable(false);
        tfCoveredAmount.setEditable(false);
        tfCashPaid.setEditable(false);

        JButton btnAddTransaction = new JButton("Add Transaction");
        JButton btnClearForm = new JButton("Clear Form");

        btnAddTransaction.addActionListener(e -> {
            Transaction transaction = new Transaction(dateY,dateM,dateD,customerID,serviceID,servicePrice,cardID,cardCover,cashPayment);
            if (inputValidation(transaction)){
                DB_CRUD.addTransaction(transaction);
                frame.dispose();
            }
        });

        btnClearForm.addActionListener(e -> {
            new Pop_newTrsc(customerID);
            frame.dispose();
        });

        lbDate.setBounds(10,30,120,30);
        lbCustomerID.setBounds(10,60,120,30);
        lbFirstName.setBounds(10,90,120,30);
        lbLastName.setBounds(10,120,120,30);
        lbServiceID.setBounds(10,150,120,30);
        lbServiceName.setBounds(10,180,120,30);
        lbServicePrice.setBounds(10,210,120,30);
        lbPayment.setBounds(10,270,240,30);
        lbMediCardUsed.setBounds(10,300,120,30);
        lbCoveredAmount.setBounds(10,330,120,30);
        lbCashPaid.setBounds(10,360,120,30);

        tfDate.setBounds(120,30,160,30);
        tfCustomerID.setBounds(120,60,160,30);
        tfFirstName.setBounds(120,90,160,30);
        tfLastName.setBounds(120,120,160,30);
        tfServiceID.setBounds(120,150,160,30);
        tfServiceName.setBounds(120,180,160,30);
        tfServicePrice.setBounds(120,210,160,30);

        tfMediCardUsed.setBounds(120,300,160,30);
        tfCoveredAmount.setBounds(120,330,160,30);
        tfCashPaid.setBounds(120,360,160,30);

        btnAddTransaction.setBounds(0,400,140,30);
        btnClearForm.setBounds(140,400,140,30);

        pnlPreview.add(lbPreviewTitle);
        pnlPreview.add(lbDate);
        pnlPreview.add(lbCustomerID);
        pnlPreview.add(lbFirstName);
        pnlPreview.add(lbLastName);
        pnlPreview.add(lbServiceID);
        pnlPreview.add(lbServiceName);
        pnlPreview.add(lbServicePrice);
        pnlPreview.add(lbPayment);
        pnlPreview.add(lbMediCardUsed);
        pnlPreview.add(lbCoveredAmount);
        pnlPreview.add(lbCashPaid);
        pnlPreview.add(tfDate);
        pnlPreview.add(tfCustomerID);
        pnlPreview.add(tfFirstName);
        pnlPreview.add(tfLastName);
        pnlPreview.add(tfServiceID);
        pnlPreview.add(tfServiceName);
        pnlPreview.add(tfServicePrice);
        pnlPreview.add(tfMediCardUsed);
        pnlPreview.add(tfCoveredAmount);
        pnlPreview.add(tfCashPaid);
        pnlPreview.add(btnAddTransaction);
        pnlPreview.add(btnClearForm);

        frame.add(pnlPreview);
        frame.setVisible(true);
    }
    public boolean inputValidation(Transaction transaction){
        boolean inputValid = true;
        if (transaction.dateY.length() == 0 || transaction.dateM.length() ==0 || transaction.dateD.length() == 0){
            JOptionPane.showMessageDialog(frame,"Please select a date","Input Error", JOptionPane.ERROR_MESSAGE);
            inputValid = false;
        }
        if (serviceID.length() == 0){
            JOptionPane.showMessageDialog(frame,"Please select a service","Input Error", JOptionPane.ERROR_MESSAGE);
            inputValid = false;
        }
        if (cardID.length() == 0){
            JOptionPane.showMessageDialog(frame,"Please select a medi-card","Input Error", JOptionPane.ERROR_MESSAGE);
            inputValid = false;
        }

        return inputValid;
    }

}
