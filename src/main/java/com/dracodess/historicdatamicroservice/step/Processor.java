package com.dracodess.historicdatamicroservice.step;

import com.dracodess.historicdatamicroservice.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Random;

public class Processor implements ItemProcessor<Stock, Stock> {

    private static final Logger log = LoggerFactory.getLogger(Processor.class);

    @Override
    public Stock process(Stock stock) throws Exception {
        Random r = new Random();

        final String symbol = stock.getSymbol().toUpperCase();
        final Double open_price = stock.getOpen_price();
        final Double close_price =stock.getClose_price();
        final String date_of_record = stock.getDate_of_record();


        final Stock fixedStock = new Stock(r.nextLong(),symbol, open_price, close_price, date_of_record);

        log.info("working on (" + stock + ")");

        return fixedStock;
    }
}