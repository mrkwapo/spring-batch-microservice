package com.dracodess.historicdatamicroservice.config;

import com.dracodess.historicdatamicroservice.dao.StockDao;
import com.dracodess.historicdatamicroservice.model.Stock;
import com.dracodess.historicdatamicroservice.step.Listener;
import com.dracodess.historicdatamicroservice.step.Processor;
import com.dracodess.historicdatamicroservice.step.Reader;
import com.dracodess.historicdatamicroservice.step.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public StockDao stockDao;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).listener(new Listener(stockDao))
                .flow(step1()).end().build();
    }
    
    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor= new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }
    
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Stock, Stock>chunk(200000)
                .reader(Reader.reader("preprocessedDataset.csv"))
                .processor(new Processor()).writer(new Writer(stockDao))
                .taskExecutor(taskExecutor()).build();
    }
}
