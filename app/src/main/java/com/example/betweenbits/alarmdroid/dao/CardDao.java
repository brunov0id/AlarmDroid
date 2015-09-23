package com.example.betweenbits.alarmdroid.dao;

import com.example.betweenbits.alarmdroid.domain.Card;

import java.util.List;

/**
 * Created by brunov0id on 27/07/15.
 */
public interface CardDao {
    void insert(Card card);
    void update(Card card);
    void delete(Card card);
    List<Card> getListOfCard();
}
