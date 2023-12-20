package likelion.togethermarket.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SUCCESS_OK(HttpStatus.OK, "성공", 1000),
    SUCCESS_CREATED(HttpStatus.CREATED, "생성 성공", 1001),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.", 3001),

    SHOP_NOT_FOUND(HttpStatus.NOT_FOUND, "가게를 찾을 수 없습니다.", 4001),

    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.", 5001),

    MARKET_NOT_FOUND(HttpStatus.NOT_FOUND, "시장을 찾을 수 없습니다.", 6001),
    ;




    private final HttpStatus httpStatus;
    private final String message;
    private final int code;
}
