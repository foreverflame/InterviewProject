package com.example.android.DesignPattern;


public class AbstractFactory {

    public static void main(String[] args) {


    }

    public abstract class NokiaPhone {
        public abstract void powerPhone();
    }


    public class Nokia88 extends NokiaPhone {

        @Override
        public void powerPhone() {
            System.out.println("88");

        }
    }

    public class Nokia99 extends NokiaPhone {

        @Override
        public void powerPhone() {
            System.out.println("99");

        }
    }


    public abstract class Factory {

        public abstract <T extends NokiaPhone> T create(Class<T> tClass);
    }


    public class NokiaFactory extends Factory {

        @Override
        public <T extends NokiaPhone> T create(Class<T> tClass) {

            NokiaPhone nokiaPhone = null;
            try {
                nokiaPhone = (NokiaPhone) Class.forName(tClass.getName()).newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return (T) nokiaPhone;
        }
    }


    //抽象工厂模式 针对不同的产品族
    public interface Compnet {


        public interface Cpu {

            void show();

        }


        public interface Battery {
            void show();

        }


    }


    public class A99 implements Compnet.Cpu {

        @Override
        public void show() {
            System.out.println("A99");

        }
    }

    public class B88 implements Compnet.Cpu {

        @Override
        public void show() {
            System.out.println("B88");

        }


    }


    public class Battery11 implements Compnet.Battery {

        @Override
        public void show() {
            System.out.println("Battery11");
        }
    }

    public class Battery22 implements Compnet.Battery {

        @Override
        public void show() {
            System.out.println("Battery22");
        }
    }


    public abstract class Factory11 {

        abstract Compnet.Cpu createCpu();

        abstract Compnet.Battery createBattery();

    }

    public class CompunterFactory extends Factory11{

        @Override
        Compnet.Cpu createCpu() {
            return new A99();
        }

        @Override
        Compnet.Battery createBattery() {
            return new Battery11();
        }
    }










}
