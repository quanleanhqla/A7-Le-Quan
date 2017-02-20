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
import android.widget.EditText;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.adapters.ColorAdapter;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.decorations.TaskColorDecoration;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.SaveEditTask;
import com.example.quanla.pomodoro.fragments.optionItemStrategies.Strategy;
import com.example.quanla.pomodoro.networks.jsonmodels.AddTaskBodyJson;
import com.example.quanla.pomodoro.networks.services.AddNewTaskService;
import com.example.quanla.pomodoro.settings.SharedPrefs;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {

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
        if(item.getItemId()==R.id.m_ok){

            //get data from UI
            taskName = etTaskName.getText().toString();
            paymentPerHour = Float.parseFloat(etPayment.getText().toString());
            color = taskColorAdapter.getSelectedColor();

            if(TaskFragment.viewClick==0){
                strategy = new SaveEditTask();
                strategy.doOptionItem(task, taskName, paymentPerHour, color);
            }
            else {
                Task newTask = new Task(taskName, color, paymentPerHour);
                addNewTask(newTask);
                Log.d(TAG, newTask.getName());
                DbContext.instance.addTask(newTask);

            }





            getActivity().onBackPressed();


        }
        return false;
    }

    public void addNewTask(Task task){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","JWT " + SharedPrefs.getInstance().getAccessToken())
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

        AddNewTaskService addNewTaskService = retrofit.create(AddNewTaskService.class);

        //data & format
        //format =>Mediatype
        //date => json

        MediaType jsonType = MediaType.parse("application/json");
        String addNewTaskJson = (new Gson()).toJson(new AddTaskBodyJson(task.getColor(), false, null, null, null, task.getName(), task.getPayment()));
        RequestBody requestBody = RequestBody.create(jsonType, addNewTaskJson);

        addNewTaskService.addTask(requestBody).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.d(TAG, String.format("abc: %s", response.body()) );
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });
    }

}
