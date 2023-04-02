package com.api.probarber.dtos;

import com.api.probarber.models.BarberModel;
import com.api.probarber.models.ClientModel;
import com.api.probarber.models.ServiceModel;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AppointmentDto {

    private UUID clienteId;
    private UUID barberId;
    private LocalDateTime appoitmentDate;

    private List<ServiceModel> services;

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "clienteId=" + clienteId +
                ", barberId=" + barberId +
                ", appoitmentDate=" + appoitmentDate +
                ", services=" + services +
                '}';
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public UUID getBarberId() {
        return barberId;
    }

    public void setBarberId(UUID barberId) {
        this.barberId = barberId;
    }

    public LocalDateTime getAppoitmentDate() {
        return appoitmentDate;
    }

    public void setAppoitmentDate(LocalDateTime appoitmentDate) {
        this.appoitmentDate = appoitmentDate;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> service) {
        this.services = service;
    }
}
