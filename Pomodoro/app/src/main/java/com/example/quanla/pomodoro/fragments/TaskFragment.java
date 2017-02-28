package com.example.quanla.pomodoro.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.adapters.TaskAdapter;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.networks.NetContext;
import com.example.quanla.pomodoro.networks.services.TaskService;
import com.example.quanla.pomodoro.settings.SharedPrefs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment{

    private final static String TAG = "abc";

    public static int viewClick = 3;


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
        for(Task task: DbContext.instance.allTasks()){
            Log.d(TAG, String.format("Ra: %s", task.toString()));
        }
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

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Task");

        //menu options

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        rvTask.addItemDecoration(dividerItemDecoration);

        taskAdapter.setTaskItemLongClickListener(new TaskAdapter.TaskItemLongClickListener() {
            @Override
            public void onItemLongClick(final Task task) {
                final AlertDialog.Builder deleteAD = new AlertDialog.Builder(getActivity());
                deleteAD.setMessage("Delete this task?");
                deleteAD.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(((MainActivity)getActivity()).isOnline()) {
                            deleteTask(task);
                            DbContext.instance.delete(task);
                            taskAdapter.notifyDataSetChanged();

                        }
                    }

                });
                deleteAD.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAD.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                getActivity().onBackPressed();
                            }
                        });
                    }
                });
                deleteAD.show();

            }
        });

        taskAdapter.setTaskItemClickListener(new TaskAdapter.TaskItemClickListener() {
            @Override
            public void onItemClick(Task task) {

                TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
                taskDetailFragment.setTitle("Edit task");

                taskDetailFragment.setTask(task);

                //TODO: Make TaskActivity and Fragment independent
                ((MainActivity)getActivity()).replaceFragment(taskDetailFragment, true);

                viewClick = 0;

            }
        });


        taskAdapter.setIconClickListener(new TaskAdapter.IconClickListener() {
            @Override
            public void onIconClickListener(View v) {
                TimerFragment timerFragment = new TimerFragment();
                ((MainActivity)getActivity()).replaceFragment(timerFragment, true);

            }
        });


        setHasOptionsMenu(true);



    }

    private void deleteTask(Task task) {
        TaskService deleteTask = NetContext.instance.create(TaskService.class);

        deleteTask.deleteATask(task.getIdLocal()).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.body()!=null){
                    Log.d(TAG, String.format("OMG %s", response.body()));
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.fab)
    void onClick(){
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        taskDetailFragment.setTitle("Add new task");

        fragmentReplaceListener.replaceFragment(taskDetailFragment, true);

        viewClick = 1;

    }

    public void setListener(FragmentReplaceListener fragmentReplaceListener){
        this.fragmentReplaceListener=fragmentReplaceListener;
    }






}
