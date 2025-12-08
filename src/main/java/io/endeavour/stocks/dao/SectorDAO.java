package io.endeavour.stocks.dao;

import io.endeavour.stocks.vo.SectorVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class SectorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SectorVO> getAllSectors() {
        String sql = """
            SELECT sector_id, sector_name
            FROM endeavour.sector_lookup
            ORDER BY sector_id
            """;

        return jdbcTemplate.query(sql, (rs, i) ->
                new SectorVO(
                        rs.getInt("sector_id"),
                        rs.getString("sector_name")
                )
        );
    }

    public SectorVO getSectorById(int sectorId) {
        String sql = """
            SELECT sector_id, sector_name
            FROM endeavour.sector_lookup
            WHERE sector_id = ?
            """;

        List<SectorVO> list = jdbcTemplate.query(
                sql,
                new Object[]{sectorId},
                (rs, i) -> new SectorVO(
                        rs.getInt("sector_id"),
                        rs.getString("sector_name")
                )
        );

        return list.isEmpty() ? null : list.get(0);
    }
}
