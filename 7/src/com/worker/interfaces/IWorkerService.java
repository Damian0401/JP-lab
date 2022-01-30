package com.worker.interfaces;

public interface IWorkerService {
    void addListener(IWorkerListener workerListener);
    boolean handleDisconnect();
    boolean handleConnection(String name, String portAsString);
}
