package com.park.common.interfaces;

import java.io.IOException;

public interface IReceiver extends Runnable{
    void finish() throws IOException;
}
