import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Board {
    private String bno;
    private String title;
    private String userid;
    private String regdate;
    private String thumbs;
    private String views;
    private String contents;
    private String ipaddr;
}
