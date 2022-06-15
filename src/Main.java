// the launch page of the program

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //JOptionPane pop up at start, asking if backup database.
        SectionCustomer sectionCustomer = new SectionCustomer();
        JFrame mainFrame = new BHFrame(sectionCustomer);
//        new Pop_Card("9","Donald","Ho");
//    mainFrame.remove(mainFrame.cntPanel);
    }
}
