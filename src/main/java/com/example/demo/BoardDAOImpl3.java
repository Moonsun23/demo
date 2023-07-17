package com.example.demo;

import ityeji.hello.boot.spring5boot.model.Board;
import ityeji.hello.boot.spring5boot.mybatis.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("bdao") /*MemberDAOImpl의 줄임*/
@RequiredArgsConstructor    /*생성자를 자동으로 만들어줌*/
public class BoardDAOImpl3 implements BoardDAO{

    //@Autowired 없이 DI 구현 - 하나일땐 필요없음
    final BoardMapper boardMapper;

    @Override
    public int insertBoard(Board b) {

        return boardMapper.insertBoard(b);
    }

    @Override
    public List<Board> selectBoard(int stnum) {

        return boardMapper.selectBoard(stnum);
    }

    @Override
    public int selectCountBoard() {
        return boardMapper.selectCountBoard();
    }

    @Override
    public List<Board> selectFindBoard(Map<String, Object> params) {
        return boardMapper.selectFindBoard(params);
    }

    @Override
    public int countFindBoard(Map<String, Object> params) {
        return boardMapper.countFindBoard(params);
    }


    @Override
    public Board selectOneBoard(String bno) {

        boardMapper.updateViewBoard(bno);

        return boardMapper.selectOneBoard(bno);
    }
}
