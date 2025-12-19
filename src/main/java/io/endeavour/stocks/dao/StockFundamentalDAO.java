package io.endeavour.stocks.dao;

import io.endeavour.stocks.entity.stocks.StockFundamentalsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Component
public class StockFundamentalDAO {

    @Autowired
    @Qualifier(value = "entityManagerFactory")
    private EntityManager entityManager;

    public List<StockFundamentalsEntity> getTopNStockFundamentalsEntitiesUsingJPQL(Integer limit){
        TypedQuery<StockFundamentalsEntity> typedQuery = entityManager.createQuery("SELECT s " +
                "FROM StockFundamentalsEntity s " +
                "WHERE s.currentRatio IS NOT NULL order by s.currentRatio desc", StockFundamentalsEntity.class);
        typedQuery.setMaxResults(limit);
        List<StockFundamentalsEntity> stockFundamentalsEntityList = typedQuery.getResultList();
        return stockFundamentalsEntityList;
    }

    public List<StockFundamentalsEntity> getTopNStockFundamentalsEntitiesUsingCriteria(Integer limit){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockFundamentalsEntity> criteriaQuery = cb.createQuery(StockFundamentalsEntity.class);
        Root<StockFundamentalsEntity> root = criteriaQuery.from(StockFundamentalsEntity.class);
        criteriaQuery.select(root)
                .where(cb.isNotNull(root.get("currentRatio")))
                .orderBy(cb.desc(root.get("currentRatio")));
        List<StockFundamentalsEntity> resultList = entityManager.createQuery(criteriaQuery)
                .setMaxResults(limit)
                .getResultList();
        return resultList;

    }
}
