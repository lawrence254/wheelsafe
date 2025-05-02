package com.wheel.safe.wheelsafe.bikeshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bikeshop.entity.BikeSale;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.BikeSaleNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.repository.BikeSaleRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BikeSaleService {
    private final BikeSaleRepository bikeSaleRepository;
    private final BikeShopService bikeShopService;

    public BikeSale createSale(BikeSale bikeSale) {
        bikeShopService.getBikeShop(bikeSale.getBikeShop().getId());
        return bikeSaleRepository.save(bikeSale);
    }
    public BikeSale updateSale(BikeSale bikeSale) {
        bikeShopService.getBikeShop(bikeSale.getBikeShop().getId());
        return bikeSaleRepository.save(bikeSale);
    }
    public void deleteSale(Long id) {
        bikeSaleRepository.deleteById(id);
    }
    public BikeSale getSale(Long id) {
        return bikeSaleRepository.findById(id)
            .orElseThrow(() -> BikeSaleNotFoundException.withId(id));
    }
    public List<BikeSale> getAllSales() {
        return bikeSaleRepository.findAll();
    }
    public List<BikeSale> getSalesByBikeShopId(Long bikeShopId) {
        List<BikeSale> shops = bikeSaleRepository.findByBikeShopId(bikeShopId);
        if (shops.isEmpty()) {
            throw BikeSaleNotFoundException.withBikeShopId(bikeShopId);
        }
        return shops;
    }
    public List<BikeSale> getSalesByCustomerId(Long customerId) {
        List<BikeSale> sales = bikeSaleRepository.findByCustomerId(customerId);
        if (sales.isEmpty()) {
            throw BikeSaleNotFoundException.withCustomerId(customerId);
        }
        return sales;
    }
    public List<BikeSale> getSalesByStatus(SaleStatus status) {
        return bikeSaleRepository.findByStatus(status);
    }
    public List<BikeSale> getSalesByBikeModel(String bikeModel) {
        return bikeSaleRepository.findByBikeModel(bikeModel);
    }
    public List<BikeSale> getSalesBySaleDateBetween(Date startDate, Date endDate) {
        return bikeSaleRepository.findBySaleDateBetween(startDate, endDate);
    }
    public List<BikeSale> getSalesBySalePriceBetween(Double minPrice, Double maxPrice) {
        return bikeSaleRepository.findBySalePriceBetween(minPrice, maxPrice);
    }
 
}
