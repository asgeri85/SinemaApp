package com.example.sinemapp_users.Classlar;

public class Film {
    private String aciklama,ad,fotoUrl,imdb,tur,yil,yonetmen;

    public Film(String aciklama, String ad, String fotoUrl, String imdb, String tur, String yil, String yonetmen) {
        this.aciklama = aciklama;
        this.ad = ad;
        this.fotoUrl = fotoUrl;
        this.imdb = imdb;
        this.tur = tur;
        this.yil = yil;
        this.yonetmen = yonetmen;
    }

    public Film() {
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getYil() {
        return yil;
    }

    public void setYil(String yil) {
        this.yil = yil;
    }

    public String getYonetmen() {
        return yonetmen;
    }

    public void setYonetmen(String yonetmen) {
        this.yonetmen = yonetmen;
    }

    @Override
    public String toString() {
        return "Film{" +
                "aciklama='" + aciklama + '\'' +
                ", ad='" + ad + '\'' +
                ", fotoUrl='" + fotoUrl + '\'' +
                ", imdb='" + imdb + '\'' +
                ", tur='" + tur + '\'' +
                ", yil='" + yil + '\'' +
                ", yonetmen='" + yonetmen + '\'' +
                '}';
    }
}
