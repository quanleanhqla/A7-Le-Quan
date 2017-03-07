package com.example.quanla.pomodoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.adapters.viewholders.TaskViewHolder;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.events.TaskClick;
import com.example.quanla.pomodoro.events.TaskClickEvent;

import org.greenrobot.eventbus.EventBus;

import static android.content.ContentValues.TAG;

/**
 * Created by QuanLA on 2/8/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private final static String TAG = "Fix";

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //1: Create View
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_task, parent, false);

        //2: Create ViewHolder
        TaskViewHolder taskViewHolder = new TaskViewHolder(itemView);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, final int position) {
        //1: get data based on position
        final Task task = DbContext.instance.allTasks().get(position);

        //2: bind data into view
        holder.bind(task);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                if(taskItemLongClickListener!=null){
//                    taskItemLongClickListener.onItemLongClick(task);
//                }
                TaskClickEvent taskClickEvent = new TaskClickEvent(task, TaskClick.DELETE_TASK);
                EventBus.getDefault().post(taskClickEvent);
                notifyDataSetChanged();
                return false;
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send event to outside
//                if(taskItemClickListener != null){
//                    taskItemClickListener.onItemClick(task);
//                }
                TaskClickEvent taskClickEvent = new TaskClickEvent(task, TaskClick.TASK_CLICK);
                EventBus.getDefault().post(taskClickEvent);
                notifyDataSetChanged();
            }
        });
        holder.getIvPlay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(iconClickListener != null){
//                    iconClickListener.onIconClickListener(holder.getIvPlay());
//                }
                TaskClickEvent taskClickEvent = new TaskClickEvent(TaskClick.PLAY_TIMER);
                EventBus.getDefault().post(taskClickEvent);
            }
        });


        holder.getvTaskColor().setOnClickListener(new View.OnClickListener() {
            int check = -1;
            @Override
            public void onClick(View v) {
                if(check==1) {
                    holder.getIvCheck().setVisibility(View.INVISIBLE);
                    check=-check;
                }
                else{
                    holder.getIvCheck().setVisibility(View.VISIBLE);
                    check=-check;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return DbContext.instance.allTasks().size();
    }
}
