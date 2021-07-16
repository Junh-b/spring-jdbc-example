package net.junhabaek.springjdbcexample;

import net.junhabaek.springjdbcexample.domain.Item;
import net.junhabaek.springjdbcexample.domain.Member;
import net.junhabaek.springjdbcexample.domain.Order;
import net.junhabaek.springjdbcexample.repository.ItemRepository;
import net.junhabaek.springjdbcexample.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class SpringJdbcExampleApplicationTests {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void checkExistingItem() {
        //given
        Item expectedItem = new Item(1L, 300L, "jpa book");

        //when
        Item item = itemRepository.findById(1L);

        //then
        assertEquals(item.getItemId(), expectedItem.getItemId());
    }

    @Test
    void checkExistingOrder() {
        //given
        Item expectedItem = new Item(1L, 300L, "jpa book");

        Member expectedMember = new Member(1L, "junha", "seoul");

        Order expectedOrder = new Order(1L, expectedItem, expectedMember, 3L, 900L);

        //when
        Order order = orderRepository.findById(1L);

        //then
        assertEquals(order, expectedOrder);
        assertEquals(order.getMember().getName(), "junha");
    }
}
