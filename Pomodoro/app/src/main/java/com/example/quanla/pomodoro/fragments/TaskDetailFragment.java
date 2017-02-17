package com.example.quanla.pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.adapters.ColorAdapter;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.decorations.TaskColorDecoration;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.DoneAddTask;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.SaveEditTask;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.Strategy;

import butterknife.BindView;
import butterknife.ButterKnife;




/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {

    @BindView(R.id.edt_taskname)
    EditText etTaskName;

    @BindView(R.id.edt_payment)
    EditText etPayment;

    @BindView(R.id.rv_color)
    RecyclerView rvTaskColor;

    private ColorAdapter taskColorAdapter;

    private String title;
    private Strategy strategy;
    private Task task;

    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_task_detail, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        taskColorAdapter = new ColorAdapter();

        rvTaskColor.setLayoutManager(new GridLayoutManager(this.getContext(), 4));
        rvTaskColor.setAdapter(taskColorAdapter);
        rvTaskColor.addItemDecoration(new TaskColorDecoration());

        if(getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
        }

        if(task !=null){

            etTaskName.setText(task.getName());
            etPayment.setText(String.valueOf(task.getPayment()));
            taskColorAdapter.setSelectedColor(task.getColor());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_task, menu);
    }

    String taskName;
    float paymentPerHour;
    String color;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m_ok){

            //get data from UI
            taskName = etTaskName.getText().toString();
            paymentPerHour = Float.parseFloat(etPayment.getText().toString());
            color = taskColorAdapter.getSelectedColor();

            if(TaskFragment.viewClick==0){
                strategy = new SaveEditTask();
            }
            else {
                strategy = new DoneAddTask();
            }

            strategy.doOptionItem(task, taskName, paymentPerHour, color);


            getActivity().onBackPressed();


        }
        return false;
    }

}
