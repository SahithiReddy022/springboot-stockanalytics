package io.endeavour.stocks.service;

import io.endeavour.stocks.dao.StockFundamentalsDAO;
import io.endeavour.stocks.vo.StockFundamentalsVO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StockFundamentalsService {

    @Autowired
    private StockFundamentalsDAO dao;

    public List<StockFundamentalsVO> getFundamentals(List<String> tickers) {
        return dao.getFundamentals(tickers);
    }
}