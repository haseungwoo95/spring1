package com.koreait.spring.fav;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardFavService {

    @Autowired
    private BoardFavMapper mapper;

    @Autowired
    private MyUtils myUtils;

    public int insFav(BoardFavEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return mapper.insBoardFav(param);
    }

    public int selFav(BoardFavEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return mapper.selBoardFav(param);
    }

    public int delFav(BoardFavEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return mapper.delBoardFav(param);
    }
}
