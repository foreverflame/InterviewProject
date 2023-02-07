package com.example.test.design.builder;

/**
 * @description: builder模式
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class Computer {

    private String cpu;
    private String ram;
    private String keyBoard;
    private String display;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.keyBoard = builder.keyBoard;
        this.display = builder.display;
    }

    public static class Builder {
        private String cpu;
        private String ram;
        private String keyBoard;
        private String display;

        private Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder setKeyBoard(String keyBoard) {
            this.keyBoard = keyBoard;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
