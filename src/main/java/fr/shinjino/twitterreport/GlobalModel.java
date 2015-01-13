package fr.shinjino.twitterreport;

import fr.shinjino.twitterreport.core.TwitterApi;
import fr.shinjino.twitterreport.core.TwitterBot;
import fr.shinjino.twitterreport.view.MainWindow;
import fr.shinjino.twitterreport.view.item.UserListTableModel;

import javax.swing.*;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public  class GlobalModel {

    public static MainWindow mainWindow = null;
    public static TwitterBot twitterBot = null;
    public static TwitterApi twitterApi = null;
    public static Thread thread = null;

    public static class Account{
        public static String login = "";
        public static String password = "";
    }

    public static JProgressBar progressBar;

    public static UserListTableModel users;
}
