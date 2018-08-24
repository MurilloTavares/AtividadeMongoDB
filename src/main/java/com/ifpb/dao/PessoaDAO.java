package com.ifpb.dao;

import com.ifpb.models.Pessoa;

import com.ifpb.database.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class PessoaDAO {

    private MongoCollection collection;

    public PessoaDAO() {
        collection = new MongoConnection().getCollection("Pessoa");
    }

    public void salvar(Pessoa p) {
        collection.insertOne(p.toDocument());
    }

    public List<Pessoa> listar() {
        MongoCursor<Document> cursor = collection.find().iterator();

        List<Pessoa> pessoas = new ArrayList<>();

        while (cursor.hasNext()) {
            pessoas.add(new Pessoa(cursor.next()));
        }

        return pessoas;
    }

    public Pessoa buscarPorCpf(String cpf) {
        Document doc = (Document) collection.find(eq("cpf", cpf)).first();

        if (doc == null) {
            return null;
        } else {
            return new Pessoa(doc);
        }
    }

    public boolean deletarPorCpf(String cpf){
        DeleteResult result = collection.deleteOne(eq("cpf", cpf));
        
        return result.getDeletedCount()>0;
    }
    
    public boolean atualizar(Pessoa p){
        UpdateResult result = collection.updateOne(eq("cpf", p.getCpf()),
                new Document("$set", p.toDocument()));
        
        return result.getModifiedCount()>0;        
    }
    
}
