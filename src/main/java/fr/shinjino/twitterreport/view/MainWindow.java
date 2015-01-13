package fr.shinjino.twitterreport.view;

import fr.shinjino.twitterreport.GlobalModel;
import fr.shinjino.twitterreport.view.listener.SettingsButtonListener;
import fr.shinjino.twitterreport.view.listener.StartButtonListener;
import fr.shinjino.twitterreport.view.listener.StopButtonListener;
import fr.shinjino.twitterreport.view.listener.UpdateButtonListener;

import javax.swing.*;
import java.awt.*;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class MainWindow extends JFrame {
    private JTextField textFieldUriList;
    private JButton updateButton;
    private JPanel rootPanel;
    private JButton startButton;
    private JButton stopButton;
    private JButton settingsButton;
    private JProgressBar progressBar;

    public MainWindow() {
        super();
        setContentPane(rootPanel);
        pack();

        this.setMinimumSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createUIComponents();

        GlobalModel.mainWindow = this;
        GlobalModel.progressBar = this.progressBar;

        setVisible(true);
    }

    private void createUIComponents() {
        this.updateButton.addActionListener(new UpdateButtonListener(this));
        this.startButton.addActionListener(new StartButtonListener(this));
        this.stopButton.addActionListener(new StopButtonListener(this));
        this.settingsButton.addActionListener(new SettingsButtonListener(this));

    }

    public JTextField getTextFieldUriList()
    {
        return textFieldUriList;
    }

    public JButton getStartButton()
    {
        return this.startButton;
    }
    public JButton getStopButton()
    {
        return this.stopButton;
    }
    public JButton getUpdateButton()
    {
        return this.updateButton;
    }
}
