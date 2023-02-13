package kz.redmadrobot.redmadrobotbootcampproject.service.impl;

import kz.redmadrobot.redmadrobotbootcampproject.model.Ad;
import kz.redmadrobot.redmadrobotbootcampproject.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendBettingNotification(String to, Ad ad) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bookingcerikovic@gmail.com");
        message.setTo(to);
        message.setSubject("Уведомление от аукциона!");
        message.setText("Ваша ставка была перебита пользователем " + ad.getCurrentBuyerEmail() + " суммой "
                + ad.getCurrentPrice());
        mailSender.send(message);
    }

    @Override
    public void sendPurchaseNotification(String sellerEmail, String buyerEmail, String adName, int price) {
        SimpleMailMessage messageToSeller = new SimpleMailMessage();
        messageToSeller.setFrom("bookingcerikovic@gmail.com");
        messageToSeller.setTo(sellerEmail);
        messageToSeller.setSubject("Уведомление о покупке!");
        messageToSeller.setText(adName + " удалось продать на сумму " + price);
        SimpleMailMessage messageToBuyer = new SimpleMailMessage();
        messageToBuyer.setFrom("bookingcerikovic@gmail.com");
        messageToBuyer.setTo(buyerEmail);
        messageToBuyer.setSubject("Уведомление о покупке!");
        messageToBuyer.setText("Поздравляем, вы купили " + adName + " на сумму " + price);
        mailSender.send(messageToSeller);
        mailSender.send(messageToBuyer);
    }
}
