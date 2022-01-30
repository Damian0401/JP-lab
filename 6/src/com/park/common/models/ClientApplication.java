package com.park.common.models;

import java.util.Date;
import java.util.Objects;

public class ClientApplication {
    private final int port;
    private final Date connectedAt;

    public ClientApplication(int port, Date connectedAt) {
        this.port = port;
        this.connectedAt = connectedAt;
    }

    public int getPort() {
        return port;
    }

    public Date getConnectedAt() {
        return connectedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientApplication)) return false;
        ClientApplication that = (ClientApplication) o;
        return getPort() == that.getPort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPort());
    }
}
