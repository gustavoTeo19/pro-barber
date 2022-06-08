package com.api.probarber.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_APPOINTMENT")
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel cliente;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private BarberModel barber;

    @ManyToMany
    @JoinColumn(name = "service_id")
    private List<ServiceModel> service;

    @Temporal(TemporalType.DATE)
    private Calendar appoitmentDate;

    @Temporal(TemporalType.TIME)
    private

    @Column(name = "total")
    private Double total;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClientModel getCliente() {
        return cliente;
    }

    public void setCliente(ClientModel cliente) {
        this.cliente = cliente;
    }

    public BarberModel getBarber() {
        return barber;
    }

    public void setBarber(BarberModel barber) {
        this.barber = barber;
    }

    public List<ServiceModel> getService() {
        return service;
    }

    public void setService(List<ServiceModel> service) {
        this.service = service;
    }
}
