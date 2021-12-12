package io.imhungryhi.tddmock;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.imhungryhi.tddmock.helper.error.BookingNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;

import io.imhungryhi.tddmock.controller.BookingController;
import io.imhungryhi.tddmock.controller.RoomController;
import io.imhungryhi.tddmock.dao.interfaces.BookingService;
import io.imhungryhi.tddmock.dao.interfaces.CustomerService;
import io.imhungryhi.tddmock.dao.interfaces.RoomService;
import io.imhungryhi.tddmock.model.Booking;
import io.imhungryhi.tddmock.model.Customer;
import io.imhungryhi.tddmock.model.Hotel;
import io.imhungryhi.tddmock.model.Room;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {BookingController.class, RoomController.class})
class BookingControllerTests {
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RoomService roomService;

    @MockBean
    BookingService bookingService;

    @MockBean
    CustomerService customerService;

    @Test
    void sanityCheck() {
        // Nope
    }

    @Test
    void shouldGetBookings() throws Exception {
        given(bookingService.getAll())
                .willReturn(new ArrayList<Booking>());

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void shouldGetBookingDetails() throws Exception {
        Hotel hotelMeyer = new Hotel(1, "Hotel Meyer",
                "Grand-Rue", "120", null,
                "6310", "Beaufort", "Luxembourg");
        Booking bookingResult = new Booking(1,
                dateFormat.parse("1/1/2022"), dateFormat.parse("8/1/2022"),
                2,
                customerService.findById(1),
                roomService.findById(1));
        given(roomService.findById(Mockito.anyInt()))
                .willReturn(
                        new Room(1, 0, 1, 55, hotelMeyer));
        given(customerService.findById(Mockito.anyInt()))
                .willReturn(new Customer(1, "John", "Doe",
                        "Koekoekstraat", "70", null,
                        "9090", "Melle", "Belgium"));
        given(bookingService.findById(Mockito.anyInt()))
                .willReturn(bookingResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("id").value("1"));
    }

    @Test
    void shouldThrowError_OnBookingQuery() throws Exception {
        given(bookingService.findById(Mockito.anyInt()))
                .willThrow(new BookingNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetRoomDetails() throws Exception {
        Hotel hotelMeyer = new Hotel(1, "Hotel Meyer",
                "Grand-Rue", "120", null,
                "6310", "Beaufort", "Luxembourg");
        given(roomService.findById(Mockito.anyInt()))
                        .willReturn(
                            new Room(1, 0, 1, 55, hotelMeyer));

        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("numberOfSingleBeds").value(0))
                .andExpect(jsonPath("numberOfDoubleBeds").value(1))
                .andExpect(jsonPath("price").value(55));
    }
}
