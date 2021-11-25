package br.com.rodrigo.todo.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import br.com.rodrigo.todo.models.TaskModel;

import br.com.rodrigo.todo.R;

public class ListTasksAdapter extends RecyclerView.Adapter<ListTasksAdapter.ViewHolder> {
    private Context context;
    private List<TaskModel> tasks;

    public ListTasksAdapter(Context context, List<TaskModel> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_task, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskModel task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void replace(int initialPosition, int endPosition) {
        Collections.swap(tasks, initialPosition, endPosition);
        notifyItemMoved(initialPosition, endPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TaskModel task;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_title);
        }

        public void bind(TaskModel task) {
            this.task = task;
            title.setText(task.getTitle());
        }
    }
}
