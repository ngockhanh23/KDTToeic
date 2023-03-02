package fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.kdttoeic.LoginActivity;
import com.example.kdttoeic.R;
import com.example.kdttoeic.RegisterActivity;

public class SettingFragment extends Fragment {
    Button btLogin, btRegis, btInterface, btAnswer,btDownload, btRemind;
    Switch switchInterFace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        anhxa(view);
        btLogin.setOnClickListener(onclickOption());
        btRegis.setOnClickListener(onclickOption());
        return view;
    }

    @NonNull
    private View.OnClickListener onclickOption() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btLogin:
                        OpenLogin();
                        break;
                    case R.id.btRegis:
                        OpenRegis();
                        break;
                }
            }
        };
    }
    private void OpenRegis() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
    }
    private void OpenLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
    void anhxa(View view) {
        btLogin = view.findViewById(R.id.btLogin);
        btRegis = view.findViewById(R.id.btRegis);
        btInterface = view.findViewById(R.id.btInterface);
        btAnswer = view.findViewById(R.id.btAnswer);
        btDownload = view.findViewById(R.id.btDownload);
        btRemind = view.findViewById(R.id.btRemind);
        switchInterFace = view.findViewById(R.id.switchInterFace);
    }
}