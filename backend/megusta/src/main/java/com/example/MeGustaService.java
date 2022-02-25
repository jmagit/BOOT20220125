package com.example;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/me-gusta")
public class MeGustaService {
	public final String ME_GUSTA_CONT="megusta";
	@Autowired
	private StringRedisTemplate template;
	private ValueOperations<String, String> redisValue; 
	
	@PostConstruct
	private void inicializa() {
		redisValue = template.opsForValue();
		if(redisValue.get(ME_GUSTA_CONT) == null)
			redisValue.set(ME_GUSTA_CONT, "0");
	}
	
	@GetMapping
	private String get() {
		return "Llevas " + redisValue.get(ME_GUSTA_CONT);
	}
	
	@PostMapping
	private String add() {
		return "Llevas " + redisValue.increment(ME_GUSTA_CONT);
	}
	
	@PutMapping("/{id}")
	private String add(@PathVariable int id) {
		long r = 0;
		Date ini = new Date();
		for(int i= 0; i++ < id*1000; r = redisValue.increment(ME_GUSTA_CONT));
		return "Llevas " + r + ". Tardo " + ((new Date()).getTime() - ini.getTime()) + " ms.";
	}	

}
