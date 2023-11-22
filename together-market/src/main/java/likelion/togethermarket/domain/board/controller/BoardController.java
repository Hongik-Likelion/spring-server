package likelion.togethermarket.domain.board.controller;

import likelion.togethermarket.domain.board.dto.BoardModifyDto;
import likelion.togethermarket.domain.board.dto.BoardRegisterDto;
import likelion.togethermarket.domain.board.service.BoardService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;

    // 게시글 등록
    @PostMapping("")
    public ResponseEntity<?> registerNewBoard(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody BoardRegisterDto boardRegisterDto
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.registerBoard(memberId, boardRegisterDto);
    }

    // 게시글 수정
    @PutMapping("/{board_id}")
    public ResponseEntity<?> modifyMyBoard(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody BoardModifyDto boardModifyDto,
            @PathVariable("board_id") Long boardId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.modifyBoard(memberId, boardModifyDto, boardId);
    }

    // 게시글 삭제
    @DeleteMapping("/{board_id}")
    public ResponseEntity<?> deleteMyBoard(
            @PathVariable("board_id") Long boardId
    ){
        return boardService.deleteBoard(boardId);
    }

    // 시장의 모든 게시글 조회
    @GetMapping("")
    public ResponseEntity<?> getAllBoardInMarket(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam("market_id") Long market_id
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.getBoardList(memberId, market_id);
    }

}
