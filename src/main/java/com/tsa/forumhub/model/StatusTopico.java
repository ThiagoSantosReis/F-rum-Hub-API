package com.tsa.forumhub.model;

public enum StatusTopico {
    NAO_RESPONDIDO("nao_respondido"),
    RESPONDIDO("respondido"),
    NAO_SOLUCIONADO("nao_solucionado"),
    SOLUCIONADO("solucionado"),
    FECHADO("fechado");

    private String statusTopico;

    StatusTopico(String statusTopico){
        this.statusTopico = statusTopico;
    }
}

