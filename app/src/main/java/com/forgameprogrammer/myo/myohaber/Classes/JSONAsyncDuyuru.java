package com.forgameprogrammer.myo.myohaber.Classes;

import android.content.Context;
import com.forgameprogrammer.myo.myohaber.Classes.Adapters.DuyuruAdapter;

import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by The Flash on 19/04/2016.
 */
public class JSONAsyncDuyuru extends AsyncTask<Object, Void, ArrayList<Duyuru>>
{
    String site;
    ListView list;
    Context context;

    @Override
    protected ArrayList<Duyuru> doInBackground(Object... params)
    {
        site = (String) params[0];
        list = (ListView) params[1];
        context = (Context) params[2];
        ArrayList<Duyuru> duyurular = new ArrayList<>();
        JSONObject json = JSONFunctions.getJSONfromURL(site);
        try
        {
            if (json.getInt("basarili") == 1)
            {
                JSONArray jsonArray = json.getJSONArray(Constants.FIELD_DUYURU_DUYURULAR);
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    Duyuru duyuru = new Duyuru();
                    JSONObject jsonDuyuru = jsonArray.getJSONObject(i);
                    duyuru.setId(Integer.parseInt(jsonDuyuru.getString(Constants.FIELD_DUYURU_ID)));
                    duyuru.setLink(jsonDuyuru.getString(Constants.FIELD_DUYURU_LINK));
                    duyuru.setMesaj(jsonDuyuru.getString(Constants.FIELD_DUYURU_MESAJ));
                    duyuru.setTarih(jsonDuyuru.getString(Constants.FIELD_DUYURU_TARIH));
                    duyuru.setYazar(jsonDuyuru.getString(Constants.FIELD_DUYURU_YAZAR));
                    duyurular.add(duyuru);
                }
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return duyurular;
    }

    @Override
    protected void onPostExecute(ArrayList<Duyuru> duyurular)
    {
        list.setAdapter(new DuyuruAdapter(context, com.forgameprogrammer.myo.myohaber.R.layout.duyuru_list_item, duyurular));
        super.onPostExecute(duyurular);
    }
}
