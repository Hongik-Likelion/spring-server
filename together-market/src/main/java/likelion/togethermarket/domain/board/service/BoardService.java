package likelion.togethermarket.domain.board.service;

import likelion.togethermarket.domain.board.dto.BoardRegisterDto;
import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.board.entity.BoardPhoto;
import likelion.togethermarket.domain.board.entity.BoardPurchasedProduct;
import likelion.togethermarket.domain.board.repository.BoardPhotoRepository;
import likelion.togethermarket.domain.board.repository.BoardPurchasedProductRepository;
import likelion.togethermarket.domain.board.repository.BoardRepository;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.market.repository.MarketRepository;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.entity.MemberRole;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import likelion.togethermarket.domain.product.entity.Product;
import likelion.togethermarket.domain.product.repository.ProductRepository;
import likelion.togethermarket.domain.shop.entity.Shop;
import likelion.togethermarket.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardPhotoRepository boardPhotoRepository;
    private final BoardPurchasedProductRepository purchasedProductRepository;
    private final MarketRepository marketRepository;
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;


    @Transactional
    public ResponseEntity<?> registerBoard(Long memberId, BoardRegisterDto boardRegisterDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Market market = marketRepository.findById((long) boardRegisterDto.getMarket_id()).orElseThrow();
        Shop shop = shopRepository.findById((long) boardRegisterDto.getShop_id()).orElseThrow();

        Board board = null;
        if (member.getMemberRole() == MemberRole.CUSTOMER) {
            board = Board.builder().member(member)  //board 저장, customer 일때
                    .market(market)
                    .shop(shop)
                    .marketName(boardRegisterDto.getMarket_name())
                    .shopName(boardRegisterDto.getShop_name())
                    .rating(boardRegisterDto.getRating())
                    .content(boardRegisterDto.getContent())
                    .build();
        }
        else {
            board = Board.builder().member(member)  //board 저장, Owner 일때 (rating 없음)
                    .market(market)
                    .shop(shop)
                    .marketName(boardRegisterDto.getMarket_name())
                    .shopName(boardRegisterDto.getShop_name())
                    .content(boardRegisterDto.getContent())
                    .build();
        }
        boardRepository.save(board);

        for (String photo : boardRegisterDto.getPhoto()) {  // boardPhoto 저장
            BoardPhoto boardPhoto = BoardPhoto.builder().board(board).image(photo).build();
            boardPhotoRepository.save(boardPhoto);
        }

        for (Integer productId : boardRegisterDto.getPurchased_products()) {  // purchasedProducts 저장
            Product product = productRepository.findById(Long.valueOf(productId)).orElseThrow();
            BoardPurchasedProduct purchasedProduct
                    = BoardPurchasedProduct.builder().board(board).product(product).build();
            purchasedProductRepository.save(purchasedProduct);
        }

        return new ResponseEntity<BoardRegisterDto>(boardRegisterDto, HttpStatusCode.valueOf(201));
    }

}
