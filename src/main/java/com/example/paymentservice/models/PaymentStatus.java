package com.example.paymentservice.models;

import jakarta.persistence.Entity;


public enum PaymentStatus {
    SUCCESS,
    FAILURE,
    PROCESSING,
    LINK_CREATED,
    ACTION_REQUIRED,
    CREATED,
    CANCELLED

}
