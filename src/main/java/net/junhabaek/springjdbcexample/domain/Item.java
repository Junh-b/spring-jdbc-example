package net.junhabaek.springjdbcexample.domain;


public class Item {
    private Long itemId;
    private Long price;
    private String name;

    public Item(Long itemId, Long price, String name) {
        this.itemId = itemId;
        this.price = price;
        this.name = name;
    }

    public Long getItemId() {
        return itemId;
    }

    public Long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public static class Builder{
        private Long itemId;
        private Long price;
        private String name;

        public Builder withItemId(Long itemId){
            this.itemId=itemId;
            return this;
        }

        public Builder withPrice(Long price){
            this.price = price;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Item build(){
            if(price == null || name == null){
                throw new IllegalStateException("some fields are missing");
            }
            return new Item(itemId, price, name);
        }
    }
}
