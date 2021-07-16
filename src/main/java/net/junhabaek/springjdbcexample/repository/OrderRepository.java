package net.junhabaek.springjdbcexample.repository;

import net.junhabaek.springjdbcexample.domain.Item;
import net.junhabaek.springjdbcexample.domain.Member;
import net.junhabaek.springjdbcexample.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class OrderRepository {
    private JdbcTemplate jdbcTemplate;

    private final OrderRowMapper orderRowMapper = new OrderRowMapper();

    private final String FIND_SQL = "SELECT order_id, qty, total_price, " +
            "item.item_id, price, item.name as item_name, " +
            "member.member_id, address, member.name as member_name " +
            "from orders join item on orders.item_id = item.item_id " +
            "join member on orders.member_id = member.member_id " +
            "where orders.order_id=?";

    public OrderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //TODO 쿼리 짜기
    public Order findById(Long orderId){
        return (Order) this.jdbcTemplate.queryForObject(FIND_SQL,
                orderRowMapper, orderId);
    }


    class OrderRowMapper implements RowMapper<Order>{

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item.Builder()
                    .withItemId(rs.getLong("item_id"))
                    .withPrice(rs.getLong("price"))
                    .withName(rs.getString("item_name"))
                    .build();

            Member member = new Member.Builder()
                    .withMemberId(rs.getLong("member_id"))
                    .withAddress(rs.getString("address"))
                    .withName(rs.getString("member_name"))
                    .build();

            return new Order.Builder()
                    .withOrderId(rs.getLong("order_id"))
                    .withItem(item)
                    .withMember(member)
                    .withQty(rs.getLong("qty"))
                    .withTotalPrice(rs.getLong("total_price"))
                    .build();
        }
    }
}
