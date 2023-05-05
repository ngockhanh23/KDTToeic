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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kdttoeic.About;
import com.example.kdttoeic.HistoryActivity;
import com.example.kdttoeic.R;
import com.example.kdttoeic.TextSizeActivity;

public class SettingFragment extends Fragment {
    TextView tvTextSize, btInterface, tvAbout, btHistory;
    boolean nightMode;
    SwitchCompat switchInterFace;

    String filename = "config";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        return view;
    }
 //Lấy ra dữ liệu
    @Override
    public void onResume() {
        super.onResume();
        if (sharedPreferences != null) {
            nightMode = sharedPreferences.getBoolean("night", false);
            if (nightMode) {
                switchInterFace.setChecked(true);
            }
        }
    }

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

        switchInterFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("night", true);
                }
                editor.commit();

            }
        });
        //About
        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), About.class);
                startActivity(i);
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

    void OpenHistory() {
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }

    void anhxa(View view) {
        btInterface = view.findViewById(R.id.btInterface);
        tvAbout = view.findViewById(R.id.tvAbout);
        btHistory = view.findViewById(R.id.btHistory);
        tvTextSize = view.findViewById(R.id.tvTextSize);
        switchInterFace = view.findViewById(R.id.switchInterFace);
    }


}