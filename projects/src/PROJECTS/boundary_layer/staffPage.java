package PROJECTS.boundary_layer;

import PROJECTS.control_layer.cafeOwner.ownerSearchController;
import PROJECTS.control_layer.cafeOwner.ownerViewController;
import PROJECTS.control_layer.staff.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class staffPage extends JFrame {
    public staffPage(String name,String account) {
        super("welcome "+name +"to"+"use staff page");
        JTabbedPane controlTab;
        JPanel ViewSlotsPart, ViewAssignedWorkPart, IndicateWorkHourPart,BidSlotPart,ViewMyBidsPart,DeleteMyBids,exitPart;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        setVisible(true);

        ViewSlotsPart = new JPanel();
        ViewAssignedWorkPart = new JPanel();
        IndicateWorkHourPart = new JPanel();
        BidSlotPart = new JPanel();
        ViewMyBidsPart= new JPanel();
        DeleteMyBids= new JPanel();
        exitPart=new JPanel();

        ViewSlotsPart.setBackground(Color.GRAY);
        ViewAssignedWorkPart.setBackground(Color.GRAY);
        IndicateWorkHourPart.setBackground(Color.GRAY);
        BidSlotPart.setBackground(Color.GRAY);
        ViewMyBidsPart.setBackground(Color.GRAY);
        DeleteMyBids.setBackground(Color.GRAY);
        exitPart.setBackground(Color.GRAY);

        ViewSlotsPart.setLayout(null);
        ViewAssignedWorkPart.setLayout(null);
        IndicateWorkHourPart.setLayout(null);
        BidSlotPart.setLayout(null);
        ViewMyBidsPart.setLayout(null);
        DeleteMyBids.setLayout(null);
        exitPart.setLayout(null);

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view slot part
         */
        staffViewSlotsController staffViewSlotsController = new staffViewSlotsController();

        DefaultTableModel ViewSlotsModel = staffViewSlotsController.viewAllSlotList();
        JTable ViewSlotsTable = new JTable(ViewSlotsModel);
        ViewSlotsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane ViewSlotsScrollPane = new JScrollPane(ViewSlotsTable);
        ViewSlotsTable.setRowHeight(20);

        JButton ViewSlotsRefreshButton=new JButton("refresh");

        ViewSlotsRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffViewSlotsController staffViewSlotsController = new staffViewSlotsController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = staffViewSlotsController.refreshAllSlotList();
                //?
                ViewSlotsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    ViewSlotsModel.addRow(row);
                }
            }
        });

        ViewSlotsScrollPane.setBounds(10, 40, 460, 400);
        ViewSlotsRefreshButton.setBounds(210, 10, 80, 25);

        ViewSlotsPart.add(ViewSlotsScrollPane);
        ViewSlotsPart.add(ViewSlotsRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view slot part
         */
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view assigned bid part  AssignedBid
         */
        staffViewAssignedBidController staffViewAssignedBidController = new staffViewAssignedBidController();

        DefaultTableModel ViewAssignedBidModel = staffViewAssignedBidController.viewAssignedBidsList(account);
        JTable ViewAssignedBidTable = new JTable(ViewAssignedBidModel);
        ViewAssignedBidTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane ViewAssignedBidScrollPane = new JScrollPane(ViewAssignedBidTable);
        ViewAssignedBidTable.setRowHeight(20);

        JButton ViewAssignedBidRefreshButton=new JButton("refresh");

        ViewAssignedBidRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffViewAssignedBidController staffViewAssignedBidController = new staffViewAssignedBidController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = staffViewAssignedBidController.refreshAssignedBidsList(account);
                //?
                ViewAssignedBidModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    ViewAssignedBidModel.addRow(row);
                }
            }
        });

        ViewAssignedBidScrollPane.setBounds(10, 40, 460, 400);
        ViewAssignedBidRefreshButton.setBounds(210, 10, 80, 25);

        ViewAssignedWorkPart.add(ViewAssignedBidScrollPane);
        ViewAssignedWorkPart.add(ViewAssignedBidRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view assigned bid part
         */
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * Indicate Work Hour Part
         */

        JLabel WorkingHourLabel=new JLabel("Please enter new Max Working Hour :");

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);

        JFormattedTextField WorkingHourField = new JFormattedTextField(formatter);
        WorkingHourField.setColumns(10); // Set the desired number of columns

        JButton WorkingHourButton = new JButton("Indicate");


        staffIndicateHourController staffIndicateHourController = new staffIndicateHourController();
        DefaultTableModel WorkingHourModel = staffIndicateHourController.viewHourList(account);

        JTable WorkingHourTable = new JTable(WorkingHourModel);

        WorkingHourTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane WorkingHourScrollPane = new JScrollPane(WorkingHourTable);
        WorkingHourTable.setRowHeight(20);
        WorkingHourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int hours = (int)WorkingHourField.getValue();

                staffIndicateHourController staffIndicateHourController = new staffIndicateHourController();
                /**
                 * call search controller
                 */


                if (staffIndicateHourController.indicateMaxHour(account,hours)){
                    DefaultTableModel updatedModel = staffIndicateHourController.refreshHourList(account);
                    //?
                    WorkingHourModel.setRowCount(0);
                    for (int i = 0; i < updatedModel.getRowCount(); i++) {
                        Vector<Object> row = new Vector<>();
                        for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                            row.add(updatedModel.getValueAt(i, j));
                        }
                        WorkingHourModel.addRow(row);
                    }
                    JOptionPane.showMessageDialog(staffPage.this, "The Max working hour has been successfully updated to the database");
                }else {
                    JOptionPane.showMessageDialog(staffPage.this,
                            "The Max working hour is valid, please re-enter, (can not less then 1 hour or higher then 100 hours) ");
                }
            }
        });

        WorkingHourLabel.setBounds(30, 50, 350, 30);
        WorkingHourField.setBounds(30, 90, 250, 30);
        WorkingHourButton.setBounds(350, 90, 100, 30);
        WorkingHourScrollPane.setBounds(30, 130, 430, 200);
        //WorkingHourRefreshButton.setBounds(50, 2000, 450, 280);

        IndicateWorkHourPart.add(WorkingHourLabel);
        IndicateWorkHourPart.add(WorkingHourField);
        IndicateWorkHourPart.add(WorkingHourButton);
        IndicateWorkHourPart.add(WorkingHourScrollPane);
        //IndicateWorkHourPart.add(WorkingHourRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         *  end Indicate Work Hour Part
         */

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * bid Part
         */

        JLabel bidLabel=new JLabel("Please enter enter slot NO and choose role to bid :");

        NumberFormat bidintegerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter bidformatter = new NumberFormatter(bidintegerFormat);
        bidformatter.setValueClass(Integer.class);
        bidformatter.setAllowsInvalid(false);

        JFormattedTextField bidField = new JFormattedTextField(bidformatter);
        bidField.setColumns(10); // Set the desired number of columns


        JComboBox<String> bidComboBox = new JComboBox<>(new String[]{"chef", "cashier", "waiter"});

        JButton bidSlotButton = new JButton("submit");
        JButton bidSlotRefreshButton = new JButton("refresh");


        staffBidSlotController staffBidSlotController = new staffBidSlotController();

        DefaultTableModel bidSlotModel = staffBidSlotController.viewAllSlotList();

        JTable bidSlotTable = new JTable(bidSlotModel);

        bidSlotTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane bidSlotScrollPane = new JScrollPane(bidSlotTable);
        bidSlotTable.setRowHeight(20);
        bidSlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int slotNo = (int)bidField.getValue();
                String role = (String) bidComboBox.getSelectedItem();

                staffBidSlotController staffBidSlotController = new staffBidSlotController();
                /**
                 * call search controller
                 */
                if (staffBidSlotController.bidSlots(account,slotNo,role)){
                    JOptionPane.showMessageDialog(staffPage.this, "Your bid application has been submitted, please wait for the manager to process your application");
                }else {
                    JOptionPane.showMessageDialog(staffPage.this,
                            "The slot no is not exist or this slot is already full ,\n or your working hour is not enough to bid this slot !");
                }
            }
        });

        bidSlotRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffBidSlotController staffBidSlotController = new staffBidSlotController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = staffBidSlotController.refreshAllSlotList();
                //?
                bidSlotModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    bidSlotModel.addRow(row);
                }
            }
        });

        bidLabel.setBounds(30, 20, 350, 30);
        bidField.setBounds(30, 50, 250, 30);
        bidComboBox.setBounds(30, 90, 250, 30);
        bidSlotButton.setBounds(350, 90, 100, 30);
        bidSlotScrollPane.setBounds(30, 130, 430, 260);
        bidSlotRefreshButton.setBounds(210, 400, 80, 25);

        BidSlotPart.add(bidLabel);
        BidSlotPart.add(bidField);
        BidSlotPart.add(bidComboBox);
        BidSlotPart.add(bidSlotButton);
        BidSlotPart.add(bidSlotScrollPane);
        BidSlotPart.add(bidSlotRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         *  end bid Part
         */


////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view my bid part
         */
        staffViewBidController staffViewMyBidsController = new staffViewBidController();

        DefaultTableModel ViewMyBidsModel = staffViewMyBidsController.viewPersonalBid(account);
        JTable ViewMyBidsTable = new JTable(ViewMyBidsModel);
        ViewMyBidsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane ViewMyBidsScrollPane = new JScrollPane(ViewMyBidsTable);
        ViewMyBidsTable.setRowHeight(20);

        JButton ViewMyBidsRefreshButton=new JButton("refresh");

        ViewMyBidsRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffViewBidController staffViewBidController = new staffViewBidController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = staffViewBidController.refreshPersonalBid(account);
                //?
                ViewMyBidsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    ViewMyBidsModel.addRow(row);
                }
            }
        });

        ViewMyBidsScrollPane.setBounds(10, 40, 460, 400);
        ViewMyBidsRefreshButton.setBounds(210, 10, 80, 25);

        ViewMyBidsPart.add(ViewMyBidsScrollPane);
        ViewMyBidsPart.add(ViewMyBidsRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view my bid part
         */

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * delete Part
         */

        JLabel deleteLabel=new JLabel("Please enter Bid NO to delete :");

        NumberFormat deleteFormat = NumberFormat.getIntegerInstance();
        NumberFormatter deleteFormatter = new NumberFormatter(deleteFormat);
        deleteFormatter.setValueClass(Integer.class);
        deleteFormatter.setAllowsInvalid(false);

        JFormattedTextField deleteField = new JFormattedTextField(deleteFormatter);
        deleteField.setColumns(10); // Set the desired number of columns

        JButton deleteButton = new JButton("Delete");
        JButton deleteRefreshButton = new JButton("Refresh");


        staffDeleteBidsController staffDeleteBidsController = new staffDeleteBidsController();
        DefaultTableModel deleteModel = staffDeleteBidsController.viewPersonalBid(account);

        JTable deleteTable = new JTable(deleteModel);

        deleteTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane deleteScrollPane = new JScrollPane(deleteTable);
        deleteTable.setRowHeight(20);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int bidsNO = (int)deleteField.getValue();

                staffDeleteBidsController staffDeleteBidsController = new staffDeleteBidsController();
                /**
                 * call DELETE controller
                 */

                if (staffDeleteBidsController.deleteBids(account,bidsNO)){
                    DefaultTableModel updatedModel = staffDeleteBidsController.refreshPersonalBid(account);
                    deleteModel.setRowCount(0);
                    for (int i = 0; i < updatedModel.getRowCount(); i++) {
                        Vector<Object> row = new Vector<>();
                        for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                            row.add(updatedModel.getValueAt(i, j));
                        }
                        deleteModel.addRow(row);
                    }
                    JOptionPane.showMessageDialog(staffPage.this, "The Bid has been successfully deleted ");
                }else {
                    JOptionPane.showMessageDialog(staffPage.this,
                            "The bid NO is valid, please re-enter");
                }
            }
        });
        deleteRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel updatedModel = staffDeleteBidsController.refreshPersonalBid(account);
                deleteModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    deleteModel.addRow(row);
                }

            }
        });

        deleteLabel.setBounds(30, 50, 350, 30);
        deleteField.setBounds(30, 90, 250, 30);
        deleteButton.setBounds(350, 90, 100, 30);
        deleteScrollPane.setBounds(30, 130, 430, 280);
        deleteRefreshButton.setBounds(200, 420, 100, 30);

        DeleteMyBids.add(deleteLabel);
        DeleteMyBids.add(deleteField);
        DeleteMyBids.add(deleteButton);
        DeleteMyBids.add(deleteScrollPane);
        DeleteMyBids.add(deleteRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         *  end delete Part
         */










        JButton exitButton = new JButton("Log out");
        exitButton.setBounds(220, 110, 100, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmationResult = JOptionPane.showConfirmDialog(
                        staffPage.this,
                        "Are you sure you want log out ?",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );
                if (confirmationResult == JOptionPane.OK_OPTION) {
                    logOut();
                } else {
                    // User clicked Cancel, do nothing or provide appropriate action
                }
            }
        });
        exitPart.add(exitButton);

        controlTab =new JTabbedPane(JTabbedPane.LEFT); //创建选项卡并使选项卡垂直排列
        controlTab.add("View Slots list", ViewSlotsPart);
        controlTab.add("View assigned work", ViewAssignedWorkPart);
        controlTab.add("Indicate work Hour", IndicateWorkHourPart);
        controlTab.add("Bid slot", BidSlotPart);
        controlTab.add("View my bids", ViewMyBidsPart);
        controlTab.add("Delete my bids", DeleteMyBids);
        controlTab.add("Log out", exitPart);
        controlTab.setFont(new Font("STYLE_BOLD", Font.PLAIN, 20));
        this.add(controlTab);

        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); // Set frame visibility after adding components
    }
    public void logOut(){
        dispose();
        new loginPage();
    }
}