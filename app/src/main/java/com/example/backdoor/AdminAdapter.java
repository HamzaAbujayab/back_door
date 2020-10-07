package com.example.backdoor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminHolder> {

    public List<Admin> adminListAdmin;
    public Context contextAdmin;

    public AdminAdapter(List<Admin> adminListAdmin, Context contextAdmin) {
        this.adminListAdmin = adminListAdmin;
        this.contextAdmin = contextAdmin;
    }

    @NonNull
    @Override
    public AdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adminView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_layout, parent, false);
        AdminAdapter.AdminHolder adminHolder = new AdminAdapter.AdminHolder(adminView);
        return adminHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHolder holder, final int position) {
        Admin admin = adminListAdmin.get(position);
        holder.usernameTVAdmin.setText(admin.getUserNameAdmin());
        holder.emailTVAdmin.setText(admin.getEmailAdmin());
        holder.deleteAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  userNameAdmin = adminListAdmin.get(position).getUserNameAdmin();
                adminListAdmin.remove(position);
                notifyDataSetChanged();
                Toast.makeText(contextAdmin,"Admin "+userNameAdmin+" has been deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return adminListAdmin.size();
    }

    class AdminHolder extends RecyclerView.ViewHolder {

        TextView usernameTVAdmin;
        TextView emailTVAdmin;
        Button deleteAdmin;

        public AdminHolder(@NonNull View itemView) {
            super(itemView);
            usernameTVAdmin = itemView.findViewById(R.id.usernameTVAdmin);
            emailTVAdmin = itemView.findViewById(R.id.emailTVAdmin);
            deleteAdmin = itemView.findViewById(R.id.deleteAdmin);
        }
    }

}
