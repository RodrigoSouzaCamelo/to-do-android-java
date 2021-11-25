package br.com.rodrigo.todo.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.rodrigo.todo.models.TaskModel;

public class TaskDAO {
    private final static ArrayList<TaskModel> tasks = new ArrayList<>();

    public List<TaskModel> all() {
        return (List<TaskModel>) tasks.clone();
    }

    public void create(TaskModel... tasks) {
        TaskDAO.tasks.addAll(Arrays.asList(tasks));
    }

    public void replace(int initialPosition, int endPosition) {
        Collections.swap(tasks, initialPosition, endPosition);
    }
}
