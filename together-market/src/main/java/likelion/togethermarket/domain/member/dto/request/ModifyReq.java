package likelion.togethermarket.domain.member.dto.request;


import lombok.Getter;

import java.util.Map;

@Getter
public class ModifyReq {
    private Map<String, Object> data;
}
