package kz.redmadrobot.redmadrobotbootcampproject.controller;

import kz.redmadrobot.redmadrobotbootcampproject.model.Ad;
import kz.redmadrobot.redmadrobotbootcampproject.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/ad")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    private static final String CREATE = "/create";
    private static final String PURCHASE = "/purchase";

    @ResponseStatus(OK)
    @GetMapping
    public List<Ad> findAllByFilterParam(@RequestParam String filterParam) {
        return adService.findAll(filterParam);
    }

    @ResponseStatus(CREATED)
    @PostMapping(CREATE)
    public void create(@RequestBody Ad ad) {
        adService.create(ad);
    }

    @ResponseStatus(OK)
    @PatchMapping(PURCHASE)
    public void update(@RequestBody Ad ad) {
        adService.update(ad);
    }
}
