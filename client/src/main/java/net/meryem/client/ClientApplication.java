package net.meryem.client;

import net.meryem.client.entities.Client;
import net.meryem.client.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;  // Ajoutez cette ligne d'importation
@EnableDiscoveryClient

@SpringBootApplication
 public class ClientApplication {

         public static void main(String[] args) {
         SpringApplication.run(ClientApplication.class, args);
         }

         @Bean
         CommandLineRunner initializeDatabase(ClientRepository  clientRepository) {
         return args -> {
             clientRepository.save(new Client(Long.parseLong("1"), "Amine SAFI", Float.parseFloat("23")));
             clientRepository.save(new Client(Long.parseLong("2"), "Amal ALAOUI", Float.parseFloat("22")));
             clientRepository.save(new Client(Long.parseLong("3"), "Samir RAMI", Float.parseFloat("22")));
                     };
         }
}
