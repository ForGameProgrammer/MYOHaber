package android.forgamer.myo.myohaber.Classes.Adapters;

import android.app.Activity;
import android.content.Context;
import android.forgamer.myo.myohaber.Classes.Program;
import android.forgamer.myo.myohaber.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by The Flash on 02/05/2016.
 */
public class ProgramAdapter extends ArrayAdapter<Program>
{

    ArrayList<Program> programlar;
    int resId;
    Context context;

    public ProgramAdapter(Context context, int resource, ArrayList<Program> objects)
    {
        super(context, resource, objects);
        programlar = objects;
        resId = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        ProgramHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resId, parent, false);

            holder = new ProgramHolder();
            holder.tvSaat = (TextView) row.findViewById(R.id.tvSaat);
            holder.tvOgretmen = (TextView) row.findViewById(R.id.tvOgretmen);
            holder.tvYer = (TextView) row.findViewById(R.id.tvYer);
            holder.tvDers = (TextView) row.findViewById(R.id.tvDers);

            row.setTag(holder);
        } else
        {
            holder = (ProgramHolder) row.getTag();
        }
        holder.tvDers.setText(programlar.get(position).getDersAdi());
        int saat = programlar.get(position).getOgretim() == 2 ? position + 15 : position + 8;
        int saat1 = saat + 1;
        holder.tvSaat.setText(saat + ":15-" + saat1 + ":00");
        holder.tvOgretmen.setText(programlar.get(position).getOgretmenAdi());
        holder.tvYer.setText(programlar.get(position).getYer());

        return row;
    }

    class ProgramHolder
    {
        TextView tvSaat;
        TextView tvDers;
        TextView tvOgretmen;
        TextView tvYer;
    }
}
