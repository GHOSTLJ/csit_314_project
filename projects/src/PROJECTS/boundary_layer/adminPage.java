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
        JPanel viewPart, addPart, searchPart, updatePart, suspendPart, exitPart,activePart;

        viewPart = new JPanel();
        addPart = new JPanel();
        searchPart = new JPanel();
        updatePart = new JPanel();
        suspendPart = new JPanel();
        activePart= new JPanel();
        exitPart = new JPanel();

        JPanel viewProfilePart = new JPanel();
        JPanel addProfilePart = new JPanel();
        JPanel searchProfilePart = new JPanel();
        JPanel updateProfilePart = new JPanel();
        JPanel suspendProfilePart = new JPanel();
        JPanel activeProfilePart = new JPanel();

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
        scrollPane.setBounds(50, 50, 450, 350);
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

        /*ImageIcon scaledIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/PROJECTS/image/questionMark.png"))
                        .getImage()
                        .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        );
        JLabel questionMarkLabel = new JLabel(scaledIcon);
        questionMarkLabel.setToolTipText("<html>1. Administrator<br>2. Cafe owner<br>3. Manager<br>4. Staff</html>");*/
        adminAddUserController adminAddUserController = new adminAddUserController();
        DefaultTableModel addUserProfileModel = adminAddUserController.viewProfileList();
        JTable addUserProfileTable = new JTable(addUserProfileModel);
        addUserProfileTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane addUserProfileScrollPane = new JScrollPane(addUserProfileTable);
        addUserProfileTable.setRowHeight(20);

        addUserProfileScrollPane.setBounds(60, 230, 400, 200);


        JTextField addAccountField=new JTextField(15);
        JTextField addPasswordField=new JTextField(15);
        JTextField addNameField=new JTextField(15);
        JTextField addProfileNoField=new JTextField(15);
        JButton submitButton=new JButton("submit");
        JButton clearButton=new JButton("clear");
        JButton addRefreshButton=new JButton("refresh");



        addAccountLabel.setBounds(90, 60, 150, 30);
        addPasswordLabel.setBounds(90, 90, 150, 30);
        addNameLabel.setBounds(90, 120, 150, 30);
        addProfileNoLabel.setBounds(90, 150, 150, 30);
        // Set the bounds for the question mark label
        //questionMarkLabel.setBounds(400, 150, 30, 30);

        addAccountField.setBounds(230, 60, 150, 25);
        addPasswordField.setBounds(230, 90, 150, 25);
        addNameField.setBounds(230, 120, 150, 25);
        addProfileNoField.setBounds(230, 150, 150, 25);

        clearButton.setBounds(150, 190, 80, 25);
        submitButton.setBounds(250, 190, 80, 25);
        addRefreshButton.setBounds(350, 190, 80, 25);


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

        addRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminUpdateUserController adminUpdateUserController = new adminUpdateUserController();
                DefaultTableModel updatedModel = adminUpdateUserController.refreshProfileList();
                //?
                addUserProfileModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    addUserProfileModel.addRow(row);
                }
            }
        });


        //search part
        /**
         * search user part
         */

        String[][] datas = {};
        String[] titles = { "Account", "Password","Name","Profile","Status" };
        JLabel searchAccountLabel=new JLabel("Please enter the account(Support fuzzy query) you want to search :");
        JTextField searchAccountField=new JTextField(15);
        JButton searchButton=new JButton("Search");


        DefaultTableModel searchModel = new DefaultTableModel(datas,titles);

        JTable searchTable = new JTable(searchModel);

        searchTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane searchScrollPane = new JScrollPane(searchTable);
        searchTable.setRowHeight(20);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = searchAccountField.getText();
                adminSearchUserController user = new adminSearchUserController();

                DefaultTableModel updatedModel = user.searchUser(account);
                searchModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    searchModel.addRow(row);
                }
            }
        });


        searchAccountLabel.setBounds(30, 60, 450, 30);
        searchAccountField.setBounds(50, 110, 250, 30);
        searchButton.setBounds(370, 110, 100, 30);
        searchScrollPane.setBounds(50, 150, 450, 260);



        /**
         * update user part
         *
         */

        JLabel updateTitleLabel=new JLabel("  Please enter account to update user ");
        JLabel updateAccountLabel=new JLabel("   Account :");
        JLabel updatePasswordLabel=new JLabel("Password :");
        JLabel updateNameLabel=new JLabel("       Name :");
        JLabel updateProfileNoLabel=new JLabel("Profile No :");

        /*ImageIcon updateScaledIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/PROJECTS/image/questionMark.png"))
                        .getImage()
                        .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        );
        JLabel updateQuestionMarkLabel = new JLabel(updateScaledIcon);
        updateQuestionMarkLabel.setToolTipText("<html>1. Administrator<br>2. Cafe owner<br>3. Manager<br>4. Staff</html>");*/
        adminUpdateUserController adminUpdateUserController = new adminUpdateUserController();
        DefaultTableModel updateUserModel = adminUpdateUserController.viewAllUserList();
        JTable updateUserTable = new JTable(updateUserModel);
        updateUserTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane updateUserTableScrollPane = new JScrollPane(updateUserTable);
        updateUserTable.setRowHeight(20);
        updateUserTableScrollPane.setBounds(30, 230, 250, 200);



        DefaultTableModel updateUserProfileModel = adminUpdateUserController.viewProfileList();
        JTable updateUserProfileTable = new JTable(updateUserProfileModel);
        updateUserProfileTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane updateUserProfileScrollPane = new JScrollPane(updateUserProfileTable);
        updateUserProfileTable.setRowHeight(20);

        updateUserProfileScrollPane.setBounds(300, 230, 200, 200);


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
        //updateQuestionMarkLabel.setBounds(400, 190, 30, 30);
        JButton updateSubmitButton=new JButton("submit");
        JButton updateClearButton=new JButton("clear");
        JButton updateRefreshButton=new JButton("refresh");

        updateTitleLabel.setBounds(50, 20, 450, 30);
        updateAccountLabel.setBounds(90, 60, 150, 30);
        updatePasswordLabel.setBounds(90, 90, 150, 30);
        updateNameLabel.setBounds(90, 120, 150, 30);
        updateProfileNoLabel.setBounds(90, 150, 150, 30);

        updateAccountField.setBounds(230, 60, 150, 25);
        updatePasswordField.setBounds(230, 90, 150, 25);
        updateNameField.setBounds(230, 120, 150, 25);
        updateProfileNoField.setBounds(230, 150, 150, 25);


        updateSubmitButton.setBounds(250, 190, 80, 25);
        updateClearButton.setBounds(150, 190, 80, 25);
        updateRefreshButton.setBounds(350, 190, 80, 25);
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

        updateRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminUpdateUserController adminUpdateUserController = new adminUpdateUserController();
                DefaultTableModel updatedModel = adminUpdateUserController.refreshAllUserList();
                DefaultTableModel updatedModel2 = adminUpdateUserController.refreshProfileList();
                //?
                updateUserModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    updateUserModel.addRow(row);
                }
                updateUserProfileModel.setRowCount(0);
                for (int i = 0; i < updatedModel2.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel2.getColumnCount(); j++) {
                        row.add(updatedModel2.getValueAt(i, j));
                    }
                    updateUserProfileModel.addRow(row);
                }
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
        //refreshButton.setBounds(210, 10, 80, 25);
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

        /**
         * activate user part
         *
         */
        JLabel activateAccountLabel=new JLabel("Please enter the account you want to activate :");
        JTextField activateAccountField=new JTextField(15);
        JButton activateButton=new JButton("activate");

        adminActivateUserController adminActivateUserController = new adminActivateUserController();

        DefaultTableModel activateModel = adminActivateUserController.viewAllUserList();
        JTable activateTable = new JTable(activateModel);
        activateTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane activateScrollPane = new JScrollPane(activateTable);
        activateTable.setRowHeight(20);
        JButton activateRefreshButton=new JButton("refresh");
        activateRefreshButton.setBounds(210, 10, 80, 25);
        activateRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminActivateUserController adminActivateUserController = new adminActivateUserController();
                DefaultTableModel updatedModel = adminActivateUserController.refreshAllUserList();
                //?
                activateModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    activateModel.addRow(row);
                }
            }
        });

        activateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = activateAccountField.getText();


                int confirmationResult = JOptionPane.showConfirmDialog(
                        adminPage.this,
                        "Are you sure you want to suspend this user? This user will not be able to log in",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );
                if (confirmationResult == JOptionPane.OK_OPTION) {
                    adminActivateUserController adminActivateUserController = new adminActivateUserController();
                    boolean suspendSuccess = adminActivateUserController.activateAccount(account);
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

        activateAccountLabel.setBounds(30, 60, 350, 30);
        activateAccountField.setBounds(50, 110, 250, 30);
        activateButton.setBounds(370, 110, 100, 30);

        activateScrollPane.setBounds(50, 150, 450, 250);
        activateRefreshButton.setBounds(200, 410, 100, 30);

        ///////////////////////////////////////////////////////////////////////////////////////////////////
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
        ///////////////////////////////////////////////////////////////
        /**
         * view profile part
         *
         */
        adminViewProfileController adminViewProfileController = new adminViewProfileController();

        DefaultTableModel profileModel = adminViewProfileController.viewAllProfileList();
        JTable profileTable = new JTable(profileModel);
        profileTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane profileScrollPane = new JScrollPane(profileTable);
        profileTable.setRowHeight(20);
        JButton profileRefreshButton=new JButton("refresh");
        profileRefreshButton.setBounds(210, 10, 80, 25);
        profileScrollPane.setBounds(50, 50, 450, 350);
        profileRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminViewProfileController adminViewProfileController = new adminViewProfileController();
                DefaultTableModel updatedModel = adminViewProfileController.refreshAllProfileList();
                //?
                profileModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    profileModel.addRow(row);
                }
            }
        });


        ///////////////////////////////////////////////////////////////
        /**
         * add new profile part
         *
         */
        //ToolTipManager.sharedInstance().setInitialDelay(100);
        JLabel addProfileLabel=new JLabel("   New Profile :");

        addProfileLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));

        JTextField addProfileField=new JTextField(15);

        JButton addProfileSubmitButton=new JButton("submit");
        JButton addProfileClearButton=new JButton("clear");

        addProfileLabel.setBounds(90, 120, 150, 30);
        addProfileField.setBounds(230, 120, 150, 25);
        addProfileSubmitButton.setBounds(280, 270, 80, 25);
        addProfileSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String profile = addProfileField.getText();

                adminAddProfileController user = new adminAddProfileController();
                if (user.addVerification(profile)){
                    JOptionPane.showMessageDialog(adminPage.this, "The new profile has been successfully added to the database");
                }else {
                    JOptionPane.showMessageDialog(adminPage.this,
                            "Your input is not valid, please re-enter, (profile can not be empty," +
                                    " or this profile is already exist ! " );
                }
            }
        });


        addProfileClearButton.setBounds(180, 270, 80, 25);
        // Clear button
        addProfileClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProfileField.setText("");
            }
        });

        addProfilePart.add(addProfileLabel);
        addProfilePart.add(addProfileField);
        addProfilePart.add(addProfileSubmitButton);
        addProfilePart.add(addProfileClearButton);
        ///////////////////////////////////////////////////////////////
        /**
         *
         * search profile part
         */

        JLabel searchProfileLabel=new JLabel("Please enter the profile(Support fuzzy query) you want to search :");
        JTextField searchProfileField=new JTextField(15);
        JButton searchProfileButton=new JButton("Search");


        String[][] ProfileData = {};
        String[] ProfileTitles = { "profile no", "profile" };
        DefaultTableModel searchProfileModel = new DefaultTableModel(ProfileData,ProfileTitles);
        JTable searchProfileTable = new JTable(searchProfileModel);
        searchProfileTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane searchProfileScrollPane = new JScrollPane(searchProfileTable);
        searchProfileTable.setRowHeight(20);


        searchProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String profile = searchProfileField.getText();
                adminSearchProfileController adminSearchProfileController= new adminSearchProfileController();

                DefaultTableModel updatedModel = adminSearchProfileController.searchProfileList(profile);
                searchProfileModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    searchProfileModel.addRow(row);
                }
            }
        });


        searchProfileLabel.setBounds(30, 60, 450, 30);
        searchProfileField.setBounds(50, 110, 250, 30);
        searchProfileButton.setBounds(370, 110, 100, 30);
        searchProfileScrollPane.setBounds(50, 150, 450, 260);
        ///////////////////////////////////////////////////////////////
        /**
         *
         * update profile part
         */
        JLabel updateProfileLabel=new JLabel("Please enter the profile NO you want to update :");
        JTextField updateProfileField=new JTextField(15);

        JLabel updateNameProfileLabel=new JLabel("Please enter the new profile name you want to update :");
        JTextField updateNameProfileField=new JTextField(15);
        JButton updateProfileButton=new JButton("update");

        adminUpdateProfileController adminUpdateController = new adminUpdateProfileController();

        DefaultTableModel updateProfileModel = adminUpdateController.viewProfileList();
        JTable updateProfileTable = new JTable(updateProfileModel);
        updateProfileTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane updateProfileScrollPane = new JScrollPane(updateProfileTable);
        updateProfileTable.setRowHeight(20);



        JButton updateProfileRefreshButton=new JButton("refresh");

        updateProfileScrollPane.setBounds(50, 50, 450, 350);


        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String profileNOString = updateProfileField.getText();
                    int profileNO = Integer.parseInt(profileNOString);
                    String profileName = updateNameProfileField.getText();

                    adminUpdateProfileController adminUpdateProfileController = new adminUpdateProfileController();
                    if (adminUpdateProfileController.updateProfile(profileNO,profileName)){
                        JOptionPane.showMessageDialog(adminPage.this, "the profile Successfully updated");
                    }else {
                        JOptionPane.showMessageDialog(adminPage.this, "The profile number does not exist,\n or the profile name you entered is exist or invalid (greater than four digits).");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(adminPage.this, "Invalid profile number. Please enter a numeric value.");
                }
            }
        });
        /*updateProfileRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminUpdateProfileController adminUpdateProfileController = new adminUpdateProfileController();
                DefaultTableModel updatedModel = adminUpdateProfileController.refreshProfileList();
                updateProfileModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    updateProfileModel.addRow(row);
                }
            }
        });*/
        updateProfileRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminUpdateProfileController adminUpdateProfileController = new adminUpdateProfileController();
                DefaultTableModel updatedModel = adminUpdateProfileController.refreshProfileList();

                SwingUtilities.invokeLater(() -> {
                    updateProfileModel.setRowCount(0);
                    for (int i = 0; i < updatedModel.getRowCount(); i++) {
                        Vector<Object> row = new Vector<>();
                        for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                            row.add(updatedModel.getValueAt(i, j));
                        }
                        updateProfileModel.addRow(row);
                    }
                });
            }
        });


        updateProfileLabel.setBounds(30, 10, 450, 30);
        updateProfileField.setBounds(50, 40, 250, 30);
        updateNameProfileLabel.setBounds(30, 70, 450, 30);
        updateNameProfileField.setBounds(50, 100, 250, 30);

        updateProfileButton.setBounds(370, 100, 100, 30);
        updateProfileRefreshButton.setBounds(210, 420, 80, 25);
        updateProfileScrollPane.setBounds(50, 150, 420, 260);

        ///////////////////////////////////////////////////////////////
        /**
         *
         * suspend profile part
         */


        JLabel suspendProfileLabel=new JLabel("Please enter the profile NO you want to Suspend :");
        JTextField suspendProfileField=new JTextField(15);
        JButton suspendProfileButton=new JButton("Suspend");

        adminSuspendProfileController adminSuspendProfileController = new adminSuspendProfileController();

        DefaultTableModel suspendProfileModel = adminSuspendProfileController.viewProfileList();
        JTable suspendProfileTable = new JTable(suspendProfileModel);
        suspendProfileTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane suspendProfileScrollPane = new JScrollPane(suspendProfileTable);
        suspendProfileTable.setRowHeight(20);

        suspendProfileScrollPane.setBounds(50, 50, 450, 350);

        JButton suspendProfileRefreshButton=new JButton("refresh");


        suspendProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmationResult = JOptionPane.showConfirmDialog(
                        adminPage.this,
                        "Are you sure you want to suspend the profile?,\n all user is this profile can not log in system",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (confirmationResult == JOptionPane.OK_OPTION) {
                    try {
                        String profileNOString = suspendProfileField.getText();
                        int profileNO = Integer.parseInt(profileNOString);

                            adminSuspendProfileController adminSuspendProfileController = new adminSuspendProfileController();
                            if (adminSuspendProfileController.suspendProfile(profileNO)){
                                JOptionPane.showMessageDialog(adminPage.this, "all user (profile your entered) Successfully suspend");
                            }else {
                                JOptionPane.showMessageDialog(adminPage.this, "The profile number does not exist,\n,or no user is this profile \n or you can not suspend Administrator.");
                            }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(adminPage.this, "Invalid profile number. Please enter a numeric value.");
                    }
                } else {
                    // User clicked Cancel, do nothing or provide appropriate action
                }
            }
        });
        suspendProfileRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSuspendProfileController adminSuspendProfileController = new adminSuspendProfileController();
                DefaultTableModel updatedModel = adminSuspendProfileController.refreshProfileList();
                suspendProfileModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    suspendProfileModel.addRow(row);
                }
            }
        });



        suspendProfileLabel.setBounds(30, 70, 450, 30);
        suspendProfileField.setBounds(50, 100, 250, 30);

        suspendProfileButton.setBounds(370, 100, 100, 30);
        suspendProfileRefreshButton.setBounds(210, 420, 80, 25);
        suspendProfileScrollPane.setBounds(50, 150, 420, 260);

        ///////////////////////////////////////////////////////////////
        /**
         *
         * activate profile part
         */
        JLabel activateProfileLabel=new JLabel("Please enter the profile NO you want to active :");
        JTextField activateProfileField=new JTextField(15);
        JButton activateProfileButton=new JButton("activate");

        adminActivateProfileController adminActivateProfileController = new adminActivateProfileController();

        DefaultTableModel activateProfileModel = adminActivateProfileController.viewProfileList();
        JTable activateProfileTable = new JTable(activateProfileModel);
        activateProfileTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        JScrollPane activateProfileScrollPane = new JScrollPane(activateProfileTable);
        activateProfileTable.setRowHeight(20);

        activateProfileScrollPane.setBounds(50, 50, 450, 350);

        JButton activateProfileRefreshButton=new JButton("refresh");


        activateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmationResult = JOptionPane.showConfirmDialog(
                        adminPage.this,
                        "Are you sure you want to activate this profile?,\n all user is this profile can log in system",
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (confirmationResult == JOptionPane.OK_OPTION) {
                    try {
                        String profileNOString = activateProfileField.getText();
                        int profileNO = Integer.parseInt(profileNOString);

                        adminActivateProfileController adminActivateProfileController = new adminActivateProfileController();
                        if (adminActivateProfileController.activateProfile(profileNO)){
                            JOptionPane.showMessageDialog(adminPage.this, "all user (profile your entered) Successfully activated");
                        }else {
                            JOptionPane.showMessageDialog(adminPage.this, "The profile number does not exist,\n,or no user is this profile \n or you can not activate Administrator.");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(adminPage.this, "Invalid profile number. Please enter a numeric value.");
                    }
                } else {
                    // User clicked Cancel, do nothing or provide appropriate action
                }
            }
        });
        activateProfileRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminActivateProfileController adminActivateProfileController = new adminActivateProfileController();
                DefaultTableModel updatedModel = adminActivateProfileController.refreshProfileList();
                activateProfileModel.setRowCount(0);
                for (int i = 0; i < updatedModel.getRowCount(); i++) {
                    Vector<Object> row = new Vector<>();
                    for (int j = 0; j < updatedModel.getColumnCount(); j++) {
                        row.add(updatedModel.getValueAt(i, j));
                    }
                    activateProfileModel.addRow(row);
                }
            }
        });



        activateProfileLabel.setBounds(30, 70, 450, 30);
        activateProfileField.setBounds(50, 100, 250, 30);

        activateProfileButton.setBounds(370, 100, 100, 30);
        activateProfileRefreshButton.setBounds(210, 420, 80, 25);
        activateProfileScrollPane.setBounds(50, 150, 420, 260);



        ///////////////////////////////////////////////////////////////
        viewPart.setBackground(Color.PINK);
        addPart.setBackground(Color.PINK);
        searchPart.setBackground(Color.PINK);
        updatePart.setBackground(Color.PINK);
        suspendPart.setBackground(Color.PINK);
        activePart.setBackground(Color.PINK);
        exitPart.setBackground(Color.PINK);

        viewPart.setLayout(null);
        addPart.setLayout(null);
        searchPart.setLayout(null);
        updatePart.setLayout(null);
        suspendPart.setLayout(null);
        activePart.setLayout(null);
        exitPart.setLayout(null);


        viewProfilePart.setBackground(Color.PINK);
        addProfilePart.setBackground(Color.PINK);
        searchProfilePart.setBackground(Color.PINK);
        updateProfilePart.setBackground(Color.PINK);
        suspendProfilePart.setBackground(Color.PINK);
        activeProfilePart.setBackground(Color.PINK);


        viewProfilePart.setLayout(null);
        addProfilePart.setLayout(null);
        searchProfilePart.setLayout(null);
        updateProfilePart.setLayout(null);
        suspendProfilePart.setLayout(null);
        activeProfilePart.setLayout(null);
////////////////////////////////////////////////////////////////////////////////

        exitPart.add(exitButton);

        viewPart.add(scrollPane);
        viewPart.add(refreshButton);

        addPart.add(addAccountLabel);
        addPart.add(addPasswordLabel);
        addPart.add(addNameLabel);
        addPart.add(addProfileNoLabel);

        addPart.add(addUserProfileScrollPane);


        addPart.add(addAccountField);
        addPart.add(addPasswordField);
        addPart.add(addNameField);
        addPart.add(addProfileNoField);
        addPart.add(submitButton);
        addPart.add(clearButton);
        addPart.add(addRefreshButton);



        searchPart.add(searchAccountLabel);
        searchPart.add(searchAccountField);
        searchPart.add(searchButton);
        searchPart.add(searchScrollPane);


        updatePart.add(updateTitleLabel);
        updatePart.add(updateAccountLabel);
        updatePart.add(updatePasswordLabel);
        updatePart.add(updateNameLabel);
        updatePart.add(updateProfileNoLabel);
        //updatePart.add(updateQuestionMarkLabel);
        updatePart.add(updateUserTableScrollPane);
        updatePart.add(updateUserProfileScrollPane);



        updatePart.add(updateAccountField);
        updatePart.add(updatePasswordField);
        updatePart.add(updateNameField);
        updatePart.add(updateProfileNoField);
        updatePart.add(updateSubmitButton);
        updatePart.add(updateClearButton);
        updatePart.add(updateRefreshButton);



        suspendPart.add(suspendAccountLabel);
        suspendPart.add(suspendAccountField);
        suspendPart.add(suspendButton);
        suspendPart.add(suspendScrollPane);
        suspendPart.add(suspendRefreshButton);


        activePart.add(activateAccountLabel);
        activePart.add(activateAccountField);
        activePart.add(activateButton);
        activePart.add(activateScrollPane);
        activePart.add(activateRefreshButton);



        viewProfilePart.add(profileScrollPane);
        viewProfilePart.add(profileRefreshButton);

        searchProfilePart.add(searchProfileLabel);
        searchProfilePart.add(searchProfileField);
        searchProfilePart.add(searchProfileButton);
        searchProfilePart.add(searchProfileScrollPane);

        updateProfilePart.add(updateProfileLabel);
        updateProfilePart.add(updateProfileField);
        updateProfilePart.add(updateNameProfileLabel);
        updateProfilePart.add(updateNameProfileField);
        updateProfilePart.add(updateProfileButton);
        updateProfilePart.add(updateProfileRefreshButton);
        updateProfilePart.add(updateProfileScrollPane);


        suspendProfilePart.add(suspendProfileLabel);
        suspendProfilePart.add(suspendProfileField);
        suspendProfilePart.add(suspendProfileButton);
        suspendProfilePart.add(suspendProfileRefreshButton);
        suspendProfilePart.add(suspendProfileScrollPane);


        activeProfilePart.add(activateProfileLabel);
        activeProfilePart.add(activateProfileField);
        activeProfilePart.add(activateProfileButton);
        activeProfilePart.add(activateProfileRefreshButton);
        activeProfilePart.add(activateProfileScrollPane);

        controlTab = new JTabbedPane(JTabbedPane.LEFT);
        controlTab.add("View user list", viewPart);
        controlTab.add("Add user", addPart);
        controlTab.add("Search user", searchPart);
        controlTab.add("Update user", updatePart);
        controlTab.add("Suspend user", suspendPart);
        controlTab.add("Activate user", activePart);

        controlTab.add("View profile list", viewProfilePart);
        controlTab.add("Add profile", addProfilePart);
        controlTab.add("Search profile", searchProfilePart);
        controlTab.add("Update profile", updateProfilePart);
        controlTab.add("Suspend profile", suspendProfilePart);
        controlTab.add("Activate profile", activeProfilePart);

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
