package com.sauce.evspot.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sauce.evspot.R;

/**
 * Created by Junyoung on 2016-06-27.
 */

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(this.getContext(), "This is ListFragment", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}
