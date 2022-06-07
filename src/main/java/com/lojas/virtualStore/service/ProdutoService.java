package com.lojas.virtualStore.service;

import com.lojas.virtualStore.domain.Produto;
import com.lojas.virtualStore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private boolean existsById(Long id){
        return produtoRepository.existsById(id);
    }

    public Produto findById(Long id) throws ResourceNotFoundException{
        Produto domain = produtoRepository.findById(id).orElse(null);
        if(domain==null){
            throw new ResourceNotFoundException("Produto não informado com o id: " + id);
        }else return domain;
    }

    public Page<Produto> findAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> findAllByNome(String nome, Pageable page){
        Page<Produto> domains = produtoRepository.findByNome(nome, page);
        return domains;
    }

    public Produto save(Produto domain) throws BadResourceException, ResourceAlreadyExistsException{
        if(!StringUtils.isEmpty(domain.getNome())){
            if(domain.getId()!=null && existsById(domain.getId())){
                throw new ResourceAlreadyExistsException("Produto com id: " +domain.getId() + "já existe");
            }
            return produtoRepository.save(domain);
        }else {
            BadResourceException exc = new BadResourceException("Erro ao salvar o produto");
            throw exc;
        }
    }

    public void update(Produto domain)
        throws BadResourceException, ResourceNotFoundException{
        if(!StringUtils.isEmpty(domain.getId())){
            if(!existsById(domain.getId())){
                throw new ResourceNotFoundException("Produto não encontrado com o id: " + domain.getId());
            }
            produtoRepository.save(domain);
        }else{
            BadResourceException exc = new BadResourceException("Falha ao salvar o produto");
            exc.addErrorMessage("Produto está nulo ou em branco");
            throw exc;
        }
    }

    public void deletedById(Long id) throws ResourceNotFoundException{
        if(!existsById(id)){
            throw new ResourceNotFoundException("Produto não encotrado com i id: " + id);
        }else {
            produtoRepository.deleteById(id);
        }
    }

    public Long count(){
        return  produtoRepository.count();
    }

}
