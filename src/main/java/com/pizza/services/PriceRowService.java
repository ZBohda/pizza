package com.pizza.services;

import com.pizza.domain.dto.PriceRowFormDTO;
import com.pizza.domain.entities.PriceRow;
import com.pizza.repository.PriceRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceRowService {

    @Autowired
    private PriceRowRepository priceRowRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CurrencyService currencyService;

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

    public void addNewPriceRowFromPriceRowFormDTO(PriceRowFormDTO priceRowFormDTO, long productId) {
        priceRowRepository.create(createPriceRowFromPriceRowFormDTO(priceRowFormDTO, productId));
    }

    public void updatePriceRowFromPriceRowFormDTO(PriceRowFormDTO priceRowFormDTO, long priceRowId){
        PriceRow priceRow = priceRowRepository.read(priceRowId);
        priceRow.setActive(priceRowFormDTO.isActive());
        priceRow.setCurrency(currencyService.getCurrencyByCode(priceRowFormDTO.getCurrencyCode()));
        priceRow.setPrice(Double.parseDouble(priceRowFormDTO.getPrice()));
        priceRowRepository.update(priceRow);
    }

    private PriceRow createPriceRowFromPriceRowFormDTO(PriceRowFormDTO priceRowFormDTO, long productId) {
        PriceRow priceRow = new PriceRow();
        priceRow.setActive(priceRowFormDTO.isActive());
        priceRow.setProduct(productService.getProduct(productId));
        priceRow.setCurrency(currencyService.getCurrencyByCode(priceRowFormDTO.getCurrencyCode()));
        priceRow.setPrice(Double.parseDouble(priceRowFormDTO.getPrice()));
        return priceRow;
    }

    public PriceRow getPriceRowById(long id) {
        return priceRowRepository.read(id);
    }

    public PriceRowFormDTO getPriceRowFormDTOFromPriceRow(PriceRow priceRow){
        PriceRowFormDTO priceRowFormDTO = new PriceRowFormDTO();
        priceRowFormDTO.setActive(priceRow.isActive());
        priceRowFormDTO.setCurrencyCode(priceRow.getCurrency().getCode());
        priceRowFormDTO.setPrice(Double.toString(priceRow.getPrice()));
        return priceRowFormDTO;
    }
}
