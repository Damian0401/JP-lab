package com.park.common.interfaces;

import java.io.IOException;

public interface ISender {
    String send(String host, int port, String message) throws IOException;
    String send(String host, int port, String message, boolean isResponse) throws IOException;
}
