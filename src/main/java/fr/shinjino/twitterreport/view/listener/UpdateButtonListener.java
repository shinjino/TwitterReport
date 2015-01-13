package fr.shinjino.twitterreport.view.listener;

import fr.shinjino.twitterreport.GlobalModel;
import fr.shinjino.twitterreport.view.MainWindow;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class UpdateButtonListener implements ActionListener {
    private MainWindow mainWindow = null;

    public UpdateButtonListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            URL url = new URL(mainWindow.getTextFieldUriList().getText());
            String page = IOUtils.toString(url.openConnection().getInputStream());
            String[] usernames = page.split("\n");

            for (String username : usernames) {
                if (username.startsWith("https://twitter.com/")) {
                    username = username.replace("https://twitter.com/", "");
                }
                GlobalModel.users.addRow(username, "NEW");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.mainWindow, "Bad Url.");

        }
    }
}
