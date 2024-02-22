package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @GetMapping(value = "/api/boards")
    public ApiUtil<?> findAll(HttpServletResponse response) {
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); //MessageConverter -> 옛날엔 xml, 요즘은 json
    }

}
