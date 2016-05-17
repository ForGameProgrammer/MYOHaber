package android.forgamer.myo.myohaber.Classes;

import java.util.ArrayList;

/**
 * Created by The Flash on 01/05/2016.
 */
public class DersProgram
{
    ArrayList<Sinif> siniflar;
    ArrayList<Ders> dersler;
    ArrayList<Program> programlar;
    ArrayList<Ogretmen> ogretmenler;

    public DersProgram(ArrayList<Sinif> siniflar, ArrayList<Ders> dersler, ArrayList<Program> programlar, ArrayList<Ogretmen> ogretmenler)
    {
        this.siniflar = siniflar;
        this.dersler = dersler;
        this.programlar = programlar;
        this.ogretmenler = ogretmenler;
    }

    public DersProgram()
    {
    }

    public ArrayList<Program> getProgramlarFromSinifId(int sinifId)
    {
        ArrayList<Program> pros = new ArrayList<>();
        for (Program program : programlar)
        {
            if (program.getSinifid() == sinifId)
            {
                Ders ders = getDersFromDersId(program.getDersid());
                if (ders != null) program.setDersAdi(ders.getDersAdi());
                Ogretmen ogretmen = getOgretmenFromOgretmenId(program.getOgretmenid());
                if (ogretmen != null) program.setOgretmenAdi(ogretmen.getAdi());
                program.setOgretim(getOgretimFromSinifId(program.getSinifid()));
                pros.add(program);
            }
        }
        return pros;
    }

    public ArrayList<Program> getProgramlarFromGunAndSinifId(int day, int sinifId)
    {
        ArrayList<Program> pros = new ArrayList<>();
        for (Program program : getProgramlarFromSinifId(sinifId))
        {
            if (program.getGun() == day)
            {
                Ders ders = getDersFromDersId(program.getDersid());
                if (ders != null) program.setDersAdi(ders.getDersAdi());
                Ogretmen ogretmen = getOgretmenFromOgretmenId(program.getOgretmenid());
                if (ogretmen != null) program.setOgretmenAdi(ogretmen.getAdi());
                program.setOgretim(getOgretimFromSinifId(program.getSinifid()));
                pros.add(program);
            }
        }
        return pros;
    }

    public int getOgretimFromSinifId(int sinifId)
    {
        int ogr = 1;
        for (Sinif s : siniflar)
        {
            if (s.getId() == sinifId)
            {
                ogr = s.getOgretim();
                break;
            }
        }
        return ogr;
    }

    public Ders getDersFromDersId(int dersId)
    {
        Ders ders = null;
        for (Ders d : dersler)
        {
            if (d.getId() == dersId)
            {
                ders = d;
                break;
            }
        }
        return ders;
    }

    public Ogretmen getOgretmenFromOgretmenId(int ogretmenId)
    {
        Ogretmen ogretmen = null;
        for (Ogretmen d : ogretmenler)
        {
            if (d.getId() == ogretmenId)
            {
                ogretmen = d;
                break;
            }
        }
        return ogretmen;
    }


    public ArrayList<Sinif> getSiniflar()
    {
        return siniflar;
    }

    public void setSiniflar(ArrayList<Sinif> siniflar)
    {
        this.siniflar = siniflar;
    }

    public ArrayList<Ders> getDersler()
    {
        return dersler;
    }

    public void setDersler(ArrayList<Ders> dersler)
    {
        this.dersler = dersler;
    }

    public ArrayList<Program> getProgramlar()
    {
        return programlar;
    }

    public void setProgramlar(ArrayList<Program> programlar)
    {
        this.programlar = programlar;
    }

    public ArrayList<Ogretmen> getOgretmenler()
    {
        return ogretmenler;
    }

    public void setOgretmenler(ArrayList<Ogretmen> ogretmenler)
    {
        this.ogretmenler = ogretmenler;
    }

    public void ogretmenEkle(Ogretmen ogretmen)
    {
        ogretmenler.add(ogretmen);
    }

    public void dersEkle(Ders ders)
    {
        dersler.add(ders);
    }

    public void sinifEkle(Sinif sinif)
    {
        siniflar.add(sinif);
    }

    public void programEkle(Program program)
    {
        programlar.add(program);
    }
}