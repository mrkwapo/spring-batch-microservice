package com.dracodess.historicdatamicroservice.step;

import com.dracodess.historicdatamicroservice.dao.StockDao;
import com.dracodess.historicdatamicroservice.model.Stock;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class Writer implements ItemWriter<Stock> {

    private final StockDao stockDao;

    public Writer(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public void write(List<? extends Stock> stocks) throws Exception {
        stockDao.insert(stocks);
    }
}
