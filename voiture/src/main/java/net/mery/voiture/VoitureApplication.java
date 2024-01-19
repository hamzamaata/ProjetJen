package net.mery.voiture;

import net.mery.voiture.entities.Car;
import net.mery.voiture.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
@EnableDiscoveryClient

@SpringBootApplication
public class VoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoitureApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(5000);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}
	@Bean
	CommandLineRunner inCommandLineRunner(CarRepository carRepository){
		return  args -> {
			carRepository.save(new Car(Long.parseLong("1"),"bmw","serie1","7shs",Long.parseLong("1")));
			carRepository.save(new Car(Long.parseLong("2"),"mercedes","class a","ujs",Long.parseLong("2")));
			carRepository.save(new Car(Long.parseLong("3"),"peugeout","hui","ssns9",Long.parseLong("3")));

		};

	}
}
