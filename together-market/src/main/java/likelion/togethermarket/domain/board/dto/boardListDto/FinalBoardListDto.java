package likelion.togethermarket.domain.board.dto.boardListDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FinalBoardListDto {
    private MemberInfoDto user_info;
    private ShopInfoDto shop_info;
    private BoardInfoDto board_info;

    @Builder
    public FinalBoardListDto(MemberInfoDto user_info, ShopInfoDto shop_info, BoardInfoDto board_info) {
        this.user_info = user_info;
        this.shop_info = shop_info;
        this.board_info = board_info;
    }
}
