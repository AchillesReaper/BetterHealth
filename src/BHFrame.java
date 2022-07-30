import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BHFrame extends JFrame{
    public JPanel menuBtnPanel;
    public JPanel sectionPanel;
    public JButton btnTransaction, btnService, btnCustomer, btnBackup;


    BHFrame(JPanel sectionInsert){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Better Health Therapy Massage Centre");
        setSize(1115, 670);
        setResizable(false);

        constructMenuPanel();
        sectionPanel = sectionInsert;

        add(menuBtnPanel);
        add(sectionPanel);

        setVisible(true);
    }

    public void constructMenuPanel(){
        menuBtnPanel = new JPanel();
        menuBtnPanel.setBounds(10,10,1080,56);
        menuBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        menuBtnPanel.setBackground(Color.lightGray);

        btnCustomer = new JButton("Customer");
        btnService = new JButton("Service");
        btnTransaction = new JButton("Transaction");
        btnBackup = new JButton("Backup Data");

        btnCustomer.addActionListener(e -> {
            sectionPanel = new SectionCustomer();
            new NewMain(sectionPanel);
            this.dispose();
        });

        btnService.addActionListener(e -> {
            sectionPanel = new SectionService();
            new NewMain(sectionPanel);
            this.dispose();
        });

        btnTransaction.addActionListener(e -> {
            sectionPanel = new SectionTransaction( "");
            new NewMain(sectionPanel);
            this.dispose();
        });

        btnBackup.addActionListener( e -> {
            DB_CRUD.backupDatabase();
        });

        btnTransaction.setFont(new Font("Comic Sans", Font.BOLD, 18));
        btnTransaction.setPreferredSize(new Dimension(160,36));
        btnService.setFont(new Font("Comic Sans", Font.BOLD, 18));
        btnService.setPreferredSize(new Dimension(160,36));
        btnCustomer.setFont(new Font("Comic Sans", Font.BOLD, 18));
        btnCustomer.setPreferredSize(new Dimension(160,36));
        btnBackup.setFont(new Font("Comic Sans", Font.BOLD, 18));
        btnBackup.setPreferredSize(new Dimension(160,36));

        menuBtnPanel.add(btnCustomer);
        menuBtnPanel.add(btnService);
        menuBtnPanel.add(btnTransaction);
        menuBtnPanel.add(btnBackup);
    }
}
