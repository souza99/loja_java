package com.lojas.virtualStore.service;

import com.lojas.virtualStore.domain.ProdutoDomain;
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

    public ProdutoDomain findById(Long id) throws ResourceNotFoundException{
        ProdutoDomain domain = produtoRepository.findById(id).orElse(null);
        if(domain==null){
            throw new ResourceNotFoundException("Produto não informado com o id: " + id);
        }else return domain;
    }

    public Page<ProdutoDomain> findAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

    public Page<ProdutoDomain> findAllByNome(String nome, Pageable page){
        Page<ProdutoDomain> domains = produtoRepository.findByNome(nome, page);
        return domains;
    }

    public ProdutoDomain save(ProdutoDomain domain) throws BadResourceException, ResourceAlreadyExistsException{
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

    public void update(ProdutoDomain domain)
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

}
