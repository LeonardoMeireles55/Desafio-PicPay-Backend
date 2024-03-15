package com.leonardo.desafio.picpay.model;

public record Authorization(String message) {
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}
