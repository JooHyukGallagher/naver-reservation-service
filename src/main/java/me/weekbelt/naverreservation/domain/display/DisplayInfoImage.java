package me.weekbelt.naverreservation.domain.display;

import lombok.Getter;
import lombok.Setter;
import me.weekbelt.naverreservation.domain.FileInfo;

import javax.persistence.*;

@Entity
@Getter @Setter
public class DisplayInfoImage {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "display_info_id")
    private DisplayInfo displayInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;
}
