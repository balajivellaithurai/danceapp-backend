package com.example.danceins.service;

import com.example.danceins.model.Payment;
import com.example.danceins.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment updatePayment(Long id, Payment details) {
        return paymentRepository.findById(id).map(p -> {
            p.setBooking(details.getBooking());
            p.setAmount(details.getAmount());
            p.setPaymentDate(details.getPaymentDate());
            p.setPaymentMethod(details.getPaymentMethod());
            p.setStatus(details.getStatus());
            return paymentRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
