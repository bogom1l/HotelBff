package com.tinqinacademy.hotelbff.api.restroutes;

public class RestApiRoutes {
    public static final String API = "/api/v1";
    public static final String API_HOTEL = API + "/hotel";
    public static final String API_SYSTEM = API + "/system";
    public static final String API_AUTH = API + "/auth";

    public static final String CHECK_ROOM_AVAILABILITY = API_HOTEL + "/rooms";
    public static final String GET_ROOM_INFO = API_HOTEL + "/{roomId}";
    public static final String BOOK_ROOM = API_HOTEL + "/{roomId}";
    public static final String UNBOOK_ROOM = API_HOTEL + "/{bookingId}";
    public static final String UPDATE_PARTIALLY_BOOKING = API_HOTEL + "/{bookingId}";
    public static final String GET_BOOKING_HISTORY = API_HOTEL + "/history/{userId}";

    public static final String REGISTER_GUEST = API_SYSTEM + "/register";
    public static final String GET_REPORT = API_SYSTEM + "/register";
    public static final String CREATE_ROOM = API_SYSTEM + "/room";
    public static final String UPDATE_ROOM = API_SYSTEM + "/room/{roomId}";
    public static final String UPDATE_PARTIALLY_ROOM = API_SYSTEM + "/room/{roomId}";
    public static final String DELETE_ROOM = API_SYSTEM + "/room/{roomId}";

    public final static String AUTH_CHECK_JWT = API_AUTH + "/validate-jwt";
}
