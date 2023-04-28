/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package jdk.net;

import java.net.SocketException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import jdk.net.ExtendedSocketOptions.PlatformSocketOptions;


@SuppressWarnings("removal")
class WindowsSocketOptions extends PlatformSocketOptions {

    private static int DEFAULT_KEEPALIVETIME = 7200000;
    private static int DEFAULT_KEEPALIVEINTERVAL = 1000;

    private int keepAliveTime;
    private int keepAliveInterval;

    public WindowsSocketOptions() {
    }

    @Override
    boolean sioKeepAliveOptionsSupported() {
        // https://learn.microsoft.com/en-us/windows/win32/winsock/so-keepalive
        // On Windows Vista and later, the number of keep-alive probes (data retransmissions) is set to 10 and cannot be changed.
        // Only keepAliveTime and keepAliveInterval are supported
        return true;
    }

    @Override
    boolean ipDontFragmentSupported() {
        return true;
    }

    @Override
    void setIpDontFragment(int fd, final boolean value, boolean isIPv6) throws SocketException {
        setIpDontFragment0(fd, value, isIPv6);
    }

    @Override
    boolean getIpDontFragment(int fd, boolean isIPv6) throws SocketException {
        return getIpDontFragment0(fd, isIPv6);
    }

    @Override
    void setSioKeepAliveValues(int fd, final int keepAliveTime, int keepAliveInterval) {
        setTcpKeepAlive(fd, keepAliveTime, keepAliveInterval);
    }

    private static native void setIpDontFragment0(int fd, boolean value, boolean isIPv6) throws SocketException;
    private static native boolean getIpDontFragment0(int fd, boolean isIPv6) throws SocketException;
    private static native void setTcpKeepAlive(int fd, int keepAliveTime, int keepAliveIntvl) throws SocketException;

    static {
        if (System.getSecurityManager() == null) {
            System.loadLibrary("extnet");
        } else {
            AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                System.loadLibrary("extnet");
                return null;
            });
        }
    }
}
