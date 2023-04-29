package jdk.net;

public class SioKeepAlive {
    private final int keepAliveTime;
    private final int keepAliveInterval;

    public SioKeepAlive(int keepAliveTime, int keepAliveInterval) {
        this.keepAliveTime = keepAliveTime;
        this.keepAliveInterval = keepAliveInterval;
    }

    int getKeepAliveTime() {
        return keepAliveTime;
    }

    int getKeepAliveInterval() {
        return keepAliveInterval;
    }
}
