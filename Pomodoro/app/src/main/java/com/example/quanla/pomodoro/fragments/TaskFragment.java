package com.example.quanla.pomodoro.fragments;


import android.app.ProgressDialog;
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
import com.example.quanla.pomodoro.events.FragmentReplaceEvent;
import com.example.quanla.pomodoro.events.TaskClick;
import com.example.quanla.pomodoro.events.TaskClickEvent;
import com.example.quanla.pomodoro.events.TimerCommand;
import com.example.quanla.pomodoro.events.TimerCommandEvent;
import com.example.quanla.pomodoro.networks.NetContext;
import com.example.quanla.pomodoro.networks.services.TaskService;
import com.example.quanla.pomodoro.settings.SharedPrefs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private ProgressDialog progressDialogGet;
    private ProgressDialog progressDialogDel;

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
        getAllTask();
        setupUI(view);
        for(Task task: DbContext.instance.allTasks()){
            Log.d(TAG, String.format("Ra: %s", task.toString()));
        }
        EventBus.getDefault().register(this);

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

//        taskAdapter.setTaskItemLongClickListener(new TaskAdapter.TaskItemLongClickListener() {
//            @Override
//            public void onItemLongClick(final Task task) {
//                final AlertDialog.Builder deleteAD = new AlertDialog.Builder(getActivity());
//                deleteAD.setMessage("Delete this task?");
//                deleteAD.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if(((MainActivity)getActivity()).isOnline()) {
//                            deleteTask(task);
//                            taskAdapter.notifyDataSetChanged();
//
//                        }
//                    }
//
//                });
//                deleteAD.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        deleteAD.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                            @Override
//                            public void onDismiss(DialogInterface dialog) {
//                                getActivity().onBackPressed();
//                            }
//                        });
//                    }
//                });
//                deleteAD.show();
//
//            }
//        });

//        taskAdapter.setTaskItemClickListener(new TaskAdapter.TaskItemClickListener() {
//            @Override
//            public void onItemClick(Task task) {
//
//                TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
//                taskDetailFragment.setTitle("Edit task");
//
//                taskDetailFragment.setTask(task);
//
//                //TODO: Make TaskActivity and Fragment independent
//                ((MainActivity)getActivity()).replaceFragment(taskDetailFragment, true);
//
//                viewClick = 0;
//
//            }
//        });


//        taskAdapter.setIconClickListener(new TaskAdapter.IconClickListener() {
//            @Override
//            public void onIconClickListener(View v) {
//                TimerFragment timerFragment = new TimerFragment();
//                ((MainActivity)getActivity()).replaceFragment(timerFragment, true);
//                TimerCommandEvent event = new TimerCommandEvent(TimerCommand.START_TIMER);
//                EventBus.getDefault().post(event);
//
//            }
//        });





        setHasOptionsMenu(true);



    }

    @Subscribe
    public void onPlayClick(final TaskClickEvent taskClickEvent){
        if(taskClickEvent.getTaskClick()== TaskClick.PLAY_TIMER){
            TimerFragment timerFragment = new TimerFragment();
            FragmentReplaceEvent fragmentReplaceEvent = new FragmentReplaceEvent(timerFragment, true);
            EventBus.getDefault().post(fragmentReplaceEvent);
            TimerCommandEvent event = new TimerCommandEvent(TimerCommand.START_TIMER);
            EventBus.getDefault().post(event);
        }
        else if(taskClickEvent.getTaskClick()==TaskClick.TASK_CLICK){
            TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
            taskDetailFragment.setTitle("Edit task");

            taskDetailFragment.setTask(taskClickEvent.getTask());

            //TODO: Make TaskActivity and Fragment independent
            EventBus.getDefault().post(new FragmentReplaceEvent(taskDetailFragment, true));

            viewClick = 0;
        }
        else if(taskClickEvent.getTaskClick()==TaskClick.DELETE_TASK){
            final AlertDialog.Builder deleteAD = new AlertDialog.Builder(getActivity());
            deleteAD.setMessage("Delete this task?");
            deleteAD.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(((MainActivity)getActivity()).isOnline()) {
                        deleteTask(taskClickEvent.getTask());
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
    }

    private void getAllTask(){
        progressDialogGet = ProgressDialog.show(this.getContext(), null, "Getting tasks from server", true);

        TaskService getTaskService = NetContext.instance.create(TaskService.class);

        getTaskService.getAllTask().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, retrofit2.Response<List<Task>> response) {

                List<Task> tasks = response.body();

                if(tasks!=null) {
                    for (Task task : tasks) {
                        Log.d(TAG, String.format("onResponse: %s", task));
                        if(task.getIdLocal()!=null) DbContext.instance.addOrUpdate(new Task(task.getName(), task.getColor(), task.getPayment(), task.getIdLocal()));
                    }
                    progressDialogGet.dismiss();

                }
                else {
                    Toast.makeText(getActivity(), "No jobs", Toast.LENGTH_SHORT).show();progressDialogGet.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                progressDialogGet.dismiss();
                Toast.makeText(getActivity(), "Cannot get tasks from server", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void deleteTask(final Task task) {
        progressDialogDel = ProgressDialog.show(this.getContext(), null, "Deleting task", true);
        TaskService deleteTask = NetContext.instance.create(TaskService.class);

        deleteTask.deleteATask(task.getIdLocal()).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.body()!=null){
                    DbContext.instance.delete(task);
                    Log.d(TAG, String.format("Delete %s", response.body()));
                    taskAdapter.notifyDataSetChanged();
                    progressDialogDel.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                progressDialogDel.dismiss();
                Toast.makeText(getActivity(), "Cannot delete this task", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.fab)
    void onClick(){
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        taskDetailFragment.setTitle("Add new task");

        EventBus.getDefault().post(new FragmentReplaceEvent(taskDetailFragment, true));

        viewClick = 1;

    }

    public void setListener(FragmentReplaceListener fragmentReplaceListener){
        this.fragmentReplaceListener=fragmentReplaceListener;
    }


    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
