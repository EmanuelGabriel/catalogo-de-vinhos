package com.srsolution.vinhos.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.srsolution.vinhos.model.Produto;
import com.srsolution.vinhos.repository.Produtos;
import com.srsolution.vinhos.repository.filter.ProdutoFilter;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	
	private static final String VIEW_CADASTRO_PRODUTO = "produto/cadastro-produto";
	private static final String VIEW_PESQUISA_PRODUTO = "produto/pesquisa-produto";
	
	private static final String VIEW_REDIRECT_PRODUTO_NOVO = "redirect:/produtos/novo";
	private static final String VIEW_REDIRECT_PRODUTOS = "redirect:/produtos";
	
	private static final String MSG_PRODUTO_DELETADO = "Produto removido com sucesso!";
	private static final String MSG_PRODUTO_SUCESSO = "Produto salvo com sucesso!"; 

	
	@Autowired
	private Produtos produtos;
	
	
	@GetMapping("/novo")
	private ModelAndView novo(Produto produto) {
		
		ModelAndView mv = new ModelAndView(VIEW_CADASTRO_PRODUTO);
		mv.addObject(produto);	
		return mv;
	}
	
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Produto produto, BindingResult result,
			 				   RedirectAttributes attr){
		if(result.hasErrors())
			
			return novo(produto);
			
		produtos.save(produto); 
		attr.addFlashAttribute("mensagem", MSG_PRODUTO_SUCESSO);
		return new ModelAndView(VIEW_REDIRECT_PRODUTO_NOVO);
		
	}
	
	
	@GetMapping 
	public ModelAndView pesquisar(ProdutoFilter produtoFilter){
	
		ModelAndView mv = new ModelAndView(VIEW_PESQUISA_PRODUTO);
		mv.addObject("produtos", 
		produtos.findByNomeContainingIgnoreCase(Optional.ofNullable(produtoFilter.getNome()).orElse("%")));
		return mv;
		
	}
	
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id){
		
		Produto produto = produtos.findOne(id);
		return novo(produto);
	}
	
	
	@DeleteMapping("/{id}") 
	public String delete(@PathVariable Long id, RedirectAttributes attr){
		produtos.delete(id);
		attr.addFlashAttribute("mensagem", MSG_PRODUTO_DELETADO);
		return VIEW_REDIRECT_PRODUTOS;
	}
	
	
}
