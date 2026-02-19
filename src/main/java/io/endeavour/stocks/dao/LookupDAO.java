package io.endeavour.stocks.dao;

import io.endeavour.stocks.mapper.SectorLookupMapper;
import io.endeavour.stocks.vo.SectorLookupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
public class LookupDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<SectorLookupVO> getSectorLookups(){
        String sql= """
                SELECT SECTOR_ID,SECTOR_NAME
                FROM ENDEAVOUR.SECTOR_LOOKUP
                """;
        List<SectorLookupVO> sectorLookupVOList=jdbcTemplate.query(sql, new Object[0], new RowMapper<SectorLookupVO>() {
            @Override
            public SectorLookupVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                SectorLookupVO sectorLookupVO=new SectorLookupVO();
                sectorLookupVO.setSectorId(rs.getInt("SECTOR_ID"));
                sectorLookupVO.setSectorName(rs.getString("SECTOR_NAME"));
                return sectorLookupVO;
            }
        });
        return sectorLookupVOList;
    }

    public SectorLookupVO getSectorLookupBySectorId(Integer sectorId){
        String sql= """
                SELECT SECTOR_ID,SECTOR_NAME
                FROM ENDEAVOUR.SECTOR_LOOKUP
                WHERE SECTOR_ID=?
                """;
        Object[] inputs={sectorId};
        SectorLookupVO sectorLookupVO =jdbcTemplate.queryForObject(sql, new SectorLookupMapper(),inputs);
            return sectorLookupVO;
    }
}
