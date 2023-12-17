package likelion.togethermarket.domain.board.dto;

import jakarta.annotation.Nullable;
import likelion.togethermarket.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ReviewBoardInfoDto {
    private int board_id;

    private String updated_at;

    private int rating;

    @Nullable
    private String photo;

    private String content;
    private int like_count;
    private boolean is_liked;

    @Builder
    public ReviewBoardInfoDto(Board board, String photo, int like_count, boolean is_liked) {
        this.board_id = board.getId().intValue();
        this.updated_at = board.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.rating = board.getRating();
        this.photo = photo;
        this.content = board.getContent();
        this.like_count = like_count;
        this.is_liked = is_liked;
    }

    public boolean getIs_liked(){
        return is_liked;
    }
}
