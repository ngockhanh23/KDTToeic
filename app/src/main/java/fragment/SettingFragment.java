package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.kdttoeic.HistoryActivity;
import com.example.kdttoeic.R;

import com.example.kdttoeic.TextSizeActivity;

public class SettingFragment extends Fragment {
    TextView tvTextSize, btInterface, btAnswer, btHistory;
    //    TextView btHistory;
    boolean nightMode;
    SwitchCompat switchInterFace;

    String filename = "config";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);


        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (sharedPreferences != null) {
//            nightMode = sharedPreferences.getBoolean("night", false);
//            if (nightMode) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            }
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);

        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHistory();
            }
        });


        //DARK-LIGHT
        sharedPreferences = getContext().getSharedPreferences(filename, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        nightMode = sharedPreferences.getBoolean("night", false);

        if (nightMode) {
            switchInterFace.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switchInterFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.commit();
            }
        });


        //TEXT-SIZE
        tvTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TextSizeActivity.class);
                startActivity(i);
            }
        });
    }

    @NonNull
    private View.OnClickListener onclickOption() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }


    void OpenHistory() {
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }

    void anhxa(View view) {
        btInterface = view.findViewById(R.id.btInterface);
        btAnswer = view.findViewById(R.id.btAnswer);
        btHistory = view.findViewById(R.id.btHistory);
        tvTextSize = view.findViewById(R.id.tvTextSize);
        switchInterFace = view.findViewById(R.id.switchInterFace);
    }

    void loadFragment(Fragment fmNew) {
        //Gọi quản lí Fragment để tiến hành thay đổi Fragment
        FragmentTransaction fmCur = getActivity().getSupportFragmentManager().beginTransaction();
        //Thay thế Fragment hiện tại = Fragment mới vào id.container là Framelayout cu của màn hình hiện Fragment
        fmCur.replace(R.id.container, fmNew);
        //Đưa Fragment mới thay đổi thày Fragment chính
        fmCur.addToBackStack(null);
        //Thực hiện việc chuyển đổi
        fmCur.commit();
    }
}