package PROJECTS.boundary_layer;

import PROJECTS.control_layer.systemAdmin.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class adminPage extends JFrame {
    public adminPage(String name ) {
        super("Welcome "+name+" : "+" to use Admin workspace");
        JTabbedPane controlTab;
        JPanel viewPart, addPart, searchPart, updatePart, suspendPart, exitPart;

        viewPart = new JPanel();
        addPart = new JPanel();
        searchPart = new JPanel();
        updatePart = new JPanel();
        suspendPart = new JPanel();
        exitPart = new JPanel();

        //view user part
        /**
         * view user part
         *
         */
        adminViewUserController adminViewController = new adminViewUserController();

        DefaultTableModel myModel = adminViewController.viewAllUserList();
        JTable table = new JTable(myModel);
        table.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(20);
        JButton refreshButton=new JButton("refresh");
        refreshButton.setBounds(210, 10, 80, 25);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminViewUserController adminViewController = new adminViewUserController();
                DefaultTableModel updatedModel = adminViewController.refreshAllUserList();
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



        //add part
        /**
         * add user part
         */

        ToolTipManager.sharedInstance().setInitialDelay(100);
        JLabel addAccountLabel=new JLabel("   Account :");
        JLabel addPasswordLabel=new JLabel("Password :");
        JLabel addNameLabel=new JLabel("       Name :");
        JLabel addProfileNoLabel=new JLabel("Profile No :");

        addAccountLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        addPasswordLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        addNameLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        addProfileNoLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));

        ImageIcon scaledIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/PROJECTS/image/questionMark.png"))
                        .getImage()
                        .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        );
        JLabel questionMarkLabel = new JLabel(scaledIcon);
        questionMarkLabel.setToolTipText("<html>1. Administrator<br>2. Cafe owner<br>3. Manager<br>4. Staff</html>");


        JTextField addAccountField=new JTextField(15);
        JTextField addPasswordField=new JTextField(15);
        JTextField addNameField=new JTextField(15);
        JTextField addProfileNoField=new JTextField(15);
        JButton submitButton=new JButton("submit");
        JButton clearButton=new JButton("clear");


        addAccountLabel.setBounds(90, 100, 150, 30);
        addPasswordLabel.setBounds(90, 130, 150, 30);
        addNameLabel.setBounds(90, 160, 150, 30);
        addProfileNoLabel.setBounds(90, 190, 150, 30);
        // Set the bounds for the question mark label
        questionMarkLabel.setBounds(400, 190, 30, 30);


        addAccountField.setBounds(230, 100, 150, 25);
        addPasswordField.setBounds(230, 130, 150, 25);
        addNameField.setBounds(230, 160, 150, 25);
        addProfileNoField.setBounds(230, 190, 150, 25);

        submitButton.setBounds(310, 270, 80, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = addAccountField.getText();
                String password = addPasswordField.getText();
                String name = addNameField.getText();
                String profile = addProfileNoField.getText();


                adminAddUserController user = new adminAddUserController();
                if (user.addVerification(account,password,name,profile)){
                    JOptionPane.showMessageDialog(adminPage.this, "The new user has been successfully added to the database");
                }else {
                    JOptionPane.showMessageDialog(adminPage.this,
                            "Your input is not valid, please re-enter, (account characters must not be less than 4," +
                                    " password must not be less than 6, " +
                            "name must not be empty, profile input please follow (1,2,3,4))");
                }
            }
        });


        clearButton.setBounds(210, 270, 80, 25);
        // Clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccountField.setText("");
                addPasswordField.setText("");
                addNameField.setText("");
                addProfileNoField.setText("");
            }
        });

        //search part
        /**
         * search user part
         */

        //search part面板上的内容
        String[][] datas = {};
        String[] titles = { "Account", "Password","Name","Profile","Status" };
        JLabel searchAccountLabel=new JLabel("Please enter the account you want to search :");
        JTextField searchAccountField=new JTextField(15);
        JButton searchButton=new JButton("Search");


        DefaultTableModel searchModel = new DefaultTableModel(datas,titles);//创建对象直接从数据库调取 aDM class
        /*DefaultTableModel searchModel = adminDataManipulation.viewFromDatabase();//创建对象直接从数据库调取 aDM class*/
        JTable searchTable = new JTable(searchModel);
        //JTable searchTable = new JTable(myModel);
        searchTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane searchScrollPane = new JScrollPane(searchTable);
        searchTable.setRowHeight(20);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = searchAccountField.getText();
                adminSearchUserController user = new adminSearchUserController();

                // Add the new row to the searchModel
                searchModel.setRowCount(0);
                searchModel.addRow(user.searchUser(account));
            }
        });


        searchAccountLabel.setBounds(30, 60, 350, 30);
        searchAccountField.setBounds(50, 110, 250, 30);
        searchButton.setBounds(370, 110, 100, 30);
        searchScrollPane.setBounds(50, 150, 450, 60);


        /**
         * update user part
         *
         */

        JLabel updateTitleLabel=new JLabel("  Please enter account to update user ");
        JLabel updateAccountLabel=new JLabel("   Account :");
        JLabel updatePasswordLabel=new JLabel("Password :");
        JLabel updateNameLabel=new JLabel("       Name :");
        JLabel updateProfileNoLabel=new JLabel("Profile No :");

        ImageIcon updateScaledIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/PROJECTS/image/questionMark.png"))
                        .getImage()
                        .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        );
        JLabel updateQuestionMarkLabel = new JLabel(updateScaledIcon);
        updateQuestionMarkLabel.setToolTipText("<html>1. Administrator<br>2. Cafe owner<br>3. Manager<br>4. Staff</html>");

        updateTitleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        updateAccountLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        updatePasswordLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        updateNameLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        updateProfileNoLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));

        JTextField updateAccountField=new JTextField(15);
        JTextField updatePasswordField=new JTextField(15);
        JTextField updateNameField=new JTextField(15);
        JTextField updateProfileNoField=new JTextField(15);
        // Set the bounds for the question mark label
        updateQuestionMarkLabel.setBounds(400, 190, 30, 30);
        JButton updateSubmitButton=new JButton("submit");
        JButton updateClearButton=new JButton("clear");

        updateTitleLabel.setBounds(50, 60, 450, 30);
        updateAccountLabel.setBounds(90, 100, 150, 30);
        updatePasswordLabel.setBounds(90, 130, 150, 30);
        updateNameLabel.setBounds(90, 160, 150, 30);
        updateProfileNoLabel.setBounds(90, 190, 150, 30);

        updateAccountField.setBounds(230, 100, 150, 25);
        updatePasswordField.setBounds(230, 130, 150, 25);
        updateNameField.setBounds(230, 160, 150, 25);
        updateProfileNoField.setBounds(230, 190, 150, 25);
        updateSubmitButton.setBounds(310, 270, 80, 25);

        updateSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = updateAccountField.getText();
                String password = updatePasswordField.getText();
                String name = updateNameField.getText();
                String profile = updateProfileNoField.getText();

                adminUpdateUserController user = new adminUpdateUserController();

                int confirmationResult = JOptionPane.showConfirmDialog(
                        adminPage.this,
                        "Are you sure you want to update the user?",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (confirmationResult == JOptionPane.OK_OPTION) {
                    if (user.updateVerification(account, password, name, profile)) {
                        JOptionPane.showMessageDialog(adminPage.this, "The new user has been successfully updated to the database");
                    } else {
                        JOptionPane.showMessageDialog(adminPage.this,
                                "Your input is not valid, please re-enter, (account characters must not be less than 4," +
                                        " password must not be less than 6, " +
                                        "name must not be empty, profile input please follow (1,2,3,4))");
                    }
                } else {
                    // User clicked Cancel, do nothing or provide appropriate action
                }
            }
        });
        updateClearButton.setBounds(210, 270, 80, 25);
        // Clear button
        updateClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAccountField.setText("");
                updatePasswordField.setText("");
                updateNameField.setText("");
                updateProfileNoField.setText("");
            }
        });

        /**
         * suspend part
         *
         */
        JLabel suspendAccountLabel=new JLabel("Please enter the account you want to suspend :");
        JTextField suspendAccountField=new JTextField(15);
        JButton suspendButton=new JButton("Suspend");

        adminSuspendUserController adminSuspendController = new adminSuspendUserController();

        DefaultTableModel suspendModel = adminSuspendController.viewAllUserList();
        JTable suspendTable = new JTable(suspendModel);
        suspendTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane suspendScrollPane = new JScrollPane(suspendTable);
        suspendTable.setRowHeight(20);
        JButton suspendRefreshButton=new JButton("refresh");
        refreshButton.setBounds(210, 10, 80, 25);
        suspendRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSuspendUserController adminSuspendController = new adminSuspendUserController();
                DefaultTableModel updatedModel = adminSuspendController.refreshAllUserList();
                //?
                suspendModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    suspendModel.addRow(row);
                }
            }
        });










        suspendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = suspendAccountField.getText();


                int confirmationResult = JOptionPane.showConfirmDialog(
                        adminPage.this,
                        "Are you sure you want to suspend this user? This user will not be able to log in",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );
                if (confirmationResult == JOptionPane.OK_OPTION) {
                    adminSuspendUserController user = new adminSuspendUserController();
                    boolean suspendSuccess = user.suspendAccount(account);
                    if (suspendSuccess) {
                        JOptionPane.showMessageDialog(adminPage.this, "The user has been successfully suspended");
                    } else {
                        JOptionPane.showMessageDialog(adminPage.this,
                                "Your input is not valid, or this user account has been deactivated" );
                    }
                } else {
                    // User clicked Cancel, do nothing or provide appropriate action
                }
            }
        });

        suspendAccountLabel.setBounds(30, 60, 350, 30);
        suspendAccountField.setBounds(50, 110, 250, 30);
        suspendButton.setBounds(370, 110, 100, 30);

        suspendScrollPane.setBounds(50, 150, 450, 250);
        suspendRefreshButton.setBounds(200, 410, 100, 30);
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        viewPart.setBackground(Color.PINK);
        addPart.setBackground(Color.PINK);
        searchPart.setBackground(Color.PINK);
        updatePart.setBackground(Color.PINK);
        suspendPart.setBackground(Color.PINK);
        exitPart.setBackground(Color.PINK);

        viewPart.setLayout(null);
        addPart.setLayout(null);
        searchPart.setLayout(null);
        updatePart.setLayout(null);
        suspendPart.setLayout(null);
        exitPart.setLayout(null);

        scrollPane.setBounds(50, 50, 450, 350);

        JButton exitButton = new JButton("Log out");
        exitButton.setBounds(220, 110, 100, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmationResult = JOptionPane.showConfirmDialog(
                        adminPage.this,
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

        viewPart.add(scrollPane);
        viewPart.add(refreshButton);

        addPart.add(addAccountLabel);
        addPart.add(addPasswordLabel);
        addPart.add(addNameLabel);
        addPart.add(addProfileNoLabel);
        addPart.add(questionMarkLabel);


        //questionMarkLabel.setToolTipText("hello");

        addPart.add(addAccountField);
        addPart.add(addPasswordField);
        addPart.add(addNameField);
        addPart.add(addProfileNoField);
        addPart.add(submitButton);
        addPart.add(clearButton);


        searchPart.add(searchAccountLabel);
        searchPart.add(searchAccountField);
        searchPart.add(searchButton);
        searchPart.add(searchScrollPane);


        updatePart.add(updateTitleLabel);
        updatePart.add(updateAccountLabel);
        updatePart.add(updatePasswordLabel);
        updatePart.add(updateNameLabel);
        updatePart.add(updateProfileNoLabel);
        updatePart.add(updateQuestionMarkLabel);

        updatePart.add(updateAccountField);
        updatePart.add(updatePasswordField);
        updatePart.add(updateNameField);
        updatePart.add(updateProfileNoField);
        updatePart.add(updateSubmitButton);
        updatePart.add(updateClearButton);


        suspendPart.add(suspendAccountLabel);
        suspendPart.add(suspendAccountField);
        suspendPart.add(suspendButton);
        suspendPart.add(suspendScrollPane);
        suspendPart.add(suspendRefreshButton);


        controlTab = new JTabbedPane(JTabbedPane.LEFT);
        controlTab.add("View user list", viewPart);
        controlTab.add("Add user", addPart);
        controlTab.add("Search user", searchPart);
        controlTab.add("Update user", updatePart);
        controlTab.add("Suspend user", suspendPart);
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
