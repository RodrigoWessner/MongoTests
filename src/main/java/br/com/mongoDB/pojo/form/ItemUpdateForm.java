package br.com.mongoDB.pojo.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemUpdateForm {
    public String name;
    public String category;
    public int quantity;
}
