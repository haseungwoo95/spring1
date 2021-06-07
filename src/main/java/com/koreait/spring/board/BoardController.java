package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model){
        List<BoardDomain> list = service.boardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model){
        System.out.println("iboard : " + param.getIboard());
        BoardDomain detail = service.boardDetail(param);
        model.addAttribute("detail", detail);
        return "board/detail";
    }

    @ResponseBody
    @RequestMapping(value = "/cmtIns", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param){
        System.out.println("cmtIns param : " + param);

        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }

    @ResponseBody
    @RequestMapping("/cmtSel")
    public List<BoardCmtDomain> cmtSel(BoardCmtEntity param){
        System.out.println("cmtSel param : " + param);
        return service.selBoardCmtList(param);
    }

    @ResponseBody
    @RequestMapping("/cmtDel")
    public int cmtDel(BoardCmtEntity param){
        int result = service.delBoardCmt(param);
        System.out.println("result : " + result);
        return result;
    }

    @ResponseBody
    @RequestMapping("/cmtUpd")
    public int cmtUpd(BoardCmtEntity param){
        int result = service.updBoardCmt(param);
        System.out.println("result : " + result);
        return result;
    }
}

