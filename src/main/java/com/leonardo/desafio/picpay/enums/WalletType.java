package com.leonardo.desafio.picpay.enums;

public enum WalletType {
    LOGISTA(2),
    CLIENTE(1);

    private int value;

    WalletType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
