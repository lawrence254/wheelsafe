package com.wheel.safe.wheelsafe.bikeshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheel.safe.wheelsafe.bikeshop.entity.BikeSale;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;

public interface BikeSaleRepository extends JpaRepository<BikeSale, Long> {
    List<BikeSale> findByBikeShopId(Long bikeShopId);
    List<BikeSale> findBySaleStatus(SaleStatus status);
    List<BikeSale> findBySaleDateBetween(Date startDate, Date endDate);
    List<BikeSale> findBySalePriceBetween(Double minPrice, Double maxPrice);

}
