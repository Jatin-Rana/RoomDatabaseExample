package demo.com.roomdatabaseexample;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteUserFragment extends Fragment {


    RecyclerView recycler_view_deletion;
    TextView textView_no_record;
    DeleteUserListAdapter deleteUserListAdapter;

    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_delete_user, container, false);
        recycler_view_deletion = rootView.findViewById(R.id.recycler_view_deletion);
        textView_no_record = rootView.findViewById(R.id.textView_no_record);

        getListData();

        return rootView;
    }

    private void getListData() {
        List<User> users = MainActivity.myAppDatabase.myDao().getUsers();
        if (users.size() != 0) {
            textView_no_record.setVisibility(View.GONE);
            recycler_view_deletion.setVisibility(View.VISIBLE);
            deleteUserListAdapter = new DeleteUserListAdapter(getContext(), users, new DeleteUserListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(User item) {
                    int id = item.getId();
                    User user = new User();
                    user.setId(id);
                    MainActivity.myAppDatabase.myDao().deleteUser(user);
                    Toast.makeText(getContext(), "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                    deleteUserListAdapter.notifyDataSetChanged();
                    getListData();
//                    recycler_view_deletion.setAdapter(deleteUserListAdapter);
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recycler_view_deletion.addItemDecoration(new DividerItemDecoration(getContext(), 0));
            recycler_view_deletion.setLayoutManager(mLayoutManager);
            recycler_view_deletion.setAdapter(deleteUserListAdapter);
        } else {
            textView_no_record.setVisibility(View.VISIBLE);
            recycler_view_deletion.setVisibility(View.GONE);
        }
    }


}
