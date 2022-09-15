package br.com.mongoDB.controller;

import br.com.mongoDB.pojo.dto.ItemDto;
import br.com.mongoDB.pojo.form.ItemForm;
import br.com.mongoDB.pojo.form.ItemUpdateForm;
import br.com.mongoDB.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDto>> get() {
        return new ResponseEntity(itemService.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> get(@PathVariable("id") String id) {
        return new ResponseEntity(itemService.get(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ItemDto> save(@RequestBody ItemForm itemForm) {
        return new ResponseEntity(itemService.save(itemForm), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable("id") String id, @RequestBody ItemUpdateForm itemUpdateForm) {
        return new ResponseEntity(itemService.update(id, itemUpdateForm), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        Boolean flag = itemService.delete(id);
        HttpStatus status = flag ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE;
        return new ResponseEntity(flag, status);
    }
}
