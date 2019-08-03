package com.krish.flowerapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.krish.flowerapp.db.Customers;
import com.krish.flowerapp.db.CustomersDAO;
import com.krish.flowerapp.db.FlowerRoomDatabase;

import java.util.List;

public class CustomersViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private CustomersDAO customersDao;
    private FlowerRoomDatabase flowerDB;
    private LiveData<List<Customers>> mAllCustomers;
    public CustomersViewModel(@NonNull Application application) {
        super(application);

        flowerDB = FlowerRoomDatabase.getDatabase(application);
        customersDao = flowerDB.customersDAO();
        mAllCustomers = customersDao.getAllCustomers();
    }

    public void insert(Customers note) {
        new InsertAsyncTask(customersDao).execute(note);
    }

    public LiveData<List<Customers>> getAllNotes() {
        return mAllCustomers;
    }

    public void update(Customers note) {
        new UpdateAsyncTask(customersDao).execute(note);
    }

    public void delete(Customers note) {
        new DeleteAsyncTask(customersDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class OperationsAsyncTask extends AsyncTask<Customers, Void, Void> {

        CustomersDAO mAsyncTaskDao;

        OperationsAsyncTask(CustomersDAO dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Customers... notes) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(CustomersDAO customersDAO) {
            super(customersDAO);
        }

        @Override
        protected Void doInBackground(Customers... notes) {
            mAsyncTaskDao.insertCustomer(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(CustomersDAO customersDAO) {
            super(customersDAO);
        }

        @Override
        protected Void doInBackground(Customers... notes) {
            mAsyncTaskDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        public DeleteAsyncTask(CustomersDAO customersDAO) {
            super(customersDAO);
        }

        @Override
        protected Void doInBackground(Customers... notes) {
            mAsyncTaskDao.delete(notes[0]);
            return null;
        }
    }
}
