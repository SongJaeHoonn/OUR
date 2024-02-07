package app.Service;

import app.Dto.BoardDto;
import app.Entity.Board;
import app.Repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createPost(BoardDto boardDto){
        boardRepository.save(Board.toBoard(boardDto));
    }

    public void updatePost(BoardDto boardDto){
        boardRepository.save(Board.toUpdateBoard(boardDto));
    }

    public void deletePostByBoardId(Long boardId){
        boardRepository.deleteById(boardId);
    }



}
