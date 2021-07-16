package net.junhabaek.springjdbcexample.domain;

public class Member {
    private Long memberId;
    private String name;
    private String address;

    public Member(Long memberId, String name, String address) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static class Builder{
        private Long memberId;
        private String address;
        private String name;

        public Builder withMemberId(Long memberId){
            this.memberId =memberId;
            return this;
        }

        public Builder withAddress(String address){
            this.address = address;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Member build(){
            if(address == null || name == null){
                throw new IllegalStateException("some fields are missing");
            }
            return new Member(memberId, name, address);
        }
    }
}

