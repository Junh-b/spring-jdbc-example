package net.junhabaek.springjdbcexample.domain;

import java.util.Objects;

public class Order {
    private Long orderId;
    private Item item;
    private Member member;
    private Long qty;
    private Long totalPrice;

    public Order(Long orderId, Item item, Member member, Long qty, Long totalPrice) {
        this.orderId = orderId;
        this.item = item;
        this.member = member;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Item getItem() {
        return item;
    }

    public Member getMember() {
        return member;
    }

    public Long getQty() {
        return qty;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public static class Builder {
        private Long orderId;
        private Item item;
        private Member member;
        private Long qty;
        private Long totalPrice;

        public Builder withOrderId(Long orderId){
            this.orderId=orderId;
            return this;
        }

        public Builder withItem(Item item){
            this.item=item;
            return this;
        }

        public Builder withMember(Member member){
            this.member=member;
            return this;
        }

        public Builder withQty(Long qty){
            this.qty=qty;
            return this;
        }

        public Builder withTotalPrice(Long totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public Order build(){
            if(member==null || item==null || qty==null || totalPrice ==null){
                throw new IllegalStateException("some fields are missing");
            }
            return new Order(orderId, item, member, qty, totalPrice);
        }


    }
}
