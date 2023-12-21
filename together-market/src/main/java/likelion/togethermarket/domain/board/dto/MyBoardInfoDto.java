package likelion.togethermarket.domain.board.dto;

import jakarta.annotation.Nullable;
import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.board.entity.BoardPhoto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class MyBoardInfoDto {

    private int board_id;

    private String updated_at;

    @Nullable
    private int rating;

    @Nullable
    private String photo;

    private String content;

    @Builder
    public MyBoardInfoDto(Board board, List<BoardPhoto> boardPhotos, List<Integer> purchased_products) {
        this.board_id = board.getId().intValue();
        this.updated_at = board.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));;
        this.rating = board.getRating();
        this.photo = (boardPhotos.isEmpty())?null:boardPhotos.get(0).getImage();
        this.content = board.getContent();
    }
}
