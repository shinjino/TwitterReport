package fr.shinjino.twitterreport.view.listener;

import fr.shinjino.twitterreport.GlobalModel;
import fr.shinjino.twitterreport.core.TwitterApiFactory;
import fr.shinjino.twitterreport.core.TwitterBot;
import fr.shinjino.twitterreport.view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class StartButtonListener implements ActionListener {
    private MainWindow mainWindow = null;

    public StartButtonListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        mainWindow.toogleMode(MainWindow.WindowMode.RUNNING);


        if (GlobalModel.Account.login.length() > 0
                && GlobalModel.Account.password.length() > 0) {
            try {
                GlobalModel.twitterApi = TwitterApiFactory.instanceFromLogin(GlobalModel.Account.login, GlobalModel.Account.password);
                GlobalModel.twitterBot = new TwitterBot(GlobalModel.twitterApi);
                GlobalModel.thread = new Thread(GlobalModel.twitterBot);
                GlobalModel.thread.start();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.mainWindow, e.getMessage());
                mainWindow.toogleMode(MainWindow.WindowMode.DEFAULT);
            }

        } else {
            JOptionPane.showMessageDialog(this.mainWindow, "You must define your twitter's credentials.\n Click on the Settings button.");
            mainWindow.toogleMode(MainWindow.WindowMode.DEFAULT);
        }
    }
}
