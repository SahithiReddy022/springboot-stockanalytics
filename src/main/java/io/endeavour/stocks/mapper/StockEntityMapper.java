package io.endeavour.stocks.mapper;

import io.endeavour.stocks.entity.stocks.StockFundamentalsEntity;
import io.endeavour.stocks.vo.StockFundamentalsVO;

import java.util.List;

public class StockEntityMapper
{
    public static List<StockFundamentalsVO> map(List<StockFundamentalsEntity> stockFundamentalsEntities){
        List<StockFundamentalsVO> stockFundamentalsVOList = stockFundamentalsEntities.stream()
                .map(stockFundamentalsEntity -> {
                    StockFundamentalsVO stockFundamentalsVO = new StockFundamentalsVO();
                    stockFundamentalsVO.setTickerSymbol(stockFundamentalsEntity.getTickerSymbol());
                    stockFundamentalsVO.setMarketCap(stockFundamentalsEntity.getMarketCap());
                    stockFundamentalsVO.setSectorId(stockFundamentalsEntity.getSectorId());
                    stockFundamentalsVO.setSubSectorId(stockFundamentalsEntity.getSubSectorId());
                    stockFundamentalsVO.setCurrentRatio(stockFundamentalsEntity.getCurrentRatio());
                    return stockFundamentalsVO;
                })
                .toList();
        return stockFundamentalsVOList;
    }
}
