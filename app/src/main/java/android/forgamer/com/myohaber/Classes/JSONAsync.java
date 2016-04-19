package android.forgamer.com.myohaber.Classes;

import android.content.Context;
import android.forgamer.com.myohaber.MainActivity;
import android.forgamer.com.myohaber.R;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by The Flash on 19/04/2016.
 */
public class JSONAsync extends AsyncTask<Object, Void, ArrayList<Duyuru>>
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
                JSONArray jsonArray = json.getJSONArray(Constants.JSON_FIELDS_DUYURULAR);
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    Duyuru duyuru = new Duyuru();
                    JSONObject jsonDuyuru = jsonArray.getJSONObject(i);
                    duyuru.setId(Integer.parseInt(jsonDuyuru.getString(Constants.JSON_FIELDS_ID)));
                    duyuru.setLink(jsonDuyuru.getString(Constants.JSON_FIELDS_LINK));
                    duyuru.setMesaj(jsonDuyuru.getString(Constants.JSON_FIELDS_MESAJ));
                    duyuru.setTarih(jsonDuyuru.getString(Constants.JSON_FIELDS_TARIH));
                    duyuru.setYazar(jsonDuyuru.getString(Constants.JSON_FIELDS_YAZAR));
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
        list.setAdapter(new DuyuruAdapter(context, R.layout.duyuru_list_item, duyurular));
        super.onPostExecute(duyurular);
    }
}
