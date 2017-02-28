package com.example.quanla.pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.adapters.ColorAdapter;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.decorations.TaskColorDecoration;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.SaveEditTask;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.Strategy;
import com.example.quanla.pomodoro.networks.NetContext;
import com.example.quanla.pomodoro.networks.jsonmodels.AddTaskBodyJson;
import com.example.quanla.pomodoro.networks.services.TaskService;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {


    String uuid;

    private static final String TAG = "abc";

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
        if(((MainActivity)getActivity()).isOnline()){
            if (item.getItemId() == R.id.m_ok) {

                //get data from UI
                taskName = etTaskName.getText().toString();
                paymentPerHour = Float.parseFloat(etPayment.getText().toString());
                color = taskColorAdapter.getSelectedColor();

                if (TaskFragment.viewClick == 0) {
                    editATask(task, taskName, color, paymentPerHour);


                    DbContext.instance.update(task, taskName, color, paymentPerHour);


                } else {
                    Task newTask = new Task(taskName, color, paymentPerHour);
                    DbContext.instance.add(newTask);
                    addNewTask(newTask);
                }

                //Hide soft keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);


                getActivity().onBackPressed();


            }
        }
        else {
            if(TaskFragment.viewClick == 0){
                DbContext.instance.update(task, taskName, color, paymentPerHour);
            }
            else {
                Task newTask = new Task(taskName, color, paymentPerHour);
                DbContext.instance.add(newTask);
            }
        }
        return false;
    }

    public void addNewTask(Task task){

        TaskService addNewTaskService = NetContext.instance.create(TaskService.class);

        //data & format
        //format =>Mediatype
        //date => json

        MediaType jsonType = MediaType.parse("application/json");
        String addNewTaskJson = (new Gson()).toJson(new AddTaskBodyJson(task.getColor(), false, null, null, task.getIdLocal(), task.getName(), task.getPayment()));
        RequestBody requestBody = RequestBody.create(jsonType, addNewTaskJson);

        addNewTaskService.addTask(requestBody).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.d(TAG, String.format("Add: %s", response.body()));

            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });
    }

    public void editATask(Task task, String name, String color, float payment){
        TaskService editATaskService = NetContext.instance.create(TaskService.class);

        editATaskService.editATask(task.getIdLocal(), name, color, payment, task.getIdLocal()).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {

                if(response.body()!=null){
                    Log.d(TAG, String.format("update: %s", response.body()) );
                }
                else Log.d(TAG, "chua edit duoc");
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });


    }



}
