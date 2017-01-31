package com.example.cloudkinetics.contactsdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by cloud kinetics on 1/28/2017.
 */
public class SentMessageAdapter extends RecyclerView.Adapter<SentMessageAdapter.ViewHolder> {
    private ArrayList<Message> list;

    public SentMessageAdapter(ArrayList<Message> list) {
        this.list = list;
        Collections.reverse(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sent_message, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message m = list.get(position);
        holder.tvPersonName.setText(m.getReceiverName());
        holder.tvDate.setText(getDate(m.getSentTimestamp()));
        holder.tvMsg.setText(m.getMessageBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPersonName;
        private TextView tvMsg;
        private TextView tvDate;

        public ViewHolder(View v) {
            super(v);
            tvPersonName = (TextView) v.findViewById(R.id.tv_person_name);
            tvDate = (TextView) v.findViewById(R.id.tv_date);
            tvMsg = (TextView) v.findViewById(R.id.tv_msg);
        }
    }

    private String formateDate = "dd MMM yy  hh:mm a";

    private String getDate(long timestamp) {
        Date d = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat(formateDate);
        return formatter.format(d);
    }

    public void setData(ArrayList<Message> list) {
        this.list = list;
        Collections.reverse(list);
        notifyDataSetChanged();
    }

}
