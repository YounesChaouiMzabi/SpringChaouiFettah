package com.example.subscriptionmicrocervice.service;

import com.example.subscriptionmicrocervice.CourseClient;
import com.example.subscriptionmicrocervice.StudentClient;
import com.example.subscriptionmicrocervice.dto.SubscriptionDto;
import com.example.subscriptionmicrocervice.model.Subscription;
import com.example.subscriptionmicrocervice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private CourseClient courseClient;




    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Mono<Subscription> getSubscriptionById(Long subscriptionId) {
        return Mono.fromSupplier(() -> subscriptionRepository.findById(subscriptionId).orElse(null));
    }



    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }


    public Subscription createSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = new Subscription();
        //subscription.setStudentId(subscriptionDto.getStudentId()); // Récupérer l'ID de l'étudiant depuis SubscriptionDto et le définir dans la souscription
        subscription.setStudentId(subscriptionDto.getEtudiant().getId());
        //subscription.setCourseId(subscriptionDto.getCourseId()); // Récupérer l'ID du cours depuis SubscriptionDto et le définir dans la souscription
        subscription.setCourseId(subscriptionDto.getCours().getId()); // Récupérer l'ID du cours depuis SubscriptionDto et le définir dans la souscription
        return subscriptionRepository.save(subscription);
    }

    public List<SubscriptionDto> getAllInscriptions() {
        return subscriptionRepository.findAll().stream().map(inscription -> {
            SubscriptionDto fullInscriptionDTO = new SubscriptionDto();
            fullInscriptionDTO.setId(inscription.getId());
            //fullInscriptionDTO.setEtudiant(etudiantFeignClient.getEtudiantById(inscription.getEtudiantId()));
            fullInscriptionDTO.setEtudiant(studentClient.getStudentById(inscription.getStudentId()));
            //fullInscriptionDTO.setCours(coursFeignClient.getCoursById(inscription.getCoursId()));
            fullInscriptionDTO.setCours(courseClient.getCourseById(inscription.getCourseId()));
            return fullInscriptionDTO;
        }).collect(Collectors.toList());
    }



}
