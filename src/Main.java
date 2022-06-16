// the launch page of the program

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //JOptionPane pop up at start, asking if backup database.
//        SectionCustomer sectionCustomer = new SectionCustomer();
        SectionService sectionService = new SectionService();
        JFrame mainFrame = new BHFrame(sectionService);
//        new Pop_Card("9","Donald","Ho");
//    mainFrame.remove(mainFrame.cntPanel);
    }
}
