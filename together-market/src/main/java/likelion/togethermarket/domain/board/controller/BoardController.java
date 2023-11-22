package likelion.togethermarket.domain.board.controller;

import likelion.togethermarket.domain.board.dto.BoardRegisterDto;
import likelion.togethermarket.domain.board.service.BoardService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<?> registerNewBoard(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody BoardRegisterDto boardRegisterDto
            ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.registerBoard(memberId, boardRegisterDto);
    }
}
