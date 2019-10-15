package demo.com.roomdatabaseexample;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    Button btn_addUser, btn_viewUser, btn_deleteUser, btn_updateUser;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        btn_addUser = rootView.findViewById(R.id.btn_addUser);
        btn_viewUser = rootView.findViewById(R.id.btn_viewUser);
        btn_deleteUser = rootView.findViewById(R.id.btn_deleteUser);
        btn_updateUser = rootView.findViewById(R.id.btn_updateUser);
        btn_addUser.setOnClickListener(this);
        btn_viewUser.setOnClickListener(this);
        btn_deleteUser.setOnClickListener(this);
        btn_updateUser.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_addUser:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddUserFragment()).addToBackStack(null).commit();
                break;

            case R.id.btn_viewUser:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new ReadUserFragment()).addToBackStack(null).commit();
                break;

            case R.id.btn_deleteUser:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteUserFragment()).addToBackStack(null).commit();
                break;

            case R.id.btn_updateUser:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateUserFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
