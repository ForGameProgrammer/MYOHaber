package com.forgameprogrammer.myo.myohaber.Classes;

/**
 * Created by The Flash on 01/05/2016.
 */
public class Sinif
{
    private int id;
    private String sinifAdi;
    private int ogretim;

    public Sinif(int id, String sinifAdi, int ogretim)
    {
        this.id = id;
        this.sinifAdi = sinifAdi;
        this.ogretim = ogretim;
    }

    public Sinif()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSinifAdi()
    {
        return sinifAdi;
    }

    public void setSinifAdi(String sinifAdi)
    {
        this.sinifAdi = sinifAdi;
    }

    public int getOgretim()
    {
        return ogretim;
    }

    public void setOgretim(int ogretim)
    {
        this.ogretim = ogretim;
    }
}
