package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class boardDomain extends BoardEntity{
    private String writerNm;
    private String profileImg;
}