package jsilgado.spring.openbootcampspringej789.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jsilgado.spring.openbootcampspringej789.domain.Laptop;
import jsilgado.spring.openbootcampspringej789.repository.LaptopRepository;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    public Laptop findById(Long id) {
        return laptopRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró el laptop con ID: " + id));
    }

    public Laptop create(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public Laptop update(Long id, Laptop laptop) {
        Laptop existingLaptop = findById(id);
        existingLaptop.setBrand(laptop.getBrand());
        existingLaptop.setModel(laptop.getModel());
        existingLaptop.setPrice(laptop.getPrice());
        return laptopRepository.save(existingLaptop);
    }

    public void delete(Long id) {
        laptopRepository.delete(findById(id));
    }
    
    public void deleteAll() {
    	laptopRepository.findAll().stream().forEach(x -> this.delete(x.getId()));	
    }
}

