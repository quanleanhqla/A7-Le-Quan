package com.example.quanla.pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.adapters.TaskAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {

    FragmentReplaceListener fragmentReplaceListener;

    @BindView(R.id.rv_task)
    RecyclerView rvTask;

//    @BindView(R.id.btn_color)
//    Button btnColor;

    private TaskAdapter taskAdapter;

    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        setupUI(view);

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        taskAdapter = new TaskAdapter();
        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(this.getContext()));

        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setTitle(R.string.Tasks);

        setHasOptionsMenu(true);

    }

    @OnClick(R.id.fab)
    void onClick(){
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        //TODO: Make TaskActivity and Fragment independent
        fragmentReplaceListener.replaceFragment(taskDetailFragment, true);
    }

    public void setListener(FragmentReplaceListener fragmentReplaceListener){
        this.fragmentReplaceListener=fragmentReplaceListener;
    }

}
