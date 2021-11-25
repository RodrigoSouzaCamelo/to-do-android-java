package br.com.rodrigo.todo.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.rodrigo.todo.dao.TaskDAO;
import br.com.rodrigo.todo.databinding.FragmentTasksBinding;
import br.com.rodrigo.todo.models.TaskModel;

public class TasksFragment extends Fragment {

    private TasksViewModel taksViewModel;
    private FragmentTasksBinding binding;
    private ListTasksAdapter adapter;
    private List<TaskModel> allTasks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        taksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);

        binding = FragmentTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        allTasks = getAllTasks();

        configRecyclerView(container);

        return root;
    }

    private void configRecyclerView(ViewGroup container) {
        RecyclerView listTasks = binding.fragmentTasksListTasks;
        adapter = new ListTasksAdapter(container.getContext(), allTasks);
        listTasks.setAdapter(adapter);
        configItemTouchHelper(listTasks);
    }

    private void configItemTouchHelper(RecyclerView listTasks) {
        ItemTouchHelper itemTouchHelper =
                new ItemTouchHelper(new TaskItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(listTasks);
    }

    private List<TaskModel> getAllTasks() {
        TaskDAO dao = new TaskDAO();

        for(int i = 0; i < 4; i++) {
            dao.create(new TaskModel("Title " + i));
        }

        return dao.all();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}