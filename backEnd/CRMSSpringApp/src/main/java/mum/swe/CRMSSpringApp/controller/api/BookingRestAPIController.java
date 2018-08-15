package mum.swe.CRMSSpringApp.controller.api;

import mum.swe.CRMSSpringApp.model.Booking;
import mum.swe.CRMSSpringApp.service.BookingService;
import mum.swe.CRMSSpringApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookingRestAPIController {
    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @PostMapping(value = "/bookings")
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @GetMapping(value = "/bookings/{id}")
    @ResponseBody
    public Optional<Booking> findBookingById(@PathVariable Long id) {
    	return bookingService.findById(id);
    }

    @DeleteMapping(value = "/bookings/{id}")
    public @ResponseBody
    boolean deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return true;
    }
    
	@PutMapping(value = "/bookings")
	public boolean updateBooking(@RequestBody Booking booking) {
		bookingService.save(booking);
		return true;
	}
}
