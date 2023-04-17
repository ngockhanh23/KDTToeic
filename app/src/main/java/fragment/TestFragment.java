package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kdttoeic.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kdttoeic.MainActivity;
import com.example.kdttoeic.R;
import com.example.kdttoeic.TestDesPageActivity;
import com.example.kdttoeic.adapter.TestAdapter;
import com.example.kdttoeic.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends Fragment implements TestAdapter.Listener {

    private RecyclerView rcvTest;
    private ArrayList<Test> myTest;
    private TestAdapter testAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvTest = view.findViewById(R.id.rcv_test);
        myTest = new ArrayList<>();
        testAdapter = new TestAdapter(myTest, this);
        rcvTest.setAdapter(testAdapter);

        Test test1 = new Test("De thi 1", "200 cau","300 - 500");
        Test test2 = new Test("De thi 2", "200 cau", "500 - 700");
        Test test3 = new Test("De thi 3", "200 cau","700 - 900");

        myTest.add(test1);
        myTest.add(test2);
        myTest.add(test3);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rcvTest.setLayoutManager(linearLayoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_test, container, false);

        return view;
    }

    @Override
    public void OnOpenExam(String mucde) {
        Intent intent = new Intent(getActivity(), TestDesPageActivity.class);
        intent.putExtra("mucde", mucde);
        startActivity(intent);
    }
}
