package android.forgamer.myo.myohaber.Classes;

/**
 * Created by The Flash on 29/04/2016.
 */
public class Program
{
    private int id;
    private int dersid;
    private int gun;
    private int derssira;
    private int ogretmenid;
    private int sinifid;
    private String yer;

    private String dersAdi;
    private String ogretmenAdi;
    private int ogretim;

    public int getOgretim()
    {
        return ogretim;
    }

    public void setOgretim(int ogretim)
    {
        this.ogretim = ogretim;
    }

    public String getOgretmenAdi()
    {
        return ogretmenAdi;
    }

    public void setOgretmenAdi(String ogretmenAdi)
    {
        this.ogretmenAdi = ogretmenAdi;
    }

    public String getDersAdi()
    {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi)
    {
        this.dersAdi = dersAdi;
    }

    public Program(int id, int dersid, int gun, int derssira, int ogretmenid, int sinifid, String yer)
    {
        this.id = id;
        this.dersid = dersid;
        this.gun = gun;
        this.derssira = derssira;
        this.ogretmenid = ogretmenid;
        this.sinifid = sinifid;
        this.yer = yer;
    }

    public Program()
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

    public int getDersid()
    {
        return dersid;
    }

    public void setDersid(int dersid)
    {
        this.dersid = dersid;
    }

    public int getGun()
    {
        return gun;
    }

    public void setGun(int gun)
    {
        this.gun = gun;
    }

    public int getDerssira()
    {
        return derssira;
    }

    public void setDerssira(int derssira)
    {
        this.derssira = derssira;
    }

    public int getOgretmenid()
    {
        return ogretmenid;
    }

    public void setOgretmenid(int ogretmenid)
    {
        this.ogretmenid = ogretmenid;
    }

    public int getSinifid()
    {
        return sinifid;
    }

    public void setSinifid(int sinifid)
    {
        this.sinifid = sinifid;
    }

    public String getYer()
    {
        return yer;
    }

    public void setYer(String yer)
    {
        this.yer = yer;
    }
}
