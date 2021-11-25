package br.com.rodrigo.todo.ui.tasks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.rodrigo.todo.dao.TaskDAO;
import br.com.rodrigo.todo.models.TaskModel;

public class TaskItemTouchHelper extends ItemTouchHelper.Callback {
    private ListTasksAdapter adapter;

    public TaskItemTouchHelper(ListTasksAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeDirections = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int dragDirections = ItemTouchHelper.DOWN | ItemTouchHelper.UP
                | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;

        return makeMovementFlags(dragDirections, swipeDirections);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int posicaoInicial = viewHolder.getAdapterPosition();
        int posicaoFinal = target.getAdapterPosition();
        replacePositionTask(posicaoInicial, posicaoFinal);
        return true;
    }

    private void replacePositionTask(int initialPosition, int endPosition) {
        new TaskDAO().replace(initialPosition, endPosition);
        adapter.replace(initialPosition, endPosition);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
