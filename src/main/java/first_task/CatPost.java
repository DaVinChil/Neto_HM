package first_task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatPost {
    private String id;
    private String text;
    private String type;
    private String user;
    private Integer upvotes;

    @Override
    public String toString(){
        return text;
    }
}
