package com.lojas.virtualStore.service;

import com.lojas.virtualStore.domain.Cupom;
import com.lojas.virtualStore.domain.Produto;
import com.lojas.virtualStore.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CupomService {

    @Autowired
    private CupomRepository cupomRepository;


    private boolean existsById(Long id){
        return cupomRepository.existsById(id);
    }

    public Cupom findById(Long id) throws ResourceNotFoundException{
        Cupom domain = cupomRepository.findById(id).orElse(null);
        if(domain==null){
            throw new ResourceNotFoundException("Cupom não encontrado com o id: " + id);
        }else return domain;
    }

    public Page<Cupom> findAll(Pageable pageable){
        return cupomRepository.findAll(pageable);
    }

    public Page<Cupom> findAllByNome(String nome, Pageable page){
        Page<Cupom> domains = cupomRepository.findByNome(nome, page);
        return domains;
    }

    public Page<Cupom> findAllByHash(String hash, Pageable page){
        Page<Cupom> domains = cupomRepository.findByHash(hash, page);
        return domains;
    }



}
