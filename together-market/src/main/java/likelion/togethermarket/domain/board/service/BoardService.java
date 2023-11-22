package likelion.togethermarket.domain.board.service;

import likelion.togethermarket.domain.board.dto.BoardModifyDto;
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

import java.util.List;
import java.util.OptionalDouble;

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
            shop.updateRating(getLatestAverageRating(shop)); // 평균 별점 업데이트
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

        Board finalBoard = board;
        boardRegisterDto.getPhoto().stream()  // boardPhoto 저장
                .map(photo -> BoardPhoto.builder().board(finalBoard).image(photo).build())
                .forEach(boardPhotoRepository::save);

        boardRegisterDto.getPurchased_products().stream()  // purchaseProducts 저장
                .map(product_id -> BoardPurchasedProduct.builder()
                        .board(finalBoard)
                        .product(productRepository.findById(Long.valueOf(product_id)).orElseThrow()).build())
                .forEach(purchasedProductRepository::save);

        return new ResponseEntity<BoardRegisterDto>(boardRegisterDto, HttpStatusCode.valueOf(201));
    }

    public float getLatestAverageRating(Shop shop){
        List<Board> boardList = boardRepository.findAllByShop(shop);
        OptionalDouble average = boardList.stream().map(Board::getRating).toList()
                .stream().mapToInt(Integer::intValue).average();
        return (float) average.getAsDouble();
    }

    @Transactional
    public ResponseEntity<?> modifyBoard(Long memberId, BoardModifyDto boardModifyDto, Long boardId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Board board = boardRepository.findById(boardId).orElseThrow();

        if (member.getMemberRole() == MemberRole.CUSTOMER){
            board.modify(boardModifyDto.getMarket_name(), boardModifyDto.getShop_name(),
                    boardModifyDto.getContent(), boardModifyDto.getRating());
            // 고객의 경우 가게의 평균 별점 업데이트
            board.getShop().updateRating(getLatestAverageRating(board.getShop()));
        }
        else {
            board.modify(boardModifyDto.getMarket_name(), boardModifyDto.getShop_name(),
                    boardModifyDto.getContent());
        }

        boardPhotoRepository.deleteAllByBoard(board);
        purchasedProductRepository.deleteAllByBoard(board);

        boardModifyDto.getPhoto().stream()  // boardPhoto 저장
                        .map(photo -> BoardPhoto.builder().board(board).image(photo).build())
                                .forEach(boardPhotoRepository::save);

        boardModifyDto.getPurchased_products().stream()  // purchasedProducts 저장
                .map(product_id -> BoardPurchasedProduct.builder().board(board)
                        .product(productRepository.findById(Long.valueOf(product_id)).orElseThrow()).build())
                .forEach(purchasedProductRepository::save);

        return new ResponseEntity<BoardModifyDto>(boardModifyDto, HttpStatusCode.valueOf(200));
    }
}
