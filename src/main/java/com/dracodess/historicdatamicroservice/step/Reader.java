package com.dracodess.historicdatamicroservice.step;

import com.dracodess.historicdatamicroservice.model.Stock;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class Reader {
    public static FlatFileItemReader<Stock> reader(String path) {
        FlatFileItemReader<Stock> reader = new FlatFileItemReader<Stock>();

        reader.setResource(new ClassPathResource(path));
        reader.setLineMapper(new DefaultLineMapper<Stock>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "id", "symbol", "open_price", "close_price", "date_of_record" });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Stock>() {
                    {
                        setTargetType(Stock.class);
                    }
                });
            }
        });
        return reader;
    }
}
