package com.dracodess.historicdatamicroservice.dao;

import com.dracodess.historicdatamicroservice.model.Stock;

import java.util.List;


public interface StockDao {
    public void insert(List<? extends Stock> stocks);
    List<Stock> loadAllStocks();
}
