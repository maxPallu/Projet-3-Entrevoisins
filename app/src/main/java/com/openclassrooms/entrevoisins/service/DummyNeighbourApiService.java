package com.openclassrooms.entrevoisins.service;

import android.content.Context;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favoriteNeighbour = new ArrayList<>();
        for(int i =0; i<neighbours.size(); i++) {
            if(neighbours.get(i).getIsFavorite() == 1) {
                favoriteNeighbour.add(neighbours.get(i));
            }
        }
        return favoriteNeighbour;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        neighbour.setIsFavorite(0);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void addNeighbourFavorite(Neighbour neighbour) {
        neighbour.setIsFavorite(1);
    }

    @Override
    public void deleteFavorite(Neighbour neighbour) {
        neighbour.setIsFavorite(0);
    }
}
