package com.example.springbootdockerelk.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bortnik
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = -8176310406085164593L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="BOOK_SEQUENCE_GENERATOR", sequenceName = "BOOK_SEQUENCE", initialValue = 1, allocationSize = 1)
    @Column(name = "ID", updatable = false)
    private Long id;
    @Column(name = "NAME")
    private String name;

}
