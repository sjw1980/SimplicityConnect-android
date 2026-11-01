package com.siliconlabs.bledemo.bluetooth.ble

enum class Property {
    BROADCAST,
    READ,
    WRITE,
    WRITE_WITHOUT_RESPONSE,
    RELIABLE_WRITE,
    NOTIFY,
    INDICATE,
    EXTENDED_PROPS;

    fun isWriteProperty(): Boolean {
        return this == WRITE || this == WRITE_WITHOUT_RESPONSE || this == RELIABLE_WRITE
    }

    enum class Type {
        AUTHENTICATED,
        BONDED,
        ENCRYPTED
    }
}