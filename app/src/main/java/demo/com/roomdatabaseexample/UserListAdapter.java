package demo.com.roomdatabaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    Context context;
    List<User> users;

    public UserListAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_userId, textView_userName, textView_userEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_userId = itemView.findViewById(R.id.textView_userId);
            textView_userName = itemView.findViewById(R.id.textView_userName);
            textView_userEmail = itemView.findViewById(R.id.textView_userEmail);
        }
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.textView_userId.setText("User ID :- " + String.valueOf(user.getId()));
        holder.textView_userName.setText("User Name :- " + user.getName());
        holder.textView_userEmail.setText("User Email :- " + user.getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
