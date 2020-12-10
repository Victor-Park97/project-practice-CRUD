package com.spring.mybaits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String boardlist(Model model) {
		model.addAttribute("list", boardService.getBoardList());
		return "list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addPost() {
		return "addpostform";
	}
	
	@RequestMapping(value="/addok", method = RequestMethod.POST)
	public String addPostOk(BoardVO vo) {
		if(boardService.insertBoard(vo) == 0)
			System.out.println("������ �߰� ���� ");
		else
			System.out.println("������ �߰� ����!!!");
		return "redirect:list";
	}
	
	@RequestMapping(value="/editform/{id}", method = RequestMethod.GET)
	public String editPost(@PathVariable("id") int id, Model model) {
		BoardVO boardVO = boardService.getBoard(id);
		model.addAttribute("u", boardVO);
		return "editform";
	}
	
	@RequestMapping(value="/editok", method = RequestMethod.POST)
	public String editPostOk(BoardVO vo) {
		if(boardService.updateBoard(vo) == 0)
			System.out.println("������ ���� ���� ");
		else
			System.out.println("������ ���� ����!!!");
		return "redirect:list";
	}
	
	@RequestMapping(value="/deleteok/{id}", method = RequestMethod.GET)
	public String deletePostOk(@PathVariable("id") int id) {
		if(boardService.deleteBoard(id) == 0)
			System.out.println("������ ���� ���� ");
		else
			System.out.println("������ ���� ����!!!");
		return "redirect:../list";
	}
}