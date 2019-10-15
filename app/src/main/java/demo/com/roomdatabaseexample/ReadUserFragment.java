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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

    TextView textView_no_record;
    RecyclerView recyclerview;
    UserListAdapter userListAdapter;

    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_read_user, container, false);
        textView_no_record = rootView.findViewById(R.id.textView_no_record);
        recyclerview = rootView.findViewById(R.id.recyclerview);

        getListData();

        return rootView;
    }

    private void getListData() {
        List<User> users = MainActivity.myAppDatabase.myDao().getUsers();
        if (users.size() != 0) {
            textView_no_record.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
            userListAdapter = new UserListAdapter(getContext(), users);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.addItemDecoration(new DividerItemDecoration(getContext(), 0));
            recyclerview.setLayoutManager(mLayoutManager);
            recyclerview.setAdapter(userListAdapter);
        } else {
            textView_no_record.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
        }
    }
}
