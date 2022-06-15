import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BHFrame extends JFrame implements ActionListener {
    public JPanel menuBtnPanel;
    public JPanel sectionPanel;
    public JButton transactionBtn, serviceBtn, customerBtn, backupBtn;


    BHFrame(JPanel sectionInsert){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Better Health Therapy Massage Centre");
        setSize(1100, 660);

        constructMenuPanel();
        sectionPanel = sectionInsert;

        add(menuBtnPanel);
        add(sectionPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // show layer 2 buttons
        if (e.getSource() == transactionBtn){

            setVisible(true);
        }

        if (e.getSource() == serviceBtn){

            setVisible(true);
        }

        if (e.getSource() == customerBtn){
            sectionPanel = new SectionCustomer();
            new NewMain(sectionPanel);
            this.dispose();

        }

    }

    public void constructMenuPanel(){
        menuBtnPanel = new JPanel();
        menuBtnPanel.setBounds(10,10,1080,56);
        menuBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        menuBtnPanel.setBackground(Color.lightGray);

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
        menuBtnPanel.add(serviceBtn);
        menuBtnPanel.add(customerBtn);
        menuBtnPanel.add(backupBtn);
    }


}
