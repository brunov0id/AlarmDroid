package com.example.betweenbits.alarmdroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betweenbits.alarmdroid.R;
import com.example.betweenbits.alarmdroid.adapter.CardAdapter;
import com.example.betweenbits.alarmdroid.domain.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunov0id on 21/07/15.
 */
public class CardFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Card> listCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        listCard = new ArrayList<Card>();

        CardAdapter cardAdapter = new CardAdapter(getActivity(), listCard);
        recyclerView.setAdapter(cardAdapter);

        return view;
    }

}
