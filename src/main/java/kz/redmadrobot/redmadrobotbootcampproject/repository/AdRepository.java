package kz.redmadrobot.redmadrobotbootcampproject.repository;

import kz.redmadrobot.redmadrobotbootcampproject.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAllByStatusTrueOrderByNameDesc();
    List<Ad> findAllByStatusTrueOrderByNameAsc();
    List<Ad> findAllByStatusTrueOrderByMinPriceDesc();
    List<Ad> findAllByStatusTrueOrderByMinPriceAsc();
}
