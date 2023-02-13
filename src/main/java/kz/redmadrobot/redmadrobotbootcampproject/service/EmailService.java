package kz.redmadrobot.redmadrobotbootcampproject.service;

import kz.redmadrobot.redmadrobotbootcampproject.model.Ad;

public interface EmailService {
    void sendBettingNotification(String to, Ad ad);
    void sendPurchaseNotification(String sellerEmail, String buyerEmail, String adName, int price);
}
