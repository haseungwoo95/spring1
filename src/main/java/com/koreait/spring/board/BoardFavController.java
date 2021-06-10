package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController //json을 목적으로 하는 컨트롤러?
@RequestMapping("/board")
public class BoardFavController {

    @Autowired
    private BoardFavService service;

    @PostMapping("/fav")
    public Map<String, Integer> insFav(@RequestBody BoardFavEntity param){
        Map<String, Integer> result = new HashMap();
        result.put("result", service.insFav(param));
        return result;
    }

    @GetMapping("/fav")
    public Map<String, Integer> selFav(BoardFavEntity param){
        Map<String, Integer> result = new HashMap();
        result.put("result", service.selFav(param));
        return result;
    }

    @DeleteMapping("/fav")
    public Map<String, Integer> delFav(BoardFavEntity param){
        Map<String, Integer> result = new HashMap();
        result.put("result", service.delFav(param));
        return result;
    }
}
