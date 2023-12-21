package likelion.togethermarket.domain.board.dto;

import likelion.togethermarket.domain.board.dto.boardListDto.BoardInfoDetailDto;
import likelion.togethermarket.domain.board.dto.boardListDto.MemberInfoDto;
import likelion.togethermarket.domain.board.dto.boardListDto.ShopInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SingleFinalDto {
    private String market_name;
    private MemberInfoDto user_info;
    private ShopInfoDto shop_info;
    private BoardInfoDetailDto board_info;

    @Builder
    public SingleFinalDto(String market_name, MemberInfoDto user_info, ShopInfoDto shop_info, BoardInfoDetailDto board_info) {
        this.market_name = market_name;
        this.user_info = user_info;
        this.shop_info = shop_info;
        this.board_info = board_info;
    }
}
