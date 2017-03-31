package com.algaworks.goc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.goc.model.Usuario;
import com.algaworks.goc.repository.Usuarios;

@Controller
public class UsuarioController {

	@Autowired
	private Usuarios usuario;

	
	@RequestMapping("/usuario")
	public ModelAndView listarUsuario() {
		ModelAndView mv = new ModelAndView("Usuario");
		mv.addObject(new Usuario());
		mv.addObject("usuarios", usuario.findAll());
		return mv;
	}
	
	@RequestMapping(value="/usuario", method = RequestMethod.POST)
	public String salvar(Usuario usuario) {
		this.usuario.save(usuario);
		return "redirect:/usuario";
	}
	
	@RequestMapping(value = "/usuario/excluir/{id}")
	public ModelAndView excluirUsuarioByPathVariable(@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("Usuario");
		this.usuario.delete(id);
		String message = "Usuário foi apagado com sucesso.";
		mv.addObject("message", message);	
		mv.addObject(new Usuario());
		mv.addObject("usuarios", usuario.findAll());
		return mv;
	}

}
