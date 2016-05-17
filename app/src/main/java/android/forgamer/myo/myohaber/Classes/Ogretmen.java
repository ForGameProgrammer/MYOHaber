package android.forgamer.myo.myohaber.Classes;

/**
 * Created by The Flash on 01/05/2016.
 */
public class Ogretmen
{
    private int id;
    private String adi;

    public Ogretmen(int id, String adi)
    {
        this.id = id;
        this.adi = adi;
    }

    public Ogretmen()
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

    public String getAdi()
    {
        return adi;
    }

    public void setAdi(String adi)
    {
        this.adi = adi;
    }
}
