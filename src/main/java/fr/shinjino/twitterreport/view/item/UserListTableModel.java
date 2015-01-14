package fr.shinjino.twitterreport.view.item;

import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class UserListTableModel extends DefaultTableModel {

    private static final String[] COLUMNS_NAME = {"Nom", "Twitter", "Status"};

    private HashMap<String, String> datas = new HashMap<String, String>();

    public UserListTableModel() {
        super(COLUMNS_NAME, 0);
    }

    public void addRow(String screenName, String status) {
        if (!datas.containsKey(screenName)) {
            String[] row = new String[3];

            row[0] = screenName;
            row[1] = "https://twitter.com/" + screenName;
            row[2] = status;

            this.datas.put(screenName, status);
            this.addRow(row);
        }
    }

    @Override
    public void removeRow(int i) {
        String value = (String) this.getValueAt(i, 0);
        super.removeRow(i);
        if (datas.containsKey(value)) {
            datas.remove(value);
        }
    }

    public void setStatus(String username, String status) {
        if (datas.containsKey(username)) {
            int rowId = 0;
            while (!this.getValueAt(rowId, 0).equals(username)) {
                rowId++;
            }

            if (rowId < this.getRowCount()) {
                this.setValueAt(status, rowId, 2);
                this.datas.put(username, status);
            }
        }
    }

    public Collection<String> getUsers() {
        return this.datas.keySet();
    }

    public Collection<String> getNewUsers() {
        List<String> usernames = new ArrayList<String>();

        for (Map.Entry<String, String> entity : this.datas.entrySet()) {
            if (entity.getValue().contentEquals("NEW"))
                usernames.add(entity.getKey());
        }

        return usernames;
    }
}