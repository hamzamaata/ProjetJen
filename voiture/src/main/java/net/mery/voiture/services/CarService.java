package net.mery.voiture.services;
import net.mery.voiture.entities.Car;
import net.mery.voiture.entities.Client;
import net.mery.voiture.models.CarResponse;
import net.mery.voiture.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8888/SERVICE-CLIENT";

    public List<CarResponse> findAll() {
        List<Car> cars = carRepository.findAll();
        ResponseEntity<Client[]> response =
                restTemplate.getForEntity(this.URL + "/api/client", Client[].class);
        Client[] clients = response.getBody();
        return  cars.stream().map((Car car)-> mapToCarResponse(car,clients)).toList();
    }
    private CarResponse mapToCarResponse(Car car , Client[] clients){
        Client foundClient = Arrays.stream(clients)
                .filter(client -> client.getId().equals(car.getClient_id()))
                .findFirst()
                .orElse(null);
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(foundClient)
                .matricule(car.getMatricule())
                .model(car.getModel())
                .build();

    }
    public CarResponse findById(Long id) throws  Exception{
        Car car = carRepository.findById(id).orElseThrow(()->
                new Exception(("Invalide car id!")));
        Client client = restTemplate.getForObject(this.URL+"/api/client/"+car.getClient_id(), Client.class);
        return CarResponse.builder()
                .id((Long)car.getId())
                .brand(car.getBrand())
                .client(client)
                .matricule(car.getMatricule())
                .model(car.getModel())
                .build();
    }

}