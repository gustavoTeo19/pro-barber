package com.api.probarber.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_LOYALTY_PLAN")
public class LoyaltyPlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany(mappedBy = "loyaltyPlan")
    private List<ClientModel> clients;

    @Column(nullable = false)
    private int necessary_amount;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Boolean isDelete;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ClientModel> getClients() {
        return clients;
    }

    public void setClients(List<ClientModel> clients) {
        this.clients = clients;
    }

    public int getNecessary_amount() {
        return necessary_amount;
    }

    public void setNecessary_amount(int necessary_amount) {
        this.necessary_amount = necessary_amount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
