package com.example.quanla.pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.adapters.TaskAdapter;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.Strategy;
import com.example.quanla.pomodoro.networks.services.GetTaskService;
import com.example.quanla.pomodoro.settings.SharedPrefs;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {

    private final static String TAG = "abc";

    public static int viewClick = 0;

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

    private void getAllTask(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "JWT " + SharedPrefs.getInstance().getAccessToken())
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GetTaskService getTaskService = retrofit.create(GetTaskService.class);

        getTaskService.getAllTask().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, retrofit2.Response<List<Task>> response) {
                List<Task> tasks = response.body();
                if(tasks==null) {
                    Log.d(TAG, "fail");
                    Log.d(TAG, "abc:"+SharedPrefs.getInstance().getAccessToken());
                }
                else {
                    DbContext.instance.delete();
                    for (Task task : tasks) {
                        Log.d(TAG, String.format("onResponse: %s", task));
                        if(task.getColor()!=null) DbContext.instance.addTask(task);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }


}
