package com.korea.basic1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String subject;

    private String writer;

    @ManyToOne
    private Bookstore bookstore;

    // 책은 제목과 저자, 보유 서점으로 구성됩니다.
}
