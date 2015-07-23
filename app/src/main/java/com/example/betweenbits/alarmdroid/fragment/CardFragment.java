package com.example.betweenbits.alarmdroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betweenbits.alarmdroid.MainActivity;
import com.example.betweenbits.alarmdroid.R;
import com.example.betweenbits.alarmdroid.adapter.CardAdapter;
import com.example.betweenbits.alarmdroid.domain.Card;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by brunov0id on 21/07/15.
 */
public class CardFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    private RecyclerView recyclerView;
    private List<Card> listCard;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        listCard = ((MainActivity) getActivity()).getCards();

        CardAdapter cardAdapter = new CardAdapter(getActivity(), listCard);
        recyclerView.setAdapter(cardAdapter);

//        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.normal_plus);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar now = Calendar.getInstance();
//                TimePickerDialog dpd = TimePickerDialog.newInstance(
//                        (TimePickerDialog.OnTimeSetListener) getActivity(),
//                        now.get(Calendar.HOUR),
//                        now.get(Calendar.MINUTE), true);
//                dpd.show(getActivity().getFragmentManager(), "TimePickerDialog");
//            }
//        });

        return view;
    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int hourOfDay, int minute) {

//        mListView.add(card);
    }

}
