package PROJECTS.boundary_layer;

import PROJECTS.control_layer.manager.*;
import PROJECTS.control_layer.staff.staffBidSlotController;
import PROJECTS.control_layer.staff.staffViewSlotsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Vector;

public class managerPage extends JFrame {
    public managerPage(){
        super("welcome use manager page");
        JTabbedPane controlTab;
        JPanel ViewAllSlotsPart, ViewStaffListPart,ViewAllBidsPart,ViewNotFullSlotsPart, OfferWorkSlotPart,HandleBidsPart,exitPart;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        setVisible(true);

        ViewAllSlotsPart = new JPanel();
        ViewStaffListPart = new JPanel();
        ViewAllBidsPart = new JPanel();
        ViewNotFullSlotsPart = new JPanel();
        OfferWorkSlotPart= new JPanel();
        HandleBidsPart= new JPanel();
        exitPart=new JPanel();

        ViewAllSlotsPart.setBackground(Color.GRAY);
        ViewStaffListPart.setBackground(Color.GRAY);
        ViewAllBidsPart.setBackground(Color.GRAY);
        ViewNotFullSlotsPart.setBackground(Color.GRAY);
        OfferWorkSlotPart.setBackground(Color.GRAY);
        HandleBidsPart.setBackground(Color.GRAY);
        exitPart.setBackground(Color.GRAY);

        ViewAllSlotsPart.setLayout(null);
        ViewStaffListPart.setLayout(null);
        ViewAllBidsPart.setLayout(null);
        ViewNotFullSlotsPart.setLayout(null);
        OfferWorkSlotPart.setLayout(null);
        HandleBidsPart.setLayout(null);
        exitPart.setLayout(null);

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view all slot part
         */
        managerViewAllWorkSlotsController managerViewAllWorkSlotsController = new managerViewAllWorkSlotsController();

        DefaultTableModel ViewAllSlotsModel = managerViewAllWorkSlotsController.viewAllSlotList();
        JTable ViewAllSlotsTable = new JTable(ViewAllSlotsModel);
        ViewAllSlotsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane ViewAllSlotsScrollPane = new JScrollPane(ViewAllSlotsTable);
        ViewAllSlotsTable.setRowHeight(20);

        JButton ViewAllSlotsRefreshButton=new JButton("refresh");

        ViewAllSlotsRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerViewAllWorkSlotsController managerViewAllWorkSlotsController = new managerViewAllWorkSlotsController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = managerViewAllWorkSlotsController.refreshAllSlotList();
                //?
                ViewAllSlotsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    ViewAllSlotsModel.addRow(row);
                }
            }
        });

        ViewAllSlotsScrollPane.setBounds(10, 40, 560, 400);
        ViewAllSlotsRefreshButton.setBounds(240, 520, 80, 25);

        ViewAllSlotsPart.add(ViewAllSlotsScrollPane);
        ViewAllSlotsPart.add(ViewAllSlotsRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view all slot part
         */

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view all Staffs part
         */
        managerViewStaffListController managerViewStaffListController = new managerViewStaffListController();
        DefaultTableModel ViewAllStaffsModel = managerViewStaffListController.viewAllStaffList();

        JTable ViewAllStaffsTable = new JTable(ViewAllStaffsModel);
        ViewAllStaffsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane ViewAllStaffsScrollPane = new JScrollPane(ViewAllStaffsTable);
        ViewAllStaffsTable.setRowHeight(20);

        JButton ViewAllStaffsRefreshButton=new JButton("refresh");

        ViewAllStaffsRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerViewStaffListController managerViewStaffListController = new managerViewStaffListController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = managerViewStaffListController.refreshAllStaffList();
                //?
                ViewAllStaffsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    ViewAllStaffsModel.addRow(row);
                }
            }
        });

        ViewAllStaffsScrollPane.setBounds(10, 40, 560, 400);
        ViewAllStaffsRefreshButton.setBounds(240, 520, 80, 25);

        ViewStaffListPart.add(ViewAllStaffsScrollPane);
        ViewStaffListPart.add(ViewAllStaffsRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view all staff part
         */
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view all BIDS part
         */
        managerViewAllBidsController managerViewAllBidsController = new managerViewAllBidsController();
        DefaultTableModel ViewAllBidsModel = managerViewAllBidsController.viewAllBids();

        JTable ViewAllBidsTable = new JTable(ViewAllBidsModel);
        ViewAllBidsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane ViewAllBidsScrollPane = new JScrollPane(ViewAllBidsTable);
        ViewAllBidsTable.setRowHeight(20);

        JButton ViewAllBidsRefreshButton=new JButton("refresh");

        ViewAllBidsRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerViewAllBidsController managerViewAllBidsController = new managerViewAllBidsController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = managerViewAllBidsController.refreshAllBids();
                //?
                ViewAllBidsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    ViewAllBidsModel.addRow(row);
                }
            }
        });

        ViewAllBidsScrollPane.setBounds(10, 40, 560, 400);
        ViewAllBidsRefreshButton.setBounds(240, 520, 80, 25);

        ViewAllBidsPart.add(ViewAllBidsScrollPane);
        ViewAllBidsPart.add(ViewAllBidsRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view all BIDS part
         */
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view Available slot part
         */
        managerViewAvailableSlotController managerViewAvailableSlotController = new managerViewAvailableSlotController();

        DefaultTableModel ViewAvailableSlotsModel = managerViewAvailableSlotController.viewAvailableSlotList();
        JTable ViewAvailableSlotsTable = new JTable(ViewAvailableSlotsModel);
        ViewAvailableSlotsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane ViewAvailableSlotsScrollPane = new JScrollPane(ViewAvailableSlotsTable);
        ViewAvailableSlotsTable.setRowHeight(20);

        JButton ViewAvailableSlotsRefreshButton=new JButton("refresh");

        ViewAvailableSlotsRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerViewAvailableSlotController managerViewAvailableSlotController = new managerViewAvailableSlotController();
                /**
                 * call view controller
                 */
                DefaultTableModel updatedModel = managerViewAvailableSlotController.refreshAvailableSlotList();
                //?
                ViewAvailableSlotsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    ViewAvailableSlotsModel.addRow(row);
                }
            }
        });

        ViewAvailableSlotsScrollPane.setBounds(10, 40, 560, 400);
        ViewAvailableSlotsRefreshButton.setBounds(240, 520, 80, 25);

        ViewNotFullSlotsPart.add(ViewAvailableSlotsScrollPane);
        ViewNotFullSlotsPart.add(ViewAvailableSlotsRefreshButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view Available slot part
         */



      /*  ////////////////////////////////////////////////////////////////////////////////////////////////////////
        *//**
         *
         * view offer work part
         */

        JLabel offerWorkTitleLabel=new JLabel("  Please enter Slot NO and Username to offer work to the staff ");
        JLabel offerWorkSlotNOLabel=new JLabel("  Slot NO :");
        JLabel offerWorkStaffNOLabel=new JLabel("  Username :");
        JLabel offerWorkRoleLabel=new JLabel("   Role :");
        offerWorkTitleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
        offerWorkSlotNOLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        offerWorkStaffNOLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        offerWorkRoleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));


        NumberFormat offerWorkSlotNOIntegerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter offerWorkSlotFormatter = new NumberFormatter(offerWorkSlotNOIntegerFormat);
        offerWorkSlotFormatter.setValueClass(Integer.class);
        offerWorkSlotFormatter.setAllowsInvalid(false);

        JFormattedTextField offerWorkSlotNOField = new JFormattedTextField(offerWorkSlotFormatter);
        JTextField offerWorkStaffNOField=new JTextField(15);

        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"chef", "cashier", "waiter"});

        JButton offerWorkAllocateButton = new JButton("allocate");
        JButton offerWorkClearButton = new JButton("clear");
        JButton offerWorkRefreshButton = new JButton("refresh");

        offerWorkAllocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerOfferWorkController managerOfferWorkController = new managerOfferWorkController();
                /**
                 * call offer work controller
                 */
                int slotNO =(int)offerWorkSlotNOField.getValue();
                String account=offerWorkStaffNOField.getText();
                String role = (String) roleComboBox.getSelectedItem();

                if (managerOfferWorkController.offerWork(slotNO,account,role)){
                    JOptionPane.showMessageDialog(managerPage.this, "You have successfully allocated the work of your staff");
                }else {
                    JOptionPane.showMessageDialog(managerPage.this, "Your input is not correct,can not allocated the work");
                }
            }
        });




        managerOfferWorkController managerOfferWorkController = new managerOfferWorkController();

        DefaultTableModel offerWorkViewAvailableSlotsModel = managerOfferWorkController.viewAvailableSlotList();
        JTable offerWorkViewAvailableSlotsTable = new JTable(offerWorkViewAvailableSlotsModel);
        ViewAvailableSlotsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane offerWorkViewAvailableSlotsScrollPane = new JScrollPane(offerWorkViewAvailableSlotsTable);
        offerWorkViewAvailableSlotsTable.setRowHeight(20);

        DefaultTableModel offerWorkViewAllStaffsModel = managerOfferWorkController.viewAllStaffList();

        JTable offerWorkViewAllStaffsTable = new JTable(offerWorkViewAllStaffsModel);
        ViewAllStaffsTable.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane offerWorkViewAllStaffsScrollPane = new JScrollPane(offerWorkViewAllStaffsTable);
        offerWorkViewAllStaffsTable.setRowHeight(20);


        offerWorkRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerOfferWorkController managerOfferWorkController = new managerOfferWorkController();
                /**
                 * call offer work controller(refresh)
                 */
                DefaultTableModel updatedModel = managerOfferWorkController.refreshAvailableSlotList();
                offerWorkViewAvailableSlotsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    offerWorkViewAvailableSlotsModel.addRow(row);
                }

                DefaultTableModel updatedModel2 = managerOfferWorkController.refreshAllStaffList();
                offerWorkViewAllStaffsModel.setRowCount(0);
                for (int i = 0; i < updatedModel2.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel2.getColumnCount(); j++) {
                        row.add(updatedModel2.getValueAt(i, j));
                    }
                    offerWorkViewAllStaffsModel.addRow(row);
                }
            }
        });

        offerWorkClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offerWorkSlotNOField.setText("");
                offerWorkStaffNOField.setText("");

            }
        });


        offerWorkTitleLabel.setBounds(20, 20, 550, 30);
        offerWorkSlotNOLabel.setBounds(60, 70, 150, 30);
        offerWorkStaffNOLabel.setBounds(60, 110, 150, 30);
        offerWorkRoleLabel.setBounds(60, 150, 150, 30);

        offerWorkSlotNOField.setBounds(200, 70, 150, 25);
        offerWorkStaffNOField.setBounds(200, 110, 150, 25);
        roleComboBox.setBounds(200, 150, 150, 25);

        offerWorkClearButton.setBounds(390, 70, 80, 25);
        offerWorkAllocateButton.setBounds(390, 110, 80, 25);
        offerWorkRefreshButton.setBounds(390, 150, 80, 25);

        offerWorkViewAvailableSlotsScrollPane.setBounds(20, 200, 380, 330);
        offerWorkViewAllStaffsScrollPane.setBounds(410, 200, 150, 330);


        OfferWorkSlotPart.add(offerWorkTitleLabel);
        OfferWorkSlotPart.add(offerWorkSlotNOLabel);
        OfferWorkSlotPart.add(offerWorkStaffNOLabel);
        OfferWorkSlotPart.add(offerWorkRoleLabel);

        OfferWorkSlotPart.add(offerWorkSlotNOField);
        OfferWorkSlotPart.add(offerWorkStaffNOField);
        OfferWorkSlotPart.add(roleComboBox);

        OfferWorkSlotPart.add(offerWorkClearButton);
        OfferWorkSlotPart.add(offerWorkAllocateButton);
        OfferWorkSlotPart.add(offerWorkRefreshButton);

        OfferWorkSlotPart.add(offerWorkViewAvailableSlotsScrollPane);
        OfferWorkSlotPart.add(offerWorkViewAllStaffsScrollPane);

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * Handle Bids Part handleBids
         */

        JLabel handleBidsLabel=new JLabel("Please enter enter Bid id approve or reject :");

        NumberFormat handleBidsIntegerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter handleBidsFormatter = new NumberFormatter(handleBidsIntegerFormat);
        handleBidsFormatter.setValueClass(Integer.class);
        handleBidsFormatter.setAllowsInvalid(false);

        JFormattedTextField handleBidsField = new JFormattedTextField(handleBidsFormatter);
        handleBidsField.setColumns(10); // Set the desired number of columns



        JButton handleBidsApproveButton = new JButton("approve");
        JButton handleBidsRejectButton = new JButton("reject");

        JButton handleBidsRefreshButton = new JButton("refresh");


        managerApproveBidsController managerApproveBidsController = new managerApproveBidsController();

        DefaultTableModel handleBidsModel = managerApproveBidsController.viewPendingBids();

        JTable handleBidsTable = new JTable(handleBidsModel);

        handleBidsTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane handleBidsScrollPane = new JScrollPane(handleBidsTable);
        handleBidsTable.setRowHeight(20);
        handleBidsApproveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bidNO = (int)handleBidsField.getValue();

                managerApproveBidsController managerApproveBidsController = new managerApproveBidsController();


                if (managerApproveBidsController.approveBid(bidNO)){
                    JOptionPane.showMessageDialog(managerPage.this, "Your approving is success ");
                }else {
                    JOptionPane.showMessageDialog(managerPage.this,
                            "Your approving is not success,please check your input value");
                }
            }
        });



        handleBidsRejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bidNO = (int)handleBidsField.getValue();

                managerRejectBidsController managerRejectBidsController = new managerRejectBidsController();

                // call reject controller
                if (!managerRejectBidsController.rejectBid(bidNO)){
                    JOptionPane.showMessageDialog(managerPage.this, "Your reject process is success ");
                }else {
                    JOptionPane.showMessageDialog(managerPage.this,
                            "Your reject process is not success,please check your input value");
                }
            }
        });



        handleBidsRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerApproveBidsController managerApproveBidsController = new managerApproveBidsController();


                DefaultTableModel updatedModel = managerApproveBidsController.refreshPendingBids();
                //?
                handleBidsModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    handleBidsModel.addRow(row);
                }
            }
        });

        handleBidsLabel.setBounds(30, 20, 350, 30);
        handleBidsField.setBounds(30, 50, 250, 30);

        handleBidsApproveButton.setBounds(380, 40, 100, 30);
        handleBidsRejectButton.setBounds(380, 90, 100, 30);

        handleBidsScrollPane.setBounds(30, 130, 480, 260);
        handleBidsRefreshButton.setBounds(250, 400, 80, 25);

        HandleBidsPart.add(handleBidsLabel);
        HandleBidsPart.add(handleBidsField);
        HandleBidsPart.add(handleBidsApproveButton);
        HandleBidsPart.add(handleBidsRejectButton);
        HandleBidsPart.add(handleBidsScrollPane);
        HandleBidsPart.add(handleBidsRefreshButton);




        JButton exitButton = new JButton("Log out");
        exitButton.setBounds(240, 110, 100, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmationResult = JOptionPane.showConfirmDialog(
                        managerPage.this,
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


        controlTab =new JTabbedPane(JTabbedPane.LEFT);
        controlTab.add("View Slots List", ViewAllSlotsPart);
        controlTab.add("View Staff List", ViewStaffListPart);
        controlTab.add("View All Bids", ViewAllBidsPart);
        controlTab.add("View Available Slots", ViewNotFullSlotsPart);
        controlTab.add("Offer Works", OfferWorkSlotPart);
        controlTab.add("Handle Bids", HandleBidsPart);
        controlTab.add("Log out", exitPart);
        controlTab.setFont(new Font("STYLE_BOLD", Font.PLAIN, 20));
        this.add(controlTab);

        //this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); // Set frame visibility after adding components

    }
    public void logOut(){
        dispose();
        new loginPage();
    }
}
