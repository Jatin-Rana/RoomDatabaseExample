package demo.com.roomdatabaseexample;


import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateUserFragment extends Fragment {


    RecyclerView recycler_view_update;
    TextView textView_no_record;
    UpdateUserListAdapter updateUserListAdapter;


    public UpdateUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_update_user, container, false);
        recycler_view_update = rootView.findViewById(R.id.recycler_view_update);
        textView_no_record = rootView.findViewById(R.id.textView_no_record);

        getListData();

        return rootView;
    }


    private void getListData() {
        final List<User> users = MainActivity.myAppDatabase.myDao().getUsers();
        if (users.size() != 0) {
            textView_no_record.setVisibility(View.GONE);
            recycler_view_update.setVisibility(View.VISIBLE);
            updateUserListAdapter = new UpdateUserListAdapter(getContext(), users, new UpdateUserListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(final User item) {
                   /* int id = item.getId();
                    User user = new User();
                    user.setId(id);
                    MainActivity.myAppDatabase.myDao().deleteUser(user);
                    Toast.makeText(getContext(), "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                    updateUserListAdapter.notifyDataSetChanged();
                    getListData();*/

                    LayoutInflater factory = LayoutInflater.from(getContext());
                    final View updateDialogView = factory.inflate(R.layout.update_dialog, null);
                    EditText edittext_user_id = updateDialogView.findViewById(R.id.edittext_user_id);
                    final EditText edittext_user_name = updateDialogView.findViewById(R.id.edittext_user_name);
                    final EditText edittext_user_email = updateDialogView.findViewById(R.id.edittext_user_email);
                    Button btn_update_user = updateDialogView.findViewById(R.id.btn_update_user);

                    edittext_user_id.setText(String.valueOf(item.getId()));
                    edittext_user_name.setText(item.getName());
                    edittext_user_email.setText(item.getEmail());
                    
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(false);
                    builder.setView(updateDialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    btn_update_user.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            User user = new User();
                            user.setId(item.getId());
                            user.setName(edittext_user_name.getText().toString());
                            user.setEmail(edittext_user_email.getText().toString());
                            MainActivity.myAppDatabase.myDao().updateUser(user);
                            getListData();
                            alertDialog.dismiss();
                        }
                    });
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recycler_view_update.addItemDecoration(new DividerItemDecoration(getContext(), 0));
            recycler_view_update.setLayoutManager(mLayoutManager);
            recycler_view_update.setAdapter(updateUserListAdapter);
        } else {
            textView_no_record.setVisibility(View.VISIBLE);
            recycler_view_update.setVisibility(View.GONE);
        }
    }
}
