package android.forgamer.com.myohaber.Classes.Adapters;

import android.app.Activity;
import android.content.Context;
import android.forgamer.com.myohaber.Classes.Duyuru;
import android.forgamer.com.myohaber.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by The Flash on 19/04/2016.
 */
public class DuyuruAdapter extends ArrayAdapter<Duyuru>
{
    ArrayList<Duyuru> duyurular;
    int resId;
    Context context;

    public DuyuruAdapter(Context context, int resource, ArrayList<Duyuru> objects)
    {
        super(context, resource, objects);
        duyurular = objects;
        resId = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        DuyuruHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resId, parent, false);

            holder = new DuyuruHolder();
            holder.tvMesaj = (TextView) row.findViewById(R.id.tvMesaj);
            holder.tvTarih = (TextView) row.findViewById(R.id.tvTarih);
            holder.tvYazar = (TextView) row.findViewById(R.id.tvYazar);

            row.setTag(holder);
        } else
        {
            holder = (DuyuruHolder) row.getTag();
        }
        holder.tvMesaj.setText(duyurular.get(position).getMesaj());
        holder.tvYazar.setText(duyurular.get(position).getYazar());
        holder.tvTarih.setText(duyurular.get(position).getTarih());

        return row;
    }

    class DuyuruHolder
    {
        TextView tvMesaj;
        TextView tvTarih;
        TextView tvYazar;
    }
}
