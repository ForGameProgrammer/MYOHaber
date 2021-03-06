package com.forgameprogrammer.myo.myohaber.Fragments;

import android.content.Context;
import com.forgameprogrammer.myo.myohaber.Classes.Constants;
import com.forgameprogrammer.myo.myohaber.Classes.JSONAsyncDuyuru;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DuyuruFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DuyuruFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DuyuruFragment extends Fragment
{
    ListView listDuyurular;

    private OnFragmentInteractionListener mListener;

    public DuyuruFragment()
    {
        // Required empty public constructor
    }


    public static DuyuruFragment newInstance()
    {
        DuyuruFragment fragment = new DuyuruFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(com.forgameprogrammer.myo.myohaber.R.layout.fragment_duyuru, container, false);
        // Inflate the layout for this fragment
        listDuyurular = (ListView) v.findViewById(com.forgameprogrammer.myo.myohaber.R.id.listDuyurular);
        new JSONAsyncDuyuru().execute(Constants.JSON_DUYURU, listDuyurular, getActivity());
        return v;
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
     * <p>
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
