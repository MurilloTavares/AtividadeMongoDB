package com.ifpb.view;

import com.ifpb.dao.PessoaDAO;
import com.ifpb.dao.VendaDAO;
import com.ifpb.models.ItemVenda;
import com.ifpb.models.Pessoa;
import com.ifpb.models.Produto;
import com.ifpb.models.Venda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {
    
    public static void main(String[] args){
        
        // Salvando venda
        VendaDAO vDao = new VendaDAO();
        
        Produto arroz = new Produto(1, "Arroz", 5);
        Produto feijao = new Produto(2, "Feijao", 10);
        
        ItemVenda item1 = new ItemVenda(arroz, 3);
        ItemVenda item2 = new ItemVenda(feijao, 2);
        
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);
        
        Venda venda = new Venda(1, itens, LocalDateTime.now());
        
        vDao.salvar(venda);
        
        // Buscando venda        
        venda = vDao.buscar(1);
        System.out.println("Buscando venda: " + venda);
        
        // Inserindo Item em venda
        Produto macarrao = new Produto(3, "Macarrao", 8);
        ItemVenda item3 = new ItemVenda(macarrao, 4);
        
        boolean insercao = vDao.inserirItem(1, item3);
        venda = vDao.buscar(1);
        
        System.out.println("Insercao: " + insercao);
        System.out.println("Nova venda: " + venda);
        
        /* // Deletando venda
        boolean resultado = vDao.deletar(1);
        venda = vDao.buscar(1);
        
        System.out.println("venda deletada: " + resultado);
        System.out.println("venda apos delecao: " + venda); */   
        
    }
    
}
