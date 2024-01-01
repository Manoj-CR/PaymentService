package com.example.paymentservice.repository;

import com.example.paymentservice.models.Payment;
import com.example.paymentservice.models.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface  PaymentRepository extends JpaRepository<Payment, Long> {

    Payment save(Payment payment);
    Optional<Payment> findPaymentByPaymentReferenceId(String paymentRefId);

    @Query("select c from Payment c where c.paymentStatus !='SUCCESS'")
    List<Payment> findAllPayment();
}
