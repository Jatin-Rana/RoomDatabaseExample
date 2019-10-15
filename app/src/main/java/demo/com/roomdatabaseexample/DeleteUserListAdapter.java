package demo.com.roomdatabaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeleteUserListAdapter extends RecyclerView.Adapter<DeleteUserListAdapter.ViewHolder> {

    Context context;
    List<User> users;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(User item);
    }

    public DeleteUserListAdapter(Context context, List<User> users, OnItemClickListener listener) {
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_userId, textView_userName, textView_userEmail;
        ImageView imageView_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_userId = itemView.findViewById(R.id.textView_userId);
            textView_userName = itemView.findViewById(R.id.textView_userName);
            textView_userEmail = itemView.findViewById(R.id.textView_userEmail);
            imageView_delete = itemView.findViewById(R.id.imageView_delete);
        }
    }

    @NonNull
    @Override
    public DeleteUserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delete_user_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteUserListAdapter.ViewHolder holder, int position) {
        final User user = users.get(position);
        holder.textView_userId.setText("User ID :- " + String.valueOf(user.getId()));
        holder.textView_userName.setText("User Name :- " + user.getName());
        holder.textView_userEmail.setText("User Email :- " + user.getEmail());


        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
