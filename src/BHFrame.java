import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BHFrame extends JFrame implements ActionListener {
    public JPanel menuBtnPanel = new JPanel();
    public JPanel controlPanel = new JPanel();
    public JPanel contentPanel= new JPanel();
    public JButton transactionBtn, newTransactionBtn, allTransactionBtn, searchTransactionBtn,
            serviceBtn, extServiceBtn, addServiceBtn, rmServiceBtn,
            customerBtn, newCustomerBtn, allCustomerBtn, searchCustomerBtn, addCardBtn,
            backupBtn;
    public JButton exportCSVBtn;
    public JTable contentTable;
    public JScrollPane scrollPan;

    BHFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Better Health Therapy Massage Centre");
        setSize(1100, 720);

        // layer1 panel setting
        menuBtnPanel.setBounds(10,10,1080,56);
        menuBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        menuBtnPanel.setBackground(Color.darkGray);

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

        menuBtnPanel.add(transactionBtn);
//        menuBtnPanel.add(serviceBtn);
        menuBtnPanel.add(customerBtn);
        menuBtnPanel.add(backupBtn);

        //control panel format
        controlPanel.setBounds(750,70,340,600);
        controlPanel.setBackground(Color.darkGray);
        //Content panel format
        contentPanel.setBounds(10,70,730,600);
        contentPanel.setBackground(Color.darkGray);

        add(menuBtnPanel);
        add(controlPanel);
        add(contentPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // show layer 2 buttons
        if (e.getSource() == transactionBtn){
            if (controlPanel != null){remove(controlPanel);}
            controlPanel = new JPanel();


            add(controlPanel);
            setVisible(true);
        }

        if (e.getSource() == serviceBtn){
            if (controlPanel != null){remove(controlPanel);}
            controlPanel = new JPanel();




            add(controlPanel);
            setVisible(true);
        }

        if (e.getSource() == customerBtn){
            if (controlPanel != null){remove(controlPanel);}
            if (contentPanel != null){remove(contentPanel);}
            controlPanel = new CustomerControl();
            contentPanel = new ContentAllCstm();

            add(controlPanel);
            add(contentPanel);
            setVisible(true);
        }

        if (e.getSource() == controlPanel.addNewBtn){
            System.out.println("it works");
        }

    }


}
