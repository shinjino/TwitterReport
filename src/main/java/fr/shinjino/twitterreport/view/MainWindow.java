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

    private WindowMode mode = WindowMode.DEFAULT;

    public enum WindowMode {
        DEFAULT,
        RUNNING
    }

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

    public void toogleMode(WindowMode mode) {
        switch (mode) {
            case DEFAULT:
                this.startButton.setEnabled(true);
                this.updateButton.setEnabled(true);
                this.stopButton.setEnabled(false);
                break;
            case RUNNING:
                this.startButton.setEnabled(false);
                this.updateButton.setEnabled(false);
                this.stopButton.setEnabled(true);
                break;
        }
        this.mode = mode;
    }

    public JTextField getTextFieldUriList() {
        return textFieldUriList;
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    public JButton getStopButton() {
        return this.stopButton;
    }

    public JButton getUpdateButton() {
        return this.updateButton;
    }
}
