package com.forgameprogrammer.myo.myohaber.Classes;

/**
 * Created by The Flash on 19/04/2016.
 */
public class Duyuru
{
    private String mesaj;
    private int id;
    private String yazar;
    private String tarih;
    private String link;

    public Duyuru()
    {
    }

    public Duyuru(String mesaj, int id, String yazar, String tarih, String link)
    {
        this.mesaj = mesaj;
        this.id = id;
        this.yazar = yazar;
        this.tarih = tarih;
        this.link = link;
    }

    public String getMesaj()
    {
        return mesaj;
    }

    public void setMesaj(String mesaj)
    {
        this.mesaj = mesaj;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getYazar()
    {
        return yazar;
    }

    public void setYazar(String yazar)
    {
        this.yazar = yazar;
    }

    public String getTarih()
    {
        return tarih;
    }

    public void setTarih(String tarih)
    {
        this.tarih = tarih;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }
}
