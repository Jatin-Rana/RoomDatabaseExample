package demo.com.roomdatabaseexample;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {

    EditText edittext_user_name, edittext_user_email, edittext_user_id;
    Button btn_save_user;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_add_user, container, false);
        edittext_user_name = rootview.findViewById(R.id.edittext_user_name);
        edittext_user_email = rootview.findViewById(R.id.edittext_user_email);
        edittext_user_id = rootview.findViewById(R.id.edittext_user_id);
        btn_save_user = rootview.findViewById(R.id.btn_save_user);


        btn_save_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = Integer.parseInt(edittext_user_id.getText().toString());
                String str_userName = edittext_user_name.getText().toString();
                String str_emailID = edittext_user_email.getText().toString();

                User user = new User();
                user.setId(userid);
                user.setName(str_userName);
                user.setEmail(str_emailID);

                MainActivity.myAppDatabase.myDao().addUser(user);
                Toast.makeText(getContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();
                edittext_user_id.setText("");
                edittext_user_name.setText("");
                edittext_user_email.setText("");
            }
        });
        return rootview;
    }

}
