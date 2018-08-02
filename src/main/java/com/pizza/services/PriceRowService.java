package com.pizza.services;

import com.pizza.domain.entities.PriceRow;
import com.pizza.repository.PriceRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceRowService {

    @Autowired
    private PriceRowRepository priceRowRepository;

    public List<PriceRow> getAllPriceRowsForProduct(long id) {
        return priceRowRepository.findAllPriceRowsForProduct(id);
    }

    public List<PriceRow> getAllPriceRowsForCurrency(long id) {
        return priceRowRepository.findAllPriceRowsForCurrency(id);
    }

    public void deactivateAllPriceRowsForCurrency(long id) {
        List<PriceRow> priceRows = priceRowRepository.findAllPriceRowsForCurrency(id);
        for (PriceRow priceRow : priceRows) {
            priceRow.setActive(false);
            priceRowRepository.update(priceRow);
        }
    }
}
