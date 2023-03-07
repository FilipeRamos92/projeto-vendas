package com.market.projetovendas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.projetovendas.exception.ResourceNotFoundException;
import com.market.projetovendas.model.Seller;
import com.market.projetovendas.repository.SellerRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class SellerController {
    
    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/sellers")
    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }

    @GetMapping("/sellers/{id}")
    public ResponseEntity<Seller> getEmployeeById(@PathVariable Long id) {
		Seller seller = sellerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not exist with id :" + id));
		return ResponseEntity.ok(seller);
	}

    @PostMapping("/sellers")
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerRepository.save(seller);
    }

    @PutMapping("/sellers/{id}")
	public ResponseEntity<Seller> updateEmployee(@PathVariable Long id, @RequestBody Seller sellerDetails){
		Seller seller = sellerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not exist with id :" + id));
		
        seller.setName(sellerDetails.getName());
        seller.setRegistration(sellerDetails.getRegistration());
		
		Seller updatedSeller = sellerRepository.save(seller);
		return ResponseEntity.ok(updatedSeller);
	}

    @DeleteMapping("/sellers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Seller seller = sellerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not exist with id :" + id));
		
        sellerRepository.delete(seller);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
    
}
