package likelion.togethermarket.domain.board.service;

import likelion.togethermarket.domain.board.dto.BoardModifyDto;
import likelion.togethermarket.domain.board.dto.BoardRegisterDto;
import likelion.togethermarket.domain.board.dto.boardListDto.BoardInfoDto;
import likelion.togethermarket.domain.board.dto.boardListDto.FinalBoardListDto;
import likelion.togethermarket.domain.board.dto.boardListDto.MemberInfoDto;
import likelion.togethermarket.domain.board.dto.boardListDto.ShopInfoDto;
import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.board.entity.BoardPhoto;
import likelion.togethermarket.domain.board.entity.BoardPurchasedProduct;
import likelion.togethermarket.domain.board.repository.*;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.market.repository.MarketRepository;
import likelion.togethermarket.domain.member.entity.BlackList;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.entity.MemberRole;
import likelion.togethermarket.domain.member.repository.BlackListRepository;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import likelion.togethermarket.domain.product.repository.ProductRepository;
import likelion.togethermarket.domain.shop.entity.Shop;
import likelion.togethermarket.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final ReportRepository reportRepository;
    private final LikeRepository likeRepository;
    private final BlackListRepository blackListRepository;


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
        OptionalDouble average = boardList.stream()
                .mapToInt(Board::getRating).average();
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

    // 게시글 삭제
    public ResponseEntity<?> deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        boardRepository.delete(board);
        board.getShop().updateRating(getLatestAverageRating(board.getShop()));
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    // 시장 게시글 전체 조회
    public ResponseEntity<?> getBoardList(Long memberId, Long marketId) {
        Member reqMember = memberRepository.findById(memberId).orElseThrow(); // 요청을 보낸 멤버
        Market market = marketRepository.findById(marketId).orElseThrow();
        List<Board> boards = boardRepository.findAllByMarket(market);


        List<Board> boardsToRemove = new ArrayList<>();  // 조회하지 않을 명단
        for (Board board : boards){
            List<BlackList> blackLists = blackListRepository.findAllByMember(reqMember);
            if (reportRepository.countByBoard(board) > 4L){
                boardsToRemove.add(board);  // 신고 누적 4회 이상은 조회 x 명단에 추가
            } else if (blackLists.stream().anyMatch(blackList
                    -> blackList.getBlackListId().equals(board.getMember().getId()))) {
                boardsToRemove.add(board);  // 내가 블락한 유저가 쓴 글도 조회 x 명단에 추가
            }
        }
        boards.removeAll(boardsToRemove);  // 조회 x 명단 제거

        List<FinalBoardListDto> boardListDtos = new ArrayList<>();  // 반환할 최종 DTO 빌드
        for (Board board : boards){
            List<BoardPhoto> boardPhotos = boardPhotoRepository.findAllByBoard(board);
            long likeCount = likeRepository.countByBoard(board);

            BoardInfoDto boardInfoDto = BoardInfoDto.builder().board(board)   // board_info 생성
                    .is_liked(likeRepository.existsByBoardAndMember(board, reqMember))
                    .like_count((int) likeCount)
                    .photo(boardPhotos.isEmpty() ? null : boardPhotos.get(0).getImage()).build();

            MemberInfoDto memberInfoDto = MemberInfoDto.builder().board(board).build();// user_info 생성

            ShopInfoDto shopInfoDto = ShopInfoDto.builder().board(board).build();// shop_info 생성

            FinalBoardListDto listDto = FinalBoardListDto.builder().user_info(memberInfoDto)
                    .shop_info(shopInfoDto).board_info(boardInfoDto).build();
            boardListDtos.add(listDto);
        }

        return new ResponseEntity<List<FinalBoardListDto>>(boardListDtos, HttpStatusCode.valueOf(200));
    }
}
