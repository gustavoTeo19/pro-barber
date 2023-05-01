package com.api.probarber.dtos;

import com.api.probarber.models.BarberModel;
import com.api.probarber.models.ClientModel;
import com.api.probarber.models.ServiceModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
public class AppointmentDto {

    private UUID clienteId;
    private UUID barberId;
    private LocalDateTime appoitmentDate;

    private List<UUID> services;

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "clienteId=" + clienteId +
                ", barberId=" + barberId +
                ", appoitmentDate=" + appoitmentDate +
                ", services=" + services +
                '}';
    }
}
