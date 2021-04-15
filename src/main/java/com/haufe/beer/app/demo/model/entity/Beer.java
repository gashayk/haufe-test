package com.haufe.beer.app.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "BEERS")
public class Beer extends Audible implements Serializable {

    private static final long serialVersionUID = 3472167989463932737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "PROVIDER_ID")
    private Provider provider;

    private String name;
    private String graduation;
    private String type;
    private String description;
}
