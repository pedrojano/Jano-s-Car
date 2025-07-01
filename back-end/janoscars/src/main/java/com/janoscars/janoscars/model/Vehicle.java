package com.janoscars.janoscars.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Tipo do veiculo

    @Column(nullable = false)
    private String make; // Fabricante do Veiculo

    @Column(nullable = false)
    private String model; // Modelo do Veiculo

    @Column(nullable = false, unique = true)
    private String licensePlate; // Placa do Veiculo

    // Relacionamento muitos para um: muitos veiculos para um usuário
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // user_id é a chave estrangeira na tabela de vehicles

    private User user; // Usuário dono do veículo
}
