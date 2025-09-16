//importing the java libraries necessary to run the application
import java.util.ArrayList;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.*;

public class ArtGalleryGUI{
    private ArrayList<ArtGalleryVisitor> visitors = new ArrayList<  >();
    private DefaultTableModel model;

    public ArtGalleryGUI(){

        /*
         * Reads serialized ArtGalleryVisitor objects from "Visitors.dat" and stores them in the visitors list.
         * Uses a loop to read until the end of the file, handling EOFException to stop reading.
         * Displays a success or error message based on whether the file was read successfully.
         */

        try{
            ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("Visitors.dat"));
            while (true){
                try{
                    ArtGalleryVisitor v = (ArtGalleryVisitor) OIS.readObject();
                    visitors.add(v);
                }
                catch(EOFException exc){
                    break;
                }

            } 
            JOptionPane.showMessageDialog(null, "Read from file", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception exc){
            JOptionPane.showMessageDialog(null, "Failed to read from file", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JFrame myFrame = new JFrame("Art Gallery Visitor Management System");
        myFrame.setSize(1200, 820);
        myFrame.setResizable(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());

        /*=====================================================
         * to set the visible title of the GUI Screen
        ======================================================*/
        JLabel titleLabel = new JLabel("My Art Gallery");
        titleLabel.setBackground(new Color(179, 213, 255));
        titleLabel.setForeground(new Color(14, 95, 106));
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 54));    
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);//center-align the text horizontally
        titleLabel.setOpaque(true); // because JLabel is not opaque so to set background
        titleLabel.setBorder(new EmptyBorder(30,0,30,0));//empty border set to increase padding
        myFrame.add(titleLabel,BorderLayout.NORTH);

        /*==================================================
         *              SIDEBAR PANEL
        ====================================================*/
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(250,0));
        sidebar.setBackground(new Color(23, 162, 181));

        JPanel topButtonsPanel = new JPanel();
        topButtonsPanel.setLayout(new BoxLayout(topButtonsPanel, BoxLayout.Y_AXIS));
        topButtonsPanel.setBackground(new Color(23, 162, 181));

        /*======================================================
         *          REGISTER GUEST BUTTON
        ========================================================*/
        JButton registrationButton = new JButton("Register Guest");
        registrationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registrationButton.setPreferredSize(new Dimension(200, 50));
        registrationButton.setBackground(new Color(58,63,75));
        registrationButton.setForeground(new Color(255,255,255));
        registrationButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        registrationButton.setFocusPainted(false);
        registrationButton.setMaximumSize(registrationButton.getPreferredSize());
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        topButtonsPanel.add(Box.createVerticalGlue());
        topButtonsPanel.add(registrationButton);

        /*==========================================================
         *              ART ACQUISITIONS BUTTON
        ===========================================================*/
        JButton purchaseButton = new JButton("Art Acquisitions");
        purchaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        purchaseButton.setPreferredSize(new Dimension(200, 50));
        purchaseButton.setBackground(new Color(58,63,75));
        purchaseButton.setForeground(new Color(255,255,255));
        purchaseButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        purchaseButton.setFocusPainted(false);
        purchaseButton.setMaximumSize(purchaseButton.getPreferredSize());
        topButtonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        topButtonsPanel.add(purchaseButton);

        /*==========================================================
         *              DISPLAY VISITOR DETAILS BUTTON
        ============================================================*/
        JButton recordButton = new JButton("Display Visitor Details");
        recordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        recordButton.setPreferredSize(new Dimension(200, 50));
        recordButton.setBackground(new Color(58,63,75));
        recordButton.setForeground(new Color(255,255,255)); 
        recordButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        recordButton.setFocusPainted(false);
        recordButton.setMaximumSize(recordButton.getPreferredSize());
        topButtonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        topButtonsPanel.add(recordButton);
        topButtonsPanel.add(Box.createVerticalGlue());

        //panel created to set 4 buttons at the bottom of the sidebar
        JPanel bottomButtonGrid = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 cols, 10px gaps
        bottomButtonGrid.setBackground(new Color(23, 162, 181));
        bottomButtonGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10)); // padding around

        /*======================================================
         *                  LOG VISIT BUTTON
        ========================================================*/
        JButton logVisitButton = new JButton("Log Visit");
        logVisitButton.setPreferredSize(new Dimension(110, 35));
        logVisitButton.setMaximumSize(new Dimension(110, 35));
        logVisitButton.setBackground(new Color(58,63,75));
        logVisitButton.setForeground(Color.WHITE);
        logVisitButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        logVisitButton.setFocusPainted(false);
        bottomButtonGrid.add(logVisitButton);

        /*=============================================================
         *                  CHECK UPGRADE BUTTON
        ===============================================================*/
        JButton checkUpgradeButton = new JButton("<html><center>Check<br>Upgrade</center></html>");
        checkUpgradeButton.setPreferredSize(new Dimension(110, 35));
        checkUpgradeButton.setBackground(new Color(58,63,75));
        checkUpgradeButton.setForeground(Color.WHITE);
        checkUpgradeButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        checkUpgradeButton.setFocusPainted(false);
        bottomButtonGrid.add(checkUpgradeButton);

        /*===============================================
         *              SAVE TO FILE BUTTON
        ===================================================*/        
        JButton saveButton = new JButton("Save to File");
        saveButton.setPreferredSize(new Dimension(110, 35));
        saveButton.setBackground(new Color(58,63,75));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        saveButton.setFocusPainted(false);
        bottomButtonGrid.add(saveButton);

        /*========================================================
         *              READ FROM FILE BUTTON
        =========================================================*/
        JButton readButton = new JButton("<html><center>Read from<br>File</center></html>");
        readButton.setPreferredSize(new Dimension(120, 35));
        readButton.setBackground(new Color(58,63,75));
        readButton.setForeground(Color.WHITE);
        readButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        readButton.setFocusPainted(false);
        bottomButtonGrid.add(readButton);

        //==========add sidebar==================
        sidebar.add(Box.createVerticalGlue());      
        sidebar.add(topButtonsPanel);               
        sidebar.add(Box.createVerticalGlue());  
        sidebar.add(bottomButtonGrid); 
        myFrame.add(sidebar, BorderLayout.WEST);

        //==========Card layout for main panel===================
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);//main container panel that uses card system

        //register guest panel inside main panel with borders
        JPanel registerGuestPanel = new JPanel(new GridBagLayout());
        registerGuestPanel.setBackground(new Color(230, 240, 255)); 
        Border lineBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        Font titleFont = new Font("Garamond", Font.BOLD, 25);
        Color titleColor = new Color(0, 51, 102);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                lineBorder, "Visitor's Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleFont, titleColor);
        registerGuestPanel.setBorder(BorderFactory.createCompoundBorder(
                titledBorder, BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        cardLayout.show(mainPanel, "registerPanel");

        //=======gridbad for registration page=====================
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); //padding added
        gbc.anchor = GridBagConstraints.WEST; //components stick to the left side of the grid cell
        mainPanel.add(registerGuestPanel, "registerPanel");
        registrationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "register");
                }
            });

        // Wrapper panel to center and top-align the Register Guest panel with custom spacing and background
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(new Color(230, 240, 255));
        GridBagConstraints wrapGbc = new GridBagConstraints();
        wrapGbc.gridx = 0;
        wrapGbc.gridy = 0;
        wrapGbc.anchor = GridBagConstraints.NORTH; // Align it towards top-center
        wrapGbc.insets = new Insets(20, 0, 45, 0); //padding
        wrapperPanel.add(registerGuestPanel, wrapGbc);
        mainPanel.add(wrapperPanel, "registerPanel"); //wrapper panel added to the main panel

        /*==========================================================
         *                      VISITOR ID
        ============================================================*/
        JLabel visitorIdLabel = new JLabel("Visitor ID: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        visitorIdLabel.setFont(new Font("Garamond", Font.BOLD,28));
        registerGuestPanel.add(visitorIdLabel,gbc);
        JTextField visitorIdField = new JTextField(15);
        gbc.gridx = 1;
        visitorIdField.setFont(new Font("Arial", Font.PLAIN, 24));
        registerGuestPanel.add(visitorIdField, gbc);

        /*========================================================
         *                      NAME
        ==========================================================*/
        JLabel nameLabel = new JLabel("Name: ");
        gbc.gridx = 0;
        gbc.gridy = 1; //move to next row (row1 ==> row 2)
        nameLabel.setFont(new Font("Garamond", Font.BOLD,28));
        registerGuestPanel.add(nameLabel,gbc);
        JTextField nameField = new JTextField(15);
        gbc.gridx = 1;
        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
        registerGuestPanel.add(nameField, gbc);

        /*=========================================================
         *                  CONTACT NUMBER
        ===========================================================*/
        JLabel contactLabel = new JLabel("Contact number: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contactLabel.setFont(new Font("Garamond", Font.BOLD,28));
        registerGuestPanel.add(contactLabel,gbc);
        JTextField contactField = new JTextField(15);
        gbc.gridx = 1;
        contactField.setFont(new Font("Arial", Font.PLAIN, 24));
        registerGuestPanel.add(contactField,gbc);

        /*============================================================
         *              GENDER RADIO BUTTON
        =============================================================*/
        JLabel genderLabel = new JLabel("Gender: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        genderLabel.setFont(new Font("Garamond", Font.BOLD,28));
        registerGuestPanel.add(genderLabel,gbc);

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setFont(new Font("Garamond", Font.BOLD,25));
        maleButton.setBackground(new Color(230, 240, 255));
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setFont(new Font("Garamond", Font.BOLD,25));
        femaleButton.setBackground(new Color(230, 240, 255));
        JRadioButton otherButton = new JRadioButton("Others");
        otherButton.setFont(new Font("Garamond", Font.BOLD,25));
        otherButton.setBackground(new Color(230, 240, 255));

        ButtonGroup bg = new ButtonGroup();
        bg.add(maleButton);
        bg.add(femaleButton);
        bg.add(otherButton);

        JPanel genderPanel = new JPanel();
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);
        genderPanel.setBackground(new Color(230, 240, 255));
        gbc.gridx = 1;
        registerGuestPanel.add(genderPanel,gbc);

        //Registration date buttons
        JLabel dateLabel = new JLabel("Registration Date: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        dateLabel.setFont(new Font("Garamond", Font.BOLD,28));
        registerGuestPanel.add(dateLabel,gbc);

        String[] days = new String[32];//31 days + 1 select 
        days[0] = "Day";
        for (int i = 1; i <= 31; i++){
            days[i] = String.valueOf(i);
        }
        JComboBox<String> dayCombo=new JComboBox<>(days);
        dayCombo.setFont(new Font("Garamond", Font.BOLD,24));

        String[] months = {"Month","January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        JComboBox<String> monthCombo=new JComboBox<>(months);
        monthCombo.setFont(new Font("Garamond", Font.BOLD,24));

        String[] years = new String[37];
        years[0] = "Year";
        for (int i = 1; i <= 36; i++){
            years[i] = String.valueOf(1989 + i);
        }
        JComboBox<String> yearCombo=new JComboBox<>(years);
        yearCombo.setFont(new Font("Garamond", Font.BOLD,24));
        yearCombo.setSelectedIndex(0); //"Year" to show

        JPanel datePanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dayCombo);
        datePanel.add(monthCombo);
        datePanel.add(yearCombo);
        datePanel.setBackground(new Color(230, 240, 255));
        gbc.gridx = 1;
        registerGuestPanel.add(datePanel,gbc);          

        /*==============================================================
         *                      TICKET TYPE
        =================================================================*/
        JLabel ticketTypeLabel = new JLabel("Ticket Type: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        ticketTypeLabel.setFont(new Font("Garamond", Font.BOLD,28));
        registerGuestPanel.add(ticketTypeLabel,gbc);
        String[] type = {"Select","Standard", "Elite"};
        JComboBox<String> typeCombo=new JComboBox<>(type);
        gbc.gridx = 1;
        typeCombo.setFont(new Font("Garamond", Font.BOLD,24));
        registerGuestPanel.add(typeCombo,gbc);

        /*============================================================
         *                       TICKET TYPE
        ==============================================================*/
        JLabel ticketPriceLabel = new JLabel("Ticket Pice: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        ticketPriceLabel.setFont(new Font("Garamond", Font.BOLD,28));
        registerGuestPanel.add(ticketPriceLabel,gbc);
        JTextField ticketPriceField = new JTextField(15);
        ticketPriceField.setEditable(false); // make it read-only-based on ticket type the visitor has choosen
        ticketPriceField.setFont(new Font("Garamond", Font.BOLD,24));
        gbc.gridx = 1;
        registerGuestPanel.add(ticketPriceField, gbc);

        typeCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String ticketTypeChoice = (String) typeCombo.getSelectedItem();
                    if (ticketTypeChoice.equals("Standard")) {
                        ticketPriceField.setText("1000");
                    } else if (ticketTypeChoice.equals("Elite")) {
                        ticketPriceField.setText("2000");
                    } else if (ticketTypeChoice.equals("Select")){
                        ticketPriceField.setText("");//clear field
                    }
                }
            });            
        myFrame.add(mainPanel, BorderLayout.CENTER);

        //==========Card Layout for bottom button panel======================
        CardLayout buttonCardLayout = new CardLayout();
        JPanel buttonPanelContainer = new JPanel(buttonCardLayout);

        //==========bottom button Panel with borders=========================
        JPanel registrationButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        registrationButtonPanel.setBackground(new Color(230, 240, 255));
        registrationButtonPanel.setPreferredSize(new Dimension(0, 100));
        TitledBorder buttonBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 3),
                "Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Garamond", Font.BOLD, 22),
                new Color(0, 51, 102)
            );
        registrationButtonPanel.setBorder(buttonBorder);

        /*============================================================
         *                  ADD VISITOR BUTTON
        =============================================================*/
        JButton addVisitorButton = new JButton("Add Visitor");
        addVisitorButton.setBackground(new Color(58, 63, 75));
        addVisitorButton.setForeground(Color.WHITE);
        addVisitorButton.setFocusPainted(false);
        addVisitorButton.setPreferredSize(new Dimension(120, 37));
        addVisitorButton.setFont(new 
            Font("Helvetica", Font.BOLD, 14));
        registrationButtonPanel.add(addVisitorButton);

        /*=================================================================
         *             ADD VISITOR BUTTON FUNCTIONALITY
        ===================================================================*/
        /*
        This action listener is triggered when the "Add Visitor" button is clicked.
         * It performs a series of validations on user input fields including ID, name, contact number,
         * gender selection, date selection, ticket type, and ticket price.
         */
        addVisitorButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        String inputID = visitorIdField.getText().trim();
                        if(inputID.isEmpty()){
                            JOptionPane.showMessageDialog(null, "ID Field cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        if(!inputID.matches("\\d+")){
                            //not a number or contains non-digit characters
                            JOptionPane.showMessageDialog(null, "ID must contain only positive whole numbers!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int id = Integer.parseInt(inputID);
                        if(id <= 0){
                            JOptionPane.showMessageDialog(null, "Id must be a positive integer!", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        // Check for duplicate visitor ID
                        for (ArtGalleryVisitor visitor : visitors) {
                            if (visitor.getVisitorId() == id) {
                                JOptionPane.showMessageDialog(null, "Visitor with this ID already exists!", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        String inputName = nameField.getText().trim();
                        if (inputName.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Name field cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        if (!inputName.matches("^[A-Za-z ]+$")) {
                            JOptionPane.showMessageDialog(null, "Name can only contain letters and spaces.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (inputName.length() < 2 || inputName.length() > 50) {
                            JOptionPane.showMessageDialog(null, "Name must be between 2 and 50 characters.", "Invalid Name Length", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String inputContact = contactField.getText().trim();
                        if(inputContact.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Contact number cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        if (!inputContact.matches("\\d{10}")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid 10 digit phone number!", "Warning", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if (!maleButton.isSelected() && !femaleButton.isSelected() && !otherButton.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Please select a gender!", "Missing Selection", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (dayCombo.getSelectedItem().equals("Day") ||
                        monthCombo.getSelectedItem().equals("Month") ||
                        yearCombo.getSelectedItem().equals("Year")) {
                            JOptionPane.showMessageDialog(registerGuestPanel,
                                "Please select a valid day, month, or year for the registration date.",
                                "Invalid Date",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String ticketType = (String) typeCombo.getSelectedItem();
                        if (ticketType == null || ticketType.equals("Select Type")) {
                            JOptionPane.showMessageDialog(null, "Please select a ticket type!", "Missing Type", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        String costText = ticketPriceField.getText().trim();
                        if (costText.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Ticket price cannot be empty!", "Missing Cost", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        double cost = Double.parseDouble(costText);
                        if (cost <= 0) {
                            JOptionPane.showMessageDialog(null, "Ticket cost must be a positive number!", "Invalid Cost", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String name = nameField.getText().trim();
                        String contact = contactField.getText().trim();
                        String gender = maleButton.isSelected() ? "Male" : (femaleButton.isSelected() ? "Female" : "");
                        String date = dayCombo.getSelectedItem() + " " + monthCombo.getSelectedItem() + " " + yearCombo.getSelectedItem();

                        ArtGalleryVisitor newVisitor = null;
                        if (ticketType.equals("Standard")) {
                            newVisitor = new StandardVisitor(id, name, gender, contact, date, cost, ticketType);
                        } else{
                            newVisitor = new EliteVisitor(id, name, gender, contact, date, cost, ticketType);
                        }
                        visitors.add(newVisitor);
                        String successMessage = "\"" + name + "\" has been successfully added as a \"" + ticketType + "\" visitor.";
                        JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

                    }catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter valid numeric values for ID and Cost!", "Parsing Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            });

        /*===========================================================
         *                  CLEAR FIELD BUTTON
        ===========================================================*/
        JButton clearFieldButton = new JButton("Clear Fields");
        clearFieldButton.setBackground(new Color(58, 63, 75));
        clearFieldButton.setForeground(Color.WHITE);
        clearFieldButton.setFocusPainted(false);
        clearFieldButton.setPreferredSize(new Dimension(120, 37));
        clearFieldButton.setFont(new 
            Font("Helvetica", Font.BOLD, 14));
        registrationButtonPanel.add(clearFieldButton); 
        buttonPanelContainer.add(registrationButtonPanel, "registrationButtons");

        /*=================================================================
         *          CLEAR FIELD BUTTON FUNCTIONALITY
        ===================================================================*/
        /*
        This action listener is triggered when the "Clear Fields" button is clicked.
         * It resets all input fields in the visitor registration form to their default states
         */
        clearFieldButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        visitorIdField.setText("");
                        nameField.setText("");
                        contactField.setText("");
                        ticketPriceField.setText("");
                        bg.clearSelection();
                        dayCombo.setSelectedIndex(0);
                        monthCombo.setSelectedIndex(0);
                        yearCombo.setSelectedIndex(0);
                        typeCombo.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(null, "All fields cleared.", "Cleared", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error while clearing fields: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*===========================================================================
         *                  LOG VISIT BUTTON FUNCTIONALITY
        ============================================================================*/
        ArrayList<Integer> loggedInVisitors = new ArrayList<>();
        // Validates visitor ID, finds the matching visitor, logs their visit, and shows a success or error message.

        logVisitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String inputId = visitorIdField.getText().trim();
                        if (inputId.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Register before logging in!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (!inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Visitor ID must be a positive number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        foundVisitor.logVisit();
                        JOptionPane.showMessageDialog(null, "Visit successfully logged for visitor: " + foundVisitor.getFullName(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid number format for Visitor ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*==============================================================
         *              CHECK UPGRADE BUTTON FUNCTIONALITY
        ===============================================================*/
        // Validates visitor ID, checks if the visitor is eligible for a discount upgrade, and shows the result.
        checkUpgradeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String inputId = visitorIdField.getText().trim();
                    try{
                        if (inputId.isEmpty() || !inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Visitor ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (foundVisitor instanceof StandardVisitor){
                            StandardVisitor standard = (StandardVisitor) foundVisitor;
                            boolean upgrade = standard.checkDiscountUpgrade();
                            if(upgrade){
                                JOptionPane.showMessageDialog(null, "Discount upgraded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Not eligible yet", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                "Only Standard Visitors can have discount upgrades.",
                                "Not Eligible", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Something went wrong: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        );

        /*==============================================================
         *              ART ACQUISITION PANEL
        ===============================================================*/
        JPanel AcquisitionsPanel = new JPanel(new BorderLayout());
        AcquisitionsPanel.setBackground(new Color(245, 245, 255));
        mainPanel.add(AcquisitionsPanel, "acquisitions");

        JLabel acquisitionsTitle = new JLabel("Art Acquisition and Billing Section");
        acquisitionsTitle.setFont(new Font("Garamond", Font.BOLD, 30));
        acquisitionsTitle.setForeground(new 
            Color(0, 51, 102));
        acquisitionsTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel titleWrapper = new JPanel(new BorderLayout());
        titleWrapper.setBackground(new Color(245, 245, 255));
        titleWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titleWrapper.add(acquisitionsTitle, BorderLayout.CENTER);

        GridBagConstraints AcquisitionsGbc = new GridBagConstraints();
        AcquisitionsGbc.gridx = 0;
        AcquisitionsGbc.gridy = 0;
        AcquisitionsGbc.gridwidth = 2;
        AcquisitionsGbc.anchor = GridBagConstraints.CENTER;
        AcquisitionsGbc.fill = GridBagConstraints.HORIZONTAL;
        AcquisitionsPanel.add(titleWrapper, BorderLayout.NORTH);

        JPanel artAcquisitionsPanel = new JPanel(new GridBagLayout());
        artAcquisitionsPanel.setBackground(new Color(230, 240, 255));
        GridBagConstraints acGbc = new GridBagConstraints();
        acGbc.insets = new Insets(10, 10, 10, 10); // padding
        acGbc.anchor = GridBagConstraints.WEST;

        JPanel centerWrapper = new JPanel(new GridBagLayout()); // centers child
        centerWrapper.setBackground(new Color(230, 240, 255)); // match parent
        centerWrapper.add(artAcquisitionsPanel); // add inner form panel

        AcquisitionsPanel.add(centerWrapper, BorderLayout.CENTER); // add wrapper to CENTER

        artAcquisitionsPanel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2), // outer visible border
                BorderFactory.createEmptyBorder(20, 25, 20, 25)// inner padding (top, left, bottom, right)
            )
        );

        /*======================================================
         *      VISITOR ID FOR ACT ACQUISITION PANEL
        ===========================================================*/
        JLabel visitorId1Label = new JLabel("Visitor ID: ");
        acGbc.gridx = 0;
        acGbc.gridy = 0;
        visitorId1Label.setFont(new Font("Garamond", Font.BOLD,28));
        artAcquisitionsPanel.add(visitorId1Label,acGbc);
        JTextField visitorId1Field = new JTextField(15);
        acGbc.gridx = 1;
        visitorId1Field.setFont(new Font("Arial", Font.PLAIN, 24));
        artAcquisitionsPanel.add(visitorId1Field, acGbc);

        /*==========================================================
         *                  ARTWORK NAME
        =============================================================*/
        JLabel artworkNameLabel = new JLabel("Artwork's name: ");
        acGbc.gridx = 0;
        acGbc.gridy = 1;
        artworkNameLabel.setFont(new Font("Garamond", Font.BOLD,28));
        artAcquisitionsPanel.add(artworkNameLabel,acGbc);
        JTextField artworkNameField = new JTextField(15);
        acGbc.gridx = 1;
        artworkNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        artAcquisitionsPanel.add(artworkNameField, acGbc);

        /*==========================================================
         *                  ARTWORK PRICE
        =============================================================*/
        JLabel artworkPriceLabel = new JLabel("Artwork's price: ");
        acGbc.gridx = 0;
        acGbc.gridy = 2;
        artworkPriceLabel.setFont(new Font("Garamond", Font.BOLD,28));
        artAcquisitionsPanel.add(artworkPriceLabel,acGbc);
        JTextField artworkPriceField = new JTextField(15);
        acGbc.gridx = 1;
        artworkPriceField.setFont(new Font("Arial", Font.PLAIN, 24));
        artAcquisitionsPanel.add(artworkPriceField, acGbc);

        /*==========================================================
         *                CANCELLATION REASON
        =============================================================*/
        JLabel cancellationReasonLabel = new JLabel("Cancellation Reason: ");
        acGbc.gridx = 0;
        acGbc.gridy = 3;
        cancellationReasonLabel.setFont(new Font("Garamond", Font.BOLD,28));
        artAcquisitionsPanel.add(cancellationReasonLabel,acGbc);
        JTextField cancellationReasonField = new JTextField(15);
        acGbc.gridx = 1;
        cancellationReasonField.setFont(new Font("Arial", Font.PLAIN, 24));
        artAcquisitionsPanel.add(cancellationReasonField, acGbc);

        JPanel acquisitionBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        acquisitionBottomPanel.setBackground(new Color(230, 240, 255));
        acquisitionBottomPanel.setPreferredSize(new 
            Dimension(0, 100));
        TitledBorder buttonBorder1 = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 3),
                "Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Garamond", Font.BOLD, 22),
                new Color(0, 51, 102)
            );
        acquisitionBottomPanel.setBorder(buttonBorder);

        /*==========================================================
         *               BUY PRODUCT BUTTON
        =============================================================*/
        JButton buyProductButton = new JButton("Buy Product");
        buyProductButton.setBackground(new Color(58, 63, 75));
        buyProductButton.setForeground(Color.WHITE);
        buyProductButton.setFocusPainted(false);
        buyProductButton.setPreferredSize(new Dimension(110, 30));
        buyProductButton.setFont(new 
            Font("Helvetica", Font.BOLD, 12));
        acquisitionBottomPanel.add(buyProductButton);

        /*===========================================================
         *              BUY BUTTON FUNCTIONALITY
        ============================================================*/
        // Validates visitor ID and artwork details, processes the purchase, and displays a confirmation or error message.
        buyProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String inputId = visitorId1Field.getText().trim();
                        if (inputId.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter a Visitor ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (!inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Visitor ID must be a positive number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Retrieve product details
                        String artworkName = artworkNameField.getText().trim();
                        String artworkPriceStr = artworkPriceField.getText().trim();
                        // Check if artwork name field is empty
                        if (artworkName.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter artwork name.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        // Check if artwork price field is empty
                        if (artworkPriceStr.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter artwork price.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        double artworkPrice = Double.parseDouble(artworkPriceStr);
                        if (artworkPrice <= 0) {
                            JOptionPane.showMessageDialog(null, "Artwork price must be a positive number!", "Invalid Price", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Call the visitor's buyProduct method
                        String message = foundVisitor.buyProduct(artworkName, artworkPrice);
                        //String message = "\"" + artworkName + "\" has been bought successfully!";
                        JOptionPane.showMessageDialog(null, message, "Buy Product", JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Artwork price must be a valid number!", "Invalid Price", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*============================================================
         *            CANCEL PRODUCT BUTTON
        ==============================================================*/
        JButton cancelProductButton = new JButton("Cancel Product");
        cancelProductButton.setBackground(new Color(58, 63, 75));
        cancelProductButton.setForeground(Color.WHITE);
        cancelProductButton.setFocusPainted(false);
        cancelProductButton.setPreferredSize(new Dimension(125, 30));
        cancelProductButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        acquisitionBottomPanel.add(cancelProductButton);

        /*==============================================================
         *          CANCEL PRODUCT BUTTON FUNCTIONALITY
        ================================================================*/
        // Validates visitor ID and cancellation details, cancels the specified artwork, and shows a confirmation or error message.
        cancelProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String inputId = visitorId1Field.getText().trim(); // Text field for Visitor ID in your Art Acquisition panel

                        if (inputId.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter Visitor ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (!inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Visitor ID must be a positive number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String artworkName = artworkNameField.getText().trim(); // Field for artwork name
                        String cancellationReason = cancellationReasonField.getText().trim(); // Field for reason of cancellation
                        if (artworkName.isEmpty() || cancellationReason.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Artwork name and cancellation reason cannot be empty!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        foundVisitor.cancelProduct(artworkName, cancellationReason);
                        String message = "\"" + artworkName + "\" has been cancelled.";
                        JOptionPane.showMessageDialog(null, message, "Product Cancelled", JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid number format for Visitor ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*============================================================================
         *              ASSIGN PERSONAL ART ADVISOR BUTTON
        =============================================================================*/
        JButton personalAdvisorButton = new JButton("Assign Personal Art Advisor");
        personalAdvisorButton.setBackground(new Color(58, 63, 75));
        personalAdvisorButton.setForeground(Color.WHITE);
        personalAdvisorButton.setFocusPainted(false);
        personalAdvisorButton.setPreferredSize(new Dimension(200, 30));
        personalAdvisorButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        // personalAdvisorButton.setEnabled(false);
        acquisitionBottomPanel.add(personalAdvisorButton);

        /*===============================================================================
         *              ASSIGN PERSONAL ART ADVISOR BUTTON FUNCTIONALITY
        ==================================================================================*/
        // Validates visitor ID and assigns a personal art advisor to eligible Elite visitors based on reward points.
        personalAdvisorButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    String inputId = visitorId1Field.getText().trim();
                    try{
                        if (inputId.isEmpty() || !inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Visitor ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (foundVisitor instanceof EliteVisitor) {
                            EliteVisitor elite = (EliteVisitor) foundVisitor;
                            if(elite.assignPersonalArtAdvisor()){
                                JOptionPane.showMessageDialog(null, "Personal Art Advisor assigned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Reward point not equal to 5000. Cannot assign advisor", "Not Eligible", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                "Only Elite Visitors can have a Personal Art Advisor.",
                                "Not Eligible", JOptionPane.WARNING_MESSAGE);
                        }
                    }catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Something went wrong: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            });

        /*============================================================
         *              CALCULATE DISCOUNT BUTTON
        ==============================================================*/
        JButton discountButton = new JButton("Calculate Discount");
        discountButton.setBackground(new Color(58, 63, 75));
        discountButton.setForeground(Color.WHITE);
        discountButton.setFocusPainted(false);
        discountButton.setPreferredSize(new Dimension(140, 30));
        discountButton.setFont(new 
            Font("Helvetica", Font.BOLD, 12));
        acquisitionBottomPanel.add(discountButton);

        /*================================================================
         *              DISCOUNT BUTTON FUNCTIONALITY
        =================================================================*/
        // Validates visitor ID, calculates applicable discount, and displays visitor and artwork details with the discount amount.
        discountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputId = visitorId1Field.getText().trim();
                    try{
                        if (inputId.isEmpty() || !inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Visitor ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        double discount = foundVisitor.calculateDiscount();
                        String visitorName = foundVisitor.getFullName();
                        String artworkName = artworkNameField.getText().trim(); // or however you retrieve it

                        String message = "Visitor Name: " + visitorName +
                            "\nArtwork Name: " + artworkName +
                            "\nDiscount Received: Rs. " + discount;

                        JOptionPane.showMessageDialog(null, message, "Discount Details", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Something went wrong: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*================================================================
         *              CALCULATE REWARD POINT BUTTON
        ==================================================================*/
        JButton rewardPointsButton = new JButton("Calculate Reward Points");
        rewardPointsButton.setBackground(new Color(58, 63, 75));
        rewardPointsButton.setForeground(Color.WHITE);
        rewardPointsButton.setFocusPainted(false);
        rewardPointsButton.setPreferredSize(new Dimension(180, 30));
        rewardPointsButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        acquisitionBottomPanel.add(rewardPointsButton);

        /*==================================================================
         *              CALCULATE REWARD POINT BUTTON FUNCTIONALITY
        ===================================================================*/
        // Validates visitor ID, calculates reward points, and displays visitor and artwork details with the earned points.
        rewardPointsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String inputId = visitorId1Field.getText().trim();
                        if (inputId.isEmpty() || !inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Visitor ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        double points = foundVisitor.calculateRewardPoint();
                        String visitorName = foundVisitor.getFullName();
                        String artworkName = artworkNameField.getText().trim(); 

                        String message = "Visitor Name: " + visitorName +
                            "\nArtwork Name: " + artworkName +
                            "\nReward points Received: Rs. " + points;

                        JOptionPane.showMessageDialog(null, message,"Reward Points", JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*=================================================================
         *              GENERATE BILL BUTTON
        ==================================================================*/
        JButton billButton = new JButton("Generate Bill");
        billButton.setBackground(new Color(58, 63, 75));
        billButton.setForeground(Color.WHITE);
        billButton.setFocusPainted(false);
        billButton.setPreferredSize(new Dimension(120, 30));
        billButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        acquisitionBottomPanel.add(billButton);

        /*=======================================================================
         *              GENERATE BILL BUTTON FUNCTIONALITY
        ========================================================================= */
        // Validates visitor ID, generates a detailed bill for purchased artwork, saves it to a file, and shows confirmation.
        billButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        String inputId = visitorId1Field.getText().trim();
                        if (inputId.isEmpty() || !inputId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Visitor ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int visitorId = Integer.parseInt(inputId);
                        ArtGalleryVisitor foundVisitor = null;
                        for (ArtGalleryVisitor v : visitors) {
                            if (v.getVisitorId() == visitorId) {
                                foundVisitor = v;
                                break;
                            }
                        }
                        if (foundVisitor == null) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        foundVisitor.generateBill();
                        if (!foundVisitor.isBought) {
                            JOptionPane.showMessageDialog(null, "No purchase made to generate bill.", "No Purchase", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        String billText = "*****************************************\n"
                            + "*          ART GALLERY BILL             *\n"
                            + "*****************************************\n"
                            + "Visitor ID     : " + foundVisitor.visitorId + "\n"
                            + "Visitor Name   : " + foundVisitor.fullName + "\n"
                            + "Artwork Name   : " + foundVisitor.artworkName + "\n"
                            + "Artwork Price  : " + foundVisitor.artworkPrice + "\n"
                            + "Discount Amount: " + foundVisitor.discountAmount + "\n"
                            + "Final Price    : " + foundVisitor.finalPrice + "\n"
                            + "*****************************************\n";

                        // Create filename based on Visitor ID and Name
                        String cleanName = foundVisitor.fullName.replaceAll("\\s+", ""); // remove spaces
                        String fileName = "Bill_" + foundVisitor.visitorId + "_" + cleanName + ".txt";

                        // Write the bill to file
                        try (FileWriter fw = new FileWriter(fileName)) {
                            fw.write(billText);
                        }

                        // Show success message
                        JOptionPane.showMessageDialog(null,
                            "Bill generated successfully!\nSaved as :" + fileName, "Success", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );

        buttonPanelContainer.add(acquisitionBottomPanel, "acquisitionButtons");
        myFrame.add(buttonPanelContainer, BorderLayout.SOUTH);

        /*============================================================
         *              VISITORS DETAIL PANEL
        =============================================================*/
        JPanel RecordsPanel = new JPanel(new BorderLayout());
        RecordsPanel.setBackground(new Color(245, 245, 255));
        mainPanel.add(RecordsPanel, "records");

        JLabel recordsTitle = new JLabel("Visitor Records");
        recordsTitle.setFont(new Font("Garamond", Font.BOLD, 30));
        recordsTitle.setForeground(new Color(0, 51, 102));
        recordsTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel recordTitleWrapper = new JPanel(new BorderLayout());
        recordTitleWrapper.setBackground(new Color(245, 245, 255));
        recordTitleWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        recordTitleWrapper.add(recordsTitle, BorderLayout.CENTER);

        GridBagConstraints recordGbc = new GridBagConstraints();
        recordGbc.gridx = 0;
        recordGbc.gridy = 0;
        recordGbc.gridwidth = 2;
        recordGbc.anchor = GridBagConstraints.CENTER;
        recordGbc.fill = GridBagConstraints.HORIZONTAL;

        RecordsPanel.add(recordTitleWrapper, BorderLayout.NORTH);
        JPanel tablePanel = new JPanel();
        // Column headers
        String[] columnNames = {"Visitor ID", "Name", "Gender", "Contact Number", "Ticket Type", "Active Status", "Visit Count" };

        // Add 10 empty rows as placeholders
        String[][] data = new String[30][columnNames.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                data[i][j] = ""; // leave cells blank
            }
        }

        // Create a DefaultTableModel with empty rows
        model = new DefaultTableModel(data, columnNames);

        // Create JTable and set font/style
        JTable visitorTable = new JTable(model);
        visitorTable.setFont(new Font("Arial", Font.PLAIN, 16));
        visitorTable.setRowHeight(28);
        visitorTable.getTableHeader().setFont(new Font("Garamond", Font.BOLD, 18));
        visitorTable.getTableHeader().setBackground(new Color(220, 220, 250));
        visitorTable.getTableHeader().setForeground(new Color(0, 51, 102));

        // Wrap table in JScrollPane
        JScrollPane scrollPane = new JScrollPane(visitorTable);

        // Add scroll pane to the center of RecordsPanel
        RecordsPanel.add(scrollPane, BorderLayout.CENTER);

        //===bottom button for Visitor Details panel===
        JPanel visitorDetailsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        visitorDetailsPanel.setBackground(new Color(230, 240, 255));
        visitorDetailsPanel.setPreferredSize(new Dimension(0, 100));
        TitledBorder buttonBorder2 = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 3),
                "Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Garamond", Font.BOLD, 22),
                new Color(0, 51, 102)
            );
        visitorDetailsPanel.setBorder(buttonBorder);

        /*==================================================
         *          REFRESH BUTTON
        ===================================================*/
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(58, 63, 75));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setPreferredSize(new Dimension(120, 37));
        refreshButton.setFont(new 
            Font("Helvetica", Font.BOLD, 14));
        visitorDetailsPanel.add(refreshButton);

        /*==========================================================
         *          REFRESH BUTTON FUNCTIONALITY
        ===========================================================*/
        // Clears the table and reloads visitor data from the list, displaying updated records or a message if empty.
        refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        model.setRowCount(0); // Clear previous data in table

                        // Loop through visitors list and add each to the table
                        for (ArtGalleryVisitor visitor : visitors) {
                            String[] row = new String[]{
                                    String.valueOf(visitor.getVisitorId()),
                                    visitor.getFullName(),
                                    visitor.getGender(),
                                    visitor.getContactNumber(),
                                    visitor.getTicketType(),
                                    visitor.getIsActive() ? "Active" : "Inactive",
                                    String.valueOf(visitor.getVisitCount())
                                };
                            model.addRow(row); // Add the row to table
                        }

                        if (visitors.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No visitor record to display!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error while refreshing table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        buttonPanelContainer.add(visitorDetailsPanel, "refreshButtons");
        myFrame.add(buttonPanelContainer, BorderLayout.SOUTH);

        registrationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "registerPanel");
                    buttonCardLayout.show(buttonPanelContainer, "registrationButtons");
                }
            });

        purchaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "acquisitions");
                    buttonCardLayout.show(buttonPanelContainer, "acquisitionButtons");
                }
            });

        recordButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(mainPanel, "records");
                    buttonCardLayout.show(buttonPanelContainer, "refreshButtons");
                }
            });

        /*=========================================================
         * SAVE TO FILE BUTTON FUNCTIONALITY
        ===========================================================*/
        // Saves visitor data to a binary file and writes formatted visitor details to a text file, then confirms success.
        saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream("Visitors.dat"));
                        FileWriter fileWriter = new FileWriter("VisitorDetails.txt"); 
                        BufferedWriter bw = new BufferedWriter(fileWriter);

                        // Header line with fixed-width columns
                        String header = String.format(
                                "%-3s %-15s %-15s %-12s %-20s %-15s %-15s %-15s %-15s %-15s %-15s %-20s %-15s %-15s %-15s",
                                "ID", "Name", "Contact No", "Gender", "Reg Date", "Ticket Type", "Visit Count", "Cancel Count",
                                "Active", "Purchased", "Buy Count", "Artwork Name", "Artwork Price", "Discount", "Final Price"
                            );

                        bw.write(header);
                        bw.newLine();
                        bw.write("=".repeat(header.length()));
                        bw.newLine();

                        for (ArtGalleryVisitor v : visitors) {
                            objOS.writeObject(v);
                            String row = String.format(
                                    "%-3d %-15s %-15s %-12s %-20s %-15s %-15d %-15d %-15s %-15s %-15d %-20s %-15.2f %-15f %-15f",
                                    v.getVisitorId(),
                                    v.getFullName(),
                                    v.getContactNumber(),
                                    v.getGender(),
                                    v.getRegistrationDate(),
                                    v.getTicketType(),
                                    v.getVisitCount(),
                                    v.getCancelCount(),
                                    String.valueOf(v.getIsActive()),
                                    String.valueOf(v.getIsBought()),
                                    v.getBuyCount(),
                                    v.getArtworkName(),
                                    v.getArtworkPrice(),
                                    v.getDiscountAmount(),
                                    v.getFinalPrice()
                                );

                            bw.write(row);
                            bw.newLine();

                        }

                        bw.close();
                        JOptionPane.showMessageDialog(null, "Visitor details added successfull", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*===================================================================
         *                  READ FROM FILE
        ====================================================================*/
        // Reads visitor details from a text file and displays them in a scrollable text area within a new frame.
        readButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        File file = new File("VisitorDetails.txt");
                        if (!file.exists()) {
                            JOptionPane.showMessageDialog(null, "VisitorDetails.txt file not found!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        StringBuilder sb = new StringBuilder();

                        while ((line = br.readLine()) != null) {
                            sb.append(line).append("\n");
                        }
                        br.close();

                        // Display in a new frame with scrollable text area
                        JFrame displayFrame = new JFrame("Stored Visitor Records");
                        displayFrame.setSize(800, 600);
                        displayFrame.setLocationRelativeTo(null);

                        JTextArea textArea = new JTextArea(sb.toString());
                        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                        textArea.setEditable(false);

                        JScrollPane scrollPane = new JScrollPane(textArea);
                        displayFrame.add(scrollPane);

                        displayFrame.setVisible(true);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        /*===================================================================
         *                  CLOSE CONFIRMATION OPTION
        ====================================================================*/
        myFrame.addWindowListener(
            new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent w){
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to save before closing?", "Closing", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION){
                        //save operation
                        System.exit(0);
                    }else if(choice == JOptionPane.NO_OPTION){
                        System.exit(0);
                    }
                }
            }
        );

        myFrame.setVisible(true);
    }

    public static void main(String[] args){
        new ArtGalleryGUI();
    }
}

