package com.mycompany.ticket;

public enum TicketStatus {
    Pending (1,"Đang chờ xác nhận"),
    Confirmed (2, "Đã xác nhận"),
    Cancelled (3, "Đã Hủy");

    private final int statusCode;
    private final String statusName;

    TicketStatus(int statusCode, String statusName) {
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public static TicketStatus fromStatusCode(int statusCode) {
        for (TicketStatus status : values()) {
            if (status.getStatusCode() == statusCode) {
                return status;
            }
        }
        return null;
    }
}
