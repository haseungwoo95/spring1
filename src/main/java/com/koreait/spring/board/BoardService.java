package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import com.koreait.spring.cmt.BoardCmtDomain;
import com.koreait.spring.cmt.BoardCmtEntity;
import com.koreait.spring.cmt.BoardCmtMapper;
import com.koreait.spring.fav.BoardFavEntity;
import com.koreait.spring.fav.BoardFavMapper;
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
    private BoardFavMapper favMapper;

    @Autowired
    private MyUtils myUtils;

    public List<BoardDomain> boardList(BoardDTO param){
        param.setIuser(myUtils.getLoginUserPk());

        int startIdx = (param.getPage() - 1) * param.getRecordCnt();
        param.setStartIdx(startIdx);

        return mapper.boardList(param);
    }

    public int selMaxPageVal(BoardDTO param){
        return mapper.selMaxPageVal(param);
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

        BoardFavEntity favparam = new BoardFavEntity();
        favparam.setChkfav(0);
        favparam.setIboard(param.getIboard());
        favMapper.delBoardFav(favparam);

        param.setIuser(myUtils.getLoginUserPk());
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
