package it.unibo.application.data.entities.compatibility;

public class Socket {
    private String socketName;

    public Socket(final String socketName) {
        this.socketName = socketName;
    }

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(final String socketName) {
        this.socketName = socketName;
    }
}
