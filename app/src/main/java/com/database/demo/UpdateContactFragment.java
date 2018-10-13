package com.database.demo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

class UpdateContactFragment extends DialogFragment{
    Button mSubmitButton,mDeleteButton;
    EditText mName,mContactNumber;
    final int mContactId;
    Contact mContact;

    public UpdateContactFragment(int mContactId) {
        this.mContactId = mContactId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.update_contact_fragment, container, false);
        getDialog().setTitle("Update Contact");

        final DatabaseHandler db = new DatabaseHandler(getActivity());

        mContact = db.getContact(mContactId);

        mName = (EditText) rootView.findViewById(R.id.et_name);
        mName.setText(mContact.getName().toString());
        mContactNumber = (EditText) rootView.findViewById(R.id.et_contact_number);
        mContactNumber.setText(mContact.getPhoneNumber().toString());

        mSubmitButton = (Button) rootView.findViewById(R.id.but_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateContact(new Contact(mName.getText().toString(), mContactNumber.getText().toString()),mContactId);
                Toast.makeText(getActivity(), "Updated!!..", Toast.LENGTH_SHORT).show();
                List<Contact> contacts = db.getAllContacts();
                ((MainActivity)getActivity()).recyclerView.setAdapter(new ContactAdapter(contacts,getActivity()));
                /*ContactAdapter object = new ContactAdapter();
                object.notifyDataSetChanged();*/
                dismiss();
            }
        });

        mDeleteButton = (Button) rootView.findViewById(R.id.but_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteContact(mContactId);
                Toast.makeText(getActivity(), "Deleted!!..", Toast.LENGTH_SHORT).show();
                List<Contact> contacts = db.getAllContacts();
                ((MainActivity)getActivity()).recyclerView.setAdapter(new ContactAdapter(contacts,getActivity()));
                dismiss();
            }
        });

        return rootView;
    }

}
