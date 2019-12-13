package com.river.powermock;

public interface MockService {
    int count(MockModel model);

    MockModel getModel(String name);

    boolean makeFile(String path);
}
