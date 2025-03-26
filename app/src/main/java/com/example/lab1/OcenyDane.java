package com.example.lab1;

public class OcenyDane {
    private Ocena currentOcena;
    private String nazwaOcena;

    OcenyDane(String nazwaOcena) {
        this.nazwaOcena = nazwaOcena;
    }

    public Ocena getCurrentOcena() {
        return currentOcena;
    }

    public String getNazwaOcena() {
        return nazwaOcena;
    }

    public void setCurrentOcena(Ocena currentOcena) {
        this.currentOcena = currentOcena;
    }

    public void setNazwaOcena(String nazwaOcena) {
        this.nazwaOcena = nazwaOcena;
    }
}
