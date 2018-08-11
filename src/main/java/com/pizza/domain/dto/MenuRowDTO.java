package com.pizza.domain.dto;

import com.pizza.domain.entities.PriceRow;
import com.pizza.web.AdminController;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.codec.Base64;

import java.io.UnsupportedEncodingException;

public class MenuRowDTO {

    private static final Logger LOG = Logger.getLogger(MenuRowDTO.class.getName());

    private String encodedPicture;
    private PriceRow priceRow;

    public MenuRowDTO(PriceRow priceRow) {
        this.priceRow = priceRow;
        encodedPicture = getEncodedPicture(priceRow);
    }

    public PriceRow getPriceRow() {
        return priceRow;
    }

    public void setPriceRow(PriceRow priceRow) {
        this.priceRow = priceRow;
    }

    public String getEncodedPicture() {
        return encodedPicture;
    }

    public void setEncodedPicture(String encodedPicture) {
        this.encodedPicture = encodedPicture;
    }

    private String getEncodedPicture(PriceRow priceRow) {
        String encodedPicture = null;
        byte[] bytes = Base64.encode(priceRow.getProduct().getPicture());
        try {
            encodedPicture = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
        }
        return encodedPicture;
    }
}
