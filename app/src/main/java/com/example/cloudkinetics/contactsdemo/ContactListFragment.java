package com.example.cloudkinetics.contactsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cloud kinetics on 1/28/2017.
 */
public class ContactListFragment extends Fragment implements OnContactSelectListener {
    private RecyclerView mRecyclerView;
    private ContactListAdapter mAdapter;

    public static ContactListFragment newInstance() {
        ContactListFragment fragment = new ContactListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.contact_list_view);
        mAdapter = new ContactListAdapter(new ArrayList<Contact>());
        mAdapter.setContactSelectedListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        ArrayList<Contact> list = getContactsFromJSON(Constants.contact_json);
        mAdapter.setData(list);
    }

    private ArrayList<Contact> getContactsFromJSON(String jsonStr) {
        ArrayList<Contact> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonStr);
            JSONArray array = object.getJSONArray("contacts");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Contact c = new Contact();
                c.setContactId(obj.getInt("contact_id"));
                c.setContactName(obj.getString("contact_name"));
                c.setContactNumber(obj.getString("contact_number"));
                list.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void onSelect(Contact c) {
        Intent i = new Intent(getActivity(), ContactDetailsActivity.class);
        i.putExtra("contact", c);
        startActivity(i);
    }

}
