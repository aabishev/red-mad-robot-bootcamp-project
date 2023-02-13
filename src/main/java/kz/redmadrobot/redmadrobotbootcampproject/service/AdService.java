package kz.redmadrobot.redmadrobotbootcampproject.service;

import kz.redmadrobot.redmadrobotbootcampproject.model.Ad;

import java.util.List;

public interface AdService {
    List<Ad> findAll(String filterParam);
    void create(Ad ad);
    void update(Ad ad);
}
