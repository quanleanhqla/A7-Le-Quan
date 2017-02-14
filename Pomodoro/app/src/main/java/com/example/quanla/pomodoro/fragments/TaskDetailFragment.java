package com.example.quanla.pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.adapters.MyColorAdapter;
import com.example.quanla.pomodoro.databases.models.Color;

import java.util.ArrayList;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {

    @BindView(R.id.gv_color)
    GridView gvColor;

    private ArrayList<Color> arrColor;
    private int index=0;
    MyColorAdapter adapter = null;
    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        setupUI(view);


        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        arrColor=new ArrayList<>();
        arrColor.add(new Color(R.color.color1,true));
        arrColor.add(new Color(R.color.color2,false));
        arrColor.add(new Color(R.color.color3,false));
        arrColor.add(new Color(R.color.color4,false));
        arrColor.add(new Color(R.color.color5,false));
        arrColor.add(new Color(R.color.color6,false));
        arrColor.add(new Color(R.color.color7,false));
        arrColor.add(new Color(R.color.color8,false));

        adapter = new MyColorAdapter(this.getActivity(), arrColor);
        gvColor.setAdapter(adapter);
        gvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(index!=position)
                {   Color indexColor=arrColor.get(index);
                    indexColor.setCheck(false);
                    arrColor.set(index,indexColor);
                    Color color=arrColor.get(position);
                    color.setCheck(true);
                    arrColor.set(position,color);
                    adapter.notifyDataSetChanged();
                    index=position;
                }

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_task, menu);
    }
}
