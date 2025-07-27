package com.wheel.safe.wheelsafe.bikeshop.service;

import org.junit.jupiter.api.Test;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleCreateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleResponseDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleUpdateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeSale;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.BikeSaleNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.repository.BikeSaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BikeSaleServiceTest {


        private BikeSaleRepository bikeSaleRepository;
        private BikeSaleService bikeSaleService;

        @BeforeEach
        void setUp() {
            bikeSaleRepository = mock(BikeSaleRepository.class);
            bikeSaleService = new BikeSaleService(bikeSaleRepository);
        }

        @Test
        void testCreateSale() {
            BikeSaleCreateRequestDto dto = mock(BikeSaleCreateRequestDto.class);
            BikeSale sale = new BikeSale();
            when(dto.toEntity()).thenReturn(sale);
            BikeSale savedSale = new BikeSale();
            savedSale.setId(1L);
            savedSale.setSaleStatus(SaleStatus.PENDING);
            when(bikeSaleRepository.save(sale)).thenReturn(savedSale);

            BikeSaleResponseDto responseDto = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class).when(() -> BikeSaleResponseDto.fromEntity(savedSale)).thenReturn(responseDto);

            BikeSaleResponseDto result = bikeSaleService.createSale(dto);

            assertEquals(responseDto, result);
            assertEquals(SaleStatus.PENDING, sale.getSaleStatus());
            verify(bikeSaleRepository).save(sale);
        }

        @Test
        void testUpdateSale() {
            BikeSaleUpdateRequestDto dto = mock(BikeSaleUpdateRequestDto.class);
            BikeSale existingSale = new BikeSale();
            when(dto.getId()).thenReturn(1L);
            when(bikeSaleRepository.findById(1L)).thenReturn(Optional.of(existingSale));
            BikeSale updatedSale = new BikeSale();
            when(dto.toEntity(existingSale)).thenReturn(updatedSale);
            BikeSale savedSale = new BikeSale();
            when(bikeSaleRepository.save(updatedSale)).thenReturn(savedSale);

            BikeSaleResponseDto responseDto = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class).when(() -> BikeSaleResponseDto.fromEntity(savedSale)).thenReturn(responseDto);

            BikeSaleResponseDto result = bikeSaleService.updateSale(dto);

            assertEquals(responseDto, result);
            verify(bikeSaleRepository).save(updatedSale);
        }

        @Test
        void testUpdateSale_NotFound() {
            BikeSaleUpdateRequestDto dto = mock(BikeSaleUpdateRequestDto.class);
            when(dto.getId()).thenReturn(2L);
            when(bikeSaleRepository.findById(2L)).thenReturn(Optional.empty());

            assertThrows(BikeSaleNotFoundException.class, () -> bikeSaleService.updateSale(dto));
        }

        @Test
        void testDeleteSale() {
            bikeSaleService.deleteSale(1L);
            verify(bikeSaleRepository).deleteById(1L);
        }

        @Test
        void testGetSale() {
            BikeSale sale = new BikeSale();
            when(bikeSaleRepository.findById(1L)).thenReturn(Optional.of(sale));
            BikeSaleResponseDto responseDto = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class).when(() -> BikeSaleResponseDto.fromEntity(sale)).thenReturn(responseDto);

            BikeSaleResponseDto result = bikeSaleService.getSale(1L);

            assertEquals(responseDto, result);
        }

        @Test
        void testGetSale_NotFound() {
            when(bikeSaleRepository.findById(2L)).thenReturn(Optional.empty());
            assertThrows(BikeSaleNotFoundException.class, () -> bikeSaleService.getSale(2L));
        }

        @Test
        void testGetAllSales() {
            BikeSale sale1 = new BikeSale();
            BikeSale sale2 = new BikeSale();
            when(bikeSaleRepository.findAll()).thenReturn(Arrays.asList(sale1, sale2));
            BikeSaleResponseDto dto1 = mock(BikeSaleResponseDto.class);
            BikeSaleResponseDto dto2 = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class)
                .when(() -> BikeSaleResponseDto.fromEntity(sale1)).thenReturn(dto1);
            mockStatic(BikeSaleResponseDto.class)
                .when(() -> BikeSaleResponseDto.fromEntity(sale2)).thenReturn(dto2);

            List<BikeSaleResponseDto> result = bikeSaleService.getAllSales();

            assertEquals(2, result.size());
            assertTrue(result.contains(dto1));
            assertTrue(result.contains(dto2));
        }

        @Test
        void testGetSalesByBikeShopId() {
            BikeSale sale = new BikeSale();
            when(bikeSaleRepository.findByBikeShopId(10L)).thenReturn(List.of(sale));
            BikeSaleResponseDto dto = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class)
                .when(() -> BikeSaleResponseDto.fromEntity(sale)).thenReturn(dto);

            List<BikeSaleResponseDto> result = bikeSaleService.getSalesByBikeShopId(10L);

            assertEquals(1, result.size());
            assertEquals(dto, result.get(0));
        }

        @Test
        void testGetSalesByBikeShopId_NotFound() {
            when(bikeSaleRepository.findByBikeShopId(20L)).thenReturn(Collections.emptyList());
            assertThrows(BikeSaleNotFoundException.class, () -> bikeSaleService.getSalesByBikeShopId(20L));
        }

        @Test
        void testGetSalesByStatus() {
            BikeSale sale = new BikeSale();
            when(bikeSaleRepository.findBySaleStatus(SaleStatus.COMPLETED)).thenReturn(List.of(sale));
            BikeSaleResponseDto dto = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class)
                .when(() -> BikeSaleResponseDto.fromEntity(sale)).thenReturn(dto);

            List<BikeSaleResponseDto> result = bikeSaleService.getSalesByStatus(SaleStatus.COMPLETED);

            assertEquals(1, result.size());
            assertEquals(dto, result.get(0));
        }

        @Test
        void testGetSalesBySaleDateBetween() {
            Date start = new Date(1000);
            Date end = new Date(2000);
            BikeSale sale = new BikeSale();
            when(bikeSaleRepository.findBySaleDateBetween(start, end)).thenReturn(List.of(sale));
            BikeSaleResponseDto dto = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class)
                .when(() -> BikeSaleResponseDto.fromEntity(sale)).thenReturn(dto);

            List<BikeSaleResponseDto> result = bikeSaleService.getSalesBySaleDateBetween(start, end);

            assertEquals(1, result.size());
            assertEquals(dto, result.get(0));
        }

        @Test
        void testGetSalesBySalePriceBetween() {
            BikeSale sale = new BikeSale();
            when(bikeSaleRepository.findBySalePriceBetween(100.0, 200.0)).thenReturn(List.of(sale));
            BikeSaleResponseDto dto = mock(BikeSaleResponseDto.class);
            mockStatic(BikeSaleResponseDto.class)
                .when(() -> BikeSaleResponseDto.fromEntity(sale)).thenReturn(dto);

            List<BikeSaleResponseDto> result = bikeSaleService.getSalesBySalePriceBetween(100.0, 200.0);

            assertEquals(1, result.size());
            assertEquals(dto, result.get(0));
        }
}

