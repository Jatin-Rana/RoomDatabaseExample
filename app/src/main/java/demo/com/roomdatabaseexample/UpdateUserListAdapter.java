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

class UpdateUserListAdapter extends RecyclerView.Adapter<UpdateUserListAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    Context context;
    List<User> users;

    public interface OnItemClickListener {
        void onItemClick(User item);
    }


    public UpdateUserListAdapter(Context context, List<User> users, OnItemClickListener listener) {
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_userId, textView_userName, textView_userEmail;
        ImageView imageView_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_userId = itemView.findViewById(R.id.textView_userId);
            textView_userName = itemView.findViewById(R.id.textView_userName);
            textView_userEmail = itemView.findViewById(R.id.textView_userEmail);
            imageView_edit = itemView.findViewById(R.id.imageView_edit);
        }
    }

    @NonNull
    @Override
    public UpdateUserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.update_user_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateUserListAdapter.ViewHolder holder, int position) {
        final User user = users.get(position);
        holder.textView_userId.setText("User ID :- " + String.valueOf(user.getId()));
        holder.textView_userName.setText("User Name :- " + user.getName());
        holder.textView_userEmail.setText("User Email :- " + user.getEmail());


        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
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
