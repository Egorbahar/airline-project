package com.godeltech.persistence.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "aircraft")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
