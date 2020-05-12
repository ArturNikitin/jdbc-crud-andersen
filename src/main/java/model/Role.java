package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;
    private String name;

    public Role(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nName: " + name;
    }
}
