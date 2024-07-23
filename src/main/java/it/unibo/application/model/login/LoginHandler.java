package it.unibo.application.model.login;

public class LoginHandler {
    private Boolean isConnected;
    private String username;
    private String password;
    private Thread connectedThread;
    private volatile boolean running;

    public LoginHandler() {
        this.isConnected = false;
        this.username = "";
        this.password = "";
    }
    public Boolean getIsConnected() {
        return isConnected;
    }
    public void setIsConnected(Boolean isConnected) {
        this.isConnected = isConnected;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean performLogin(String username, char[] password) {
        Boolean success = true; //login check goes here
        if (success) {
            this.isConnected = true;
            beginConnectionCheckRoutine();
            return true;
        } else {
            this.isConnected = false;
            return false;
        }
    }

    private void beginConnectionCheckRoutine() {
        if (running) {
            throw new IllegalStateException();
        }
        running = true;
        connectedThread = new Thread(() -> {
            while (running) {
                try {
                    System.out.println("Checking connection");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //TODO
                }
            }
            this.running = false;
            this.isConnected = false;
        });
        System.out.println("LoginHandler thread started");
        connectedThread.start();
    }

    public void stopConnectionCheckRoutine() {
        running = false;
        isConnected = false;
        System.out.println("LoginHandler thread stopped");
    }
}
