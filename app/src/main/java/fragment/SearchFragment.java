package fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kdttoeic.Data.KDTToeicDB;
import com.example.kdttoeic.R;
import com.example.kdttoeic.SearchActivity;
import com.example.kdttoeic.WordDetailActivitty;
import com.example.kdttoeic.adapter.WordAdapter;
import com.example.kdttoeic.model.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements WordAdapter.Listener {

    RecyclerView rvSearch;
    ArrayList<Word> arrayList;
    WordAdapter wordAdapter;
    KDTToeicDB kdtToeicDB;

    String filename = "config";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int typeSort;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSearch = view.findViewById(R.id.rvWords);
        arrayList = new ArrayList<>();
        kdtToeicDB = new KDTToeicDB(getContext());

        arrayList = kdtToeicDB.getVocab();

        wordAdapter = new WordAdapter(arrayList, this);
        rvSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSearch.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvSearch.setAdapter(wordAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnSort) {
            if (typeSort == 1) {
                typeSort = 2;
                Collections.sort(arrayList, new Comparator<Word>() {
                    @Override
                    public int compare(Word o1, Word o2) {
                        return o1.compareTo(o2);
                    }
                });
            } else {
                typeSort = 1;
                Collections.sort(arrayList, new Comparator<Word>() {
                    @Override
                    public int compare(Word o1, Word o2) {
                        return o2.compareTo(o1);
                    }
                });
            }
            wordAdapter.notifyDataSetChanged();
            kdtToeicDB.deleteVocab();
            kdtToeicDB.SortVocab(arrayList);
        }
        if (item.getItemId() == R.id.mnSearch) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnWordClick(Word word) {
        Intent iOpenDetailWord = new Intent(getActivity(), WordDetailActivitty.class);
        iOpenDetailWord.putExtra("word", word);
        startActivity(iOpenDetailWord);
    }
}