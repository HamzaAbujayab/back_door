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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    public List<User> userListUser;
    public Context contextUser;

    public UserAdapter(List<User> userListUser, Context contextUser) {
        this.userListUser = userListUser;
        this.contextUser = contextUser;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        UserHolder userHolder = new UserHolder(userView);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserHolder holder, final int position) {
        User user = userListUser.get(position);
        holder.usernameTVUser.setText(user.getUserNameUser());
        holder.emailTVUser.setText(user.getEmailUser());
        holder.deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  userNameUser = userListUser.get(position).getUserNameUser();
                userListUser.remove(position);
                notifyDataSetChanged();
                Toast.makeText(contextUser,"User "+userNameUser+" has been deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListUser.size();
    }



    class UserHolder extends RecyclerView.ViewHolder {

        TextView usernameTVUser;
        TextView emailTVUser;
        Button deleteUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            usernameTVUser = itemView.findViewById(R.id.usernameTVUser);
            emailTVUser = itemView.findViewById(R.id.emailTVUser);
            deleteUser = itemView.findViewById(R.id.deleteUser);
        }
    }
}