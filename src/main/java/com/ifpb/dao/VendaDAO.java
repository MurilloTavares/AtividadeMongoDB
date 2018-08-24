package com.ifpb.dao;

import com.ifpb.database.MongoConnectionPojo;
import com.ifpb.models.ItemVenda;
import com.ifpb.models.Venda;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Indexes;
import static com.mongodb.client.model.Updates.push;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    
    private MongoCollection collection;
    
    public VendaDAO() {
        collection = new MongoConnectionPojo().getCollection("Venda", Venda.class);
        collection.createIndex(Indexes.ascending("codigo"));
    }
    
    public void salvar(Venda venda){
        collection.insertOne(venda);
    }
    
    public List<Venda> listar(){
        MongoCursor cursor = collection.find().iterator();
        
        List<Venda> vendas = new ArrayList<>();
        
        while(cursor.hasNext()){
            vendas.add((Venda) cursor.next());
        }
        
        return vendas;
    }
    
    public Venda buscar(int codigo){
        return (Venda) collection.find(eq("codigo", codigo)).first();
    }
    
    public boolean deletar(int codigo){
        long count = collection.deleteOne(eq("codigo", codigo)).getDeletedCount();
        return count > 0;
    }
    
    public boolean inserirItem(int codigo, ItemVenda item){        
        long count = collection.updateOne(eq("codigo", codigo), push("itens", item)).getModifiedCount();        
        return count > 0;
    }
    
}
