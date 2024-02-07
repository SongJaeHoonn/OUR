package app.Entity;

import app.Dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    @Column(nullable = false)
    private String head;

    @Column(nullable = false)
    private String text;

    @JoinColumn(name = "member", referencedColumnName = "memberId")
    private String member;

    @Column(nullable = false)
    private Date date;

    public static Board toBoard(BoardDto boardDto){
        Board board = new Board();
        board.setBoardId(boardDto.getBoardId());
        board.setHead(boardDto.getHead());
        board.setText(boardDto.getText());
        board.setMember(boardDto.getMember());
        board.setDate(boardDto.getDate());
        return board;
    }

    public static Board toUpdateBoard(BoardDto boardDto){
        Board board = new Board();
        board.setBoardId(boardDto.getBoardId());
        board.setHead(boardDto.getHead());
        board.setText(boardDto.getText());
        board.setMember(boardDto.getMember());
        board.setDate(boardDto.getDate());
        return board;
    }

}
