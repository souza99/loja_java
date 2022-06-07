package com.lojas.virtualStore.service;

import com.lojas.virtualStore.domain.Usuario;
import com.lojas.virtualStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    private boolean existsById(Long id){
        return usuarioRepository.existsById(id);
    }

    private boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    private boolean existsByCpfCnpj(String cpfCnpj){
        return usuarioRepository.existsByCpfCnpj(cpfCnpj);
    }

    public Usuario findById(Long id) throws ResourceNotFoundException{
        Usuario domain = usuarioRepository.findById(id).orElse(null);
        if(domain==null){
            throw new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
        }else return domain;
    }

    public Page<Usuario> findAll(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> findAllByCpfCnpj(String cpfCnpj, Pageable page){
        Page<Usuario> domains = usuarioRepository.findByCpfCnpj(cpfCnpj, page);
        return domains;
    }

    public Usuario save(Usuario domain) throws BadResourceException, ResourceAlreadyExistsException{
        if(!StringUtils.isEmpty(domain.getCpfCnpf())){
            if(domain.getId()!=null && existsById(domain.getId())){
                throw new ResourceAlreadyExistsException("Usuario com id: " +domain.getId() + "já existe");
            }
            if(domain.getEmail()!=null && existsByEmail(domain.getEmail())) {
                throw new ResourceAlreadyExistsException("Email já cadastrado: " + domain.getEmail());
            }
            if(domain.getCpfCnpf()!=null && existsByCpfCnpj(domain.getCpfCnpf())){
                throw new ResourceAlreadyExistsException("CFP/CNPJ já cadastrado: " + domain.getCpfCnpf());
            }
            return usuarioRepository.save(domain);
        }else {
            BadResourceException exc = new BadResourceException("Erro ao salvar o usuario");
            throw exc;
        }
    }


    public void update(Usuario domain)
            throws BadResourceException, ResourceNotFoundException{
        if(!StringUtils.isEmpty(domain.getId())){
            if(!existsById(domain.getId())){
                throw new ResourceNotFoundException("Usuario não encontrado com o id: " + domain.getId());
            }
            usuarioRepository.save(domain);
        }else{
            BadResourceException exc = new BadResourceException("Falha ao salvar o usuario");
            exc.addErrorMessage("Usuario está nulo ou em branco");
            throw exc;
        }
    }



    public void deletedById(Long id) throws ResourceNotFoundException{
        if(!existsById(id)){
            throw new ResourceNotFoundException("Usuario não encotrado com o id: " + id);
        }else {
            usuarioRepository.deleteById(id);
        }
    }

    public Long count(){
        return  usuarioRepository.count();
    }


}
