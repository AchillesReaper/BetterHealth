import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BHFrame extends JFrame implements ActionListener {
    public JPanel btnLayer1Panel, btnLayer2Panel, contentPanel;
    public JButton transactionBtn, serviceBtn, customerBtn, backupBtn;

    //layer2 transaction btn
    public JButton newTransactionBtn, allTransactionBtn, searchTransactionBtn;

    //layer2 service btn
    public JButton extServiceBtn, addServiceBtn, rmServiceBtn;

    //layer2 customer btn
    public JButton newCustomerBtn, allCustomerBtn, searchCustomerBtn;


    BHFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Better Health Therapy Massage Centre");
        setSize(780, 640);

        // layer1 panel setting
        btnLayer1Panel = new JPanel();
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

        //layer2 panel setting

        add(btnLayer1Panel);

//        add(contentPanel);

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
    }
}
