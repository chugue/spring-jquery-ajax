package shop.mtcoding.blog.board;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @PutMapping("/api/boards/{id}/update")
    public ApiUtil<?> update(@RequestBody BoardRequest.UpdateDTO requestDTO,
                             @PathVariable Integer id){
        Board board = boardRepository.selectOne(id);
        if (board == null){
            return new ApiUtil<>(404, "게시글을 찾을 수 없습니다.");
        }

        boardRepository.update(requestDTO, id);
        return new ApiUtil<>(null);
    }

    @PostMapping("/api/boards")
    public ApiUtil<?> write (@RequestBody BoardRequest.WriteDTO requestDTO){
        boardRepository.insert(requestDTO);
        return new ApiUtil<>(null);
    }

    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response){
        Board board = boardRepository.selectOne(id);
        if (board == null){
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다.");
        }
        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }


    @GetMapping(value = "/api/boards")
    public ApiUtil<?> findAll(HttpServletResponse response) {
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); //MessageConverter -> 옛날엔 xml, 요즘은 json
    }


}
