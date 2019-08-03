package com.krish.flowerapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.krish.flowerapp.R;
import com.krish.flowerapp.customs.TextDrawable;
import com.krish.flowerapp.db.Customers;

import java.util.List;
import java.util.Random;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Customers customers);

    }

    private OnDeleteClickListener onDeleteClickListener;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Customers> mList;
    RecyclerView mRecyclerView;

    public CustomerAdapter(OnDeleteClickListener onDeleteClickListener, Context mContext,RecyclerView mRecyclerView) {
        layoutInflater = LayoutInflater.from(mContext);

        this.onDeleteClickListener = onDeleteClickListener;
        this.mContext = mContext;
        this.mRecyclerView=mRecyclerView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = layoutInflater.inflate(R.layout.customer_singlerow_layout, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Random rnd = new Random();
        int color= Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        TextDrawable drawable= null;
        if (mList.get(i).getCustomerName() != null) {
            drawable = TextDrawable.builder().buildRound(mList.get(i).getCustomerName().charAt(0)+"",color);
        }
        myViewHolder.mCustomerImage.setImageDrawable(drawable);

        myViewHolder.mCustomerName.setText(mList.get(i).getCustomerName());
        myViewHolder.mCustomerNumber.setText(mList.get(i).getMobileNumber());
        myViewHolder.mCustomerAddress.setText(mList.get(i).getAddress());

    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setNotes(List<Customers> customers) {
        mList = customers;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout mRootLayout,mOptionsLayout;
        TextView mCustomerName,mCustomerNumber,mCustomerAddress;
        ImageView mCustomerImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mRootLayout=itemView.findViewById(R.id.vL_main_Layout);
            mOptionsLayout=itemView.findViewById(R.id.vL_options_layout);

            mCustomerName=itemView.findViewById(R.id.vT_customer_name);
            mCustomerNumber=itemView.findViewById(R.id.vT_customer_number);
            mCustomerAddress=itemView.findViewById(R.id.vT_customer_address);

            mCustomerImage=itemView.findViewById(R.id.vI_contact_pic);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.vL_main_Layout:
                    boolean shouldExpandDetailsLayout = mOptionsLayout.getVisibility() == View.GONE;

                    AutoTransition autoTransition = new AutoTransition();
                    autoTransition.setDuration(200);

                    if (shouldExpandDetailsLayout) {
                        // Expand the the item which is previously not expanded
                        mRootLayout.setBackground(mContext.getResources().getDrawable(R.drawable.focused_card));

                        // notifyItemChanged(position);
                        mOptionsLayout.setVisibility(View.VISIBLE);

                    }
                    else {
                        //collapse the item which is previously opened
                        mRootLayout.setBackground(null);
                        mOptionsLayout.setVisibility(View.GONE);
                    }

                    TransitionManager.beginDelayedTransition( mRecyclerView, autoTransition);
                    mRootLayout.setActivated(shouldExpandDetailsLayout);

                    mRootLayout.setActivated(false);
                    break;
                case R.id.vT_lsil_delete:

                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mList.get(getAdapterPosition()));
                    }
                    break;
                case R.id.vT_lsil_update:

                   /* Intent intent = new Intent(mContext, EditNoteActivity.class);
                    intent.putExtra("note_id", mList.get(getAdapterPosition()).getId());
                    ((Activity)mContext).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);*/
                    break;
            }
        }
    }
}
