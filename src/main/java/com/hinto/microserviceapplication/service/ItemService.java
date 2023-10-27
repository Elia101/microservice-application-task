package com.hinto.microserviceapplication.service;

import com.hinto.microserviceapplication.dto.ItemGetDto;
import com.hinto.microserviceapplication.dto.ItemPatchDto;
import com.hinto.microserviceapplication.dto.ItemPostDto;
import com.hinto.microserviceapplication.exception.ItemExceptions;
import com.hinto.microserviceapplication.model.Item;
import com.hinto.microserviceapplication.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    public ItemRepository itemRepository;

    public ItemGetDto getItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        ItemGetDto itemGetDto = new ItemGetDto();

        if(item.isPresent()){
            itemGetDto.setId(item.get().getId());
            itemGetDto.setTitle(item.get().getTitle());
            itemGetDto.setDate(item.get().getDate());
            itemGetDto.setQty(item.get().getQty());
        } else {
            throw new ItemExceptions("Item with id " + id + " not found");
        }

        return itemGetDto;
    }

    public ItemGetDto insertItem(ItemPostDto itemPostDto) {

        if(itemPostDto.getTitle() == null || itemPostDto.getTitle().isEmpty()){
            throw new ItemExceptions("Title is mandatory and cannot be null or empty");
        }
        if (itemPostDto.getQty() <= 0){
            throw new ItemExceptions("Quantity must be greater than zero");
        }

        Item item = new Item();
        item.setTitle(itemPostDto.getTitle());
        item.setDate(itemPostDto.getDate());
        item.setQty(itemPostDto.getQty());

        Item createdItem = itemRepository.save(item);

        return getItemById(createdItem.getId());
    }

    public ItemGetDto updateItem(Long id, ItemPatchDto itemPatchDto) {
        Item existingData = itemRepository.findById(id).orElse(null);

        if (existingData != null) {
            if(itemPatchDto.getTitle() != null){
                existingData.setTitle(itemPatchDto.getTitle());
            }
            if (itemPatchDto.getDate() != null){
                existingData.setDate(itemPatchDto.getDate());
            }
            if (itemPatchDto.getQty() != 0){
                existingData.setQty(itemPatchDto.getQty());
            }
            itemRepository.save(existingData);
        } else {
            throw new ItemExceptions("Item with id " + id + " not found");
        }

        return getItemById(existingData.getId());
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}

