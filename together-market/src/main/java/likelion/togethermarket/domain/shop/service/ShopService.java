package likelion.togethermarket.domain.shop.service;

import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.market.repository.MarketRepository;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import likelion.togethermarket.domain.product.entity.Product;
import likelion.togethermarket.domain.product.entity.SellingProducts;
import likelion.togethermarket.domain.product.repository.ProductRepository;
import likelion.togethermarket.domain.product.repository.SellingProductsRepository;
import likelion.togethermarket.domain.shop.dto.ShopRegisterDto;
import likelion.togethermarket.domain.shop.dto.response.WishShopResDto;
import likelion.togethermarket.domain.shop.entity.Shop;
import likelion.togethermarket.domain.shop.entity.WishShop;
import likelion.togethermarket.domain.shop.repository.ShopRepository;
import likelion.togethermarket.domain.shop.repository.WishShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {
    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final SellingProductsRepository sellingProductsRepository;
    private final MarketRepository marketRepository;
    private final ProductRepository productRepository;
    private final WishShopRepository wishShopRepository;

    @Transactional
    public ResponseEntity<?> registerShop(Long memberId, ShopRegisterDto reqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Market market = marketRepository.findById((long) reqDto.getMarket_id()).orElseThrow();
        List<Product> products = reqDto.getProduct_categories().stream().map(productId
                        -> productRepository.findById(Long.valueOf(productId)).orElseThrow())
                .toList();

        Shop shop = Shop.builder().market(market)
                .member(member)
                .shopName(reqDto.getShop_name())
                .shopAddress(reqDto.getShop_address())
                .sellingProducts(reqDto.getSelling_products())
                .openingTime(reqDto.getOpening_time())
                .closingTime(reqDto.getClosing_time())
                .openingFrequency(reqDto.getOpening_frequency()).build();

        shopRepository.saveAndFlush(shop);

        for (Product p : products){
            SellingProducts sellingProduct = SellingProducts.builder().product(p).shop(shop).build();
            sellingProductsRepository.save(sellingProduct);
        }

        return new ResponseEntity<ShopRegisterDto>(reqDto, HttpStatusCode.valueOf(201));
    }

    public ResponseEntity<?> registerWishShop(Long memberId, Long shopId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        WishShop wishShop = WishShop.builder().shop(shop).member(member).build();
        wishShopRepository.save(wishShop);
        WishShopResDto resDto = WishShopResDto.builder().shop_id(shop.getId().intValue())
                .message("Success Add favourite shop").build();

        return new ResponseEntity<WishShopResDto>(resDto, HttpStatusCode.valueOf(201));
    }
}
