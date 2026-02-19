package io.endeavour.stocks.mapper;

import io.endeavour.stocks.vo.SectorLookupVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorLookupMapper implements RowMapper<SectorLookupVO> {

    @Override
    public SectorLookupVO mapRow(ResultSet rs,int rowNum) throws SQLException {
        SectorLookupVO sectorLookupVO=new SectorLookupVO();
        sectorLookupVO.setSectorId(rs.getInt("SECTOR_ID"));
        sectorLookupVO.setSectorName(rs.getString("SECTOR_NAME"));
        return sectorLookupVO;
    }
}
