package mum.swe.CRMSSpringApp.controller.api;

import mum.swe.CRMSSpringApp.model.Payment;
import mum.swe.CRMSSpringApp.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PaymentRestAPIController {
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Payment> getAllPayments() {
        return paymentService.findAll();
    }

    @PostMapping(value = "/payments")
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.save(payment);
    }

    @GetMapping(value = "/payments/{id}")
    public @ResponseBody
    Optional<Payment> findPaymentById(@PathVariable Long id) {
    	Optional<Payment> payment = paymentService.findById(id);
        return payment;
    }

    @DeleteMapping(value = "/payments/{id}")
    public @ResponseBody
    boolean deletePayment(@PathVariable Long id) {
        paymentService.delete(id);
        return true;
    }
    
	@PutMapping(value = "/payments")
	public boolean updatePayment(@RequestBody Payment payment) {
		paymentService.save(payment);
		return true;
	}
}
