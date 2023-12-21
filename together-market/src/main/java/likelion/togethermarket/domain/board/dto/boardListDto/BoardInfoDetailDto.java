package likelion.togethermarket.domain.board.dto.boardListDto;

import jakarta.annotation.Nullable;
import likelion.togethermarket.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardInfoDetailDto {

    private int board_id;

    private String updated_at;

    @Nullable
    private int rating;

    @Nullable
    private List<String> photo;

    private String content;
    private int like_count;
    private boolean is_liked;

    private List<Integer> purchased_products;

    @Builder
    public BoardInfoDetailDto(Board board, List<String> photo, int like_count, boolean is_liked, List<Integer> purchased_products) {
        this.board_id = board.getId().intValue();
        this.updated_at = board.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.rating = board.getRating();
        this.photo = photo;
        this.content = board.getContent();
        this.like_count = like_count;
        this.is_liked = is_liked;
        this.purchased_products = purchased_products;
    }

    public boolean getIs_liked(){
        return is_liked;
    }
}
