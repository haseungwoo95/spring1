package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    public List<BoardDomain> boardList(){
        return mapper.boardList();
    }

    public BoardDomain boardDetail(BoardDTO param){
        return mapper.boardDetail(param);
    }
}
