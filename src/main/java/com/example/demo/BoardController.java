package com.example.demo;

import hi1237.hello.boot.spring5boot.model.Board;
import hi1237.hello.boot.spring5boot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor

public class BoardController {

    final BoardService bsrv;

    Logger logger = LogManager.getLogger(BoardController.class);

    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg){
        logger.info("board/list 호출!");

        m.addAttribute("bds", bsrv.readBoard(cpg));
        //bsrv.readBoard를 실행해서 넘어온 결과가 bds로 넘어옴
        m.addAttribute("cpg", cpg);

        int cntpg = bsrv.countBoard();
        m.addAttribute("cntpg", bsrv.countBoard());
        m.addAttribute("stpg", ((cpg -1) / 10) *10 +1);

        // 만일 현재페이지가 총페이지수보다 크다면
        // cpg를 1로 강제 초기화(1페이지로 강제이동)
        if ( cpg > cntpg ) {
            return "redirect:/board/list/1";
        }


        return "board/list";

    }

    @GetMapping("/view/{bno}")
    public String view(Model m, @PathVariable String bno){
        logger.info("board/view 호출!");

        m.addAttribute("bd", bsrv.readOneBoard(bno));
        //bsrv.readBoard를 실행해서 넘어온 결과가 bds로 넘어옴

        return "board/view";

    }

    //`m.addAttribute`는 Thymeleaf의 `Model` 객체에 데이터를 추가하는 역할을 합니다.
    //
    //`Model` 객체는 컨트롤러에서 뷰로 데이터를 전달하기 위해 사용됩니다. `addAttribute` 메소드를 사용하여 특정 이름으로 데이터를 추가하면, 해당 데이터는 뷰에서 접근 가능하게 됩니다.
    //
    //예를 들어, 첫 번째 코드에서 `m.addAttribute("bds", bsrv.readBoard(cpg));`라는 구문은 `bsrv.readBoard(cpg)`를 실행한 결과를 `bds`라는 이름으로 `Model` 객체에 추가합니다. 이렇게 하면 뷰에서 `bds`라는 이름으로 해당 데이터에 접근할 수 있습니다.
    //
    //두 번째 코드에서도 `m.addAttribute("bd", bsrv.readOneBoard(bno));`라는 구문을 사용하여 `bsrv.readOneBoard(bno)`의 결과를 `bd`라는 이름으로 `Model` 객체에 추가합니다. 이렇게 하면 뷰에서 `bd`라는 이름으로 해당 데이터에 접근할 수 있습니다.
    //
    //즉, `m.addAttribute`를 통해 컨트롤러에서 생성된 데이터를 뷰로 전달할 수 있습니다.

    @GetMapping("/write")
    public String write(){
        logger.info("board/write 호출!");

        return "board/write";

    }

    @PostMapping("/write")
    public String writeok(Board b){
        logger.info("board/writeok 호출!");
        String returnPage = "redirect:/board/fail";

        if(bsrv.saveBoard(b))
            returnPage = "redirect:/board/list/1";

        return returnPage;

    }

    @GetMapping("/find/{findtype}/{findkey}/{cpg}")
    public String find(Model m, @PathVariable Integer cpg, @PathVariable String findtype,
                       @PathVariable String findkey){
        logger.info("board/find 호출!");

        m.addAttribute("bds", bsrv.readFindBoard(cpg, findtype, findkey));
        m.addAttribute("cpg", cpg);

        int cntpg = bsrv.countBoard();
        m.addAttribute("cntpg", bsrv.countFindBoard(findtype, findkey));
        m.addAttribute("stpg", ((cpg -1) / 10) *10 +1);
        m.addAttribute("fkey", findkey);
        m.addAttribute("ftype", findtype);

        if ( cpg > cntpg ) {
            return "redirect:/board/list/1";
        }


        return "board/list";

    }







}
