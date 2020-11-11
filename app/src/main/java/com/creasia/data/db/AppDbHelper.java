

package com.creasia.data.db;

import com.creasia.data.db.model.DaoMaster;
import com.creasia.data.db.model.DaoSession;
import com.creasia.data.db.model.Task;
import com.creasia.data.db.model.User;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getUserDao().insert(user);
            }
        });
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mDaoSession.getUserDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Long> insertTask(Task task) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getTaskDao().insert(task);
            }
        });
    }

    @Override
    public Observable<Boolean> deleteTask(Task task) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getTaskDao().delete(task);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> updateTask(Task task) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getTaskDao().update(task);
                return true;
            }
        });
    }

    @Override
    public Observable<List<Task>> getAllTask() {
        return Observable.fromCallable(new Callable<List<Task>>() {
            @Override
            public List<Task> call() throws Exception {
                return mDaoSession.getTaskDao().loadAll();
            }
        });
    }

}
