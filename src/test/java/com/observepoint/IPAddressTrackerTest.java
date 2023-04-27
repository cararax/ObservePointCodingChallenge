package com.observepoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IPAddressTrackerTest {

    private IPAddressTracker ipAddressTracker;

    @BeforeEach
    void setUp() {
        ipAddressTracker = new IPAddressTracker();
    }

    @Test
    void requestHandled_withNewIp_shouldIncrementCount() {
        String ipAddress = "192.168.1.1";
        ipAddressTracker.requestHandled(ipAddress);

        int expectedCount = 1;
        int actualCount = ipAddressTracker.top100().get(0).getValue();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void requestHandled_withExistingIp_shouldIncrementCount() {
        String ipAddress = "192.168.1.1";
        ipAddressTracker.requestHandled(ipAddress);
        ipAddressTracker.requestHandled(ipAddress);

        int expectedCount = 2;
        int actualCount = ipAddressTracker.top100().get(0).getValue();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void top100_withLessThan100Ips_shouldReturnAllIps() {
        String ipAddress1 = "192.168.1.1";
        String ipAddress2 = "192.168.1.2";
        String ipAddress3 = "192.168.1.3";

        ipAddressTracker.requestHandled(ipAddress1);
        ipAddressTracker.requestHandled(ipAddress2);
        ipAddressTracker.requestHandled(ipAddress3);

        int expectedSize = 3;
        int actualSize = ipAddressTracker.top100().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void clear_withIps_shouldClearAllData() {
        String ipAddress = "192.168.1.1";
        ipAddressTracker.requestHandled(ipAddress);

        ipAddressTracker.clear();

        int expectedSize = 0;
        int actualSize = ipAddressTracker.top100().size();

        assertEquals(expectedSize, actualSize);
    }
}
