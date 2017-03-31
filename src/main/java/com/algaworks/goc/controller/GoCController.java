package com.algaworks.goc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.goc.model.Carta;
import com.algaworks.goc.repository.Cartas;

@Controller

public class GoCController {

	@Autowired
	private Cartas carta;

	@RequestMapping("/")
	public ModelAndView outraPagina() {
		ModelAndView mv = new ModelAndView("Index");
		return mv;
	}

	@RequestMapping("/colecao")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Colecao");
		mv.addObject(new Carta());
		mv.addObject("cartas", carta.findAll());
		return mv;
	}
	

	@RequestMapping(value="/colecao", method = RequestMethod.POST)
	public String salvar(Carta carta) {
		this.carta.save(carta);
		return "redirect:/colecao";
	}
	

//Exemplo de URL de chamada http://.../excluir?id=1234 
	@RequestMapping(value = "/colecao/excluir")
	public ModelAndView excluirCartaByRequestParamInTheUrl(@RequestParam(value="id", required=true) Long id, 
			HttpServletRequest request, HttpServletResponse response) {     
		ModelAndView mv = new ModelAndView("Colecao");
		this.carta.delete(id);
		String message = "Carta foi apagada com sucesso.";
		mv.addObject("message", message);
		mv.addObject(new Carta());
		mv.addObject("cartas", carta.findAll());
		return mv;
	}
	
//  Exemplo de URL de chamada http://.../excluir/1234    
	@RequestMapping(value = "/colecao/excluir/{id}")
	public ModelAndView excluirCartaByPathVariable(@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("Colecao");
		this.carta.delete(id);
		String message = "Carta foi apagada com sucesso.";
		mv.addObject("message", message);	
		mv.addObject(new Carta());
		mv.addObject("cartas", carta.findAll());
		return mv;
	}
	

}
