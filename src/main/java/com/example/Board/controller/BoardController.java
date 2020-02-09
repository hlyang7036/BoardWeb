package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.domain.Board;
import com.example.board.domain.Search;
import com.example.board.security.SecurityUser;
import com.example.board.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Search search) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
			
		Page<Board> boardList = boardService.getBoardList(search);
		model.addAttribute("boardList", boardList);
		
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "board/getBoard";
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "board/updateBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "board/insertBoard";
		
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		System.out.println("check2");
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
}
