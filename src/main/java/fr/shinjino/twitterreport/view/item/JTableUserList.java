package fr.shinjino.twitterreport.view.item;

import fr.shinjino.twitterreport.GlobalModel;

import javax.swing.*;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class JTableUserList extends JTable {

    public JTableUserList() {
        super(new UserListTableModel());
        GlobalModel.users = (UserListTableModel) this.getModel();
    }
}
