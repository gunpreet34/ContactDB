package com.database.demo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class AddContactFragment extends DialogFragment{
    LinearLayout addFragment;
    Button mSubmitButton;
    EditText mName,mContactNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_contact_fragment, container, false);
        getDialog().setTitle("Add new Contact");

        mName = (EditText) rootView.findViewById(R.id.et_name);
        mContactNumber = (EditText) rootView.findViewById(R.id.et_contact_number);

        mSubmitButton = (Button) rootView.findViewById(R.id.but_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.addContact(new Contact(mName.getText().toString(), mContactNumber.getText().toString()));
                Toast.makeText(getActivity(), "Added!!..", Toast.LENGTH_SHORT).show();
                List<Contact> contacts = db.getAllContacts();
                ((MainActivity)getActivity()).recyclerView.setAdapter(new ContactAdapter(contacts,getActivity()));
                dismiss();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.splash);
        addFragment = (LinearLayout) rootView.findViewById(R.id.add_fragment);
        addFragment.setAnimation(animation);

        return rootView;
    }
}
