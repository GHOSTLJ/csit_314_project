package PROJECTS.boundary_layer;

import PROJECTS.control_layer.cafeOwner.ownerAddController;
import PROJECTS.control_layer.cafeOwner.ownerViewController;
import PROJECTS.control_layer.systemAdmin.adminViewController;
import PROJECTS.entity_layer.workSlots;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ownerWorkSpace extends JFrame {
    public ownerWorkSpace() {
        super("owner workspace");
        JTabbedPane controlTab; //定义选项卡
        JPanel viewPart, addPart, searchPart,updatePart,deletePart,exitPart;	//定义面板


        viewPart= new JPanel();
        addPart = new JPanel();
        searchPart = new JPanel();
        updatePart= new JPanel();
        deletePart = new JPanel();
        exitPart=new JPanel();


        //设置侧面背景

        addPart.setBackground(Color.GRAY);
        viewPart.setBackground(Color.GRAY);
        searchPart.setBackground(Color.GRAY);
        deletePart.setBackground(Color.GRAY);
        exitPart.setBackground(Color.GRAY);



        addPart.setLayout(null);//自由布局
        //addPart.setLayout(null);//自由布局
        searchPart.setLayout(null);//自由布局
        exitPart.setLayout(null);//自由布局

/**
 *
 * add part
 */
//////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel addNewSlotStartTimeLabel=new JLabel(" Select Start Date and Time: ");
        JLabel addNewSlotEndTimeLabel=new JLabel(" Select End Date and Time: ");
        JLabel chefNeedLabel=new JLabel(" Select chef required : ");
        JLabel cashierNeedLabel=new JLabel(" Select cashier required : ");
        JLabel waiterNeedLabel=new JLabel(" Select waiter required : ");

        // Create a SpinnerDateModel with initial date, minimum, and maximum values
        SpinnerDateModel startSpinnerDateModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        SpinnerDateModel endSpinnerDateModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        JSpinner startDateTimeSpinner = new JSpinner(startSpinnerDateModel);
        JSpinner endDateTimeSpinner = new JSpinner(endSpinnerDateModel);

        // Set date format for the spinner's editor
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateTimeSpinner, "yyyy-MM-dd HH:mm");
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateTimeSpinner, "yyyy-MM-dd HH:mm");
        startDateTimeSpinner.setEditor(startEditor);
        endDateTimeSpinner.setEditor(endEditor);

        JComboBox<Integer> chefComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5}); // Example values for chefs
        JComboBox<Integer> cashierComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5}); // Example values for cashiers
        JComboBox<Integer> waiterComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5}); // Example values for waiters

        JButton submitButton = new JButton("Submit");


        submitButton.addActionListener(e -> {
            Date selectedStartTime = (Date) startDateTimeSpinner.getValue();
            Date selectedEndTime = (Date) endDateTimeSpinner.getValue();


            Timestamp startTime = new Timestamp(selectedStartTime.getTime());
            Timestamp endTime = new Timestamp(selectedEndTime.getTime());

            int chef = (int) chefComboBox.getSelectedItem(); // Get selected chef value from the combo box
            int cashier = (int) cashierComboBox.getSelectedItem(); // Get selected cashier value from the combo box
            int waiter = (int) waiterComboBox.getSelectedItem(); // Get selected waiter value from the combo box

            // Calculate the time difference in milliseconds
            long timeDifferenceInMillis = selectedEndTime.getTime() - selectedStartTime.getTime();
            double hourDifference = Math.round((timeDifferenceInMillis / 60000.0) / 60.0 * 10000.0) / 10000.0;

            Map<String, Object> slotAddInfo = new HashMap<>();
            slotAddInfo.put("startTime", startTime);
            slotAddInfo.put("endTime", endTime);
            slotAddInfo.put("hours", hourDifference);
            slotAddInfo.put("chef", chef);
            slotAddInfo.put("cashier", cashier);
            slotAddInfo.put("waiter", waiter);


            ownerAddController ownerAddController = new ownerAddController();
            //ownerAddController.addVerification(slotAddInfo);

            if (ownerAddController.addVerification(slotAddInfo)){
                JOptionPane.showMessageDialog(ownerWorkSpace.this, "The new user has been successfully added to the database");
            }else {
                JOptionPane.showMessageDialog(ownerWorkSpace.this,
                        "Your input time is not valid, please re-select, (can not less then 1 hour or higher then 8 hours ");
            }

        });

        addNewSlotStartTimeLabel.setBounds(70, 50, 180, 30);
        addNewSlotEndTimeLabel.setBounds(70, 90, 180, 30);
        startDateTimeSpinner.setBounds(230, 55, 150, 25);
        endDateTimeSpinner.setBounds(230, 95, 150, 25);

        chefNeedLabel.setBounds(70, 140, 150, 25);
        cashierNeedLabel.setBounds(70, 180, 150, 25);
        waiterNeedLabel.setBounds(70, 220, 150, 25);

        chefComboBox.setBounds(230, 140, 150, 25);
        cashierComboBox.setBounds(230, 180, 150, 25);
        waiterComboBox.setBounds(230, 220, 150, 25);

        submitButton.setBounds(230, 330, 80, 25);
        addPart.add(addNewSlotStartTimeLabel);
        addPart.add(addNewSlotEndTimeLabel);

        addPart.add(startDateTimeSpinner);
        addPart.add(endDateTimeSpinner);

        addPart.add(chefNeedLabel);
        addPart.add(cashierNeedLabel);
        addPart.add(waiterNeedLabel);

        addPart.add(chefComboBox);
        addPart.add(cashierComboBox);
        addPart.add(waiterComboBox);

        addPart.add(submitButton);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of add part
         */

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * view part
         */
        ownerViewController ownerViewController = new ownerViewController();
        DefaultTableModel myModel = ownerViewController.viewAllSlotList();
        JTable table = new JTable(myModel);
        table.setPreferredScrollableViewportSize(new Dimension(520, 380));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(20);
        JButton refreshButton=new JButton("refresh");
        refreshButton.setBounds(210, 10, 80, 25);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerViewController ownerViewController = new ownerViewController();
                DefaultTableModel updatedModel = ownerViewController.refreshAllSlotList();
                //?
                myModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    myModel.addRow(row);
                }
            }
        });


        viewPart.add(scrollPane);
        viewPart.add(refreshButton);


















        JButton exitButton = new JButton("Log out");
        exitButton.setBounds(220, 110, 100, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmationResult = JOptionPane.showConfirmDialog(
                        ownerWorkSpace.this,
                        "Are you sure you want log out ?",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );
                if (confirmationResult == JOptionPane.OK_OPTION) {
                    logOut();
                    /*dispose();
                    new loginScreen();*/
                } else {
                    // User clicked Cancel, do nothing or provide appropriate action
                }
            }
        });
        exitPart.add(exitButton);

        controlTab =new JTabbedPane(JTabbedPane.LEFT); //创建选项卡并使选项卡垂直排列
        controlTab.add("Add New Slot", addPart);
        controlTab.add("View Slot", viewPart);
        controlTab.add("Update Slot", updatePart);
        controlTab.add("Search Slot", searchPart);
        controlTab.add("Delete Slot", deletePart);
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
        new loginScreen();
    }
}
