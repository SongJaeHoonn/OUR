package app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long board_id;

    @Column(nullable = false)
    String head;

    @Column(nullable = false)
    String text;

    @JoinColumn(name = "member", referencedColumnName = "memberId")
    String member;

    @Column(nullable = false)
    Date date;
}
