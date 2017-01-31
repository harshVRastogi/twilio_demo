package com.example.cloudkinetics.contactsdemo;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by cloud kinetics on 1/28/2017.
 */
public class SentMessageFragment extends Fragment {

    private ArrayList<Message> list;
    private MyDatabase db;
    private SentMessageAdapter adapter;

    public static SentMessageFragment newInstance() {

        SentMessageFragment fragment = new SentMessageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = new MyDatabase(getContext());
        list = db.getMessageList();
        return inflater.inflate(R.layout.frag_sent_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.sent_message_list_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        if (list == null) {
            adapter = new SentMessageAdapter(new ArrayList<Message>());
        } else {
            adapter = new SentMessageAdapter(list);
        }
        mRecyclerView.setAdapter(adapter);
    }

    public void setNewData() {
        list = db.getMessageList();
        adapter.setData(list);
    }

}
