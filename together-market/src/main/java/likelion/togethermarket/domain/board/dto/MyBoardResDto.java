package likelion.togethermarket.domain.board.dto;

import likelion.togethermarket.domain.shop.dto.response.SimpleShopResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyBoardResDto {
    private SimpleShopResDto shop_info;

    private MyBoardInfoDto board_info;

    @Builder
    public MyBoardResDto(SimpleShopResDto shop_info, MyBoardInfoDto board_info) {
        this.shop_info = shop_info;
        this.board_info = board_info;
    }
}
