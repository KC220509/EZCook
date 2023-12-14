package com.example.ezcook;

import com.example.ezcook.model.h_Notification_Model;

import java.util.ArrayList;
import java.util.List;

public class NotificationData {
    private static NotificationData instance;
    private List<h_Notification_Model> notificationList;

    private NotificationData() {
        notificationList = new ArrayList<>();
    }

    public static NotificationData getInstance() {
        if (instance == null) {
            instance = new NotificationData();
        }
        return instance;
    }

    public List<h_Notification_Model> getNotificationList() {
        return notificationList;
    }
}

