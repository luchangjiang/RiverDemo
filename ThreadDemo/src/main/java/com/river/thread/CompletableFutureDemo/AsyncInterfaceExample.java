package com.river.thread.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;

public interface AsyncInterfaceExample {
    String computeSomeThing();

    CompletableFuture<String> computeSomeThingAsync();
}
