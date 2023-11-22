package likelion.togethermarket.domain.board.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardRegisterDto {
    private int market_id;
    private String market_name;
    private int shop_id;
    private String shop_name;
    private List<Integer> purchased_products;
    private List<String> photo;
    private String content;

    @Nullable
    private int rating;
}
