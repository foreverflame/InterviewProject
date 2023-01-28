package com.example.android.DesignPattern;

public class ProxyPattern {


    public static void main(String[] args) {
        LocalCountry localCountry = new LocalCountry();
        Oversase oversase = new Oversase(localCountry);
    }


    public interface People { //人都一个购买方法
        void buy();
    }


    public static class LocalCountry implements People {

        @Override
        public void buy() {
            System.out.println("国内买一个包");

        }
    }


    public static class Oversase implements People {

        private final People mPeople;

        Oversase(People people) {
            this.mPeople = people;

        }

        @Override
        public void buy() {
            System.out.println("国外代购");
            mPeople.buy();
        }
    }


}
