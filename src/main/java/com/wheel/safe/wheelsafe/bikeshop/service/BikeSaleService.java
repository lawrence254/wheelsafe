package com.wheel.safe.wheelsafe.bikeshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleCreateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleMapper;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleResponseDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleUpdateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeSale;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.BikeSaleNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.repository.BikeSaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BikeSaleService {

    private final BikeSaleRepository bikeSaleRepository;
    private final BikeSaleMapper bikeSaleMapper;

    public BikeSaleResponseDto createSale(BikeSaleCreateRequestDto bikeSale) {
        BikeSale sale = bikeSale.toEntity();
        sale.setSaleStatus(SaleStatus.PENDING);
        BikeSale savedSale = bikeSaleRepository.save(sale);
        return bikeSaleMapper.fromEntity(savedSale);
    }

    public BikeSaleResponseDto updateSale(BikeSaleUpdateRequestDto bikeSale) {
        BikeSale existingSale = bikeSaleRepository.findById(bikeSale.getId())
                .orElseThrow(() -> BikeSaleNotFoundException.withId(bikeSale.getId()));

        BikeSale updatedSale = bikeSale.toEntity(existingSale);
        BikeSale savedSale = bikeSaleRepository.save(updatedSale);

        return bikeSaleMapper.fromEntity(savedSale);
    }

    public void deleteSale(Long id) {
        bikeSaleRepository.deleteById(id);
    }

    public BikeSaleResponseDto getSale(Long id) {
        BikeSale sale = bikeSaleRepository.findById(id)
                .orElseThrow(() -> BikeSaleNotFoundException.withId(id));
        return bikeSaleMapper.fromEntity(sale);
    }

    public List<BikeSaleResponseDto> getAllSales() {
        return bikeSaleRepository.findAll().stream()
                .map(bikeSaleMapper::fromEntity)
                .toList();
    }

    public List<BikeSaleResponseDto> getSalesByBikeShopId(Long bikeShopId) {
        List<BikeSale> shops = bikeSaleRepository.findByBikeShopId(bikeShopId);
        if (shops.isEmpty()) {
            throw BikeSaleNotFoundException.withBikeShopId(bikeShopId);
        }
        return shops.stream()
                .map(bikeSaleMapper::fromEntity)
                .toList();
    }

    public List<BikeSaleResponseDto> getSalesByStatus(SaleStatus status) {
        return bikeSaleRepository.findBySaleStatus(status).stream()
                .map(bikeSaleMapper::fromEntity)
                .toList();
    }

    public List<BikeSaleResponseDto> getSalesBySaleDateBetween(Date startDate, Date endDate) {
        return bikeSaleRepository.findBySaleDateBetween(startDate, endDate).stream()
                .map(bikeSaleMapper::fromEntity)
                .toList();
    }

    public List<BikeSaleResponseDto> getSalesBySalePriceBetween(Double minPrice, Double maxPrice) {
        return bikeSaleRepository.findBySalePriceBetween(minPrice, maxPrice).stream()
                .map(bikeSaleMapper::fromEntity)
                .toList();
    }
}
