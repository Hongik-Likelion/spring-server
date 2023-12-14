package likelion.togethermarket.domain.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private ReviewUserDto user_info;
    private ReviewBoardInfoDto board_info;

    @Builder
    public ReviewDto(ReviewUserDto user_info, ReviewBoardInfoDto board_info) {
        this.user_info = user_info;
        this.board_info = board_info;
    }
}
