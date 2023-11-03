package PROJECTS.boundary_layer;

import PROJECTS.control_layer.cafeOwner.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ownerPage extends JFrame {
    public ownerPage(String name) {
        super("Welcome "+name+" : "+" to use cafe owner workspace");
        JTabbedPane controlTab;
        JPanel viewPart, addPart, searchPart,updatePart,deletePart,exitPart;	//定义面板


        viewPart= new JPanel();
        addPart = new JPanel();
        searchPart = new JPanel();
        updatePart= new JPanel();
        deletePart = new JPanel();
        exitPart=new JPanel();


        addPart.setBackground(Color.GRAY);
        viewPart.setBackground(Color.GRAY);
        searchPart.setBackground(Color.GRAY);
        updatePart.setBackground(Color.GRAY);
        deletePart.setBackground(Color.GRAY);
        exitPart.setBackground(Color.GRAY);



        addPart.setLayout(null);//自由布局
        searchPart.setLayout(null);//自由布局
        updatePart.setLayout(null);//自由布局
        deletePart.setLayout(null);//自由布局
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
/**
 * call add controller
 */
            if (ownerAddController.addVerification(slotAddInfo)){
                JOptionPane.showMessageDialog(ownerPage.this, "The new slot has been successfully added to the database");
            }else {
                JOptionPane.showMessageDialog(ownerPage.this,
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
/**
 * call view controller
 */
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
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of view part
         */


/**
 *
 * search Part
 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        String[][] data = {};
        String[] titles = { "slot ID", "start date","end date","hours","chef need","cashier need","waiter need" };
        JLabel searchSlotLabel=new JLabel("Please select the Slot start date you want to search :");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
// Create the SpinnerDateModel with the current date, null minimum and maximum values, and DAY_OF_MONTH field
        SpinnerDateModel searchStartSpinnerDateModel = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner searchStartSpinner = new JSpinner(searchStartSpinnerDateModel);
// Set the editor for the JSpinner to display the date in the desired format
        JSpinner.DateEditor editor = new JSpinner.DateEditor(searchStartSpinner, "yyyy/MM/dd");
        searchStartSpinner.setEditor(editor);

        JButton searchButton = new JButton("Search");

        DefaultTableModel searchModel = new DefaultTableModel(data, titles);
        JTable searchTable = new JTable(searchModel);

        searchTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane searchScrollPane = new JScrollPane(searchTable);
        searchTable.setRowHeight(20);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedStartTime = (Date) searchStartSpinner.getValue();
                // Format the selectedStartTime to match the desired format "yyyy-MM-dd"
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(selectedStartTime);
                ownerSearchController ownerSearchController= new ownerSearchController();
/**
* call search controller
*/
                DefaultTableModel updatedModel =ownerSearchController.searchSlot(formattedDate);
                // Set the table model to the updated model
                searchTable.setModel(updatedModel);
            }
        });

        searchSlotLabel.setBounds(30, 60, 350, 30);
        searchStartSpinner.setBounds(50, 110, 250, 30);
        searchButton.setBounds(370, 110, 100, 30);
        searchScrollPane.setBounds(50, 150, 450, 280);

        searchPart.add(searchSlotLabel);
        searchPart.add(searchStartSpinner);
        searchPart.add(searchButton);
        searchPart.add(searchScrollPane);
////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of search part
         */


/**
 *
 * update Part
 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        JLabel updateTitleLabel=new JLabel(" Enter slot No : ");
        //JTextField searchAccountField=new JTextField(15);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);

        JFormattedTextField integerField = new JFormattedTextField(formatter);
        integerField.setColumns(10); // Set the desired number of columns

        JLabel updateNewSlotStartTimeLabel=new JLabel(" Select Start Date and Time: ");
        JLabel updateNewSlotEndTimeLabel=new JLabel(" Select End Date and Time: ");
        JLabel updateChefNeedLabel=new JLabel(" Select chef required : ");
        JLabel updateCashierNeedLabel=new JLabel(" Select cashier required : ");
        JLabel updateWaiterNeedLabel=new JLabel(" Select waiter required : ");

        // Create a SpinnerDateModel with initial date, minimum, and maximum values
        SpinnerDateModel updateStartSpinnerDateModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        SpinnerDateModel updateEndSpinnerDateModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        JSpinner updateStartDateTimeSpinner = new JSpinner(updateStartSpinnerDateModel);
        JSpinner updateEndDateTimeSpinner = new JSpinner(updateEndSpinnerDateModel);

        // Set date format for the spinner's editor
        JSpinner.DateEditor updateStartEditor = new JSpinner.DateEditor(startDateTimeSpinner, "yyyy-MM-dd HH:mm");
        JSpinner.DateEditor updateEndEditor = new JSpinner.DateEditor(endDateTimeSpinner, "yyyy-MM-dd HH:mm");
        startDateTimeSpinner.setEditor(updateStartEditor);
        endDateTimeSpinner.setEditor(updateEndEditor);

        JComboBox<Integer> updateChefComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5}); // Example values for chefs
        JComboBox<Integer> updateCashierComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5}); // Example values for cashiers
        JComboBox<Integer> updateWaiterComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5}); // Example values for waiters

        JButton updateSubmitButton = new JButton("Update");


        updateSubmitButton.addActionListener(e -> {
            int SlotNo = (int)integerField.getValue();;

            Date selectedStartTime = (Date) updateStartDateTimeSpinner.getValue();
            Date selectedEndTime = (Date) updateEndDateTimeSpinner.getValue();


            Timestamp startTime = new Timestamp(selectedStartTime.getTime());
            Timestamp endTime = new Timestamp(selectedEndTime.getTime());

            int chef = (int) updateChefComboBox.getSelectedItem(); // Get selected chef value from the combo box
            int cashier = (int) updateCashierComboBox.getSelectedItem(); // Get selected cashier value from the combo box
            int waiter = (int) updateWaiterComboBox.getSelectedItem(); // Get selected waiter value from the combo box

            // Calculate the time difference in milliseconds
            long timeDifferenceInMillis = selectedEndTime.getTime() - selectedStartTime.getTime();
            double hourDifference = Math.round((timeDifferenceInMillis / 60000.0) / 60.0 * 10000.0) / 10000.0;

            Map<String, Object> slotUpdateInfo = new HashMap<>();
            slotUpdateInfo.put("SlotNo", SlotNo);
            slotUpdateInfo.put("startTime", startTime);
            slotUpdateInfo.put("endTime", endTime);
            slotUpdateInfo.put("hours", hourDifference);
            slotUpdateInfo.put("chef", chef);
            slotUpdateInfo.put("cashier", cashier);
            slotUpdateInfo.put("waiter", waiter);

            ownerUpdateController ownerUpdateController = new ownerUpdateController();
/**
 * call update controller
 */
            if (ownerUpdateController.updateSlot(slotUpdateInfo)){
                JOptionPane.showMessageDialog(ownerPage.this, "The slot has been successfully updated to the database");
            }else {
                JOptionPane.showMessageDialog(ownerPage.this,
                        "The slot is not exist or Your input time is not valid, please re-select, (can not less then 1 hour or higher then 8 hours) ");
            }
        });


        updateTitleLabel.setBounds(90, 50, 180, 30);
        integerField.setBounds(250, 50, 150, 25);

        updateNewSlotStartTimeLabel.setBounds(90, 80, 180, 30);
        updateNewSlotEndTimeLabel.setBounds(90, 120, 180, 30);
        updateStartDateTimeSpinner.setBounds(250, 85, 150, 25);
        updateEndDateTimeSpinner.setBounds(250, 125, 150, 25);

        updateChefNeedLabel.setBounds(90, 170, 150, 25);
        updateCashierNeedLabel.setBounds(90, 210, 150, 25);
        updateWaiterNeedLabel.setBounds(90, 250, 150, 25);

        updateChefComboBox.setBounds(250, 170, 150, 25);
        updateCashierComboBox.setBounds(250, 210, 150, 25);
        updateWaiterComboBox.setBounds(250, 250, 150, 25);

        updateSubmitButton.setBounds(250, 360, 100, 25);
        updatePart.add(updateTitleLabel);
        updatePart.add(integerField);

        updatePart.add(updateNewSlotStartTimeLabel);
        updatePart.add(updateNewSlotEndTimeLabel);

        updatePart.add(updateStartDateTimeSpinner);
        updatePart.add(updateEndDateTimeSpinner);

        updatePart.add(updateChefNeedLabel);
        updatePart.add(updateCashierNeedLabel);
        updatePart.add(updateWaiterNeedLabel);

        updatePart.add(updateChefComboBox);
        updatePart.add(updateCashierComboBox);
        updatePart.add(updateWaiterComboBox);

        updatePart.add(updateSubmitButton);

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of update part
         */




/**
 *
 * delete Part
 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        NumberFormat deleteIntegerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter deleteFormatter = new NumberFormatter(deleteIntegerFormat);
        deleteFormatter.setValueClass(Integer.class);
        deleteFormatter.setAllowsInvalid(false);

        JFormattedTextField deleteIntegerField = new JFormattedTextField(deleteFormatter);
        deleteIntegerField.setColumns(10); // Set the desired number of columns

        JLabel suspendAccountLabel=new JLabel("Please enter the Slot NO you want to suspend :");
        //JTextField suspendAccountField=new JTextField(15);
        JButton suspendButton=new JButton("Suspend");

        suspendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int SlotNo = (int)deleteIntegerField.getValue();;


                int confirmationResult = JOptionPane.showConfirmDialog(
                        ownerPage.this,
                        "Are you sure you want to suspend this slot? This user will not be show ",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );
                if (confirmationResult == JOptionPane.OK_OPTION) {
                    ownerSuspendController ownerSuspendController= new ownerSuspendController();
/**
 * call suspend controller
 */
                    boolean suspendSuccess = ownerSuspendController.suspendSlot(SlotNo);
                    if (suspendSuccess) {
                        JOptionPane.showMessageDialog(ownerPage.this, "The slot has been successfully suspended");
                    } else {
                        JOptionPane.showMessageDialog(ownerPage.this,
                                "Your input is not exist, or this slot has been suspended" );
                    }
                } else {
                    // User clicked Cancel, do nothing or provide appropriate action
                }
            }
        });
        suspendAccountLabel.setBounds(30, 60, 350, 30);
        deleteIntegerField.setBounds(50, 110, 250, 30);
        suspendButton.setBounds(370, 110, 100, 30);


        deletePart.add(suspendAccountLabel);
        deletePart.add(deleteIntegerField);
        deletePart.add(suspendButton);

////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *
         * end of delete part
         */
        JButton exitButton = new JButton("Log out");
        exitButton.setBounds(220, 110, 100, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmationResult = JOptionPane.showConfirmDialog(
                        ownerPage.this,
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
        controlTab.add("Add New Slot", addPart);
        controlTab.add("View Slot", viewPart);
        controlTab.add("Update Slot", updatePart);
        controlTab.add("Search Slot", searchPart);
        controlTab.add("Suspend Slot", deletePart);
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
