package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardCmtMapper cmtMapper;

    @Autowired
    private MyUtils myUtils;

    public List<BoardDomain> boardList(){
        return mapper.boardList();
    }

    public BoardDomain boardDetail(BoardDTO param){
        return mapper.boardDetail(param);
    }

    //return 값은 iboard값
    public int writeMod(BoardEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        if(param.getIboard() == 0){
            //등록
            mapper.insBoard(param);

        } else {
            //수정
            mapper.updBoard(param);
        }
        return param.getIboard();
    }

    public int delBoard(BoardEntity param){
        BoardCmtEntity cmtParam = new BoardCmtEntity();
        cmtParam.setIboard(param.getIboard());
        cmtMapper.delBoardCmt(cmtParam);

        param.setIuser(myUtils.getLoginUserPk());
        System.out.println("dels param" + param);
        return mapper.delBoard(param);
    }

    public int insBoardCmt(BoardCmtEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param){
        return cmtMapper.selBoardCmtList(param);
    }

    public int delBoardCmt(BoardCmtEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.delBoardCmt(param);
    }

    public int updBoardCmt(BoardCmtEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.updBoardCmt(param);
    }
}
