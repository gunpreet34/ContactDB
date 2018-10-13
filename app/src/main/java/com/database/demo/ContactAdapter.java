package com.database.demo;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsHolder>{
    Context mContext;
    List<Contact> mContacts;

    public ContactAdapter(List<Contact> mContacts, Context mContext) {
        this.mContext = mContext;
        this.mContacts = mContacts;
    }

    public ContactAdapter() {

    }

    @Override
    public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_row,parent,false);
        return new ContactsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactsHolder holder, final int position) {
        holder.tv_name.setText(String.valueOf(mContacts.get(position).getName()));
        holder.tv_contact.setText(String.valueOf(mContacts.get(position).getPhoneNumber()));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = position;
                FragmentManager fm = ((Activity)mContext).getFragmentManager();
                UpdateContactFragment dialogFragment = new UpdateContactFragment(Integer.parseInt(String.valueOf(mContacts.get(pos).getID())));
                dialogFragment.show(fm, "Update Contact Fragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    protected class ContactsHolder extends RecyclerView.ViewHolder{
        TextView tv_id,tv_name,tv_contact;
        CardView mCardView;
        public ContactsHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_contact = (TextView) itemView.findViewById(R.id.tv_contact);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
