import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SectionService extends JPanel {
    public JPanel pnlExtService, pnlAllService, pnlAddService, pnlUpdateService;
    public JScrollPane scpExtService, scpAllService;

    JTextField tfServiceID = new JTextField("",6);
    JTextField tfServiceName = new JTextField("",6);
    JTextField tfServiceContent = new JTextField("",6);
    JTextField tfPrice = new JTextField("",6);

    final String[] availability = new String[1];

    public SectionService(){
        setBounds(10,70,1080,700);
        setLayout(null);

        constructAddServicePnl();
        constructUpdateServicePnl();
        showExtService();
        showAllService();

    }
    public void showExtService(){
        if (pnlExtService != null){remove(pnlExtService);}
        pnlExtService = new JPanel();
        pnlExtService.setBounds(0,0,720,260);
        pnlExtService.setLayout(null);
        pnlExtService.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Service on List");
        contentTitle.setBounds(10,0,200,30);
        contentTitle.setFont(new Font("SansSerif", Font.BOLD, 14));


        String queryString = "select * from services WHERE availability = 'Yes'";
        Object[][] data = DB_CRUD.searchService(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"On-list service is not found.","",JOptionPane.PLAIN_MESSAGE);
        }

        String[] colName = {"ID", "Name", "Content","Price","Available"};
        JTable tbExtService = new JTable(data, colName);
        tbExtService.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbExtService.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbExtService.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbExtService.getSelectedRow();
                if (row == -1) {
                    return;
                }
                tfServiceID.setText((tbExtService.getModel().getValueAt(row, 0)).toString());
                tfServiceName.setText((tbExtService.getModel().getValueAt(row, 1)).toString());
                tfServiceContent.setText((tbExtService.getModel().getValueAt(row, 2)).toString());
                tfPrice.setText((tbExtService.getModel().getValueAt(row, 3)).toString());
                availability[0] = (tbExtService.getModel().getValueAt(row, 4)).toString();
            }
        });
        scpExtService = new JScrollPane(tbExtService);
        scpExtService.setBounds(10,30,700,220);
        tbExtService.setFillsViewportHeight(true);

        pnlExtService.add(contentTitle);
        pnlExtService.add(scpExtService);

        add(pnlExtService);
        setVisible(true);
    }
    public  void showAllService(){
        if (pnlAllService != null){remove(pnlAllService);}
        pnlAllService = new JPanel();
        pnlAllService.setBounds(0,270,720,280);
        pnlAllService.setLayout(null);
        pnlAllService.setBackground(Color.lightGray);

        JLabel contentTitle = new JLabel("Services not on list");
        contentTitle.setBounds(10,0,250,30);
        contentTitle.setFont(new Font("SansSerif", Font.BOLD, 14));


        String queryString = "select * from services where availability = 'No'";
        Object[][] data = DB_CRUD.searchService(queryString);
        if (data.length == 0){
            JOptionPane.showMessageDialog(null,"Off-list service is found.","",JOptionPane.PLAIN_MESSAGE);
        }

        String[] colName = {"ID", "Name", "Content","Price","Available"};
        JTable tbExtService = new JTable(data, colName);
        tbExtService.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tbExtService.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tbExtService.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbExtService.getSelectedRow();
                if (row == -1) {
                    return;
                }
                tfServiceID.setText((tbExtService.getModel().getValueAt(row, 0)).toString());
                tfServiceName.setText((tbExtService.getModel().getValueAt(row, 1)).toString());
                tfServiceContent.setText((tbExtService.getModel().getValueAt(row, 2)).toString());
                tfPrice.setText((tbExtService.getModel().getValueAt(row, 3)).toString());
                availability[0] = (tbExtService.getModel().getValueAt(row, 4)).toString();
            }
        });
        scpAllService = new JScrollPane(tbExtService);
        scpAllService.setBounds(10,30,700,240);
        tbExtService.setFillsViewportHeight(true);

        pnlAllService.add(contentTitle);
        pnlAllService.add(scpAllService);

        add(pnlAllService);
        setVisible(true);

    }

    public void constructAddServicePnl(){
        pnlAddService = new JPanel();
        pnlAddService.setBounds(730,0,350,260);
        pnlAddService.setLayout(null);
        pnlAddService.setBackground(Color.lightGray);
        JLabel titleLabel = new JLabel("Add Service");
        titleLabel.setBounds(10,0,200,30);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel lbServiceName = new JLabel("Service:");
        JLabel lbServiceContent = new JLabel("Content:");
        JLabel lbPrice = new JLabel("Price:");
        JLabel lbAvailable = new JLabel("Available:");

        JTextField tfServiceNameA = new JTextField("",6);
        JTextField tfServiceContentA = new JTextField("",6);
        JTextField tfPriceA = new JTextField("",6);

        final String[] availabilityA = new String[1];
        JRadioButton btnYes = new JRadioButton("Yes");
        btnYes.addActionListener(e -> availabilityA[0] = "Yes");
        JRadioButton btnNo = new JRadioButton("No");
        btnNo.addActionListener(e -> availabilityA[0] = "No");
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnYes);
        btnGroup.add(btnNo);
        JPanel groupButton = new JPanel();
        groupButton.add(btnYes);
        groupButton.add(btnNo);
        groupButton.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));
        groupButton.setBackground(Color.lightGray);

        JButton btnAddService = new JButton("Add Service");
        btnAddService.addActionListener(e -> {
            Service service = new Service(tfServiceNameA.getText(),tfServiceContentA.getText(),tfPriceA.getText(), availabilityA[0]);
            if (inputValidate(service)){
                DB_CRUD.addServiceToDB(service);
                showExtService();
                showAllService();
            }
        });

        lbServiceName.setBounds(10,40,90,30);
        lbServiceContent.setBounds(10,80,90,30);
        lbPrice.setBounds(10,120,90,30);
        lbAvailable.setBounds(10,160,90,30);

        tfServiceNameA.setBounds(100,40,240,30);
        tfServiceContentA.setBounds(100,80,240,30);
        tfPriceA.setBounds(100,120,240,30);
        groupButton.setBounds(100,160,240,30);

        btnAddService.setBounds(115,210,120,30);


        pnlAddService.add(titleLabel);
        pnlAddService.add(lbServiceName);
        pnlAddService.add(lbServiceContent);
        pnlAddService.add(lbPrice);
        pnlAddService.add(lbAvailable);
        pnlAddService.add(tfServiceNameA);
        pnlAddService.add(tfServiceContentA);
        pnlAddService.add(tfPriceA);
        pnlAddService.add(groupButton);
        pnlAddService.add(lbServiceName);
        pnlAddService.add(lbServiceName);
        pnlAddService.add(btnAddService);

        add(pnlAddService);
        setVisible(true);
    }

    public void constructUpdateServicePnl(){
        pnlUpdateService = new JPanel();
        pnlUpdateService.setBounds(730,270,350,280);
        pnlUpdateService.setLayout(null);
        pnlUpdateService.setBackground(Color.lightGray);
        JLabel titleLabel = new JLabel("Update Service");
        titleLabel.setBounds(10,0,200,30);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel lbServiceID = new JLabel("Service ID");
        JLabel lbServiceName = new JLabel("Service:");
        JLabel lbServiceContent = new JLabel("Content:");
        JLabel lbPrice = new JLabel("Price:");
        JLabel lbAvailable = new JLabel("Available:");
        tfServiceID.setEditable(false);
        tfServiceName.setEditable(false);
        tfServiceContent.setEditable(false);
        tfPrice.setEditable(false);

        JRadioButton btnYes = new JRadioButton("Yes");
        btnYes.addActionListener(e -> availability[0] = "Yes");
        JRadioButton btnNo = new JRadioButton("No");
        btnNo.addActionListener(e -> availability[0] = "No");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnYes);
        btnGroup.add(btnNo);
        JPanel groupButton = new JPanel();
        groupButton.add(btnYes);
        groupButton.add(btnNo);
        groupButton.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));
        groupButton.setBackground(Color.lightGray);

        JButton btnUpdateService = new JButton("Update Service");
        btnUpdateService.addActionListener(e -> {
            Service service = new Service(tfServiceName.getText(),tfServiceContent.getText(),tfPrice.getText(), availability[0]);

            if (tfServiceID.getText().length() > 0 ){
                service.setID(tfServiceID.getText());
            }
            if (inputValidate(service)){
                DB_CRUD.updateService(service);
                showExtService();
                showAllService();
            }

        });

        lbServiceID.setBounds(10,40,90,30);
        lbServiceName.setBounds(10,80,90,30);
        lbServiceContent.setBounds(10,120,90,30);
        lbPrice.setBounds(10,160,90,30);
        lbAvailable.setBounds(10,200,90,30);

        tfServiceID.setBounds(100,40,240,30);
        tfServiceName.setBounds(100,80,240,30);
        tfServiceContent.setBounds(100,120,240,30);
        tfPrice.setBounds(100,160,240,30);
        groupButton.setBounds(100,200,240,30);

        btnUpdateService.setBounds(115,240,120,30);

        pnlUpdateService.add(titleLabel);
        pnlUpdateService.add(lbServiceID);
        pnlUpdateService.add(lbServiceName);
        pnlUpdateService.add(lbServiceContent);
        pnlUpdateService.add(lbPrice);
        pnlUpdateService.add(lbAvailable);
        pnlUpdateService.add(tfServiceID);
        pnlUpdateService.add(tfServiceName);
        pnlUpdateService.add(tfServiceContent);
        pnlUpdateService.add(tfPrice);
        pnlUpdateService.add(groupButton);
        pnlUpdateService.add(lbServiceName);
        pnlUpdateService.add(lbServiceName);
        pnlUpdateService.add(btnUpdateService);

        add(pnlUpdateService);
        setVisible(true);
    }

    public boolean inputValidate(Service service){
        boolean pVal = true;
        boolean oVal = true;
        try {
            int p = Integer.parseInt(service.price);
        } catch (NumberFormatException e) {
            pVal = false;
            JOptionPane.showMessageDialog(this,"The input price is not a integer.\nPlease make change","InputError",JOptionPane.ERROR_MESSAGE);
        }
        if (service.name.length()<2 || service.content.length()<2 || service.availability == null){
            oVal = false;
            JOptionPane.showMessageDialog(this,"Please check:\nName, Content, or Available","Input Error",JOptionPane.ERROR_MESSAGE);
        }
        return (pVal && oVal);
    }
}
