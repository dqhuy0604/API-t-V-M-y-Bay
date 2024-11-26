package com.mycompany.flight;

public enum FlightStatus {
    PLANNING(1, "Đang lên kế hoạch"),
    SELLING_TICKETS(2, "Đang bán vé"),
    TICKET_SOLD_OUT(3, "Đã ngưng bán vé"),
    PREPARING_TO_DEPART(4, "Chuẩn bị cất cánh"),
    IN_FLIGHT(5, "Đang bay"),
    LANDED(6, "Đã hạ cánh"),
    COMPLETED(7, "Hoàn tất");

    private final int statusCode;
    private final String statusName;

    FlightStatus(int statusCode, String statusName) {
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public static FlightStatus fromStatusCode(int statusCode) {
        for (FlightStatus status : values()) {
            if (status.getStatusCode() == statusCode) {
                return status;
            }
        }
        return null;
    }
    public static String getNameByCode(int code) {
        for (FlightStatus status : values()) {
            if (status.getStatusCode() == code) {
                return status.getStatusName();
            }
        }
        return "Không xác định";
    }

}
