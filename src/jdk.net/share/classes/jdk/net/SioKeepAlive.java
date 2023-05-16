package jdk.net;

/**
 * Represents the Keep-Alive idle time and Keep-Alive retransmission
 * interval time for Windows platforms.
 *
 * @since 21
 */
public class SioKeepAlive {
    private final int keepAliveTime;
    private final int keepAliveInterval;

    /**
     * Creates a SioKeepAlive object.
     *
     * @param keepAliveTime the number seconds before keep-alive initiates a probe
     * @param keepAliveInterval the number of seconds to wait before retransmitting a keep-alive probe
     */
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
