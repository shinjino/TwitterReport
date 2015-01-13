package fr.shinjino.twitterreport.core;

import fr.shinjino.twitterreport.GlobalModel;

import java.util.Collection;
import java.util.Stack;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class TwitterBot implements Runnable {

    private TwitterApi twitterApi = null;
    private Stack<String> users = new Stack<String>();
    private boolean keep_running = true;

    public TwitterBot(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    @Override
    public void run() {

        this.loadUsers();
        GlobalModel.progressBar.setValue(0);
        GlobalModel.progressBar.setMaximum(this.users.size());

        Integer progression = 0;

        while (!this.users.empty() && this.keep_running) {
            String username = this.users.pop();
            this.reportUser(username);
            GlobalModel.progressBar.setValue(++progression);
        }

        GlobalModel.mainWindow.getStartButton().setEnabled(true);
        GlobalModel.mainWindow.getStopButton().setEnabled(false);
        GlobalModel.mainWindow.getUpdateButton().setEnabled(false);
    }

    public void stop() {
        this.keep_running = false;
    }

    private void loadUsers() {
        Collection<String> usernames = GlobalModel.users.getUsers();
        for (String username : usernames) {
            this.users.add(username);
        }
    }

    public void reportUser(String screenName) {
        if (this.twitterApi.reportUser(screenName)) {
            System.out.println("[REPORT] " + screenName);
            GlobalModel.users.setStatus(screenName, "DONE");
        }
    }
}
