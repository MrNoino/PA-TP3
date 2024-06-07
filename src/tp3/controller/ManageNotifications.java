package tp3.controller;

import java.util.ArrayList;
import tp3.model.Notification;

/**
 * A class to manage notifications on the database
 */
public class ManageNotifications {

    private ArrayList<Notification> notifications;

    /**
     * Class constructor initializing the ArrayList
     */
    public ManageNotifications() {
        notifications = new ArrayList<Notification>();
    }
}
