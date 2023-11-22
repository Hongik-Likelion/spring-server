package likelion.togethermarket.domain.board.dto.boardListDto;

import likelion.togethermarket.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopInfoDto {
    private int shop_id;
    private String shop_name;
    private float average_rating;

    @Builder
    public ShopInfoDto(Board board) {
        this.shop_id = board.getShop().getId().intValue();
        this.shop_name = board.getShopName();
        this.average_rating = board.getShop().getRating();
    }
}
