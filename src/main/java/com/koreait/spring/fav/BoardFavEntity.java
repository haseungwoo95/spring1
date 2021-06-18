package com.koreait.spring.fav;

import lombok.Data;

@Data
public class BoardFavEntity {
    private int iboard;
    private int iuser;
    private String regdt;
    private int chkfav;
}
