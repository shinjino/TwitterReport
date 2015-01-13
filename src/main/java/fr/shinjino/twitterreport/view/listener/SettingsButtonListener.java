package fr.shinjino.twitterreport.view.listener;

import fr.shinjino.twitterreport.view.MainWindow;
import fr.shinjino.twitterreport.view.SettingsDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class SettingsButtonListener implements ActionListener {
    public SettingsButtonListener(MainWindow mainWindow) {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SettingsDialog.draw();
    }
}
