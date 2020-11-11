

package com.creasia.data.db;

import com.creasia.data.db.model.Task;
import com.creasia.data.db.model.User;

import java.util.List;

import io.reactivex.Observable;




public interface DbHelper {

    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<Long> insertTask(final Task task);

    Observable<Boolean> deleteTask(final Task task);

    Observable<Boolean> updateTask(final Task task);

    Observable<List<Task>> getAllTask();
}


