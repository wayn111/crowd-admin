package com.wayn.filemanager.param;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Thumbnail {

    @Value("${filemanager.thumbnail.width}")
    private BigInteger width;

    public BigInteger getWidth() {
        return width;
    }

    public void setWidth(BigInteger width) {
        this.width = width;
    }
}
