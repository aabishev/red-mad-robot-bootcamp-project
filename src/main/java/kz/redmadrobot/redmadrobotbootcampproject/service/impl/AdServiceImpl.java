package kz.redmadrobot.redmadrobotbootcampproject.service.impl;

import kz.redmadrobot.redmadrobotbootcampproject.exception.ApiRequestException;
import kz.redmadrobot.redmadrobotbootcampproject.model.Ad;
import kz.redmadrobot.redmadrobotbootcampproject.repository.AdRepository;
import kz.redmadrobot.redmadrobotbootcampproject.service.AdService;
import kz.redmadrobot.redmadrobotbootcampproject.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final EmailService emailService;

    @Override
    public List<Ad> findAll(String filterParam) {
        if (filterParam.equals("byNameDesc")) {
            return adRepository.findAllByStatusTrueOrderByNameDesc();
        } else if (filterParam.equals("byNameAsc")) {
            return adRepository.findAllByStatusTrueOrderByNameAsc();
        } else if (filterParam.equals("byMinPriceDesc")) {
            return adRepository.findAllByStatusTrueOrderByMinPriceDesc();
        } else if (filterParam.equals("byMinPriceAsc")) {
            return adRepository.findAllByStatusTrueOrderByMinPriceAsc();
        }

        throw new ApiRequestException("Не правильно указан параметр фильтра", BAD_REQUEST);
    }

    @Override
    public void create(Ad ad) {
        adRepository.save(ad);
        log.info("Объявление " + ad.getName() + " пользователя " + ad.getSellerEmail() + " создано");
    }

    @Override
    @Transactional
    public void update(Ad ad) {
        Ad currentAd = adRepository.findById(ad.getId()).orElseThrow();
        if (ad.getCurrentPrice() > currentAd.getCurrentPrice()) {
            if (currentAd.getCurrentBuyerEmail() != null) {
                emailService.sendBettingNotification(currentAd.getCurrentBuyerEmail(), ad);
                log.info("Уведомление выслано пользователю " + currentAd.getCurrentBuyerEmail());
            }
            adRepository.save(ad);
            timerSchedule(ad);
        } else {
            throw new ApiRequestException("Сумма должна быть больше текущей цены!", BAD_REQUEST);
        }
    }

    private void timerSchedule(Ad ad) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Ad currentAd = adRepository.findById(ad.getId()).orElseThrow();
                if (currentAd.getCurrentPrice() > ad.getCurrentPrice() ) {
                    timer.cancel();
                    log.info("Ставка была перебита пользователем " + currentAd.getCurrentBuyerEmail());
                } else {
                    emailService.sendPurchaseNotification
                            (ad.getSellerEmail(),
                            ad.getCurrentBuyerEmail(),
                            ad.getName(),
                            ad.getCurrentPrice());
                    ad.setStatus(false);
                    adRepository.save(ad);
                    log.info("Объявление " + ad.getName() + " продано");
                }
            }
        };
        timer.schedule(timerTask, 60000);
        log.info("Запущен отчет времени на 1 минуту");
    }
}
