package com.example.subscriptionmicrocervice.repository;

import com.example.subscriptionmicrocervice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

}
