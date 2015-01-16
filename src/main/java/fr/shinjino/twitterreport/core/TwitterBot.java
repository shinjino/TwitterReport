package fr.shinjino.twitterreport.core;

import fr.shinjino.twitterreport.GlobalModel;
import fr.shinjino.twitterreport.view.MainWindow;

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

        GlobalModel.mainWindow.toogleMode(MainWindow.WindowMode.DEFAULT);

    }

    public void stop() {
        this.keep_running = false;
    }

    private void loadUsers() {
        Collection<String> usernames = GlobalModel.users.getNewUsers();
        for (String username : usernames) {
            this.users.add(username);
        }
    }

    public void reportUser(String screenName) {
        System.out.println("[REPORT] " + screenName);
        if (this.twitterApi.reportUser(screenName)) {
            GlobalModel.users.setStatus(screenName, "DONE");
        }
    }
}
