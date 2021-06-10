package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    //리스트
    @RequestMapping("/list")
    public String list(Model model){
        List<BoardDomain> list = service.boardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    //디테일
    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model){
        System.out.println("iboard : " + param.getIboard());
        BoardDomain data = service.boardDetail(param);
        model.addAttribute("data",data);
        return "board/detail";
    }

    //게시글 수정
    @GetMapping("/writeMod")
    public void writeMod(BoardDTO param, Model model){
        if(param.getIboard() > 0){
            model.addAttribute("data", service.boardDetail(param));
        }
    }

    //게시글 작성
    @PostMapping("/writeMod")
    public String writeModProc(BoardEntity param){
        int iboard = service.writeMod(param);
        return "redirect:detail?iboard=" + iboard;
    }

    //게시글 삭제
    @GetMapping("/delBoard")
    public String delBoard(BoardEntity param){
        service.delBoard(param);
        return "redirect:list";
    }

    //댓글 삽입
    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param){

        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }

    //댓글 리스트
    @ResponseBody
    @RequestMapping("/cmt/{iboard}")
    public List<BoardCmtDomain> cmtSel(@PathVariable("iboard") int iboard){
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(iboard);
        return service.selBoardCmtList(param);
    }

    //댓글 삭제
    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(BoardCmtEntity param, @PathVariable("icmt") int icmt){
        param.setIcmt(icmt);
        int result = service.delBoardCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }

    //댓글 수정
    @ResponseBody
    @RequestMapping(value ="/cmt", method = RequestMethod.PUT)
    public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param){
        int result = service.updBoardCmt(param);
        System.out.println("upd result : " + result);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }
}

