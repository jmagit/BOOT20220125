package com.example.application.dtos;

import java.math.BigDecimal;
import java.util.Date;

import com.example.domains.entities.Payment;
import com.example.domains.entities.Staff;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Value;

@Value
@JsonPropertyOrder(value = { "idPago", "fecha", "importe", "empleado" })
public class PagoEditDTO {
	int idPago;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date fecha;
	BigDecimal importe;
	int idEmpleado;
	public static PagoEditDTO from(Payment source) {
		return new PagoEditDTO(
				source.getPaymentId(),
				source.getPaymentDate(),
				source.getAmount(),
				source.getStaff().getStaffId()
				);		
	}

	public static Payment from(PagoEditDTO source) {
		return new Payment(
				source.getIdPago(),
				source.getImporte(),
				source.getFecha(),
				new Staff(source.getIdEmpleado())
				);
	}
}
