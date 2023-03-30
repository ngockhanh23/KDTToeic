package fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kdttoeic.NoteActivity;
import com.example.kdttoeic.PracFillSentenceActivity;
import com.example.kdttoeic.PracticeDesPageActivity;
import com.example.kdttoeic.R;
import com.example.kdttoeic.RegisterActivity;
import com.example.kdttoeic.VocabCatActivity;


public class HomeFragment extends Fragment {

    LinearLayout btImageDes, btAskAndAnswer, btConversation, btFillSentence,btFillParagraph, btReadAndUndersand, btTakeNote, btTest,btVocabulary;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //ánh xạ
        btImageDes = view.findViewById(R.id.btImageDes);
        btAskAndAnswer = view.findViewById(R.id.btAskAndAnswer);
        btConversation = view.findViewById(R.id.btConversation);
        btFillSentence = view.findViewById(R.id.btFillSentence);
        btFillParagraph = view.findViewById(R.id.btFillParagraph);
        btReadAndUndersand = view.findViewById(R.id.btReadAndUndersand);
        btTakeNote = view.findViewById(R.id.btTakeNote);
        btTest = view.findViewById(R.id.btTest);
        btVocabulary = view.findViewById(R.id.btVocabulary);


        btImageDes.setOnClickListener(onclickOption());
        btAskAndAnswer.setOnClickListener(onclickOption());
        btConversation.setOnClickListener(onclickOption());
        btFillSentence.setOnClickListener(onclickOption());
        btFillParagraph.setOnClickListener(onclickOption());
        btReadAndUndersand.setOnClickListener(onclickOption());
        btTakeNote.setOnClickListener(onclickOption());
        btVocabulary.setOnClickListener(onclickOption());
        btTest.setOnClickListener(onclickOption());
        return view;
    }
    private View.OnClickListener onclickOption(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btImageDes:
                        openImageDes();
                        break;
                    case R.id.btAskAndAnswer:
                        openAskAndAnswer();
                        break;
                    case R.id.btConversation:
                        openConversation();
                        break;
                    case R.id.btFillSentence:
                        openFillSentence();
                        break;
                    case R.id.btFillParagraph:
                        openFillSentence();
                        break;
                    case R.id.btReadAndUndersand:
                        openReadAndUndersand();
                        break;
                    case R.id.btTakeNote:
                        Intent i = new Intent(getActivity(), NoteActivity.class);
                        startActivity(i);
                        break;
                    case R.id.btTest:
                        openTestFragment();
                    case R.id.btVocabulary:
                        Intent i1 = new Intent(getActivity(), VocabCatActivity.class);
                        startActivity(i1);
                        break;
                }
            }
        };
    }

    private void openImageDes(){
        Intent intent = new Intent(getActivity(),PracticeDesPageActivity.class);
        startActivity(intent);
    }
    private void openAskAndAnswer(){
        Intent intent = new Intent(getActivity(),PracticeDesPageActivity.class);
        startActivity(intent);
    }
    private void openConversation(){
        Intent intent = new Intent(getActivity(),PracticeDesPageActivity.class);
        startActivity(intent);
    }
    private void openFillSentence(){
        Intent intent = new Intent(getActivity(), PracFillSentenceActivity.class);
        startActivity(intent);
    }
    private void openFillParagraph(){
        Intent intent = new Intent(getActivity(),PracticeDesPageActivity.class);
        startActivity(intent);
    }
    private void openReadAndUndersand(){
        Intent intent = new Intent(getActivity(),PracticeDesPageActivity.class);
        startActivity(intent);
    }

    private void openTestFragment(){
        Intent intent = new Intent(getActivity(), TestFragment.class);
        startActivity(intent);
        
    }




}