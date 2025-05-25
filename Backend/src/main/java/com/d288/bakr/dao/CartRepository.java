package com.d288.bakr.dao;

import com.d288.bakr.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.stereotype.Repository;

@CrossOrigin("http://localhost:4200" )
public interface CartRepository extends JpaRepository<Cart, Long> {
}
