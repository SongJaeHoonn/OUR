package app.Entity;

import app.Dto.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(nullable = false)
    private Date date;

    @Column
    private String fileName;

    @Column
    private String filePath;

    @OneToMany(mappedBy = "board")
    private List<Comment> reply;

    public static Board toBoard(BoardDto boardDto){
        Board board = new Board();
        board.setBoardId(boardDto.getBoardId());
        board.setHead(boardDto.getHead());
        board.setText(boardDto.getText());
        board.setMember(boardDto.getMember());
        board.setDate(boardDto.getDate());
        board.setFileName(boardDto.getFileName());
        board.setFilePath(boardDto.getFilePath());
        return board;
    }

    public static Board toUpdateBoard(BoardDto boardDto){
        Board board = new Board();
        board.setBoardId(boardDto.getBoardId());
        board.setHead(boardDto.getHead());
        board.setText(boardDto.getText());
        board.setMember(boardDto.getMember());
        board.setDate(boardDto.getDate());
        board.setFileName(boardDto.getFileName());
        board.setFilePath(boardDto.getFilePath());
        return board;
    }

}
