package com.example.android.DesignPattern;


public class PersonBuilder {


    int age;
    String name;
    String sexy;
    String country;


    PersonBuilder(Builder builder) {
        this.age = builder.age;
        this.sexy = builder.sexy;
        this.name = builder.name;
        this.country = builder.country;

    }


    public static class Builder {
        int age;
        String name;
        String sexy;
        String country;

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }


        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }


        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public Builder setSexy(String sexy) {
            this.sexy = sexy;
            return this;
        }


        public PersonBuilder build() {
            return new PersonBuilder(this);
        }


    }


}
