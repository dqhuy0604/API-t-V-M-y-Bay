package com.mycompany.booking;

public enum BookingStatus {
    Pending (1,"Đang chờ xác nhận"),
    Confirmed (2, "Đã xác nhận"),
    Cancelled (3, "Đã Hủy");

    private final int statusCode;
    private final String statusName;

    BookingStatus(int statusCode, String statusName) {
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public static BookingStatus fromStatusCode(int statusCode) {
        for (BookingStatus status : values()) {
            if (status.getStatusCode() == statusCode) {
                return status;
            }
        }
        return null;
    }
}
