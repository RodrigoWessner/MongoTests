package br.com.mongoDB.service;

import br.com.mongoDB.model.Item;
import br.com.mongoDB.pojo.dto.ItemDto;
import br.com.mongoDB.pojo.form.ItemForm;
import br.com.mongoDB.pojo.form.ItemUpdateForm;
import br.com.mongoDB.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDto> get() {
        List<Item> list = itemRepository.findAll();
        return list
                .stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());
    }

    public ItemDto get(String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.isPresent() ? modelMapper.map(item.get(), ItemDto.class) : null;
    }

    public ItemDto save(ItemForm itemForm) {
        Item item = itemRepository.save(modelMapper.map(itemForm, Item.class));
        return modelMapper.map(item, ItemDto.class);
    }

    @Transactional
    public ItemDto update(String id, ItemUpdateForm itemUpdateForm) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            modelMapper.map(itemUpdateForm, item.get());
            itemRepository.save(item.get());
            return modelMapper.map(item.get(), ItemDto.class);
        } else
            return null;
    }

    @Transactional
    public Boolean delete(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.delete(item.get());
            return true;
        } else {
            return false;
        }
    }
}
