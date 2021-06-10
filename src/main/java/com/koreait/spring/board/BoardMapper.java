package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> boardList();
    BoardDomain boardDetail(BoardDTO param);
    int insBoard(BoardEntity param);
    int updBoard(BoardEntity param);
    int delBoard(BoardEntity param);
}
