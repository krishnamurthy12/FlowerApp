package com.krish.flowerapp.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.krish.flowerapp.R;
import com.krish.flowerapp.activities.NewNoteActivity;
import com.krish.flowerapp.adapters.CustomerAdapter;
import com.krish.flowerapp.db.Customers;
import com.krish.flowerapp.viewmodel.CustomersViewModel;

import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static com.krish.flowerapp.MainActivity.NEW_NOTE_ACTIVITY_REQUEST_CODE;
import static com.krish.flowerapp.MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE;
import static com.krish.flowerapp.Utilities.showSnackBar;
import static com.krish.flowerapp.Utilities.showToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener ,CustomerAdapter.OnDeleteClickListener{

    CustomersViewModel customersViewModel;

    RecyclerView mRecyclerView;
    CustomerAdapter customerAdapter;

    //ProgressBar mProgressBar;
    SwipeRefreshLayout mRefreshPage;
    FloatingActionButton mAddCustomer;


    Context mContext;
    View view;


    public CustomersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_customers, container, false);
            initializeViews();

        } else {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        return view;
    }

    private void initializeViews() {

        customersViewModel= ViewModelProviders.of(this).get(CustomersViewModel.class);

        customersViewModel.getAllNotes().observe(this, new Observer<List<Customers>>() {
            @Override
            public void onChanged(@Nullable List<Customers> notes) {
                //noteListAdapter.setNotes(notes);
            }
        });

        //mProgressBar=view.findViewById(R.id.progressbar_fragment_one);

        mRefreshPage=view.findViewById(R.id.swipeRefreshLayout_fragment_one);

        mRecyclerView=view.findViewById(R.id.vR_recyclerview_fragment_one);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        customerAdapter=new CustomerAdapter(this,mContext,mRecyclerView);
        mRecyclerView.setAdapter(customerAdapter);

        mAddCustomer=view.findViewById(R.id.fab_addcustomer);

        mRefreshPage.setOnRefreshListener(this);

        mAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);

            }
        });

    }

    @Override
    public void onRefresh() {

        mRefreshPage.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.greenlight));
       refreshContent();
    }

    private void refreshContent() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Reload page
                mRefreshPage.setRefreshing(false);

            }
        },3000);
    }

    @Override
    public void OnDeleteClickListener(Customers customers) {
        customersViewModel.delete(customers);
        //startActivityForResult(new Intent(mContext,MainActivity.class),101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            /*// Code to insert note
            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id, data.getStringExtra(NewNoteActivity.NOTE_ADDED));
            noteViewModel.insert(note);*/
            showToast(mContext, getResources().getString(R.string.saved));
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            // Code to update the note
            /*ContactsContract.CommonDataKinds.Note note = new Note(
                    data.getStringExtra(EditNoteActivity.NOTE_ID),
                    data.getStringExtra(EditNoteActivity.UPDATED_NOTE));
            noteViewModel.update(note);*/

            /*Toast.makeText(
                    getApplicationContext(),
                    R.string.updated,
                    Toast.LENGTH_LONG).show();*/

        } else {
            showToast(mContext,getResources().getString(R.string.not_saved));
        }
    }
}
