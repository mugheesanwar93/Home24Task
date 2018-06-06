package de.home24.home24task.networks;

public interface RequestResponseListener<T> {
    void onResult(Integer response, T object);
}


