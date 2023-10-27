package com.hinto.microserviceapplication.controller;


import com.hinto.microserviceapplication.dto.ItemGetDto;
import com.hinto.microserviceapplication.dto.ItemPatchDto;
import com.hinto.microserviceapplication.dto.ItemPostDto;
import com.hinto.microserviceapplication.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/v1/data")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation("Read a item by Id")
    @GetMapping("/{id}")
    public ResponseEntity<ItemGetDto> getItemById(@PathVariable Long id) {
        ItemGetDto item = itemService.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Insert a new item record")
    @PostMapping()
    public ResponseEntity<ItemGetDto> insertItem(@RequestBody ItemPostDto itemPostDto) {
        ItemGetDto insertedItem = itemService.insertItem(itemPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedItem);
    }

    @ApiOperation("Update item by ID")
    @PatchMapping("/{id}")
    public ResponseEntity<ItemGetDto> updateItem(@PathVariable Long id, @RequestBody ItemPatchDto itemPatchDto) {
        ItemGetDto updatedItem = itemService.updateItem(id, itemPatchDto);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Delete item by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}

