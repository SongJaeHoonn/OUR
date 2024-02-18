package app.Service;

import app.Dto.BoardDto;
import app.Dto.MemberDto;
import app.Entity.Board;
import app.Entity.Member;
import app.Repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createPost(BoardDto boardDto, MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\OUR\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        boardDto.setFileName(fileName);
        boardDto.setFilePath("/files/" + fileName);

        boardRepository.save(Board.toBoard(boardDto));
    }

    public void updatePost(BoardDto boardDto){
        boardRepository.save(Board.toUpdateBoard(boardDto));
    }

    public void deletePostByBoardId(Long boardId){
        boardRepository.deleteById(boardId);
    }

    public List<BoardDto> findAll() {
        List<Board> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardEntityList){
            boardDtoList.add(BoardDto.toBoardDto(board));
        }
        return boardDtoList;
    }

    public BoardDto findById(Long BoardId){
        Optional<Board> board = boardRepository.findById(BoardId);
        return board.map(BoardDto::toBoardDto).orElse(null);
    }

}
