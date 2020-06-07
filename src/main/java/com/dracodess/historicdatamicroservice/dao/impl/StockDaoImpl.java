package com.dracodess.historicdatamicroservice.dao.impl;

import com.dracodess.historicdatamicroservice.dao.StockDao;
import com.dracodess.historicdatamicroservice.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StockDaoImpl extends JdbcDaoSupport implements StockDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insert(List<? extends Stock> Stocks) {
        String sql = "INSERT INTO stocks " + "(id, symbol, open_price, close_price, date_of_record, change_in_price) VALUES (?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Stock stock = Stocks.get(i);
                ps.setLong(1, stock.getId());
                ps.setString(2, stock.getSymbol());
                ps.setDouble(3, stock.getOpen_price());
                ps.setDouble(4, stock.getClose_price());
                ps.setString(5,  stock.getDate_of_record());
                ps.setBigDecimal(6, BigDecimal.valueOf(stock.getClose_price() - stock.getOpen_price()).setScale(3, RoundingMode.FLOOR));
            }

            public int getBatchSize() {
                return Stocks.size();
            }
        });

    }

    public List<Stock> loadAllStocks() {
        String sql = "SELECT * FROM stocks";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Stock> result = new ArrayList<Stock>();
        for (Map<String, Object> row : rows) {
            Stock stock = new Stock();
            stock.setId((Long) row.get("id"));
            stock.setSymbol((String) row.get("symbol"));
            stock.setOpen_price((Double) row.get("open_price"));
            stock.setClose_price((Double) row.get("close_price"));
            stock.setDate_of_record((String) row.get("date_of_record"));
            stock.setChange_in_price((Double) row.get("change_in_price"));
            result.add(stock);
        }

        return result;
    }
}
