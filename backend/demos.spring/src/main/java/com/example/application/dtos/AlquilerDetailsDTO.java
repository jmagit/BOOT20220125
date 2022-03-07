package com.example.application.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public interface AlquilerDetailsDTO extends AlquilerShortDTO {
	@JsonPropertyOrder(value = {"idPago","fecha","importe","empleado"})
	interface Payment {
		@JsonProperty("idPago")
		int getPaymentId();
		@JsonProperty("fecha")
		@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
		Date getPaymentDate();
		@JsonProperty("importe")
		BigDecimal getAmount();		
		@Value("#{target.staff.lastName + ', ' + target.staff.firstName}")
		String getEmpleado();
	}
	@JsonProperty("pagos")
	List<Payment> getPayments();
}
