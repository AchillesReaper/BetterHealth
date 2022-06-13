import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BHFrame extends JFrame implements ActionListener {
    public JPanel btnLayer1Panel = new JPanel();
    public JPanel btnLayer2Panel = new JPanel();
    public JPanel contentPanel= new JPanel();
    public JButton transactionBtn, serviceBtn, customerBtn, backupBtn;
    //layer2 transaction btn
    public JButton newTransactionBtn, allTransactionBtn, searchTransactionBtn, exportCSVBtn;
    //layer2 service btn
    public JButton extServiceBtn, addServiceBtn, rmServiceBtn;

    //layer2 customer btn
    public JButton newCustomerBtn, allCustomerBtn, searchCustomerBtn;
    public JTable contentTable;
    public JScrollPane scrollPan;

    BHFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Better Health Therapy Massage Centre");
        setSize(760, 680);

        // layer1 panel setting
        btnLayer1Panel.setBounds(10,10,740,56);
        btnLayer1Panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        btnLayer1Panel.setBackground(Color.darkGray);

        transactionBtn = new JButton("Transaction");
        serviceBtn = new JButton("Service");
        customerBtn = new JButton("Customer");
        backupBtn = new JButton("Backup Data");

        transactionBtn.setFont(new Font("Comic Sans", Font.BOLD, 18));
        transactionBtn.setPreferredSize(new Dimension(160,36));
        serviceBtn.setFont(new Font("Comic Sans", Font.BOLD, 18));
        serviceBtn.setPreferredSize(new Dimension(160,36));
        customerBtn.setFont(new Font("Comic Sans", Font.BOLD, 18));
        customerBtn.setPreferredSize(new Dimension(160,36));
        backupBtn.setFont(new Font("Comic Sans", Font.BOLD, 18));
        backupBtn.setPreferredSize(new Dimension(160,36));

        transactionBtn.addActionListener(this);
        serviceBtn.addActionListener(this);
        customerBtn.addActionListener(this);
        backupBtn.addActionListener(this);

        btnLayer1Panel.add(transactionBtn);
        btnLayer1Panel.add(serviceBtn);
        btnLayer1Panel.add(customerBtn);
        btnLayer1Panel.add(backupBtn);

        //layer2 panel format
        btnLayer2Panel.setBounds(10,70,740,56);
        btnLayer2Panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        btnLayer2Panel.setBackground(Color.darkGray);
        //Content panel format
        contentPanel.setBounds(10,130,740,500);
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        contentPanel.setBackground(Color.darkGray);

        add(btnLayer1Panel);
        add(btnLayer2Panel);
        add(contentPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // show layer 2 buttons
        if (e.getSource() == transactionBtn){
            if (btnLayer2Panel != null){remove(btnLayer2Panel);}
            btnLayer2Panel = new JPanel();
            btnLayer2Panel.setBounds(10,70,740,56);
            btnLayer2Panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
            btnLayer2Panel.setBackground(Color.darkGray);

            newTransactionBtn = new JButton("new transaction");
            allTransactionBtn = new JButton("all transaction");
            searchTransactionBtn = new JButton("search");

            newTransactionBtn.addActionListener(this);
            allTransactionBtn.addActionListener(this);
            searchTransactionBtn.addActionListener(this);

            newTransactionBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            newTransactionBtn.setPreferredSize(new Dimension(160,36));
            allTransactionBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            allTransactionBtn.setPreferredSize(new Dimension(160,36));
            searchTransactionBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            searchTransactionBtn.setPreferredSize(new Dimension(160,36));

            btnLayer2Panel.add(newTransactionBtn);
            btnLayer2Panel.add(allTransactionBtn);
            btnLayer2Panel.add(searchTransactionBtn);

            add(btnLayer2Panel);
            setVisible(true);
        }

        if (e.getSource() == serviceBtn){
            if (btnLayer2Panel != null){remove(btnLayer2Panel);}
            btnLayer2Panel = new JPanel();
            btnLayer2Panel.setBounds(10,70,740,56);
            btnLayer2Panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
            btnLayer2Panel.setBackground(Color.darkGray);

            extServiceBtn = new JButton("existing services");
            addServiceBtn = new JButton("add service");
            rmServiceBtn = new JButton("inactivate service");

            extServiceBtn.addActionListener(this);
            addServiceBtn.addActionListener(this);
            rmServiceBtn.addActionListener(this);

            extServiceBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            extServiceBtn.setPreferredSize(new Dimension(160,36));
            addServiceBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            addServiceBtn.setPreferredSize(new Dimension(160,36));
            rmServiceBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            rmServiceBtn.setPreferredSize(new Dimension(160,36));

            btnLayer2Panel.add(extServiceBtn);
            btnLayer2Panel.add(addServiceBtn);
            btnLayer2Panel.add(rmServiceBtn);

            add(btnLayer2Panel);
            setVisible(true);
        }

         if (e.getSource() == customerBtn){
            if (btnLayer2Panel != null){remove(btnLayer2Panel);}
            btnLayer2Panel = new JPanel();
            btnLayer2Panel.setBounds(10,70,740,56);
            btnLayer2Panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
            btnLayer2Panel.setBackground(Color.darkGray);

            newCustomerBtn = new JButton("new customer");
            allCustomerBtn = new JButton("all customer");
            searchCustomerBtn = new JButton("search");

            newCustomerBtn.addActionListener(this);
            allCustomerBtn.addActionListener(this);
            searchCustomerBtn.addActionListener(this);

            newCustomerBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            newCustomerBtn.setPreferredSize(new Dimension(160,36));
            allCustomerBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            allCustomerBtn.setPreferredSize(new Dimension(160,36));
            searchCustomerBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            searchCustomerBtn.setPreferredSize(new Dimension(160,36));

            btnLayer2Panel.add(newCustomerBtn);
            btnLayer2Panel.add(allCustomerBtn);
            btnLayer2Panel.add(searchCustomerBtn);

            add(btnLayer2Panel);
            setVisible(true);
        }

        if(e.getSource() == newCustomerBtn){
            new Pop_AddCustomer();
        }

        if(e.getSource() == allCustomerBtn){
            if (contentPanel != null){remove(contentPanel);}
            contentPanel = new JPanel();
            contentPanel.setBounds(10,130,740,500);
            contentPanel.setLayout(null);
            contentPanel.setBackground(Color.darkGray);

            exportCSVBtn = new JButton("Export CSV");
            exportCSVBtn.setFont(new Font("Comic Sans", Font.BOLD, 14));
            exportCSVBtn.setBounds(500,420,160,40);
            contentPanel.add(exportCSVBtn);


            String[] colName = {"First Name", "Last Name", "Medi-Care","Mobile","Gender","DOB","email","Address"};
            Object[][] data = DB_CRUD.showAllCstm();
            contentTable = new JTable(data,colName);
            contentTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
            contentTable.setFont(new Font("SansSerif", Font.PLAIN, 12));

            scrollPan = new JScrollPane(contentTable);
            scrollPan.setBounds(20,20,700,380);
            contentTable.setFillsViewportHeight(true);

            contentPanel.add(scrollPan);

            add(contentPanel);
            setVisible(true);
        }

        if(e.getSource() == searchCustomerBtn){
            new Pop_SearchCustomer();
        }
    }


}
