package com.forgameprogrammer.myo.myohaber.Classes.Adapters;

import android.app.Activity;
import android.content.Context;
import com.forgameprogrammer.myo.myohaber.Classes.Yemek;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by theflash on 17.05.2016.
 */

public class YemekAdapter extends ArrayAdapter<Yemek>
{
    ArrayList<Yemek> yemekler;
    int resId;
    Context context;

    public YemekAdapter(Context context, int resource, ArrayList<Yemek> objects)
    {
        super(context, resource, objects);
        yemekler = objects;
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
            holder.tvYemeklerTarih  = (TextView) row.findViewById(com.forgameprogrammer.myo.myohaber.R.id.tvYemeklerTarih);
            holder.tvYemeklerYemek1 = (TextView) row.findViewById(com.forgameprogrammer.myo.myohaber.R.id.tvYemeklerYemek1);
            holder.tvYemeklerYemek2 = (TextView) row.findViewById(com.forgameprogrammer.myo.myohaber.R.id.tvYemeklerYemek2);
            holder.tvYemeklerYemek3 = (TextView) row.findViewById(com.forgameprogrammer.myo.myohaber.R.id.tvYemeklerYemek3);

            row.setTag(holder);
        } else
        {
            holder = (DuyuruHolder) row.getTag();
        }

        holder.tvYemeklerTarih .setText(yemekler.get(position).getTarih());
        holder.tvYemeklerYemek1.setText(yemekler.get(position).getYemek1());
        holder.tvYemeklerYemek2.setText(yemekler.get(position).getYemek2());
        holder.tvYemeklerYemek3.setText(yemekler.get(position).getYemek3());

        return row;
    }

    class DuyuruHolder
    {
        TextView tvYemeklerTarih ;
        TextView tvYemeklerYemek1;
        TextView tvYemeklerYemek2;
        TextView tvYemeklerYemek3;
    }
}
