package com.forgameprogrammer.myo.myohaber.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import com.forgameprogrammer.myo.myohaber.Classes.Adapters.YemekAdapter;
import com.forgameprogrammer.myo.myohaber.Classes.Constants;
import com.forgameprogrammer.myo.myohaber.Classes.JSONFunctions;
import com.forgameprogrammer.myo.myohaber.Classes.Yemek;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link YemekFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YemekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YemekFragment extends Fragment
{
    ListView listYemekler;
    TextView tvYemek1;
    TextView tvYemek2;
    TextView tvYemek3;

    private OnFragmentInteractionListener mListener;

    public YemekFragment()
    {
        // Required empty public constructor
    }

    public static YemekFragment newInstance()
    {
        YemekFragment fragment = new YemekFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(com.forgameprogrammer.myo.myohaber.R.layout.fragment_yemek, container, false);
        listYemekler = (ListView) view.findViewById(com.forgameprogrammer.myo.myohaber.R.id.listYemekler);
        tvYemek1 = (TextView) view.findViewById(com.forgameprogrammer.myo.myohaber.R.id.tvYemek1);
        tvYemek2 = (TextView) view.findViewById(com.forgameprogrammer.myo.myohaber.R.id.tvYemek2);
        tvYemek3 = (TextView) view.findViewById(com.forgameprogrammer.myo.myohaber.R.id.tvYemek3);

        new JSONAsyncYemek().execute();

        return view;
    }

    private class JSONAsyncYemek extends AsyncTask<Void, Void, ArrayList<Yemek>>
    {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Yükleniyor...");
            progressDialog.show();
        }

        @Override
        protected ArrayList<Yemek> doInBackground(Void... params)
        {
            ArrayList<Yemek> yemekListesi = new ArrayList<>();

            JSONObject json = JSONFunctions.getJSONfromURL(Constants.JSON_YEMEK);
            try
            {
                if (json.getInt("basarili") == 1)
                {
                    JSONArray jsonArray = json.getJSONArray(Constants.FIELD_YEMEKLER);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        Yemek yemek = new Yemek();
                        JSONObject jsonYemek = jsonArray.getJSONObject(i);
                        yemek.setId(Integer.parseInt(jsonYemek.getString(Constants.FIELD_YEMEKLER_ID)));
                        yemek.setTarih(jsonYemek.getString(Constants.FIELD_YEMEKLER_TARIH));
                        yemek.setYemek1(jsonYemek.getString(Constants.FIELD_YEMEKLER_YEMEK1));
                        yemek.setYemek2(jsonYemek.getString(Constants.FIELD_YEMEKLER_YEMEK2));
                        yemek.setYemek3(jsonYemek.getString(Constants.FIELD_YEMEKLER_YEMEK3));
                        yemekListesi.add(yemek);
                    }
                }
            } catch (JSONException e)
            {
                e.printStackTrace();
            }

            return yemekListesi;
        }

        @Override
        protected void onPostExecute(ArrayList<Yemek> yemeks)
        {
            listYemekler.setAdapter(new YemekAdapter(getContext(), com.forgameprogrammer.myo.myohaber.R.layout.yemek_list_item, yemeks));
            Date tarih = new Date();
            String bugun = (String) DateFormat.format("yyyy-MM-d", tarih.getTime());
            tvYemek1.setText("Bugün İçin Belirtilmiş Bir Yemek Yok");
            tvYemek2.setText("Bugün İçin Belirtilmiş Bir Yemek Yok");
            tvYemek3.setText("Bugün İçin Belirtilmiş Bir Yemek Yok");
            for (Yemek y : yemeks)
            {
                if (y.getTarih().equals(bugun))
                {
                    tvYemek1.setText(y.getYemek1());
                    tvYemek2.setText(y.getYemek2());
                    tvYemek3.setText(y.getYemek3());
                }
            }
            progressDialog.dismiss();
        }
    }


    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
