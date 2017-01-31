package com.example.cloudkinetics.contactsdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cloud kinetics on 1/28/2017.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private ArrayList<Contact> list;

    public ContactListAdapter(ArrayList<Contact> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact_list, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mContactName.setText(list.get(position).getContactName());
        holder.mContactWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSelect(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mContactName;
        private ImageView mPersonImage;
        private RelativeLayout mContactWrapper;

        public ViewHolder(View v) {
            super(v);
            mContactName = (TextView) v.findViewById(R.id.tv_person_name);
            mContactWrapper = (RelativeLayout) v.findViewById(R.id.item_contact);
        }
    }

    public void setData(ArrayList<Contact> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private OnContactSelectListener listener;

    public void setContactSelectedListener(OnContactSelectListener listener) {
        this.listener = listener;
    }
}
