package com.dracodess.historicdatamicroservice.step;

import com.dracodess.historicdatamicroservice.dao.StockDao;
import com.dracodess.historicdatamicroservice.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import java.util.List;

public class Listener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private final StockDao stockDao;

    public Listener(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Finish Job! Check the results");

            List<Stock> stocks = stockDao.loadAllStocks();

            for (Stock stock : stocks) {
                log.info("Found <" + stock + "> in the database.");
            }
        }
    }
}
