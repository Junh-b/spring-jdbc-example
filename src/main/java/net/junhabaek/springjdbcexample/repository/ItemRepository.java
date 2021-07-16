package net.junhabaek.springjdbcexample.repository;

import net.junhabaek.springjdbcexample.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ItemRepository {
    private JdbcTemplate jdbcTemplate;

    private final ItemRowMapper itemRowMapper = new ItemRowMapper();

    private final String FIND_SQL = "SELECT item_id, name, price from item where item_id=?";

    public ItemRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Item findById(Long itemId){
        return jdbcTemplate.queryForObject(FIND_SQL, itemRowMapper, itemId);
    }

    class ItemRowMapper implements RowMapper<Item>{

        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Item.Builder()
                    .withItemId(rs.getLong("item_id"))
                    .withName(rs.getString("name"))
                    .withPrice(rs.getLong("price"))
                    .build();
        }
    }
}
