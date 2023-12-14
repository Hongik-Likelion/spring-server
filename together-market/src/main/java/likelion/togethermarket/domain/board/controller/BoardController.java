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


    @GetMapping("")
    public ResponseEntity<?> getAllBoards(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "market_id", required = false) Long market_id
    ){
        Long memberId = customUserDetails.getMember().getId();
        if (market_id != null){  // 시장의 모든 게시글 조회
            return boardService.getBoardList(memberId, market_id);
        }
        else {  // 내가 쓴 모든 게시글 조회
            return boardService.getMyBoard(memberId);
        }
    }

    // 게시글 단일 조회
    @GetMapping("/{board_id}")
    public ResponseEntity<?> getSingleBoardInfo(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("board_id") Long boardId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.getSingleBoard(boardId, memberId);
    }

    // 내 가게에 대한 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<?> getShopReviews(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.getMyShopReview(memberId);
    }

    // 좋아요
    @PatchMapping("/{board_id}/like")
    public ResponseEntity<?> likeToBoard(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("board_id") Long boardId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.likeBoard(memberId, boardId);
    }

    // 좋아요 취소
    @PatchMapping("/{board_id}/unlike")
    public ResponseEntity<?> cancelMyLike(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("board_id") Long boardId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.cancelLike(memberId, boardId);
    }

    // 게시글 신고하기
    @PatchMapping("/{board_id}/report")
    public ResponseEntity<?> reportBoard(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("board_id") Long boardId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return boardService.report(memberId, boardId);
    }

}
