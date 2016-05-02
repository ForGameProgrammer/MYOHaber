package android.forgamer.com.myohaber.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.forgamer.com.myohaber.Classes.Constants;
import android.forgamer.com.myohaber.Classes.Ders;
import android.forgamer.com.myohaber.Classes.DersProgram;
import android.forgamer.com.myohaber.Classes.DersSiralayici;
import android.forgamer.com.myohaber.Classes.JSONFunctions;
import android.forgamer.com.myohaber.Classes.Ogretmen;
import android.forgamer.com.myohaber.Classes.Program;
import android.forgamer.com.myohaber.Classes.Sinif;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.forgamer.com.myohaber.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgramFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgramFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgramFragment extends Fragment
{
    DersProgram dersProgramlari;
    private OnFragmentInteractionListener mListener;
    Spinner spSiniflar, spGunler;
    ArrayList<Integer> sinifId;
    ArrayList<Integer> gunId;
    ListView listProgram;

    public ProgramFragment()
    {
        // Required empty public constructor
    }

    public static ProgramFragment newInstance()
    {
        ProgramFragment fragment = new ProgramFragment();
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
        View view = inflater.inflate(R.layout.fragment_program, container, false);

        listProgram = (ListView) view.findViewById(R.id.listProgram);
        spSiniflar = (Spinner) view.findViewById(R.id.spSiniflar);
        spGunler = (Spinner) view.findViewById(R.id.spGunler);
        dersProgramlari = null;
        // Spinner click listener
        spSiniflar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (dersProgramlari != null)
                {
                    ArrayList<Program> programs = dersProgramlari.getProgramlarFromGunAndSinifId(gunId.get(spGunler.getSelectedItemPosition()), sinifId.get(spSiniflar.getSelectedItemPosition()));
                    Collections.sort(programs, new DersSiralayici());
                    ProgramAdapter adapter = new ProgramAdapter(getActivity(), R.layout.program_list_item, programs);
                    listProgram.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        spGunler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (dersProgramlari != null)
                {
                    ArrayList<Program> programs = dersProgramlari.getProgramlarFromGunAndSinifId(gunId.get(spGunler.getSelectedItemPosition()), sinifId.get(spSiniflar.getSelectedItemPosition()));
                    Collections.sort(programs, new DersSiralayici());
                    ProgramAdapter adapter = new ProgramAdapter(getActivity(), R.layout.program_list_item, programs);
                    listProgram.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        // Spinner Drop down elements
        List<String> gunler = new ArrayList<>();
        gunler.add("Pazartesi");
        gunler.add("Salı");
        gunler.add("Çarşamba");
        gunler.add("Perşembe");
        gunler.add("Cuma");
        gunId = new ArrayList<>();
        gunId.add(1);
        gunId.add(2);
        gunId.add(3);
        gunId.add(4);
        gunId.add(5);

        // Creating adapter for spinner
        ArrayAdapter<String> gunlerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, gunler);
        // Drop down layout style - list view with radio button
        gunlerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spGunler.setAdapter(gunlerAdapter);
        new programJSON().execute("");
        return view;
    }

    private class programJSON extends AsyncTask<Object, Void, DersProgram>
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
        protected DersProgram doInBackground(Object... params)
        {
            DersProgram dersProgram = new DersProgram();
            JSONObject json = JSONFunctions.getJSONfromURL(Constants.JSON_DERSPROGRAMI);
            try
            {
                if (json.getInt("basarili") == 1)
                {

                    ArrayList<Ders> dersler = new ArrayList<>();
                    ArrayList<Ogretmen> ogretmenler = new ArrayList<>();
                    ArrayList<Sinif> siniflar = new ArrayList<>();
                    ArrayList<Program> programlar = new ArrayList<>();
                    JSONArray jsonDersler = json.getJSONArray(Constants.FIELD_DERSLER);
                    JSONArray jsonOgretmenler = json.getJSONArray(Constants.FIELD_OGRETMENLER);
                    JSONArray jsonSiniflar = json.getJSONArray(Constants.FIELD_SINIFLAR);
                    JSONArray jsonProgramlar = json.getJSONArray(Constants.FIELD_PROGRAM);

                    for (int i = 0; i < jsonDersler.length(); i++)
                    {
                        Ders ders = new Ders();
                        JSONObject jsonDers = jsonDersler.getJSONObject(i);
                        ders.setId(Integer.parseInt(jsonDers.getString(Constants.FIELD_DERSLER_ID)));
                        ders.setDersAdi(jsonDers.getString(Constants.FIELD_DERSLER_DERS));
                        dersler.add(ders);
                    }

                    for (int i = 0; i < jsonOgretmenler.length(); i++)
                    {
                        Ogretmen ogretmen = new Ogretmen();
                        JSONObject jsonOgretmen = jsonOgretmenler.getJSONObject(i);
                        ogretmen.setId(Integer.parseInt(jsonOgretmen.getString(Constants.FIELD_OGRETMENLER_ID)));
                        ogretmen.setAdi(jsonOgretmen.getString(Constants.FIELD_OGRETMENLER_OGRETMENADI));
                        ogretmenler.add(ogretmen);
                    }

                    for (int i = 0; i < jsonSiniflar.length(); i++)
                    {
                        Sinif sinif = new Sinif();
                        JSONObject jsonSinif = jsonSiniflar.getJSONObject(i);
                        sinif.setId(Integer.parseInt(jsonSinif.getString(Constants.FIELD_SINIFLAR_ID)));
                        sinif.setOgretim(Integer.parseInt(jsonSinif.getString(Constants.FIELD_SINIFLAR_OGRETIM)));
                        sinif.setSinifAdi(jsonSinif.getString(Constants.FIELD_SINIFLAR_SINIFADI));
                        siniflar.add(sinif);
                    }

                    for (int i = 0; i < jsonProgramlar.length(); i++)
                    {
                        Program program = new Program();
                        JSONObject jsonProgram = jsonProgramlar.getJSONObject(i);
                        program.setId(Integer.parseInt(jsonProgram.getString(Constants.FIELD_PROGRAM_ID)));
                        program.setDersid(Integer.parseInt(jsonProgram.getString(Constants.FIELD_PROGRAM_DERSID)));
                        program.setDerssira(Integer.parseInt(jsonProgram.getString(Constants.FIELD_PROGRAM_DERSSIRA)));
                        program.setGun(Integer.parseInt(jsonProgram.getString(Constants.FIELD_PROGRAM_GUN)));
                        program.setOgretmenid(Integer.parseInt(jsonProgram.getString(Constants.FIELD_PROGRAM_OGRETMENID)));
                        program.setSinifid(Integer.parseInt(jsonProgram.getString(Constants.FIELD_PROGRAM_SINIFID)));
                        program.setYer(jsonProgram.getString(Constants.FIELD_PROGRAM_YER));
                        programlar.add(program);
                    }
                    dersProgram = new DersProgram(siniflar, dersler, programlar, ogretmenler);
                }
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            return dersProgram;
        }

        @Override
        protected void onPostExecute(DersProgram dersProgram)
        {
            dersProgramlari = dersProgram;
            List<String> siniflar = new ArrayList<>();
            sinifId = new ArrayList<>();
            for (Sinif s : dersProgram.getSiniflar())
            {
                siniflar.add(s.getSinifAdi() + " " + s.getOgretim() + ". Öğretim");
                sinifId.add(s.getId());
            }
            ArrayAdapter<String> sinifAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, siniflar);
            // Drop down layout style - list view with radio button
            sinifAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spSiniflar.setAdapter(sinifAdapter);
            progressDialog.dismiss();
        }
    }

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

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
