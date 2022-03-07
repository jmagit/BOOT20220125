package com.example.application.dtos;

import java.util.Date;
import java.util.List;

import com.example.domains.entities.Customer;
import com.example.domains.entities.Inventory;
import com.example.domains.entities.Rental;
import com.example.domains.entities.Staff;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Value;

@Value
@JsonPropertyOrder(value = { "id", "fechaAlquiler", "idInventario", "idCliente", "idEmpleado", "fechaDevolucion" })
public class AlquilerEditDTO {
	int id;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date fechaAlquiler;
	int idInventario;
	int idCliente;
	int idEmpleado;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date fechaDevolucion;
	private List<PagoEditDTO> pagos;

	public static AlquilerEditDTO from(Rental source) {
		return new AlquilerEditDTO(
				source.getRentalId(), 
				source.getRentalDate(), 
				source.getInventory().getInventoryId(),
				source.getCustomer().getCustomerId(), 
				source.getStaff().getStaffId(), 
				source.getReturnDate(),
				source.getPayments().stream().map(item -> PagoEditDTO.from(item)).toList()
				);
	}

	public static Rental from(AlquilerEditDTO source) {
		return new Rental(
				source.getId(), 
				source.getFechaAlquiler(), 
				new Inventory(source.getIdInventario()),
				new Customer(source.getIdCliente()), 
				new Staff(source.getIdEmpleado()), 
				source.getFechaDevolucion()
				);
	}

	public Rental update(Rental target) {
		actualizaPropiedadesEntidad(target);
		borrarPagosSobrantes(target);
		actualizarPagosCambiados(target);
		incorporarNuevosPagos(target);
		return target;
	}

	private void actualizaPropiedadesEntidad(Rental target) {
		target.setRentalDate(fechaAlquiler);
		if (target.getCustomer().getCustomerId() != idCliente)
			target.setCustomer(new Customer(idCliente));
		if (target.getInventory().getInventoryId() != idInventario)
			target.setInventory(new Inventory(idInventario));
		if (target.getStaff().getStaffId() != idEmpleado)
			target.setStaff(new Staff(idEmpleado));
		target.setReturnDate(fechaDevolucion);
	}

	private void borrarPagosSobrantes(Rental target) {
		// Borramos los que no estan en el DTO
		target.getPayments().stream()
				.filter(entity -> pagos.stream().noneMatch(dto -> entity.getPaymentId() == dto.getIdPago())).toList()
				.forEach(item -> target.removePayment(item));
	}

	private void actualizarPagosCambiados(Rental target) {
		// Actualizamos con el DTO la entidad
		target.getPayments().forEach(entity -> {
			var dto = pagos.stream().filter(item -> item.getIdPago() == entity.getPaymentId()).findFirst().get();
			if (entity.getPaymentDate() != dto.getFecha())
				entity.setPaymentDate(dto.getFecha());
			if (entity.getAmount() != dto.getImporte())
				entity.setAmount(dto.getImporte());
			if (entity.getStaff().getStaffId() != dto.getIdEmpleado())
				entity.setStaff(new Staff(dto.getIdEmpleado()));
		});
	}

	private void incorporarNuevosPagos(Rental target) {
		// Añadimos los nuevos del DTO a la entidad
		pagos.stream().filter(
				dto -> target.getPayments().stream().noneMatch(entity -> entity.getPaymentId() == dto.getIdPago()))
				.forEach(dto -> target.addPayment(PagoEditDTO.from(dto)));
	}

}
