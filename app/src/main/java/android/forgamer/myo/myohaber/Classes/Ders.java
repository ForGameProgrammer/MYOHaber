package android.forgamer.myo.myohaber.Classes;

/**
 * Created by The Flash on 01/05/2016.
 */
public class Ders
{
    private int id;
    private String dersAdi;

    public Ders(int id, String dersAdi)
    {
        this.id = id;
        this.dersAdi = dersAdi;
    }

    public Ders()
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

    public String getDersAdi()
    {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi)
    {
        this.dersAdi = dersAdi;
    }
}
