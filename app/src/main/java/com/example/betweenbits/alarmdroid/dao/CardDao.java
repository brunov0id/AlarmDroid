package com.example.betweenbits.alarmdroid.dao;

import com.example.betweenbits.alarmdroid.domain.Card;

/**
 * Created by brunov0id on 27/07/15.
 */
public interface CardDao {
    public void insertCard(Card card);
    public void updateCard(Card card);
    public void deleteCard(Card card);
}
