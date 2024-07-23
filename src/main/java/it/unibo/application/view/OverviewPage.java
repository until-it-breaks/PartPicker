package it.unibo.application.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import it.unibo.application.controller.Controller;
import it.unibo.application.model.states.State;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class OverviewPage extends JPanel {
    Controller controller;

    public OverviewPage(final Controller controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        final JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topBar.setBackground(Color.LIGHT_GRAY);
        final JLabel userInfoLabel = new JLabel("Logged in as: User");
        final JButton quitButton = new JButton("Quit");
        topBar.add(userInfoLabel);
        topBar.add(quitButton);

        final JPanel middleSection = new JPanel();
        middleSection.setLayout(new BoxLayout(middleSection, BoxLayout.Y_AXIS));

        final JPanel hotBuildsTitlePanel = new JPanel();
        hotBuildsTitlePanel.add(new JLabel("Hot Builds"));

        final JPanel recentBuildsRow = new JPanel();
        recentBuildsRow.setLayout(new BoxLayout(recentBuildsRow, BoxLayout.X_AXIS));

        for (int i = 1; i <= 5; i++) {
            recentBuildsRow.add(createBuildFrame("Latest Item " + i));
        }

        final JLabel viewAllBuilds = createViewAllLabel("View All Recent Builds");
        final JPanel viewAllBuildsRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewAllBuildsRow.add(viewAllBuilds);


        final JPanel ownBuildsTitlePanel = new JPanel();
        ownBuildsTitlePanel.add(new JLabel("Own Builds"));

        final JPanel ownBuildsRow = new JPanel();
        ownBuildsRow.setLayout(new BoxLayout(ownBuildsRow, BoxLayout.X_AXIS));

        for (int i = 1; i <= 3; i++) {
            ownBuildsRow.add(createBuildFrame("My Own Build " + i));
        }

        final JLabel viewAllOwnBuilds = createViewAllLabel("View All Own Builds");
        final JPanel viewAllOwnBuildsRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewAllOwnBuildsRow.add(viewAllOwnBuilds);

        middleSection.add(hotBuildsTitlePanel);
        middleSection.add(recentBuildsRow);
        middleSection.add(viewAllBuildsRow);
        middleSection.add(ownBuildsTitlePanel);
        middleSection.add(ownBuildsRow);
        middleSection.add(viewAllOwnBuildsRow);

        final JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.X_AXIS));
        bottomSection.setBackground(Color.GRAY);
        final JButton buildOwnButton = new JButton("Create a build");
        final JButton CPUsButton = new JButton("CPUs");
        final JButton VideoCardButton = new JButton("Video Cards");
        final JButton MemoryButton = new JButton("Memory");
        final JButton StorageButton = new JButton("Storage");
        final JButton CPUCoolersButton = new JButton("CPU Coolers");
        final JButton CasesButton = new JButton("Cases");
        final JButton MotherboardsButton = new JButton("Motherboards");
        final JButton PowerSuppliesButton = new JButton("Power Supplies");
        final JButton ProfileDetailsButton = new JButton("Your details");
        buildOwnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomSection.add(CPUsButton);
        bottomSection.add(VideoCardButton);
        bottomSection.add(MemoryButton);
        bottomSection.add(StorageButton);
        bottomSection.add(CPUCoolersButton);
        bottomSection.add(CasesButton);
        bottomSection.add(MotherboardsButton);
        bottomSection.add(PowerSuppliesButton);
        bottomSection.add(Box.createHorizontalGlue());
        bottomSection.add(ProfileDetailsButton);
        bottomSection.add(buildOwnButton);

        this.add(topBar, BorderLayout.NORTH);
        this.add(middleSection, BorderLayout.CENTER);
        this.add(bottomSection, BorderLayout.SOUTH);
    
        CPUsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        VideoCardButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        MemoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        StorageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        CPUCoolersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        CasesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        MotherboardsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        PowerSuppliesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        ProfileDetailsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.VIEWING_PART);
            }
            
        });
        buildOwnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setAppState(State.BUILDING);
            }
        });
    }
    
    private JPanel createBuildFrame(final String title) {
        final JPanel buildFrame = new JPanel();
        buildFrame.setLayout(new BorderLayout());
        buildFrame.setPreferredSize(new Dimension(200, 150));
        buildFrame.setBorder(BorderFactory.createEtchedBorder());

        final JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        buildFrame.add(titleLabel, BorderLayout.NORTH);

        final JTextArea description = new JTextArea("Description of " + title);
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setOpaque(false);
        description.setEditable(false);
        description.setFocusable(false);
        buildFrame.add(description, BorderLayout.CENTER);

        buildFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Ciaone");
                controller.setAppState(State.VIEWING_BUILD);
            }
        });

        return buildFrame;
    }

    private JLabel createViewAllLabel(final String text) {
        final JLabel viewAllLabel = new JLabel("<HTML><U>" + text + "</U></HTML>");
        viewAllLabel.setForeground(Color.BLUE);
        viewAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewAllLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                JOptionPane.showMessageDialog(null, "You clicked: " + text);
            }
        });
        return viewAllLabel;
    }
}
